package demo
import forsyde.io.java.drivers.ForSyDeFiodlHandler


import generator.Generator
import generator.InitProcessingModule
import generator.SDFChannelProcessingModule

import template.baremetal.CircularFIFOTemplateInc
import template.baremetal.CircularFIFOTemplateSrc
import template.baremetal.DataTypeTemplateInc
import template.baremetal.SDFChannelTemplateSrc
import template.baremetal.SDFCombTemplateInc
import template.baremetal.SDFCombTemplateSrc
import template.baremetal.SpinLockTemplateInc
import template.baremetal.SpinLockTemplateSrc
import template.baremetal.uniprocessor.SubsystemTemplateInc
import template.baremetal.uniprocessor.SubsystemTemplateInc2
import template.baremetal.uniprocessor.SubsystemTemplateSrc
import utils.Load
import generator.SDFCombProcessingModule
import generator.SubsystemUniprocessorModule
import forsyde.io.java.core.ForSyDeSystemGraph
import forsyde.io.java.core.Vertex
import forsyde.io.java.core.VertexProperty
import java.util.Map
import java.util.HashMap

class test3 {
	def static void main(String[] args){
		val path="forsyde-io\\modified2\\complete-mapped-sobel-model.forsyde.xmi";
		val path2="forsyde-io\\modified2\\sobel-application.fiodl"


		//var model = (new ForSyDeModelHandler()).loadModel(path)
		
		var model1 = Load.load(path)
		var model2 = Load.load(path2);	
		

		
		
		println("end!")		
	}
}