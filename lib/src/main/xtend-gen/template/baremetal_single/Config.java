package template.baremetal_single;

import fileAnnotation.FileType;
import fileAnnotation.FileTypeAnno;
import template.templateInterface.InitTemplate;

@FileTypeAnno(type = FileType.C_INCLUDE)
@SuppressWarnings("all")
public class Config implements InitTemplate {
  public String create() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field SDFChannel is undefined"
      + "\nThe field Generator.model refers to the missing type ForSyDeSystemGraph"
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\nvertexSet cannot be resolved"
      + "\nstream cannot be resolved"
      + "\nfilter cannot be resolved"
      + "\nconforms cannot be resolved"
      + "\ncollect cannot be resolved"
      + "\ngetIdentifier cannot be resolved"
      + "\ntoUpperCase cannot be resolved");
  }
  
  public String getFileName() {
    return "config";
  }
}
