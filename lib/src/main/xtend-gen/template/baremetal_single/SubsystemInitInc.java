package template.baremetal_single;

import fileAnnotation.FileType;
import fileAnnotation.FileTypeAnno;
import template.templateInterface.InitTemplate;

@FileTypeAnno(type = FileType.C_INCLUDE)
@Deprecated
@SuppressWarnings("all")
public class SubsystemInitInc implements InitTemplate {
  public String create() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field IntegerValue is undefined"
      + "\nThe method or field IntegerValue is undefined"
      + "\nThe field Generator.model refers to the missing type ForSyDeSystemGraph"
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\nvertexSet cannot be resolved"
      + "\nstream cannot be resolved"
      + "\nfilter cannot be resolved"
      + "\nconforms cannot be resolved"
      + "\nmap cannot be resolved"
      + "\nsafeCast cannot be resolved"
      + "\nget cannot be resolved"
      + "\ncollect cannot be resolved");
  }
  
  public String externChannel() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method findSDFChannelDataType(ForSyDeSystemGraph, Vertex) is undefined for the type Class<Query>"
      + "\nThe field Generator.sdfchannelSet refers to the missing type Vertex"
      + "\nThe field Generator.model refers to the missing type ForSyDeSystemGraph"
      + "\ngetIdentifier cannot be resolved");
  }
  
  public String getFileName() {
    return "subsystem_init";
  }
}
