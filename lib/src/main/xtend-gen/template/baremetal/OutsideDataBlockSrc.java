package template.baremetal;

import com.google.common.base.Objects;
import forsyde.io.java.core.EdgeInfo;
import forsyde.io.java.core.EdgeTrait;
import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import generator.Generator;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.eclipse.xtend2.lib.StringConcatenation;
import template.templateInterface.InitTemplate;

@SuppressWarnings("all")
public class OutsideDataBlockSrc implements InitTemplate {
  public String create() {
    String _xblockexpression = null;
    {
      ForSyDeSystemGraph model = Generator.model;
      final Predicate<Vertex> _function = new Predicate<Vertex>() {
        public boolean test(final Vertex v) {
          return (((v.getTraits().size() == 2) && (v.hasTrait(VertexTrait.IMPL_TOKENIZABLEDATABLOCK)).booleanValue()) && (v.hasTrait(VertexTrait.TYPING_TYPEDDATABLOCK)).booleanValue());
        }
      };
      Set<Vertex> set = model.vertexSet().stream().filter(_function).collect(Collectors.<Vertex>toSet());
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("#include \"../inc/datatype_definition.h\"");
      _builder.newLine();
      {
        boolean _hasElements = false;
        for(final Vertex v : set) {
          if (!_hasElements) {
            _hasElements = true;
          } else {
            _builder.appendImmediate("", "");
          }
          _builder.append("extern ");
          String _help = this.help(v);
          _builder.append(_help);
          _builder.append("  ");
          String _identifier = v.getIdentifier();
          _builder.append(_identifier);
          _builder.append(";");
          _builder.newLineIfNotEmpty();
        }
        if (_hasElements) {
          _builder.append("");
        }
      }
      _xblockexpression = _builder.toString();
    }
    return _xblockexpression;
  }
  
  public String help(final Vertex v) {
    ForSyDeSystemGraph model = Generator.model;
    final Predicate<EdgeInfo> _function = new Predicate<EdgeInfo>() {
      public boolean test(final EdgeInfo e) {
        return e.hasTrait(EdgeTrait.TYPING_DATATYPES_DATADEFINITION);
      }
    };
    final Predicate<EdgeInfo> _function_1 = new Predicate<EdgeInfo>() {
      public boolean test(final EdgeInfo e) {
        return (Objects.equal(e.getSource(), v.getIdentifier()) && Objects.equal(e.getSourcePort().get(), "dataType"));
      }
    };
    String type = model.edgeSet().stream().filter(_function).filter(_function_1).findAny().get().getTarget();
    return type;
  }
  
  public String getFileName() {
    return "outside_datablock";
  }
}
