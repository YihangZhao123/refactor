package template.baremetal

import template.templateInterface.InitTemplate
import generator.Generator
import forsyde.io.java.core.VertexTrait
import java.util.stream.Collectors
import fileAnnotation.FileTypeAnno
import fileAnnotation.FileType
import java.util.Set
import java.util.HashSet
import forsyde.io.java.core.VertexAcessor
import forsyde.io.java.core.Vertex
import forsyde.io.java.core.EdgeTrait
import forsyde.io.java.typed.viewers.moc.sdf.SDFChannel
import utils.Query

@FileTypeAnno(type=FileType.C_INCLUDE)
class CircularFIFOTemplateInc implements InitTemplate {
	private Set<VertexTrait> primitiveTraitSet
	Set<Vertex> a
	new() {
		primitiveTraitSet = new HashSet
		primitiveTraitSet.add(VertexTrait.TYPING_DATATYPES_FLOAT)
		primitiveTraitSet.add(VertexTrait.TYPING_DATATYPES_DOUBLE)
		primitiveTraitSet.add(VertexTrait.TYPING_DATATYPES_INTEGER)
		primitiveTraitSet.add(VertexTrait.TYPING_DATATYPES_ARRAY)
		
		
//		val model = Generator.model
//		a=model.vertexSet().stream()
//			.filter([v|SDFChannel.conforms(v)])
//			.map([v|Query.findSDFChannelDataType(model,v)])
//			.map([s|Query.findVertexByName(model,s)])
//			.collect(Collectors.toSet())
//		a.stream().forEach([a|println("--->"+a.getIdentifier())])
	}

	override create() {
		'''
			#ifndef CIRCULAR_FIFO_LIB_H_
			#define CIRCULAR_FIFO_LIB_H_
			#include "datatype_definition.h"
			#include "spinlock.h"
			
			«FOR primitiveTrait : primitiveTraitSet SEPARATOR "" AFTER ""»
				«primitiveChannelPrototype(primitiveTrait)»	
			«ENDFOR»
«««			«FOR aa : a SEPARATOR "" AFTER ""»
«««			«foo(aa)»
«««			«ENDFOR»
			#endif
		'''
	}

	override getFileName() {
		return "circular_fifo_lib"
	}
	def String foo(Vertex a){
		'''
		«val type=a.getIdentifier()»
		«IF !a.hasTrait(VertexTrait.TYPING_DATATYPES_ARRAY)»
			/*
			=============================================================
							«type» Prototype
			=============================================================
			*/
			typedef struct 
			{
			    «type»* buffer;
			    size_t front;
			    size_t rear;
			size_t size;	    
			}circular_fifo_«type»;
			
			void init_channel_«type»(circular_fifo_«type» *channel ,«type»* buffer, size_t size);
			int read_non_blocking_«type»(circular_fifo_«type»* channel,«type»* dst );
			int read_blocking_«type»(circular_fifo_«type»* ptr,«type»* dst,spinlock *lock);
			int write_non_blocking_«type»(circular_fifo_«type»* ptr,«type»* src );
			int write_blocking_«type»(circular_fifo_«type»* ptr,«type»* src,spinlock *lock);	
						
		«ELSE»
			«var maximumElems =help(a)»
				«IF maximumElems>0»
			«var innerType = Generator.model.outgoingEdgesOf(a).stream().filter([e|e.hasTrait(EdgeTrait.TYPING_DATATYPES_DATADEFINITION)]).filter([e|e.getSource()==type && e.getSourcePort().get()=="innerType"]).findAny().get().getTarget()»
			/*
			=============================================================
							«type» Prototype
			=============================================================
			*/
			typedef struct 
			{
			    «type»* buffer;
			    size_t front;
			    size_t rear;
				size_t size;	    
			}circular_fifo_«type»;
			
			void init_channel_«type»(circular_fifo_«type» *channel ,«type»* buffer, size_t size);
			int read_non_blocking_«type»(circular_fifo_«type»* channel,«type»* dst );
			int read_blocking_«type»(circular_fifo_«type»* ptr,«type»* dst,spinlock *lock);
			int write_non_blocking_«type»(circular_fifo_«type»* ptr,«type»* src );
			int write_blocking_«type»(circular_fifo_«type»* ptr,«type»* src,spinlock *lock);				
				«ENDIF»				
		«ENDIF»
		'''
	}
	def String primitiveChannelPrototype(VertexTrait trait) {
		'''
			«IF trait!==VertexTrait.TYPING_DATATYPES_ARRAY»
				«var typeSet=Generator.model.vertexSet().stream().filter([v|v.hasTrait(trait)]).collect(Collectors.toSet())»
				«FOR typeVertex : typeSet SEPARATOR "" AFTER ""»
					«var type=typeVertex.getIdentifier()»
					/*
					=============================================================
									«type» Prototype
					=============================================================
					*/
					typedef struct 
					{
					    «type»* buffer;
					    size_t front;
					    size_t rear;
					size_t size;	    
					}circular_fifo_«type»;
					
					void init_channel_«type»(circular_fifo_«type» *channel ,«type»* buffer, size_t size);
					int read_non_blocking_«type»(circular_fifo_«type»* channel,«type»* dst );
					int read_blocking_«type»(circular_fifo_«type»* ptr,«type»* dst,spinlock *lock);
					int write_non_blocking_«type»(circular_fifo_«type»* ptr,«type»* src );
					int write_blocking_«type»(circular_fifo_«type»* ptr,«type»* src,spinlock *lock);	
						
				«ENDFOR»
			«ELSE»
				«var typeSet=Generator.model.vertexSet().stream().filter([v|v.hasTrait(trait)]).collect(Collectors.toSet())»
				«FOR typeVertex : typeSet SEPARATOR "" AFTER ""»
					«val type=typeVertex.getIdentifier()»
					«var maximumElems =help(typeVertex)»
						«IF maximumElems>0»
							«var innerType = Generator.model.outgoingEdgesOf(typeVertex).stream().filter([e|e.hasTrait(EdgeTrait.TYPING_DATATYPES_DATADEFINITION)]).filter([e|e.getSource()==type && e.getSourcePort().get()=="innerType"]).findAny().get().getTarget()»
							/*
							=============================================================
											«type» Prototype
							=============================================================
							*/
							typedef struct 
							{
							    «type»* buffer;
							    size_t front;
							    size_t rear;
								size_t size;	    
							}circular_fifo_«type»;
							
							void init_channel_«type»(circular_fifo_«type» *channel ,«type»* buffer, size_t size);
							int read_non_blocking_«type»(circular_fifo_«type»* channel,«type»* dst );
							int read_blocking_«type»(circular_fifo_«type»* ptr,«type»* dst,spinlock *lock);
							int write_non_blocking_«type»(circular_fifo_«type»* ptr,«type»* src );
							int write_blocking_«type»(circular_fifo_«type»* ptr,«type»* src,spinlock *lock);				
						«ENDIF»			
				«ENDFOR»
			«ENDIF»
		'''
	}

	def void addPrimitiveVertexTrait(VertexTrait primitiveTrait) {
		primitiveTraitSet.add(primitiveTrait)
	}

	private def help(Vertex typeVertex) {
		var maximumElems = 0
		if (typeVertex.getProperties().get("maximumElems") !== null) {
			maximumElems = (typeVertex.getProperties().get("maximumElems").unwrap() as Integer)
		} else {
			maximumElems = (typeVertex.getProperties().get("production").unwrap() as Integer)
		}
		return maximumElems
	}

}
