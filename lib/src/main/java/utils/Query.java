package utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import forsyde.io.java.core.EdgeInfo;
import forsyde.io.java.core.EdgeTrait;
import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexAcessor;
import forsyde.io.java.core.VertexProperty;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.viewers.moc.sdf.SDFChannel;
import forsyde.io.java.typed.viewers.moc.sdf.SDFComb;
import generator.Generator;

import java.lang.Math;

public class Query {
	public static Vertex findVertexByName(ForSyDeSystemGraph model, String name) {
		return model.vertexSet().stream().filter(v -> v.getIdentifier().equals(name)).findAny().orElse(null);
	}
	
	public static String findSDFChannelDataType(ForSyDeSystemGraph model,Vertex sdf) {
		
		EdgeInfo inputedge=model.edgeSet().stream()
				.filter(e->e.hasTrait(EdgeTrait.MOC_SDF_SDFDATAEDGE))
				.filter(e->e.getSource().equals(sdf.getIdentifier()))
				.findAny()
				.orElse(null);
		EdgeInfo outputedge=model.edgeSet().stream()
				.filter(e->e.hasTrait(EdgeTrait.MOC_SDF_SDFDATAEDGE))
				.filter(e->e.getSource().equals(sdf.getIdentifier()))
				.findAny()
				.orElse(null);
		
		if(inputedge!=null) {
			//input sdf channel
			String actorname=inputedge.getTarget();
			String port = inputedge.getTargetPort().get();
			Vertex actor=findVertexByName(model,actorname);
			var impls = Query.findCombFuntionVertex(model, actor);
			
			EdgeInfo info= model.outgoingEdgesOf(actor).stream()
			.filter(e->impls.contains(e.getTarget()))
			.filter(e->e.getTargetPort().isPresent())
			.findAny().get();
			
			String implName=info.getTarget();
			String implPort = info.getTargetPort().get();
			return findImplPortDataType(model, findVertexByName(model,implName),implPort);
			
		}else {
			//output sdf channel
			String actorname=outputedge.getSource();
			String port = outputedge.getSourcePort().get();
			Vertex actor=findVertexByName(model,actorname);
			var impls = Query.findCombFuntionVertex(model, actor);
			
			EdgeInfo info= model.incomingEdgesOf(actor).stream()
			.filter(e->impls.contains(e.getSource()))
			.filter(e->e.getSourcePort().isPresent())
			.findAny().get();
			
			String implName=info.getSource();
			String implPort = info.getSourcePort().get();
			return findImplPortDataType(model, findVertexByName(model,implName),implPort);			
		}
		
	}
	/**
	 * 
	 * @param sdf   is an input channel of actor
	 * @param actor
	 * @return the actor's port which connected to the this sdf channel
	 */
	public static String findActorPortConnectedToInputChannel(ForSyDeSystemGraph model, Vertex sdf, Vertex actor) {
		EdgeInfo edge = model.edgeSet().stream().filter(e -> e.getSource() == sdf.getIdentifier())
				.filter(e -> e.getTarget() == actor.getIdentifier()).findAny().get();

		return edge.getTargetPort().orElse("Error!Port Not Found!");
	}

	public static Vertex findSDFChannel(ForSyDeSystemGraph model, Vertex actor, String port) {
		return VertexAcessor.getNamedPort(model, actor, port, VertexTrait.MOC_SDF_SDFCHANNEL).orElse(null);
	}

	/**
	 * Assuming there is an edge from actor port actorPort to impl port implPort,
	 * the aim of this function is to find port actorPort
	 * 
	 * @param actor
	 * @param impl
	 * @param implPort
	 * @return
	 */
	public static String findActorPortConnectedToImplInputPort(ForSyDeSystemGraph model, Vertex actor, Vertex impl,
			String implPort) {
		var actorPort = model.edgeSet().stream().filter(e -> e.getSource().equals(actor.getIdentifier()))
				.filter(e -> e.getTarget().equals(impl.getIdentifier())).filter(e -> e.getTargetPort().isPresent())
				.filter(e -> e.getTargetPort().get().equals(implPort))
				.findAny()
				.get().getSourcePort().get();
		return actorPort;
	}

	/**
	 * Assuming there is an edge from impl port implPort to actor port actorPort,
	 * the aim of this function is to find port actorPort
	 * 
	 * @param actor
	 * @param impl
	 * @param implPort
	 * @return
	 */
	public static String findActorPortConnectedToImplOutputPort(ForSyDeSystemGraph model, Vertex actor, Vertex impl,
			String implPort) {
		var actorPort = model.edgeSet().stream().filter(e -> e.getTarget().equals(actor.getIdentifier()))
				.filter(e -> e.getSource().equals(impl.getIdentifier()))
				// .filter(e->e.getTargetPort().isPresent())
				.filter(e -> e.getSourcePort().get().equals(implPort)).findAny().get().getTargetPort().get();
		return actorPort;
	}

