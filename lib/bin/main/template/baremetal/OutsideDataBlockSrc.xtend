package template.baremetal

import template.templateInterface.InitTemplate
import generator.Generator
import forsyde.io.java.core.Vertex
import forsyde.io.java.core.VertexTrait
import java.util.stream.Collectors
import forsyde.io.java.core.EdgeTrait

class OutsideDataBlockSrc  implements InitTemplate{
	
	override create() {
		
		var model=Generator.model
		var set=model.vertexSet().stream().filter([v|
			v.getTraits().size()==2&&v.hasTrait(VertexTrait.IMPL_TOKENIZABLEDATABLOCK)&&v.hasTrait(VertexTrait.TYPING_TYPEDDATABLOCK)
		]).collect(Collectors.toSet())
//		for(Vertex v: set){
//			var type=model.edgeSet().stream().filter(e|e.hasTrait(EdgeTrait.TYPING_DATATYPES_DATADEFINITION))
//			.filter([e|e.getSource()==v.getIdentifier()&&e.getSourcePort().get()=="dataType"])	
//			.findAny().get().getTarget()
//			
//			extern type b.getIdentifier()	
//		}
		'''
		#include "../inc/datatype_definition.h"
		«FOR v:set SEPARATOR""AFTER"" »
		extern «help(v)»  «v.getIdentifier()»;
		«ENDFOR»
		'''
	}
	
	def String help(Vertex v ){
		var model=Generator.model
		var type=model.edgeSet().stream().filter(e|e.hasTrait(EdgeTrait.TYPING_DATATYPES_DATADEFINITION))
		.filter([e|e.getSource()==v.getIdentifier()&&e.getSourcePort().get()=="dataType"])	
		.findAny().get().getTarget()		
		return type
	}
	
	
	override getFileName() {
		return "outside_datablock"
	}
	
}