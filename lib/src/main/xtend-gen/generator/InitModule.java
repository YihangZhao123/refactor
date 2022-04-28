package generator;

import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@SuppressWarnings("all")
public class InitModule implements ModuleInterface {
  private Set<VertexTrait> primitiveDataTypeSet;
  
  public InitModule() {
    HashSet<VertexTrait> _hashSet = new HashSet<VertexTrait>();
    this.primitiveDataTypeSet = _hashSet;
    this.init();
  }
  
  public void create() {
    this.process();
  }
  
  public void process() {
    ForSyDeSystemGraph model = Generator.model;
    final Predicate<Vertex> _function = new Predicate<Vertex>() {
      public boolean test(final Vertex v) {
        return InitModule.this.isDataTypeVertex(v);
      }
    };
    Set<Vertex> dataTypeSet = model.vertexSet().stream().filter(_function).collect(Collectors.<Vertex>toSet());
  }
  
  public boolean init() {
    boolean _xblockexpression = false;
    {
      this.primitiveDataTypeSet.add(VertexTrait.TYPING_DATATYPES_INTEGER);
      this.primitiveDataTypeSet.add(VertexTrait.TYPING_DATATYPES_FLOAT);
      this.primitiveDataTypeSet.add(VertexTrait.TYPING_DATATYPES_DOUBLE);
      _xblockexpression = this.primitiveDataTypeSet.add(VertexTrait.TYPING_DATATYPES_ARRAY);
    }
    return _xblockexpression;
  }
  
  public boolean add(final VertexTrait trait) {
    return this.primitiveDataTypeSet.add(trait);
  }
  
  public boolean isDataTypeVertex(final Vertex v) {
    for (final VertexTrait trait : this.primitiveDataTypeSet) {
      Boolean _hasTrait = v.hasTrait(trait);
      if ((_hasTrait).booleanValue()) {
        return true;
      }
    }
    return false;
  }
}
