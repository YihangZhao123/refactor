#include "../inc/extern_datablock.h"
#include "FreeRTOS.h"
#include "semphr.h"
SemaphoreHandle_t datablock_sem_system_img_source_global;
SemaphoreHandle_t datablock_sem_system_img_sink_global;
SemaphoreHandle_t datablock_sem_dimX_global;
SemaphoreHandle_t datablock_sem_dimY_global;
SemaphoreHandle_t datablock_sem_outputImage;
SemaphoreHandle_t datablock_sem_inputImage;