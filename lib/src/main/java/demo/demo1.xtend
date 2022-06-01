package demo


import forsyde.io.java.drivers.ForSyDeModelHandler



import generator.Generator
import generator.InitProcessingModule
import generator.SDFChannelProcessingModule
import generator.SDFCombProcessingModule
import generator.SubsystemUniprocessorModule
import template.uniprocessor.SDFChannel.SDFChannelTemplateSrc
import template.uniprocessor.actor.SDFActorSrc
import template.uniprocessor.actor.SDFActorInc
import template.uniprocessor.subsystem.SubsystemTemplateSrc
import template.uniprocessor.subsystem.SubsystemTemplateInc
import template.uniprocessor.others.DataTypeInc
import template.uniprocessor.others.DataTypeSrc
import template.uniprocessor.fifo.SpinLockTemplateInc
import template.uniprocessor.fifo.SpinLockTemplateSrc
import template.uniprocessor.others.Config
import template.uniprocessor.fifo.CircularFIFOTemplateSrc1
import template.uniprocessor.fifo.CircularFIFOTemplateInc1

/**
 * one core
 */
class demo1 {
	def static void main(String[] args) {
		val path = "forsyde-io/modified1/complete-mapped-sobel-model.forsyde.xmi";
		val path2 = "forsyde-io/modified1/sobel-application.fiodl"
		val root = "generateCode/c/single/single"

		var loader = (new ForSyDeModelHandler)
		var model = loader.loadModel(path)
		model.mergeInPlace(loader.loadModel(path2))
		
		
//		val path = "a.forsyde.xmi"
//		val root = "generateCode/c/single2"
//		var loader = (new ForSyDeModelHandler)
//		var model = loader.loadModel(path)		
		
		
		var Generator gen = new Generator(model, root)

		var sdfchannelModule = new SDFChannelProcessingModule
		sdfchannelModule.add(new SDFChannelTemplateSrc)
		gen.add(sdfchannelModule)

		var actorModule = new SDFCombProcessingModule
		actorModule.add(new SDFActorSrc)
		actorModule.add(new SDFActorInc)
		gen.add(actorModule)

		var subsystem = new SubsystemUniprocessorModule
		subsystem.add(new SubsystemTemplateSrc)
		
		subsystem.add(new SubsystemTemplateInc)
		gen.add(subsystem)

		var initModule = new InitProcessingModule
		initModule.add(new DataTypeInc)
		initModule.add(new DataTypeSrc)

		initModule.add(new CircularFIFOTemplateInc1)
		initModule.add(new CircularFIFOTemplateSrc1)
		initModule.add(new SpinLockTemplateInc)
		initModule.add(new SpinLockTemplateSrc)
		initModule.add(new Config)
		
		//initModule.add(new SubsystemInitInc)
		//initModule.add(new SubsystemInitSrc)
		
		gen.add(initModule)

		gen.create()

		println("end!")
	}
	
	def static void test(String path){
		
		var root = "generateCode/c/test1"
		var loader = (new ForSyDeModelHandler)
		var model = loader.loadModel(path)
		
		
		
		model.mergeInPlace(loader.loadModel(path))
		var Generator gen = new Generator(model, root)

		var sdfchannelModule = new SDFChannelProcessingModule
		sdfchannelModule.add(new SDFChannelTemplateSrc)
		gen.add(sdfchannelModule)

		var actorModule = new SDFCombProcessingModule
		actorModule.add(new SDFActorSrc)
		actorModule.add(new SDFActorInc)
		gen.add(actorModule)

		var subsystem = new SubsystemUniprocessorModule
		subsystem.add(new SubsystemTemplateSrc)
		
		subsystem.add(new SubsystemTemplateInc)
		gen.add(subsystem)

		var initModule = new InitProcessingModule
		initModule.add(new DataTypeInc)
		initModule.add(new DataTypeSrc)

		initModule.add(new CircularFIFOTemplateInc1)
		initModule.add(new CircularFIFOTemplateSrc1)
		initModule.add(new SpinLockTemplateInc)
		initModule.add(new SpinLockTemplateSrc)
		initModule.add(new Config)
		
		//initModule.add(new SubsystemInitInc)
		//initModule.add(new SubsystemInitSrc)
		
		gen.add(initModule)

		gen.create()

		println("end!")		
	}
}
