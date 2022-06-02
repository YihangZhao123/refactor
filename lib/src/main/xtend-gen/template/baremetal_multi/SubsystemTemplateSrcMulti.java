package template.baremetal_multi;

import fileAnnotation.FileType;
import fileAnnotation.FileTypeAnno;
import generator.Schedule;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import template.templateInterface.SubsystemTemplate;

@FileTypeAnno(type = FileType.C_SOURCE)
@SuppressWarnings("all")
public class SubsystemTemplateSrcMulti implements SubsystemTemplate {
  private Schedule s;
  
  public String create(final Schedule schedule) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field IntegerValue is undefined"
      + "\nThe method or field IntegerValue is undefined"
      + "\nThe method findSDFChannelDataType(ForSyDeSystemGraph, Vertex) is undefined for the type Class<Query>"
      + "\nThe method isOnOneCoreChannel(ForSyDeSystemGraph, Vertex) is undefined for the type Class<Query>"
      + "\nThe method findSDFChannelDataType(ForSyDeSystemGraph, Vertex) is undefined for the type Class<Query>"
      + "\nThe method isOnOneCoreChannel(ForSyDeSystemGraph, Vertex) is undefined for the type Class<Query>"
      + "\nThe method isOnOneCoreChannel(ForSyDeSystemGraph, Vertex) is undefined for the type Class<Query>"
      + "\nThe method findSDFChannelDataType(ForSyDeSystemGraph, Vertex) is undefined for the type Class<Query>"
      + "\nThe method or field SDFChannel is undefined"
      + "\nThe method findSDFChannelDataType(ForSyDeSystemGraph, Vertex) is undefined for the type Class<Query>"
      + "\nThe method isOnOneCoreChannel(ForSyDeSystemGraph, Vertex) is undefined for the type Class<Query>"
      + "\nThe method findSDFChannelDataType(ForSyDeSystemGraph, Vertex) is undefined for the type Class<Query>"
      + "\nThe field Schedule.tile refers to the missing type Vertex"
      + "\nThe field Generator.model refers to the missing type ForSyDeSystemGraph"
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\nThere is no context to infer the closure\'s argument types from. Consider typing the arguments or put the closures into a typed context."
      + "\nThe field Schedule.tile refers to the missing type Vertex"
      + "\nThe field Schedule.slots refers to the missing type Vertex"
      + "\nThe method name(Vertex) from the type Name refers to the missing type Vertex"
      + "\nThe field Schedule.outgoingchannels refers to the missing type Vertex"
      + "\nThe field Generator.model refers to the missing type ForSyDeSystemGraph"
      + "\nThe field Schedule.incomingchannels refers to the missing type Vertex"
      + "\nThe field Generator.model refers to the missing type ForSyDeSystemGraph"
      + "\nThe field Schedule.outgoingchannels refers to the missing type Vertex"
      + "\nThe field Generator.model refers to the missing type ForSyDeSystemGraph"
      + "\nThe field Schedule.outgoingchannels refers to the missing type Vertex"
      + "\nThe field Generator.model refers to the missing type ForSyDeSystemGraph"
      + "\nThe field Schedule.incomingchannels refers to the missing type Vertex"
      + "\nvertexSet cannot be resolved"
      + "\nstream cannot be resolved"
      + "\nfilter cannot be resolved"
      + "\nconforms cannot be resolved"
      + "\nmap cannot be resolved"
      + "\nsafeCast cannot be resolved"
      + "\nget cannot be resolved"
      + "\ncollect cannot be resolved"
      + "\ngetIdentifier cannot be resolved"
      + "\ngetIdentifier cannot be resolved"
      + "\n!== cannot be resolved"
      + "\ngetIdentifier cannot be resolved"
      + "\ngetIdentifier cannot be resolved"
      + "\ngetIdentifier cannot be resolved"
      + "\ngetIdentifier cannot be resolved"
      + "\ngetIdentifier cannot be resolved"
      + "\nsafeCast cannot be resolved"
      + "\nget cannot be resolved"
      + "\ngetIdentifier cannot be resolved"
      + "\ngetNumOfInitialTokens cannot be resolved"
      + "\n!== cannot be resolved"
      + "\n&& cannot be resolved"
      + "\ngetNumOfInitialTokens cannot be resolved"
      + "\n> cannot be resolved"
      + "\ngetProperties cannot be resolved"
      + "\nget cannot be resolved"
      + "\nunwrap cannot be resolved"
      + "\ngetIdentifier cannot be resolved"
      + "\ngetIdentifier cannot be resolved");
  }
  
  public String getFileName() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe field Schedule.tile refers to the missing type Vertex"
      + "\ngetIdentifier cannot be resolved");
  }
  
  public ArrayList<String> help(final HashMap<String, Integer> ordering) {
    int _size = ordering.size();
    ArrayList<String> a = new ArrayList<String>(_size);
    Set<String> _keySet = ordering.keySet();
    for (final String k : _keySet) {
      a.add((ordering.get(k)).intValue(), k);
    }
    return a;
  }
}
