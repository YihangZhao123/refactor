#ifndef DATATYPE_DEFINITION_
#define DATATYPE_DEFINITION_
#include <stdio.h>
/*
==============================================================
		TYPING_DATATYPES_DOUBLE
==============================================================
*/
typedef double DoubleType;
typedef double Double;

/*
==============================================================
		TYPING_DATATYPES_FLOAT
==============================================================
*/

/*
==============================================================
		TYPING_DATATYPES_INTEGER
==============================================================
*/
typedef unsigned short UInt16;
typedef unsigned int UInt32;

/*
==============================================================
		TYPING_DATATYPES_ARRAY
==============================================================
*/
typedef Double Array1000OfDouble[1000];
typedef Array1000OfDouble Array1000OfArrayOfDouble[1000];
typedef DoubleType Array6OfDoubleType[6];
typedef UInt16 Array2OfUInt16[2];
typedef DoubleType *ArrayXOfDoubleType;
typedef ArrayXOfDoubleType *ArrayXOfArrayXOfDoubleType;

/*
==============================================================
		Outside Source and Sink Extern
==============================================================			
*/
extern UInt16  dimX;
extern ArrayXOfArrayXOfDoubleType  system_img_sink;
extern ArrayXOfArrayXOfDoubleType  system_img_source;
extern UInt16  dimY;
		
#endif
