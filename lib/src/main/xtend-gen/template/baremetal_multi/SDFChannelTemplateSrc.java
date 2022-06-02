package template.baremetal_multi;

import fileAnnotation.FileType;
import fileAnnotation.FileTypeAnno;
import template.templateInterface.ChannelTemplate;

/**
 * without distinguish if the sdfchannel is a state variable
 */
@FileTypeAnno(type = FileType.C_SOURCE)
@SuppressWarnings("all")
public class SDFChannelTemplateSrc implements ChannelTemplate {
  public String create(final /* Vertex */Object sdfchannel) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method findSDFChannelDataType(ForSyDeSystemGraph, Vertex) is undefined for the type Class<Query>"
      + "\nThe method or field BoundedSDFChannel is undefined"
      + "\nBoundedSDFChannelViewer cannot be resolved."
      + "\nThe method isOnOneCoreChannel(ForSyDeSystemGraph, Vertex) is undefined for the type Class<Query>"
      + "\nThe method getBufferSize(Vertex) is undefined for the type Class<Query>"
      + "\nThe method getBufferSize(Vertex) is undefined for the type Class<Query>"
      + "\nThe method getTokenSize(Vertex) is undefined for the type Class<Query>"
      + "\nThe method isOnOneCoreChannel(ForSyDeSystemGraph, Vertex) is undefined for the type Class<Query>"
      + "\nThe method getTokenSize(Vertex) is undefined for the type Class<Query>"
      + "\nThe field Generator.model refers to the missing type ForSyDeSystemGraph"
      + "\nThe field Generator.model refers to the missing type ForSyDeSystemGraph"
      + "\ngetProperties cannot be resolved"
      + "\ngetIdentifier cannot be resolved"
      + "\nconforms cannot be resolved"
      + "\ngetMaximumTokens cannot be resolved"
      + "\n+ cannot be resolved"
      + "\n+ cannot be resolved");
  }
}
