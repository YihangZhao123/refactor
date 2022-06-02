package template.baremetal_multi;

import fileAnnotation.FileType;
import fileAnnotation.FileTypeAnno;
import generator.Schedule;
import template.templateInterface.SubsystemTemplate;

@FileTypeAnno(type = FileType.C_INCLUDE)
@SuppressWarnings("all")
public class SubsystemTemplateIncMulti implements SubsystemTemplate {
  private Schedule s;
  
  public String create(final Schedule s) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe field Schedule.tile refers to the missing type Vertex"
      + "\ngetIdentifier cannot be resolved"
      + "\ngetIdentifier cannot be resolved");
  }
  
  public String getFileName() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe field Schedule.tile refers to the missing type Vertex"
      + "\ngetIdentifier cannot be resolved");
  }
}
