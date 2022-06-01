package demo;

import forsyde.io.java.core.ForSyDeSystemGraph;
import forsyde.io.java.drivers.ForSyDeModelHandler;
import generator.Generator;
import generator.InitProcessingModule;
import generator.SDFChannelProcessingModule;
import generator.SDFCombProcessingModule;
import generator.SubsystemUniprocessorModule;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.InputOutput;
import template.uniprocessor.SDFChannel.SDFChannelTemplateSrc;
import template.uniprocessor.actor.SDFActorInc;
import template.uniprocessor.actor.SDFActorSrc;
import template.uniprocessor.fifo.CircularFIFOTemplateInc1;
import template.uniprocessor.fifo.CircularFIFOTemplateSrc1;
import template.uniprocessor.fifo.SpinLockTemplateInc;
import template.uniprocessor.fifo.SpinLockTemplateSrc;
import template.uniprocessor.others.Config;
import template.uniprocessor.others.DataTypeInc;
import template.uniprocessor.others.DataTypeSrc;
import template.uniprocessor.subsystem.SubsystemTemplateInc;
import template.uniprocessor.subsystem.SubsystemTemplateSrc;

/**
 * one core
 */
@SuppressWarnings("all")
public class demo1 {
  public static void main(final String[] args) {
    try {
      final String path = "forsyde-io/modified1/complete-mapped-sobel-model.forsyde.xmi";
      final String path2 = "forsyde-io/modified1/sobel-application.fiodl";
      final String root = "generateCode/c/single/single";
      ForSyDeModelHandler loader = new ForSyDeModelHandler();
      ForSyDeSystemGraph model = loader.loadModel(path);
      model.mergeInPlace(loader.loadModel(path2));
      Generator gen = new Generator(model, root);
      SDFChannelProcessingModule sdfchannelModule = new SDFChannelProcessingModule();
      SDFChannelTemplateSrc _sDFChannelTemplateSrc = new SDFChannelTemplateSrc();
      sdfchannelModule.add(_sDFChannelTemplateSrc);
      gen.add(sdfchannelModule);
      SDFCombProcessingModule actorModule = new SDFCombProcessingModule();
      SDFActorSrc _sDFActorSrc = new SDFActorSrc();
      actorModule.add(_sDFActorSrc);
      SDFActorInc _sDFActorInc = new SDFActorInc();
      actorModule.add(_sDFActorInc);
      gen.add(actorModule);
      SubsystemUniprocessorModule subsystem = new SubsystemUniprocessorModule();
      SubsystemTemplateSrc _subsystemTemplateSrc = new SubsystemTemplateSrc();
      subsystem.add(_subsystemTemplateSrc);
      SubsystemTemplateInc _subsystemTemplateInc = new SubsystemTemplateInc();
      subsystem.add(_subsystemTemplateInc);
      gen.add(subsystem);
      InitProcessingModule initModule = new InitProcessingModule();
      DataTypeInc _dataTypeInc = new DataTypeInc();
      initModule.add(_dataTypeInc);
      DataTypeSrc _dataTypeSrc = new DataTypeSrc();
      initModule.add(_dataTypeSrc);
      CircularFIFOTemplateInc1 _circularFIFOTemplateInc1 = new CircularFIFOTemplateInc1();
      initModule.add(_circularFIFOTemplateInc1);
      CircularFIFOTemplateSrc1 _circularFIFOTemplateSrc1 = new CircularFIFOTemplateSrc1();
      initModule.add(_circularFIFOTemplateSrc1);
      SpinLockTemplateInc _spinLockTemplateInc = new SpinLockTemplateInc();
      initModule.add(_spinLockTemplateInc);
      SpinLockTemplateSrc _spinLockTemplateSrc = new SpinLockTemplateSrc();
      initModule.add(_spinLockTemplateSrc);
      Config _config = new Config();
      initModule.add(_config);
      gen.add(initModule);
      gen.create();
      InputOutput.<String>println("end!");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public static void test(final String path) {
    try {
      String root = "generateCode/c/test1";
      ForSyDeModelHandler loader = new ForSyDeModelHandler();
      ForSyDeSystemGraph model = loader.loadModel(path);
      model.mergeInPlace(loader.loadModel(path));
      Generator gen = new Generator(model, root);
      SDFChannelProcessingModule sdfchannelModule = new SDFChannelProcessingModule();
      SDFChannelTemplateSrc _sDFChannelTemplateSrc = new SDFChannelTemplateSrc();
      sdfchannelModule.add(_sDFChannelTemplateSrc);
      gen.add(sdfchannelModule);
      SDFCombProcessingModule actorModule = new SDFCombProcessingModule();
      SDFActorSrc _sDFActorSrc = new SDFActorSrc();
      actorModule.add(_sDFActorSrc);
      SDFActorInc _sDFActorInc = new SDFActorInc();
      actorModule.add(_sDFActorInc);
      gen.add(actorModule);
      SubsystemUniprocessorModule subsystem = new SubsystemUniprocessorModule();
      SubsystemTemplateSrc _subsystemTemplateSrc = new SubsystemTemplateSrc();
      subsystem.add(_subsystemTemplateSrc);
      SubsystemTemplateInc _subsystemTemplateInc = new SubsystemTemplateInc();
      subsystem.add(_subsystemTemplateInc);
      gen.add(subsystem);
      InitProcessingModule initModule = new InitProcessingModule();
      DataTypeInc _dataTypeInc = new DataTypeInc();
      initModule.add(_dataTypeInc);
      DataTypeSrc _dataTypeSrc = new DataTypeSrc();
      initModule.add(_dataTypeSrc);
      CircularFIFOTemplateInc1 _circularFIFOTemplateInc1 = new CircularFIFOTemplateInc1();
      initModule.add(_circularFIFOTemplateInc1);
      CircularFIFOTemplateSrc1 _circularFIFOTemplateSrc1 = new CircularFIFOTemplateSrc1();
      initModule.add(_circularFIFOTemplateSrc1);
      SpinLockTemplateInc _spinLockTemplateInc = new SpinLockTemplateInc();
      initModule.add(_spinLockTemplateInc);
      SpinLockTemplateSrc _spinLockTemplateSrc = new SpinLockTemplateSrc();
      initModule.add(_spinLockTemplateSrc);
      Config _config = new Config();
      initModule.add(_config);
      gen.add(initModule);
      gen.create();
      InputOutput.<String>println("end!");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
