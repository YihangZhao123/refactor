package template.baremetal_single;

import fileAnnotation.FileType;
import fileAnnotation.FileTypeAnno;
import java.util.Set;
import template.templateInterface.ActorTemplate;

@FileTypeAnno(type = FileType.C_INCLUDE)
@SuppressWarnings("all")
public class SDFActorInc implements ActorTemplate {
  private /* Set<Executable> */Object a;
  
  public String create(final /* Vertex */Object actor) {
    throw new Error("Unresolved compilation problems:"
      + "\nSDFCombViewer cannot be resolved."
      + "\nThe field SDFActorInc.a refers to the missing type Executable"
      + "\nThe field Generator.model refers to the missing type ForSyDeSystemGraph"
      + "\ngetCombFunctionsPort cannot be resolved"
      + "\ngetIdentifier cannot be resolved"
      + "\ntoUpperCase cannot be resolved"
      + "\n+ cannot be resolved");
  }
}
