#include "../inc/datatype_definition.h"
inline void actor_Abs(){
	//initilize the memory
	UInt16  offsetX;
	Double  gx;
	Double  gy;
	UInt16  offsetY;
	  dims;
	ArrayXOfArrayXOfDoubleType  system_img_sink_address;
	
	//read from the input port
	read_nonblocking(offsetXIn_channel);
	read_nonblocking(offsetYIn_channel);
	read_nonblocking(resy_channel);
	read_nonblocking(resx_channel);
	for(int i=0;i<2;++i){
		read_nonblocking(dimsIn_channel);
	}
	//inline code
	//in combFunction AbsImpl
	if(gx<0.0)gx=-gx;
	if(gy<0.0)gy=-gy;
	if(offsetX>=dims[0]-2){offsetY+=1;
	offsetX=0;
	}if(offsetY>=dims[1]-2){offsetY=0;
	}system_img_sink_address[offsetX][offsetY]=gx+gy;

	//write to the output port
	write(offsetYOut_channel);
	write(offsetXOut_channel);
}
