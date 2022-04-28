package generator;

import forsyde.io.java.core.Vertex;
import forsyde.io.java.typed.viewers.moc.sdf.SDFComb;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;
import template.templateInterface.ActorTemplate;
import utils.Name;
import utils.Save;

@SuppressWarnings("all")
public class SDFCombProcessingModule implements ModuleInterface {
  private Set<ActorTemplate> templates;
  
  private static String inc = (Generator.root + "/inc/");
  
  private static String src = (Generator.root + "/inc/");
  
  public SDFCombProcessingModule() {
    HashSet<ActorTemplate> _hashSet = new HashSet<ActorTemplate>();
    this.templates = _hashSet;
  }
  
  public void create() {
    final Predicate<Vertex> _function = new Predicate<Vertex>() {
      public boolean test(final Vertex v) {
        return (SDFComb.conforms(v)).booleanValue();
      }
    };
    final Consumer<Vertex> _function_1 = new Consumer<Vertex>() {
      public void accept(final Vertex v) {
        SDFCombProcessingModule.this.process(v);
      }
    };
    Generator.model.vertexSet().stream().filter(_function).forEach(_function_1);
  }
  
  public void process(final Vertex v) {
    final Consumer<ActorTemplate> _function = new Consumer<ActorTemplate>() {
      public void accept(final ActorTemplate t) {
        String _name = Name.name(v);
        String _plus = ((Generator.root + "/src/sdfcomb_") + _name);
        String _plus_1 = (_plus + ".c");
        Save.save(_plus_1, t.create(v));
      }
    };
    this.templates.stream().forEach(_function);
  }
  
  public void add(final ActorTemplate t) {
    this.templates.add(t);
  }
}
