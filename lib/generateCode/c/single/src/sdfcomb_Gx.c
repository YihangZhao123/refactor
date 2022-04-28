#include "../inc/datatype_definition.h"
inline void actor_Gx(){
	//initilize the memory
	Double  gx;
	Array6OfDoubleType  imgBlockX;
	
	//read from the input port
	for(int i=0;i<6;++i){
		read_nonblocking(gx_channel);
	}
	//inline code
	//in combFunction GxImpl
	gx=gx-imgBlockX[0];
	gx=gx+imgBlockX[1];
	gx=gx-2.0*imgBlockX[2];
	gx=gx+2.0*imgBlockX[3];
	gx=gx-imgBlockX[4];
	gx=gx+imgBlockX[5];

	//write to the output port
	write(resx_channel);
}
