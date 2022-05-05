package generator;

import com.google.common.base.Objects;
import forsyde.io.java.core.Trait;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexAcessor;
import forsyde.io.java.core.VertexTrait;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;
import org.eclipse.xtext.xbase.lib.InputOutput;

@SuppressWarnings("all")
public class Schedule {
  public Vertex tile;
  
  public Vertex order = null;
  
  public List<Vertex> slots = new ArrayList<Vertex>();
  
  public Set<Vertex> channels = new HashSet<Vertex>();
  
  public Schedule() {
  }
  
  public Schedule(final Vertex tile) {
    this.tile = tile;
    if ((this.order == null)) {
      this.order = this.help1(VertexTrait.PLATFORM_RUNTIME_FIXEDPRIORITYSCHEDULER);
    }
    if ((this.order == null)) {
      this.order = this.help1(VertexTrait.PLATFORM_RUNTIME_ROUNDROBINSCHEDULER);
    }
    if ((this.order == null)) {
      this.order = this.help1(VertexTrait.PLATFORM_RUNTIME_STATICCYCLICSCHEDULER);
    }
    if ((this.order == null)) {
      this.order = this.help1(VertexTrait.PLATFORM_RUNTIME_STATICCYCLICSCHEDULER);
    }
    if ((this.order == null)) {
      this.order = this.help1(VertexTrait.PLATFORM_RUNTIME_TIMETRIGGEREDSCHEDULER);
    }
    if ((this.order != null)) {
      Set<String> _ports = this.order.getPorts();
      TreeSet<String> ports = new TreeSet<String>(_ports);
      ports.remove("contained");
      for (final String portname : ports) {
        {
          Vertex actor = VertexAcessor.getNamedPort(
            Generator.model, 
            this.order, portname, 
            VertexTrait.MOC_SDF_SDFCOMB).orElse(null);
          this.slots.add(actor);
        }
      }
    }
    for (final Vertex actor : this.slots) {
      if ((actor != null)) {
        final Consumer<String> _function = (String p) -> {
          if (((!Objects.equal(p, "Combinator")) && (!Objects.equal(p, "CombFunction")))) {
            this.channels.add(
              VertexAcessor.getNamedPort(Generator.model, actor, p, VertexTrait.MOC_SDF_SDFCHANNEL).orElse(null));
            this.channels.add(
              VertexAcessor.getNamedPort(Generator.model, actor, p, VertexTrait.IMPL_TOKENIZABLEDATABLOCK).orElse(null));
          }
        };
        actor.getPorts().stream().forEach(_function);
      }
    }
    boolean _contains = this.channels.contains(null);
    if (_contains) {
      this.channels.remove(null);
    }
  }
  
  private Vertex help1(final Trait a) {
    return VertexAcessor.getNamedPort(
      Generator.model, 
      this.tile, 
      "execution", a).orElse(null);
  }
  
  public void print() {
    String _identifier = this.tile.getIdentifier();
    String _plus = ("tile: " + _identifier);
    InputOutput.<String>println(_plus);
    if ((this.order == null)) {
      InputOutput.<String>println("order null");
    } else {
      String _identifier_1 = this.order.getIdentifier();
      String _plus_1 = ("order : " + _identifier_1);
      InputOutput.<String>println(_plus_1);
    }
    for (final Vertex v : this.slots) {
      if ((v == null)) {
        InputOutput.<String>println("null");
      } else {
        InputOutput.<String>println(v.getIdentifier());
      }
    }
  }
}
