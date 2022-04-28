package generator

import java.util.Set
import forsyde.io.java.core.VertexTrait
import java.util.HashSet
import java.util.stream.Collectors
import forsyde.io.java.core.Vertex

class InitModule  implements ModuleInterface{
	Set<VertexTrait> primitiveDataTypeSet
	new (){
		primitiveDataTypeSet=new HashSet
		init()
	}
	override create() {
		process()
	}
	def void process(){
		var model=Generator.model
		var dataTypeSet =model.vertexSet.stream().filter([v|isDataTypeVertex(v)]).collect(Collectors.toSet())
//		var integerDataTypeSet
	}
	
	def init(){
		primitiveDataTypeSet.add(VertexTrait.TYPING_DATATYPES_INTEGER)
		primitiveDataTypeSet.add(VertexTrait.TYPING_DATATYPES_FLOAT)
		primitiveDataTypeSet.add(VertexTrait.TYPING_DATATYPES_DOUBLE)
		primitiveDataTypeSet.add(VertexTrait.TYPING_DATATYPES_ARRAY)
	}
	def add(VertexTrait trait){
		primitiveDataTypeSet.add(trait)
	}
	def isDataTypeVertex(Vertex v){
		for(VertexTrait trait:primitiveDataTypeSet){
			if(v.hasTrait(trait)){
				return true
			}
		}
		return false
	}
}