	/**
	 * find the datatype of implPort
	 * 
	 * @param impl
	 * @param implPort
	 * @return
	 */
	public static String findImplPortDataType(ForSyDeSystemGraph model, Vertex impl, String implPort) {
		Optional<EdgeInfo> op = model.edgeSet().stream()
				.filter(e -> e.hasTrait(EdgeTrait.TYPING_DATATYPES_DATADEFINITION))
				.filter(e -> e.getSource().equals(impl.getIdentifier()))
				.filter(e -> e.getSourcePort().get().equals(implPort)).findAny();
		if (op.isPresent()) {
			return op.get().getTarget();
		}

		return "Port Not Connected To Any Type";
	}

	/**
	 * find all the input port of impl
	 * 
	 * @param impl
	 * @return
	 */
	public static Set<String> findImplInputPortSet(Vertex impl) {
		Set<String> ret = new HashSet<>();
		var properties = impl.getProperties();
		if (properties.get("inputPorts") == null) {
			return ret;
		}
		ret.addAll((ArrayList<String>) properties.get("inputPorts").unwrap());
		return ret;
	}

	/**
	 * find all the output port of impl
	 * 
	 * @param impl
	 * @return
	 */
	public static Set<String> findImplOutputPortSet(Vertex impl) {
		Set<String> ret = new HashSet<>();
		var properties = impl.getProperties();
		if (properties.get("outputPorts") == null) {
			return ret;
		}
		ret.addAll((ArrayList<String>) properties.get("outputPorts").unwrap());
		return ret;
	}

	public static Set<String> findCombFuntionVertex(ForSyDeSystemGraph model, Vertex actor) {

		var a = model.edgeSet().stream().filter(e -> e.getSource().equals(actor.getIdentifier()))
				.filter(e -> e.getSourcePort().isPresent() && e.getSourcePort().get().equals("combFunctions"))
				.map(e -> e.getTarget())
//		.map(name->model.vertexSet().stream().filter(v->v.getIdentifier().equals(name)).findAny().orElse(null) )
				.collect(Collectors.toSet());

		return a;
	}

	/**
	 * get sdf channels from which the actor read token
	 * 
	 * @param actor
	 * @return
	 */
	public static Set<Vertex> findInputSDFChannels(ForSyDeSystemGraph model,Vertex actor) {
		var inputSDFChannelNameSet = model.incomingEdgesOf(actor).stream()
				.filter(edgeinfo -> edgeinfo.hasTrait(EdgeTrait.MOC_SDF_SDFDATAEDGE)).map(e -> e.getSource())
				.collect(Collectors.toSet());

		Set<Vertex> inputSDFChannelSet = new HashSet<>();
		for (String sdfname : inputSDFChannelNameSet) {
			Vertex sdfchannel = model.vertexSet().stream().filter(v -> v.getIdentifier().equals(sdfname))
					.findAny().get();
			inputSDFChannelSet.add(sdfchannel);
		}
		return inputSDFChannelSet;
	}

	/**
	 * get all the sdf channel to which this actor writes data
	 * 
	 * @param actor
	 * @return
	 */
	public static Set<Vertex> findOutputSDFChannels(ForSyDeSystemGraph model,Vertex actor) {
		var outputSDFChannelNameSet = model.outgoingEdgesOf(actor).stream()
				.filter(edgeinfo -> edgeinfo.hasTrait(EdgeTrait.MOC_SDF_SDFDATAEDGE)).map(e -> e.getTarget())
				.collect(Collectors.toSet());

		Set<Vertex> outputSDFChannelSet = new HashSet<>();
		for (String sdfname : outputSDFChannelNameSet) {
			Vertex sdfchannel = model.vertexSet().stream().filter(v -> v.getIdentifier().equals(sdfname))
					.findAny().get();
			outputSDFChannelSet.add(sdfchannel);
		}
		return outputSDFChannelSet;
	}
	
	public static String findInputSDFChannelConnectedToActorPort(ForSyDeSystemGraph model, Vertex actor, String actorPort) {
		var edge = model.incomingEdgesOf(actor).stream()
				.filter(edgeinfo -> edgeinfo.hasTrait(EdgeTrait.MOC_SDF_SDFDATAEDGE))
				.filter(e->e.getTargetPort().get().equals(actorPort))
				.findAny()
				.orElse(null);
		if(edge!=null) {
			return edge.getSource();
		}
		return actor.getIdentifier()+" port "+actorPort+" Not read from any sdf channel";	
			
	}
	
