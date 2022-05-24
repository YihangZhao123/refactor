package template.baremetal_single

import template.templateInterface.SubsystemTemplate

import java.util.stream.Collectors
import generator.Generator
import forsyde.io.java.typed.viewers.moc.sdf.SDFChannel
import fileAnnotation.FileType
import fileAnnotation.FileTypeAnno
import generator.Schedule
import java.util.HashMap
import forsyde.io.java.typed.viewers.moc.sdf.SDFComb
import utils.Query
import forsyde.io.java.typed.viewers.values.IntegerValue

@FileTypeAnno(type=FileType.C_SOURCE)
class SubsystemTemplateSrc implements SubsystemTemplate {

	override String create(Schedule s) {
		var model = Generator.model
		var sdfcomb = model.vertexSet().stream().filter([v|SDFComb.conforms(v)]).collect(Collectors.toSet())

		var integerValues = model.vertexSet().stream()
		.filter([v|IntegerValue.conforms(v)])
		.map([v|IntegerValue.safeCast(v).get()])
		.collect(Collectors.toSet())
		'''
		#include "../inc/subsystem.h"
		#include <stdio.h>
		«FOR v : sdfcomb»
			#include "../inc/sdfcomb_«v.getIdentifier()».h"
		«ENDFOR»
		#include "../inc/datatype_definition.h"
		#include "../inc/circular_fifo_lib.h"
		/*
		==============================================
			Subsystem Function
		==============================================
		*/	
		int subsystem(){
				«FOR set : Generator.uniprocessorSchedule.entrySet() SEPARATOR "" AFTER ""»
					//printf("%s\n","enter «set.getValue().getIdentifier()»");
						«IF Generator.TESTING==1&&Generator.PC==1»
							actor_«set.getValue().getIdentifier()»();
						«ENDIF»
				«ENDFOR»	
		}
		
		
			/*
			*********************************************************
			Initialize All the Channels
			Should be called before subsystem_single_uniprocessor()
			*********************************************************
			*/
		int init_subsystem(){
			/* Extern Variables */
			«FOR value:integerValues »
				extern int «value.getIdentifier()»;
			«ENDFOR»	
			
			«externChannel()»		
			
			/* initialize the channels*/
				«FOR channel : Generator.sdfchannelSet»
					«var sdfname=channel.getIdentifier()»
«««					init_channel_«Query.findSDFChannelDataType(Generator.model,channel)»(&fifo_«sdfname»,buffer_«sdfname»,buffer_«sdfname»_size);
					ref_init(&fifo_«sdfname»,buffer_«sdfname»,buffer_«sdfname»_size);
				«ENDFOR»		
				
				«FOR channel : Generator.sdfchannelSet»
					«var sdfchannel=SDFChannel.safeCast(channel).get()»
					«IF sdfchannel.getNumOfInitialTokens()!==null&&sdfchannel.getNumOfInitialTokens()>0»
						«var b = (sdfchannel.getProperties().get("__initialTokenValues_ordering__").unwrap() as HashMap<String,Integer>) »
						«FOR k:b.keySet()»
							write_non_blocking(&fifo_«sdfchannel.getIdentifier()»,(void*)&«k»);
«««							write_non_blocking_«Query.findSDFChannelDataType(Generator.model,channel)»(&fifo_«sdfchannel.getIdentifier()»,«k»);
						«ENDFOR»
					«ENDIF»
				«ENDFOR»
				return 0;
			}		
		
		
		'''
	}

	def String externChannel(){

		'''
			«FOR channel: Generator.sdfchannelSet»
				«var sdfname=channel.getIdentifier()»
				«var type = Query.findSDFChannelDataType(Generator.model,channel)»
				/* extern sdfchannel «sdfname»*/
«««				extern «type» buffer_«sdfname»[];
«««				extern int buffer_«sdfname»_size;
«««				extern circular_fifo_«type» fifo_«sdfname»;
				extern void* buffer_«sdfname»[];
				extern size_t buffer_«sdfname»_size;
				extern ref_fifo fifo_«sdfname»;
			«ENDFOR»
		'''
	}	
	override getFileName() {
		return "subsystem"
	}

}
