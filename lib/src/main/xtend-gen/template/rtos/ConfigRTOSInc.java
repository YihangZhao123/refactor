package template.rtos;

import fileAnnotation.FileType;
import fileAnnotation.FileTypeAnno;
import template.templateInterface.InitTemplate;

@FileTypeAnno(type = FileType.C_INCLUDE)
@SuppressWarnings("all")
public class ConfigRTOSInc implements InitTemplate {
  public String create() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe field Generator.sdfcombSet refers to the missing type Vertex"
      + "\ngetIdentifier cannot be resolved"
      + "\ntoUpperCase cannot be resolved");
  }
  
  public String getFileName() {
    return "config";
  }
}
