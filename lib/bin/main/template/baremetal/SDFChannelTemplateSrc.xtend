package template.baremetal

import fileAnnotation.FileType
import fileAnnotation.FileTypeAnno
import forsyde.io.java.core.Vertex
import forsyde.io.java.core.VertexAcessor
import forsyde.io.java.core.VertexAcessor.VertexPortDirection
import forsyde.io.java.core.VertexTrait
import generator.Generator
import java.util.ArrayList
import java.util.HashMap
import java.util.HashSet
import java.util.Set
import template.templateInterface.ActorTemplate
import utils.Name
import utils.Query
import java.util.stream.Collectors
import forsyde.io.java.core.EdgeInfo
import forsyde.io.java.core.EdgeTrait
import template.templateInterface.ChannelTemplate
import forsyde.io.java.typed.viewers.decision.sdf.BoundedSDFChannel
import forsyde.io.java.typed.viewers.decision.sdf.BoundedSDFChannelViewer
import forsyde.io.java.typed.viewers.moc.sdf.SDFChannel

/**
 * without distinguish if the sdfchannel is a state variable
 * 
 */
@FileTypeAnno(type=FileType.C_SOURCE)
class SDFChannelTemplateSrc implements ChannelTemplate {

	override create(Vertex sdfchannel) {
		var type = Query.findSDFChannelDataType(Generator.model, sdfchannel)
		var properties = sdfchannel.getProperties()
		'''	
			«var sdfname=sdfchannel.getIdentifier()»
			#include "../inc/circular_fifo_lib.h"
			«IF BoundedSDFChannel.conforms(sdfchannel)»
				«var viewer = new BoundedSDFChannelViewer(sdfchannel)»
				«var maximumTokens =viewer.getMaximumTokens()»
				volatile «type» buffer_«sdfname»[«maximumTokens+1»];
				int channel_«sdfname»_size=«maximumTokens»;
				/*
					Because of circular fifo, the 
					buffer_size=channel_size+1
				*/
				int buffer_«sdfname»_size = «maximumTokens+1»;
				circular_fifo_«type» fifo_«sdfname»;
				spinlock spinlock_«sdfname»={.flag=0};
			«ELSE»
				volatile «type» buffer_«sdfname»[2];
				int channel_«sdfname»_size = 1;
				/*
					Because of circular fifo, the 
					buffer_size=channel_size+1
				*/
				int buffer_«sdfname»_size = 2;
				circular_fifo_«type» fifo_«sdfname»;
				spinlock spinlock_«sdfname»={.flag=0};	
			«ENDIF»
			
		'''
	}

}
