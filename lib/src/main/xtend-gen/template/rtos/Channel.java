package template.rtos;

import fileAnnotation.FileType;
import fileAnnotation.FileTypeAnno;
import template.templateInterface.InitTemplate;

@FileTypeAnno(type = FileType.C_SOURCE)
@SuppressWarnings("all")
public class Channel implements InitTemplate {
  public String create() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method getBufferSize(Vertex) is undefined for the type Class<Query>"
      + "\nThe method findSDFChannelDataType(ForSyDeSystemGraph, Object) is undefined for the type Class<Query>"
      + "\nThe field Generator.model refers to the missing type ForSyDeSystemGraph"
      + "\nThe field Generator.sdfchannelSet refers to the missing type Vertex"
      + "\ngetIdentifier cannot be resolved"
      + "\nqueryVertex cannot be resolved"
      + "\nget cannot be resolved");
  }
  
  public String getFileName() {
    return "sdfchannel";
  }
}
