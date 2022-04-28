package demo;

import com.google.common.base.Objects;
import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.core.Vertex;
import java.util.Set;
import utils.Load;

@SuppressWarnings("all")
public class test {
  public static void main(final String[] args) {
    final String path = "forsyde-io\\complete-mapped-sobel-model.forsyde.xmi";
    final String path2 = "forsyde-io\\sobel-application.fiodl";
    final String root = "generateCode\\c\\single";
    ForSyDeSystemGraph model1 = Load.load(path);
    ForSyDeSystemGraph model2 = Load.load(path2);
    model1.mergeInPlace(model2);
    Set<Vertex> _vertexSet = model1.vertexSet();
    for (final Vertex v : _vertexSet) {
      String _identifier = v.getIdentifier();
      boolean _equals = Objects.equal(_identifier, "Abs");
      if (_equals) {
        int b = 1;
      }
    }
  }
}
