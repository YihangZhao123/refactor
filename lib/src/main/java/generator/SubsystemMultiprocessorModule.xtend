package generator

import java.util.Set

import template.templateInterface.SubsystemTemplate
import fileAnnotation.FileTypeAnno
import fileAnnotation.FileType
import utils.Save
import java.util.HashSet

class SubsystemMultiprocessorModule implements ModuleInterface {
	Set<SubsystemTemplate> templates

	new() {
		templates = new HashSet
	}

	override create() {
		Generator.multiProcessorSchedules.stream().forEach([schedule|process(schedule)])
	}

	def process(Schedule s) {
		val schedule = s
		templates.stream().forEach( [ t |

			var anno = t.getClass().getAnnotation(FileTypeAnno)

			if (anno.type() == FileType.C_INCLUDE) {
				Save.save(Generator.root + "/inc/subsystem_" + schedule.tile.getIdentifier() + ".h",
					t.create(schedule));
			}

			if (anno.type() == FileType.C_SOURCE) {
				Save.save(Generator.root + "/src/subsystem_" + schedule.tile.getIdentifier() + ".c", t.create(schedule))
			}

		])
	}

	def add(SubsystemTemplate t) {
		templates.add(t)
	}
}