	public static String findOutputSDFChannelConnectedToActorPort(ForSyDeSystemGraph model, Vertex actor, String actorPort) {
		var edge = model.outgoingEdgesOf(actor).stream()
				.filter(edgeinfo -> edgeinfo.hasTrait(EdgeTrait.MOC_SDF_SDFDATAEDGE))
				.filter(e->e.getSourcePort().get().equals(actorPort))
				.findAny()
				.orElse(null);
		if(edge!=null) {
			return edge.getTarget();
		}
		return actor.getIdentifier()+" port "+actorPort+" Not write to any sdf channel";	
			
	}
	/**
	 * 
	 * @param v is a combFunction actor
	 * @return
	 */
	public static String getInlineCode(Vertex impl) {

		Map<String, VertexProperty> properties = impl.getProperties();
		var tmp = properties.get("inlinedCode").unwrap();
		String inlineCode = (String) tmp;

		var b = new StringBuilder(inlineCode);
		var start = 0;
		var index = 0;

		while ((index = b.indexOf(";", start)) != -1) {
			start = index + 1;
			b.insert(index + 1, "\n");
		}
		return b.toString();
	}

	/**
	 * return the number of tokens in the channel.
	 * 
	 * @param ch is a channel
	 * @return the number of tokens in the channel ch
	 */
	public static int getBufferSize(Vertex ch) {

		Map<String, VertexProperty> a = ch.getProperties();
		if (a.get("maximumTokens") != null) {
			Integer ret = (Integer) a.get("maximumTokens").unwrap();
			return ret;
		} else {
//			 long b = (Long)a.get("tokenSizeInBits").unwrap();
//			 long c = (Long)a.get("maxSizeInBits").unwrap();
//			 return (int) (c/b);
			return 1;
		}

	}

	public static long getTokenSize(SDFChannel ch) {
		Map<String, VertexProperty> a = ch.getProperties();
		long b = (Long) a.get("tokenSizeInBits").unwrap();

		return (long) Math.ceil(((float) b) / 8);

	}

	public static long getTokenSize(Vertex ch) {
		Map<String, VertexProperty> a = ch.getProperties();
		if (a.get("tokenSizeInBits") != null) {
//			System.out.println(ch.getIdentifier());
			var tmp = a.get("tokenSizeInBits").unwrap();
			if (tmp instanceof Integer) {
				int b = (Integer) a.get("tokenSizeInBits").unwrap();
				return (long) Math.ceil(((float) b) / 8);
			}
			if (tmp instanceof Long) {
				long b = (Long) a.get("tokenSizeInBits").unwrap();
				return (long) Math.ceil(((float) b) / 8);
			}

			return -1;

		} else {
			return 1;
		}

	}

	public static long getTokenSizeInBits(Vertex vertex) {

		Map<String, VertexProperty> a = vertex.getProperties();
		long b = (Long) a.get("tokenSizeInBits").unwrap();
		return b;

	}

	/**
	 * 
	 * @param vertex vertex must be a has trait SDFComb
	 * @return return wcet, if there is no wcet in model, return -1
	 */
	public static int getWCET(Vertex vertex, ForSyDeSystemGraph model) {
		Optional<Vertex> a;
		Set<Vertex> wcet = new HashSet<>();
		for (Vertex v : model.vertexSet()) {
			if (v.hasTrait("WCET")) {
				wcet.add(v);

			}
		}
		for (Vertex v : wcet) {
			a = VertexAcessor.getNamedPort(model, v, "application", VertexTrait.MOC_SDF_SDFCOMB);
			if (a.isPresent() && a.get() == vertex) {
				Map<String, VertexProperty> b = v.getProperties();
				int c = (int) b.get("time").unwrap();
				return c;
			}
		}
		return -1;
	}
	
	public  static  String getInnerType(ForSyDeSystemGraph model,Vertex arrayType) {
		var innerType = model.outgoingEdgesOf(arrayType).stream().filter( e ->
			e.hasTrait(EdgeTrait.TYPING_DATATYPES_DATADEFINITION)
		).filter(e->e.getSource().equals(arrayType.getIdentifier())  && e.getSourcePort().get().equals("innerType")).findAny().
			get().getTarget();
		return innerType;
	}

	public  static int  getMaximumElems(Vertex typeVertex) {
		var maximumElems = 0;
		if (typeVertex.getProperties().get("maximumElems") != null) {
			maximumElems = ((Integer)typeVertex.getProperties().get("maximumElems").unwrap() );
		} else {
			maximumElems = ((Integer)typeVertex.getProperties().get("production").unwrap() );
		}
		return maximumElems;
	}
}
