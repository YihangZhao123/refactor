package demo

import utils.Load

import generator.Generator
import generator.*
import template.baremetal.SDFCombTemplateSrc

class demo1 {
	def static void main(String[] args) {
		val path="forsyde-io\\complete-mapped-sobel-model.forsyde.xmi";
		val path2="forsyde-io\\sobel-application.fiodl"
		val root="generateCode\\c\\single"
		var model1 = Load.load(path)
		var model2 = Load.load(path2);	
		
		model1.mergeInPlace(model2)
		
		var Generator gen = new Generator(model1,root)
		
		var actorModule= new SDFCombProcessingModule
		actorModule.add(new SDFCombTemplateSrc)
		gen.add(actorModule)
		
		
//		gen.add(new InitModule)
//		
		gen.create()
		
		println("end!")		
	}
}