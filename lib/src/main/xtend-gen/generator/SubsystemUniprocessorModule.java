package generator;

import com.google.common.base.Objects;
import fileAnnotation.FileType;
import fileAnnotation.FileTypeAnno;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import template.templateInterface.SubsystemTemplate;
import utils.Save;

@SuppressWarnings("all")
public class SubsystemUniprocessorModule implements ModuleInterface {
  private Set<SubsystemTemplate> templates;
  
  public SubsystemUniprocessorModule() {
    HashSet<SubsystemTemplate> _hashSet = new HashSet<SubsystemTemplate>();
    this.templates = _hashSet;
  }
  
  @Override
  public void create() {
    this.process();
  }
  
  public void process() {
    final Consumer<SubsystemTemplate> _function = (SubsystemTemplate t) -> {
      FileTypeAnno anno = t.getClass().<FileTypeAnno>getAnnotation(FileTypeAnno.class);
      FileType _type = anno.type();
      boolean _equals = Objects.equal(_type, FileType.C_INCLUDE);
      if (_equals) {
        String _fileName = t.getFileName();
        String _plus = ((Generator.root + "/inc/") + _fileName);
        String _plus_1 = (_plus + ".h");
        Save.save(_plus_1, t.create(null));
      }
      FileType _type_1 = anno.type();
      boolean _equals_1 = Objects.equal(_type_1, FileType.C_SOURCE);
      if (_equals_1) {
        String _fileName_1 = t.getFileName();
        String _plus_2 = ((Generator.root + "/src/") + _fileName_1);
        String _plus_3 = (_plus_2 + ".c");
        Save.save(_plus_3, t.create(null));
      }
    };
    this.templates.stream().forEach(_function);
  }
  
  public boolean add(final SubsystemTemplate t) {
    return this.templates.add(t);
  }
}
