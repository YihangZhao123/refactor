package template.baremetal;

import fileAnnotation.FileType;
import fileAnnotation.FileTypeAnno;
import org.eclipse.xtend2.lib.StringConcatenation;
import template.templateInterface.InitTemplate;

@FileTypeAnno(type = FileType.C_SOURCE)
@SuppressWarnings("all")
public class SpinLockTemplateSrc implements InitTemplate {
  @Override
  public String create() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#include \"../inc/spinlock.h\"");
    _builder.newLine();
    _builder.append("void spinlock_get(spinlock* lock){");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("while(__sync_lock_test_and_set(&lock->flag,1)==1){");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("void spinlock_release(spinlock* lock){");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("__sync_lock_test_and_set(&lock->flag,0);");
    _builder.newLine();
    _builder.append("}\t");
    _builder.newLine();
    return _builder.toString();
  }
  
  @Override
  public String getFileName() {
    return "spinlock";
  }
}
