package template.baremetal_single;

import fileAnnotation.FileType;
import fileAnnotation.FileTypeAnno;
import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.typed.viewers.moc.sdf.SDFComb;
import generator.Generator;
import generator.Schedule;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.eclipse.xtend2.lib.StringConcatenation;
import template.templateInterface.SubsystemTemplate;

@FileTypeAnno(type = FileType.C_SOURCE)
@SuppressWarnings("all")
public class SubsystemTemplateSrc implements SubsystemTemplate {
  public String create(final Schedule s) {
    String _xblockexpression = null;
    {
      ForSyDeSystemGraph model = Generator.model;
      final Predicate<Vertex> _function = new Predicate<Vertex>() {
        public boolean test(final Vertex v) {
          return (SDFComb.conforms(v)).booleanValue();
        }
      };
      Set<Vertex> sdfcomb = model.vertexSet().stream().filter(_function).collect(Collectors.<Vertex>toSet());
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("#include \"../inc/subsystem.h\"");
      _builder.newLine();
      _builder.append("#include <stdio.h>");
      _builder.newLine();
      {
        for(final Vertex v : sdfcomb) {
          _builder.append("#include \"../inc/sdfcomb_");
          String _identifier = v.getIdentifier();
          _builder.append(_identifier);
          _builder.append(".h\"");
          _builder.newLineIfNotEmpty();
        }
      }
      _builder.append("/*");
      _builder.newLine();
      _builder.append("==============================================");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Subsystem Function");
      _builder.newLine();
      _builder.append("==============================================");
      _builder.newLine();
      _builder.append("*/\t");
      _builder.newLine();
      _builder.append("int fire_subsystem_single_uniprocessor(){");
      _builder.newLine();
      {
        Set<Map.Entry<Integer, Vertex>> _entrySet = Generator.uniprocessorSchedule.entrySet();
        boolean _hasElements = false;
        for(final Map.Entry<Integer, Vertex> set : _entrySet) {
          if (!_hasElements) {
            _hasElements = true;
          } else {
            _builder.appendImmediate("", "\t\t");
          }
          _builder.append("\t\t");
          _builder.append("//printf(\"%s\\n\",\"enter ");
          String _identifier_1 = set.getValue().getIdentifier();
          _builder.append(_identifier_1, "\t\t");
          _builder.append("\");");
          _builder.newLineIfNotEmpty();
          {
            if (((Generator.TESTING == 1) && (Generator.PC == 1))) {
              _builder.append("\t\t");
              _builder.append("\t");
              _builder.append("actor_");
              String _identifier_2 = set.getValue().getIdentifier();
              _builder.append(_identifier_2, "\t\t\t");
              _builder.append("();");
              _builder.newLineIfNotEmpty();
            }
          }
        }
        if (_hasElements) {
          _builder.append("", "\t\t");
        }
      }
      _builder.append("}");
      _builder.newLine();
      _xblockexpression = _builder.toString();
    }
    return _xblockexpression;
  }
  
  public String getFileName() {
    return "subsystem";
  }
}
