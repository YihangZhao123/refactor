package template.baremetal;

import fileAnnotation.FileType;
import fileAnnotation.FileTypeAnno;
import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexTrait;
import forsyde.io.java.typed.viewers.moc.sdf.SDFChannel;
import generator.Generator;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.eclipse.xtend2.lib.StringConcatenation;
import template.templateInterface.InitTemplate;
import utils.Query;

@FileTypeAnno(type = FileType.C_SOURCE)
@SuppressWarnings("all")
public class CircularFIFOTemplateSrc implements InitTemplate {
  private Set<VertexTrait> primitiveTraitSet;
  
  private Set<Vertex> a;
  
  public CircularFIFOTemplateSrc() {
    HashSet<VertexTrait> _hashSet = new HashSet<VertexTrait>();
    this.primitiveTraitSet = _hashSet;
    final ForSyDeSystemGraph model = Generator.model;
    final Predicate<Vertex> _function = new Predicate<Vertex>() {
      public boolean test(final Vertex v) {
        return (SDFChannel.conforms(v)).booleanValue();
      }
    };
    final Function<Vertex, String> _function_1 = new Function<Vertex, String>() {
      public String apply(final Vertex v) {
        return Query.findSDFChannelDataType(model, v);
      }
    };
    final Function<String, Vertex> _function_2 = new Function<String, Vertex>() {
      public Vertex apply(final String s) {
        return Query.findVertexByName(model, s);
      }
    };
    this.a = model.vertexSet().stream().filter(_function).<String>map(_function_1).<Vertex>map(_function_2).collect(Collectors.<Vertex>toSet());
  }
  
