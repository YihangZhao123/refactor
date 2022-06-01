package generator;

import java.util.HashSet;
import java.util.Set;
import template.templateInterface.ActorTemplate;

@SuppressWarnings("all")
public class SDFCombProcessingModule implements ModuleInterface {
  private Set<ActorTemplate> templates;
  
  public SDFCombProcessingModule() {
    HashSet<ActorTemplate> _hashSet = new HashSet<ActorTemplate>();
    this.templates = _hashSet;
  }
  
  public void create() {
    throw new Error("Unresolved compilation problems:"
      + "\nSDFComb cannot be resolved to a type."
      + "\nThe field Generator.model refers to the missing type ForSyDeSystemGraph"
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\nThe method process(Vertex) from the type SDFCombProcessingModule refers to the missing type Vertex"
      + "\nvertexSet cannot be resolved"
      + "\nstream cannot be resolved"
      + "\nfilter cannot be resolved"
      + "\nconforms cannot be resolved"
      + "\nforEach cannot be resolved");
  }
  
  public void process(final /* Vertex */Object v) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method name(Vertex) from the type Name refers to the missing type Vertex"
      + "\nThe method create(Vertex) from the type ActorTemplate refers to the missing type Vertex"
      + "\nThe method name(Vertex) from the type Name refers to the missing type Vertex"
      + "\nThe method create(Vertex) from the type ActorTemplate refers to the missing type Vertex");
  }
  
  public void add(final ActorTemplate t) {
    this.templates.add(t);
  }
}
