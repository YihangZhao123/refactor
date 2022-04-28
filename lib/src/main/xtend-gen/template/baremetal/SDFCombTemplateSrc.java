package template.baremetal;

import forsyde.io.java.core.Vertex;
import forsyde.io.java.core.VertexAcessor;
import forsyde.io.java.core.VertexProperty;
import forsyde.io.java.core.VertexTrait;
import generator.Generator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import org.eclipse.xtend2.lib.StringConcatenation;
import template.templateInterface.ActorTemplate;
import utils.Name;
import utils.Query;

/* @srcFile */@SuppressWarnings("all")
public class SDFCombTemplateSrc implements ActorTemplate {
  private Set<Vertex> implActorSet;
  
  public String create(final Vertex vertex) {
    String _xblockexpression = null;
    {
      this.implActorSet = VertexAcessor.getMultipleNamedPort(Generator.model, vertex, "combFunctions", 
        VertexTrait.IMPL_ANSICBLACKBOXEXECUTABLE, VertexAcessor.VertexPortDirection.OUTGOING);
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("#include \"../inc/datatype_definition.h\"");
      _builder.newLine();
      String name = Name.name(vertex);
      _builder.newLineIfNotEmpty();
      _builder.append("inline void actor_");
      _builder.append(name);
      _builder.append("(){");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("//initilize the memory");
      _builder.newLine();
      _builder.append("\t");
      String _initMemory = this.initMemory();
      _builder.append(_initMemory, "\t");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("//read from the input port");
      _builder.newLine();
      _builder.append("\t");
      String _read = this.read(vertex);
      _builder.append(_read, "\t");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("//inline code");
      _builder.newLine();
      _builder.append("\t");
      String _inlineCode = this.getInlineCode();
      _builder.append(_inlineCode, "\t");
      _builder.newLineIfNotEmpty();
      _builder.newLine();
      _builder.append("\t");
      _builder.append("//write to the output port");
      _builder.newLine();
      _builder.append("\t");
      String _write = this.write(vertex);
      _builder.append(_write, "\t");
      _builder.newLineIfNotEmpty();
      _builder.append("}");
      _builder.newLine();
      _xblockexpression = _builder.toString();
    }
    return _xblockexpression;
  }
  
  public String initMemory() {
    Set<String> variableNameRecord = new HashSet<String>();
    String ret = "";
    for (final Vertex impl : this.implActorSet) {
      {
        Object _unwrap = impl.getProperties().get("portToTypeName").unwrap();
        final HashMap<String, String> portToTypeNameHashMap = ((HashMap<String, String>) _unwrap);
        Object _unwrap_1 = impl.getProperties().get("inputPorts").unwrap();
        final ArrayList<String> inputPortList = ((ArrayList<String>) _unwrap_1);
        Object _unwrap_2 = impl.getProperties().get("outputPorts").unwrap();
        final ArrayList<String> outputPortList = ((ArrayList<String>) _unwrap_2);
        HashSet<String> inputPortAndoutputPortSet = new HashSet<String>();
        inputPortAndoutputPortSet.addAll(inputPortList);
        inputPortAndoutputPortSet.addAll(outputPortList);
        String _ret = ret;
        StringConcatenation _builder = new StringConcatenation();
        {
          boolean _hasElements = false;
          for(final String port : inputPortAndoutputPortSet) {
            if (!_hasElements) {
              _hasElements = true;
            } else {
              _builder.appendImmediate("", "");
            }
            {
              boolean _contains = variableNameRecord.contains(port);
              boolean _not = (!_contains);
              if (_not) {
                String _get = portToTypeNameHashMap.get(port);
                _builder.append(_get);
                _builder.append("  ");
                _builder.append(port);
                _builder.append(";");
                _builder.newLineIfNotEmpty();
                boolean tmp = variableNameRecord.add(port);
                _builder.newLineIfNotEmpty();
              }
            }
          }
          if (_hasElements) {
            _builder.append("");
          }
        }
        ret = (_ret + _builder);
      }
    }
    return ret;
  }
  