  public String create() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#include \"../inc/datatype_definition.h\"");
    _builder.newLine();
    _builder.append("#include \"../inc/circular_fifo_lib.h\"");
    _builder.newLine();
    _builder.append("/*");
    _builder.newLine();
    _builder.append("*******************************************************");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("*******************************************************");
    _builder.newLine();
    _builder.append("*/");
    _builder.newLine();
    {
      boolean _hasElements = false;
      for(final VertexTrait primitiveTrait : this.primitiveTraitSet) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate("", "");
        }
        CharSequence _primitiveChannelDefinition = this.primitiveChannelDefinition(primitiveTrait);
        _builder.append(_primitiveChannelDefinition);
        _builder.append("\t");
        _builder.newLineIfNotEmpty();
      }
      if (_hasElements) {
        _builder.append("");
      }
    }
    return _builder.toString();
  }
  
  public CharSequence primitiveChannelDefinition(final VertexTrait trait) {
    StringConcatenation _builder = new StringConcatenation();
    final Predicate<Vertex> _function = new Predicate<Vertex>() {
      public boolean test(final Vertex v) {
        return (v.hasTrait(trait)).booleanValue();
      }
    };
    Set<Vertex> typeVertexSet = Generator.model.vertexSet().stream().filter(_function).collect(Collectors.<Vertex>toSet());
    _builder.newLineIfNotEmpty();
    {
      boolean _hasElements = false;
      for(final Vertex typeVertex : typeVertexSet) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate("", "");
        }
        final String type = typeVertex.getIdentifier();
        _builder.newLineIfNotEmpty();
        {
          Boolean _hasTrait = typeVertex.hasTrait(VertexTrait.TYPING_DATATYPES_ARRAY);
          boolean _not = (!(_hasTrait).booleanValue());
          if (_not) {
            _builder.append("/*");
            _builder.newLine();
            _builder.append("=============================================================");
            _builder.newLine();
            _builder.append("\t\t\t\t");
            _builder.append(type, "\t\t\t\t");
            _builder.append(" Channel Definition");
            _builder.newLineIfNotEmpty();
            _builder.append("=============================================================");
            _builder.newLine();
            _builder.append("*/\t\t\t\t");
            _builder.newLine();
            _builder.append("void init_channel_");
            _builder.append(type);
            _builder.append("(circular_fifo_");
            _builder.append(type);
            _builder.append(" *channel ,");
            _builder.append(type);
            _builder.append("* buffer, size_t size){");
            _builder.newLineIfNotEmpty();
            _builder.append("    ");
            _builder.append("channel->buffer = buffer;");
            _builder.newLine();
            _builder.append("    ");
            _builder.append("channel->size=size;");
            _builder.newLine();
            _builder.append("    ");
            _builder.append("channel->front = 0;");
            _builder.newLine();
            _builder.append("    ");
            _builder.append("channel->rear = 0;\t\t\t");
            _builder.newLine();
            _builder.append("}");
            _builder.newLine();
            _builder.append("\t\t\t");
            _builder.newLine();
            _builder.append("int read_non_blocking_");
            _builder.append(type);
            _builder.append("(circular_fifo_");
            _builder.append(type);
            _builder.append(" *channel, ");
            _builder.append(type);
            _builder.append(" *data){");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("if(channel->front==channel->rear){");
            _builder.newLine();
            _builder.append("\t    \t");
            _builder.append("//empty ");
            _builder.newLine();
            _builder.append("\t    \t");
            _builder.append("return -1;");
            _builder.newLine();
            _builder.append("\t    \t\t\t");
            _builder.newLine();
            _builder.append("\t   ");
            _builder.append("}else{");
            _builder.newLine();
            _builder.append("\t    \t");
            _builder.append("*data = channel->buffer[channel->front];");
            _builder.newLine();
            _builder.append("\t    \t");
            _builder.append("#if defined(TEST)");
            _builder.newLine();
            _builder.append("\t    \t");
            _builder.append("printf(\"buffer ");
            _builder.append(type, "\t    \t");
            _builder.append(": before read, front: %d, rear %d size:%d\\n\",channel->front,channel->rear,channel->size);");
            _builder.newLineIfNotEmpty();
            _builder.append("\t    \t");
            _builder.append("#endif");
            _builder.newLine();
            _builder.append("\t    \t");
            _builder.append("channel->front= (channel->front+1)%channel->size;");
            _builder.newLine();
            _builder.append("\t    \t");
            _builder.append("#if defined(TEST)");
            _builder.newLine();
            _builder.append("\t    \t");
            _builder.append("printf(\"buffer ");
            _builder.append(type, "\t    \t");
            _builder.append(": after read, front: %d, rear %d size:%d\\n\",channel->front,channel->rear,channel->size);");
            _builder.newLineIfNotEmpty();
            _builder.append("\t    \t");
            _builder.append("#endif");
            _builder.newLine();
            _builder.append("\t    \t");
            _builder.append("return 0;");
            _builder.newLine();
            _builder.append("\t    ");
            _builder.append("}");
            _builder.newLine();
            _builder.append("}");
            _builder.newLine();
            _builder.append("int read_blocking_");
            _builder.append(type);
            _builder.append("(circular_fifo_");
            _builder.append(type);
            _builder.append("* channel,");
            _builder.append(type);
            _builder.append("* data,spinlock* lock){");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("spinlock_get(lock);");
            _builder.newLine();
            _builder.append("\t");
            _builder.append("if(channel->front==channel->rear){");
            _builder.newLine();
            _builder.append("\t    \t");
            _builder.append("//empty ");
            _builder.newLine();
            _builder.append("\t    \t");
            _builder.append("spinlock_release(lock);");
            _builder.newLine();
            _builder.append("\t    \t");
            _builder.append("return -1;");
            _builder.newLine();
            _builder.append("\t    \t\t\t");
            _builder.newLine();
            _builder.append("\t   ");
            _builder.append("}else{");
            _builder.newLine();
            _builder.append("\t    \t");
            _builder.append("*data = channel->buffer[channel->front];");
            _builder.newLine();
            _builder.append("\t    \t");
            _builder.append("//printf(\"buffer ");
            _builder.append(type, "\t    \t");
            _builder.append(": before read, front: %d, rear %d size:%d\\n\",channel->front,channel->rear,channel->size);");
            _builder.newLineIfNotEmpty();
            _builder.append("\t    \t");
            _builder.append("channel->front= (channel->front+1)%channel->size;");
            _builder.newLine();
            _builder.append("\t    \t");
            _builder.append("//printf(\"buffer ");
            _builder.append(type, "\t    \t");
            _builder.append(": after read, front: %d, rear %d size:%d\\n\",channel->front,channel->rear,channel->size);");
            _builder.newLineIfNotEmpty();
            _builder.append("\t    \t");
            _builder.append("spinlock_release(lock);");
            _builder.newLine();
            _builder.append("\t    \t");
            _builder.append("return 0;");
            _builder.newLine();
            _builder.append("\t    ");
            _builder.append("}");
            _builder.newLine();
            _builder.append("}\t\t\t\t");
            _builder.newLine();
            _builder.append("\t\t\t");
            _builder.newLine();
            _builder.append("int write_non_blocking_");
            _builder.append(type);
            _builder.append("(circular_fifo_");
            _builder.append(type);
            _builder.append("* channel, ");
            _builder.append(type);
            _builder.append(" value){");
            _builder.newLineIfNotEmpty();
            _builder.append("    ");
            _builder.append("/*if the buffer is full*/");
            _builder.newLine();
            _builder.append("    ");
            _builder.append("if((channel->rear+1)%channel->size == channel->front){");
            _builder.newLine();
            _builder.append("        ");
            _builder.append("//full!");
            _builder.newLine();
            _builder.append("        ");
            _builder.append("//discard the data");
            _builder.newLine();
            _builder.append("        ");
            _builder.append("//printf(\"buffer full error\\n!\");");
            _builder.newLine();
            _builder.append("        ");
            _builder.append("return -1;");
            _builder.newLine();
            _builder.append("     ");
            _builder.append("}else{");
            _builder.newLine();
            _builder.append("        ");
            _builder.append("channel->buffer[channel->rear] = value;");
            _builder.newLine();
            _builder.append("       ");
            _builder.append("//printf(\"buffer ");
            _builder.append(type, "       ");
            _builder.append(":before write, front: %d, rear %d size:%d\\n\",channel->front,channel->rear,channel->size);");
            _builder.newLineIfNotEmpty();
            _builder.append("        ");
            _builder.append("channel->rear= (channel->rear+1)%channel->size;");
            _builder.newLine();
            _builder.append("        ");
            _builder.append("//printf(\"buffer ");
            _builder.append(type, "        ");
            _builder.append(":after write, front: %d, rear %d size:%d\\n\",channel->front,channel->rear,channel->size);");
            _builder.newLineIfNotEmpty();
            _builder.append("        ");
            _builder.append("return 0;");
            _builder.newLine();
            _builder.append("    ");
            _builder.append("}\t\t\t");
            _builder.newLine();
            _builder.newLine();
            _builder.append("}\t");
            _builder.newLine();
            _builder.append("\t\t\t");
            _builder.newLine();
            _builder.append("int write_blocking_");
            _builder.append(type);
            _builder.append("(circular_fifo_");
            _builder.append(type);
            _builder.append("* channel, ");
            _builder.append(type);
            _builder.append(" value,spinlock* lock){");
            _builder.newLineIfNotEmpty();
            _builder.append("\t");
            _builder.append("spinlock_get(lock);");
            _builder.newLine();
            _builder.append("\t");
            _builder.newLine();
            _builder.append("\t   ");
            _builder.append("/*if the buffer is full*/");
            _builder.newLine();
            _builder.append("\t   ");
            _builder.append("if((channel->rear+1)%channel->size == channel->front){");
            _builder.newLine();
            _builder.append("\t       ");
            _builder.append("//full!");
            _builder.newLine();
            _builder.append("\t       ");
            _builder.append("//discard the data");
            _builder.newLine();
            _builder.append("\t       ");
            _builder.append("//printf(\"buffer full error\\n!\");");
            _builder.newLine();
            _builder.append("\t       ");
            _builder.append("spinlock_release(lock);");
            _builder.newLine();
            _builder.append("\t       ");
            _builder.append("return -1;");
            _builder.newLine();
            _builder.append("\t    ");
            _builder.append("}else{");
            _builder.newLine();
            _builder.append("\t       ");
            _builder.append("channel->buffer[channel->rear] = value;");
            _builder.newLine();
            _builder.append("\t      ");
            _builder.append("//printf(\"buffer ");
            _builder.append(type, "\t      ");
            _builder.append(":before write, front: %d, rear %d size:%d\\n\",channel->front,channel->rear,channel->size);");
            _builder.newLineIfNotEmpty();
            _builder.append("\t       ");
            _builder.append("channel->rear= (channel->rear+1)%channel->size;");
            _builder.newLine();
            _builder.append("\t       ");
            _builder.append("//printf(\"buffer ");
            _builder.append(type, "\t       ");
            _builder.append(":after write, front: %d, rear %d size:%d\\n\",channel->front,channel->rear,channel->size);");
            _builder.newLineIfNotEmpty();
            _builder.append("\t       ");
            _builder.append("spinlock_release(lock);");
            _builder.newLine();
            _builder.append("\t       ");
            _builder.append("return 0;");
            _builder.newLine();
            _builder.append("\t   ");
            _builder.append("}\t\t\t\t");
            _builder.newLine();
            _builder.append("}");
            _builder.newLine();
          } else {
            if ((this.help1(typeVertex) && (Query.getMaximumElems(typeVertex) > 0))) {
              _builder.append("/*");
              _builder.newLine();
              _builder.append("=============================================================");
              _builder.newLine();
              _builder.append("\t\t\t\t");
              _builder.append(type, "\t\t\t\t");
              _builder.append(" Channel Definition");
              _builder.newLineIfNotEmpty();
              _builder.append("=============================================================");
              _builder.newLine();
              _builder.append("*/\t\t\t\t");
              _builder.newLine();
              _builder.append("void init_channel_");
              _builder.append(type);
              _builder.append("(circular_fifo_");
              _builder.append(type);
              _builder.append(" *channel ,");
              _builder.append(type);
              _builder.append("* buffer, size_t size){");
              _builder.newLineIfNotEmpty();
              _builder.append("    ");
              _builder.append("channel->buffer = buffer;");
              _builder.newLine();
              _builder.append("    ");
              _builder.append("channel->size=size;");
              _builder.newLine();
              _builder.append("    ");
              _builder.append("channel->front = 0;");
              _builder.newLine();
              _builder.append("    ");
              _builder.append("channel->rear = 0;\t\t\t");
              _builder.newLine();
              _builder.append("}");
              _builder.newLine();
              _builder.append("\t\t\t");
              _builder.newLine();
              _builder.append("int read_non_blocking_");
              _builder.append(type);
              _builder.append("(circular_fifo_");
              _builder.append(type);
              _builder.append(" *channel, ");
              _builder.append(type);
              _builder.append(" *data){");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append("if(channel->front==channel->rear){");
              _builder.newLine();
              _builder.append("\t    \t");
              _builder.append("//empty ");
              _builder.newLine();
              _builder.append("\t    \t");
              _builder.append("return -1;");
              _builder.newLine();
              _builder.append("\t    \t\t\t");
              _builder.newLine();
              _builder.append("\t   ");
              _builder.append("}else{");
              _builder.newLine();
              _builder.append("\t     \t");
              _builder.append("for(int i=0;i < ");
              int _maximumElems = Query.getMaximumElems(typeVertex);
              _builder.append(_maximumElems, "\t     \t");
              _builder.append("; ++i){");
              _builder.newLineIfNotEmpty();
              _builder.append("\t     \t\t");
              _builder.append("(*data)[i]=channel->buffer[channel->front][i];");
              _builder.newLine();
              _builder.append("\t     \t");
              _builder.append("}");
              _builder.newLine();
              _builder.append("\t    \t");
              _builder.append("#if defined(TEST)");
              _builder.newLine();
              _builder.append("\t    \t");
              _builder.append("printf(\"buffer ");
              _builder.append(type, "\t    \t");
              _builder.append(": before read, front: %d, rear %d size:%d\\n\",channel->front,channel->rear,channel->size);");
              _builder.newLineIfNotEmpty();
              _builder.append("\t    \t");
              _builder.append("#endif");
              _builder.newLine();
              _builder.append("\t    \t");
              _builder.append("channel->front= (channel->front+1)%channel->size;");
              _builder.newLine();
              _builder.append("\t    \t");
              _builder.append("#if defined(TEST)");
              _builder.newLine();
              _builder.append("\t    \t");
              _builder.append("printf(\"buffer ");
              _builder.append(type, "\t    \t");
              _builder.append(": after read, front: %d, rear %d size:%d\\n\",channel->front,channel->rear,channel->size);");
              _builder.newLineIfNotEmpty();
              _builder.append("\t    \t");
              _builder.append("#endif");
              _builder.newLine();
              _builder.append("\t    \t");
              _builder.append("return 0;");
              _builder.newLine();
              _builder.append("\t    ");
              _builder.append("}");
              _builder.newLine();
              _builder.append("}");
              _builder.newLine();
              _builder.append("int read_blocking_");
              _builder.append(type);
              _builder.append("(circular_fifo_");
              _builder.append(type);
              _builder.append("* channel,");
              _builder.append(type);
              _builder.append("* data,spinlock* lock){");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append("spinlock_get(lock);");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("if(channel->front==channel->rear){");
              _builder.newLine();
              _builder.append("\t    \t");
              _builder.append("//empty ");
              _builder.newLine();
              _builder.append("\t    \t");
              _builder.append("spinlock_release(lock);");
              _builder.newLine();
              _builder.append("\t    \t");
              _builder.append("return -1;");
              _builder.newLine();
              _builder.append("\t    \t\t\t");
              _builder.newLine();
              _builder.append("\t   ");
              _builder.append("}else{");
              _builder.newLine();
              _builder.append("\t     \t");
              _builder.append("for(int i=0;i < ");
              int _maximumElems_1 = Query.getMaximumElems(typeVertex);
              _builder.append(_maximumElems_1, "\t     \t");
              _builder.append("; ++i){");
              _builder.newLineIfNotEmpty();
              _builder.append("\t     \t\t");
              _builder.append("(*data)[i]=channel->buffer[channel->front][i];");
              _builder.newLine();
              _builder.append("\t     \t");
              _builder.append("}");
              _builder.newLine();
              _builder.append("\t    \t");
              _builder.append("//printf(\"buffer ");
              _builder.append(type, "\t    \t");
              _builder.append(": before read, front: %d, rear %d size:%d\\n\",channel->front,channel->rear,channel->size);");
              _builder.newLineIfNotEmpty();
              _builder.append("\t    \t");
              _builder.append("channel->front= (channel->front+1)%channel->size;");
              _builder.newLine();
              _builder.append("\t    \t");
              _builder.append("//printf(\"buffer ");
              _builder.append(type, "\t    \t");
              _builder.append(": after read, front: %d, rear %d size:%d\\n\",channel->front,channel->rear,channel->size);");
              _builder.newLineIfNotEmpty();
              _builder.append("\t    \t");
              _builder.append("spinlock_release(lock);");
              _builder.newLine();
              _builder.append("\t    \t");
              _builder.append("return 0;");
              _builder.newLine();
              _builder.append("\t    ");
              _builder.append("}");
              _builder.newLine();
              _builder.append("}\t\t\t\t");
              _builder.newLine();
              _builder.append("\t\t\t");
              _builder.newLine();
              _builder.append("int write_non_blocking_");
              _builder.append(type);
              _builder.append("(circular_fifo_");
              _builder.append(type);
              _builder.append("* channel, ");
              _builder.append(type);
              _builder.append(" value){");
              _builder.newLineIfNotEmpty();
              _builder.append("    ");
              _builder.append("/*if the buffer is full*/");
              _builder.newLine();
              _builder.append("    ");
              _builder.append("if((channel->rear+1)%channel->size == channel->front){");
              _builder.newLine();
              _builder.append("        ");
              _builder.append("//full!");
              _builder.newLine();
              _builder.append("        ");
              _builder.append("//discard the data");
              _builder.newLine();
              _builder.append("        ");
              _builder.append("//printf(\"buffer full error\\n!\");");
              _builder.newLine();
              _builder.append("        ");
              _builder.append("return -1;");
              _builder.newLine();
              _builder.append("     ");
              _builder.append("}else{");
              _builder.newLine();
              _builder.append("     \t");
              _builder.append("for(int i=0;i < ");
              int _maximumElems_2 = Query.getMaximumElems(typeVertex);
              _builder.append(_maximumElems_2, "     \t");
              _builder.append("; ++i){");
              _builder.newLineIfNotEmpty();
              _builder.append("     \t\t");
              _builder.append("channel->buffer[channel->rear][i] = value[i];");
              _builder.newLine();
              _builder.append("     \t");
              _builder.append("}");
              _builder.newLine();
              _builder.append("        ");
              _builder.newLine();
              _builder.append("       ");
              _builder.append("//printf(\"buffer ");
              _builder.append(type, "       ");
              _builder.append(":before write, front: %d, rear %d size:%d\\n\",channel->front,channel->rear,channel->size);");
              _builder.newLineIfNotEmpty();
              _builder.append("        ");
              _builder.append("channel->rear= (channel->rear+1)%channel->size;");
              _builder.newLine();
              _builder.append("        ");
              _builder.append("//printf(\"buffer ");
              _builder.append(type, "        ");
              _builder.append(":after write, front: %d, rear %d size:%d\\n\",channel->front,channel->rear,channel->size);");
              _builder.newLineIfNotEmpty();
              _builder.append("        ");
              _builder.append("return 0;");
              _builder.newLine();
              _builder.append("    ");
              _builder.append("}\t\t\t");
              _builder.newLine();
              _builder.newLine();
              _builder.append("}\t");
              _builder.newLine();
              _builder.append("\t\t\t");
              _builder.newLine();
              _builder.append("int write_blocking_");
              _builder.append(type);
              _builder.append("(circular_fifo_");
              _builder.append(type);
              _builder.append("* channel, ");
              _builder.append(type);
              _builder.append(" value,spinlock* lock){");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append("spinlock_get(lock);");
              _builder.newLine();
              _builder.append("\t");
              _builder.newLine();
              _builder.append("\t   ");
              _builder.append("/*if the buffer is full*/");
              _builder.newLine();
              _builder.append("\t   ");
              _builder.append("if((channel->rear+1)%channel->size == channel->front){");
              _builder.newLine();
              _builder.append("\t       ");
              _builder.append("//full!");
              _builder.newLine();
              _builder.append("\t       ");
              _builder.append("//discard the data");
              _builder.newLine();
              _builder.append("\t       ");
              _builder.append("//printf(\"buffer full error\\n!\");");
              _builder.newLine();
              _builder.append("\t       ");
              _builder.append("spinlock_release(lock);");
              _builder.newLine();
              _builder.append("\t       ");
              _builder.append("return -1;");
              _builder.newLine();
              _builder.append("\t    ");
              _builder.append("}else{");
              _builder.newLine();
              _builder.append("\t     \t");
              _builder.append("for(int i=0;i < ");
              int _maximumElems_3 = Query.getMaximumElems(typeVertex);
              _builder.append(_maximumElems_3, "\t     \t");
              _builder.append("; ++i){");
              _builder.newLineIfNotEmpty();
              _builder.append("\t     \t\t");
              _builder.append("channel->buffer[channel->rear][i] = value[i];");
              _builder.newLine();
              _builder.append("\t     \t");
              _builder.append("}");
              _builder.newLine();
              _builder.append("\t      ");
              _builder.append("//printf(\"buffer ");
              _builder.append(type, "\t      ");
              _builder.append(":before write, front: %d, rear %d size:%d\\n\",channel->front,channel->rear,channel->size);");
              _builder.newLineIfNotEmpty();
              _builder.append("\t       ");
              _builder.append("channel->rear= (channel->rear+1)%channel->size;");
              _builder.newLine();
              _builder.append("\t       ");
              _builder.append("//printf(\"buffer ");
              _builder.append(type, "\t       ");
              _builder.append(":after write, front: %d, rear %d size:%d\\n\",channel->front,channel->rear,channel->size);");
              _builder.newLineIfNotEmpty();
              _builder.append("\t       ");
              _builder.append("spinlock_release(lock);");
              _builder.newLine();
              _builder.append("\t       ");
              _builder.append("return 0;");
              _builder.newLine();
              _builder.append("\t   ");
              _builder.append("}\t\t\t\t");
              _builder.newLine();
              _builder.append("}");
              _builder.newLine();
            } else {
            }
          }
        }
      }
      if (_hasElements) {
        _builder.append("");
      }
    }
    return _builder;
  }
  
  public String getFileName() {
    return "circular_fifo_lib";
  }
  
  public boolean help1(final Vertex v) {
    String inner = Query.getInnerType(Generator.model, v);
    Vertex innerVertex = Query.findVertexByName(Generator.model, inner);
    Boolean _hasTrait = innerVertex.hasTrait(VertexTrait.TYPING_DATATYPES_ARRAY);
    if ((_hasTrait).booleanValue()) {
      return false;
    } else {
      return true;
    }
  }
}
