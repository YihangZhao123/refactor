package generator

import forsyde.io.java.typed.viewers.moc.sdf.SDFComb
import forsyde.io.java.core.Vertex
import java.util.Set
import template.templateInterface.ActorTemplate
import java.util.HashSet
import utils.Save
import utils.Name

class SDFCombProcessingModule  implements ModuleInterface{
	Set<ActorTemplate> templates
	static String inc=Generator.root+"/inc/";
	static String src=Generator.root+"/inc/";
	new (){
		templates= new HashSet
	}
	
	override create() {

		Generator.model.vertexSet().stream().filter([v|SDFComb::conforms(v)]).forEach([v|process(v)])
	}
	
	def void process(Vertex v){
		templates.stream().forEach( [t| 
			 Save.save(Generator.root+"/src/sdfcomb_"+Name.name(v)+".c",t.create(v))
		] )
	}
	
	def void add(ActorTemplate t){
		templates.add(t)
	}
	
}