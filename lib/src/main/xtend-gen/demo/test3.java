package demo;

import forsyde.io.java.core.ForSyDeSystemGraph;
import org.eclipse.xtext.xbase.lib.InputOutput;
import utils.Load;

@SuppressWarnings("all")
public class test3 {
  public static void main(final String[] args) {
    final String path = "forsyde-io\\modified2\\complete-mapped-sobel-model.forsyde.xmi";
    final String path2 = "forsyde-io\\modified2\\sobel-application.fiodl";
    ForSyDeSystemGraph model1 = Load.load(path);
    ForSyDeSystemGraph model2 = Load.load(path2);
    InputOutput.<String>println("end!");
  }
}