  public String read(final Vertex vertex) {
    String _xblockexpression = null;
    {
      VertexProperty consumption = vertex.getProperties().get("consumption");
      String _xifexpression = null;
      if ((consumption != null)) {
        String _xblockexpression_1 = null;
        {
          Object _unwrap = consumption.unwrap();
          HashMap<String, Integer> inputPortsHashMap = ((HashMap<String, Integer>) _unwrap);
          StringConcatenation _builder = new StringConcatenation();
          {
            Set<String> _keySet = inputPortsHashMap.keySet();
            boolean _hasElements = false;
            for(final String port : _keySet) {
              if (!_hasElements) {
                _hasElements = true;
              } else {
                _builder.appendImmediate("", "");
              }
              {
                Integer _get = inputPortsHashMap.get(port);
                boolean _equals = ((_get).intValue() == 1);
                if (_equals) {
                  _builder.append("read_nonblocking(");
                  _builder.append(port);
                  _builder.append("_channel);");
                  _builder.newLineIfNotEmpty();
                } else {
                  _builder.append("for(int i=0;i<");
                  Integer _get_1 = inputPortsHashMap.get(port);
                  _builder.append(_get_1);
                  _builder.append(";++i){");
                  _builder.newLineIfNotEmpty();
                  _builder.append("\t");
                  _builder.append("read_nonblocking(");
                  _builder.append(port, "\t");
                  _builder.append("_channel);");
                  _builder.newLineIfNotEmpty();
                  _builder.append("}");
                  _builder.newLine();
                }
              }
            }
            if (_hasElements) {
              _builder.append("");
            }
          }
          _xblockexpression_1 = _builder.toString();
        }
        _xifexpression = _xblockexpression_1;
      } else {
        StringConcatenation _builder = new StringConcatenation();
        _xifexpression = _builder.toString();
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }
  
  public String write(final Vertex vertex) {
    String _xblockexpression = null;
    {
      VertexProperty production = vertex.getProperties().get("production");
      String _xifexpression = null;
      if ((production != null)) {
        String _xblockexpression_1 = null;
        {
          Object _unwrap = production.unwrap();
          HashMap<String, Integer> inputPortsHashMap = ((HashMap<String, Integer>) _unwrap);
          StringConcatenation _builder = new StringConcatenation();
          {
            Set<String> _keySet = inputPortsHashMap.keySet();
            boolean _hasElements = false;
            for(final String port : _keySet) {
              if (!_hasElements) {
                _hasElements = true;
              } else {
                _builder.appendImmediate("", "");
              }
              {
                Integer _get = inputPortsHashMap.get(port);
                boolean _equals = ((_get).intValue() == 1);
                if (_equals) {
                  _builder.append("write(");
                  _builder.append(port);
                  _builder.append("_channel);");
                  _builder.newLineIfNotEmpty();
                } else {
                  _builder.append("for(int i=0;i<");
                  Integer _get_1 = inputPortsHashMap.get(port);
                  _builder.append(_get_1);
                  _builder.append(";++i){");
                  _builder.newLineIfNotEmpty();
                  _builder.append("\t");
                  _builder.append("write(");
                  _builder.append(port, "\t");
                  _builder.append("_channel);");
                  _builder.newLineIfNotEmpty();
                  _builder.append("}");
                  _builder.newLine();
                }
              }
            }
            if (_hasElements) {
              _builder.append("");
            }
          }
          _xblockexpression_1 = _builder.toString();
        }
        _xifexpression = _xblockexpression_1;
      } else {
        StringConcatenation _builder = new StringConcatenation();
        _xifexpression = _builder.toString();
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }
  
  private String getInlineCode() {
    StringConcatenation _builder = new StringConcatenation();
    {
      boolean _hasElements = false;
      for(final Vertex impl : this.implActorSet) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate("", "");
        }
        _builder.append("//in combFunction ");
        String _identifier = impl.getIdentifier();
        _builder.append(_identifier);
        _builder.newLineIfNotEmpty();
        String _inlineCode = Query.getInlineCode(impl);
        _builder.append(_inlineCode);
        _builder.newLineIfNotEmpty();
      }
      if (_hasElements) {
        _builder.append("");
      }
    }
    return _builder.toString();
  }
}
