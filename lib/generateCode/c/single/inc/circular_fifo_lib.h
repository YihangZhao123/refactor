#ifndef CIRCULAR_FIFO_LIB_H_
#define CIRCULAR_FIFO_LIB_H_
#include "datatype_definition.h"
#include "spinlock.h"

/*
=============================================================
				DoubleType Prototype
=============================================================
*/
typedef struct 
{
    DoubleType* buffer;
    size_t front;
    size_t rear;
size_t size;	    
}circular_fifo_DoubleType;

void init_channel_DoubleType(circular_fifo_DoubleType *channel ,DoubleType* buffer, size_t size);
int read_non_blocking_DoubleType(circular_fifo_DoubleType* channel,DoubleType* dst );
int read_blocking_DoubleType(circular_fifo_DoubleType* ptr,DoubleType* dst,spinlock *lock);
int write_non_blocking_DoubleType(circular_fifo_DoubleType* ptr,DoubleType* src );
int write_blocking_DoubleType(circular_fifo_DoubleType* ptr,DoubleType* src,spinlock *lock);	
	
/*
=============================================================
				Double Prototype
=============================================================
*/
typedef struct 
{
    Double* buffer;
    size_t front;
    size_t rear;
size_t size;	    
}circular_fifo_Double;

void init_channel_Double(circular_fifo_Double *channel ,Double* buffer, size_t size);
int read_non_blocking_Double(circular_fifo_Double* channel,Double* dst );
int read_blocking_Double(circular_fifo_Double* ptr,Double* dst,spinlock *lock);
int write_non_blocking_Double(circular_fifo_Double* ptr,Double* src );
int write_blocking_Double(circular_fifo_Double* ptr,Double* src,spinlock *lock);	
	
/*
=============================================================
				UInt16 Prototype
=============================================================
*/
typedef struct 
{
    UInt16* buffer;
    size_t front;
    size_t rear;
size_t size;	    
}circular_fifo_UInt16;

void init_channel_UInt16(circular_fifo_UInt16 *channel ,UInt16* buffer, size_t size);
int read_non_blocking_UInt16(circular_fifo_UInt16* channel,UInt16* dst );
int read_blocking_UInt16(circular_fifo_UInt16* ptr,UInt16* dst,spinlock *lock);
int write_non_blocking_UInt16(circular_fifo_UInt16* ptr,UInt16* src );
int write_blocking_UInt16(circular_fifo_UInt16* ptr,UInt16* src,spinlock *lock);	
	
/*
=============================================================
				UInt32 Prototype
=============================================================
*/
typedef struct 
{
    UInt32* buffer;
    size_t front;
    size_t rear;
size_t size;	    
}circular_fifo_UInt32;

void init_channel_UInt32(circular_fifo_UInt32 *channel ,UInt32* buffer, size_t size);
int read_non_blocking_UInt32(circular_fifo_UInt32* channel,UInt32* dst );
int read_blocking_UInt32(circular_fifo_UInt32* ptr,UInt32* dst,spinlock *lock);
int write_non_blocking_UInt32(circular_fifo_UInt32* ptr,UInt32* src );
int write_blocking_UInt32(circular_fifo_UInt32* ptr,UInt32* src,spinlock *lock);	
	
	/*
	=============================================================
					Array1000OfArrayOfDouble Prototype
	=============================================================
	*/
	typedef struct 
	{
	    Array1000OfArrayOfDouble* buffer;
	    size_t front;
	    size_t rear;
		size_t size;	    
	}circular_fifo_Array1000OfArrayOfDouble;
	
	void init_channel_Array1000OfArrayOfDouble(circular_fifo_Array1000OfArrayOfDouble *channel ,Array1000OfArrayOfDouble* buffer, size_t size);
	int read_non_blocking_Array1000OfArrayOfDouble(circular_fifo_Array1000OfArrayOfDouble* channel,Array1000OfArrayOfDouble* dst );
	int read_blocking_Array1000OfArrayOfDouble(circular_fifo_Array1000OfArrayOfDouble* ptr,Array1000OfArrayOfDouble* dst,spinlock *lock);
	int write_non_blocking_Array1000OfArrayOfDouble(circular_fifo_Array1000OfArrayOfDouble* ptr,Array1000OfArrayOfDouble* src );
	int write_blocking_Array1000OfArrayOfDouble(circular_fifo_Array1000OfArrayOfDouble* ptr,Array1000OfArrayOfDouble* src,spinlock *lock);				
	/*
	=============================================================
					Array6OfDoubleType Prototype
	=============================================================
	*/
	typedef struct 
	{
	    Array6OfDoubleType* buffer;
	    size_t front;
	    size_t rear;
		size_t size;	    
	}circular_fifo_Array6OfDoubleType;
	
	void init_channel_Array6OfDoubleType(circular_fifo_Array6OfDoubleType *channel ,Array6OfDoubleType* buffer, size_t size);
	int read_non_blocking_Array6OfDoubleType(circular_fifo_Array6OfDoubleType* channel,Array6OfDoubleType* dst );
	int read_blocking_Array6OfDoubleType(circular_fifo_Array6OfDoubleType* ptr,Array6OfDoubleType* dst,spinlock *lock);
	int write_non_blocking_Array6OfDoubleType(circular_fifo_Array6OfDoubleType* ptr,Array6OfDoubleType* src );
	int write_blocking_Array6OfDoubleType(circular_fifo_Array6OfDoubleType* ptr,Array6OfDoubleType* src,spinlock *lock);				
	/*
	=============================================================
					Array2OfUInt16 Prototype
	=============================================================
	*/
	typedef struct 
	{
	    Array2OfUInt16* buffer;
	    size_t front;
	    size_t rear;
		size_t size;	    
	}circular_fifo_Array2OfUInt16;
	
	void init_channel_Array2OfUInt16(circular_fifo_Array2OfUInt16 *channel ,Array2OfUInt16* buffer, size_t size);
	int read_non_blocking_Array2OfUInt16(circular_fifo_Array2OfUInt16* channel,Array2OfUInt16* dst );
	int read_blocking_Array2OfUInt16(circular_fifo_Array2OfUInt16* ptr,Array2OfUInt16* dst,spinlock *lock);
	int write_non_blocking_Array2OfUInt16(circular_fifo_Array2OfUInt16* ptr,Array2OfUInt16* src );
	int write_blocking_Array2OfUInt16(circular_fifo_Array2OfUInt16* ptr,Array2OfUInt16* src,spinlock *lock);				
	/*
	=============================================================
					Array1000OfDouble Prototype
	=============================================================
	*/
	typedef struct 
	{
	    Array1000OfDouble* buffer;
	    size_t front;
	    size_t rear;
		size_t size;	    
	}circular_fifo_Array1000OfDouble;
	
	void init_channel_Array1000OfDouble(circular_fifo_Array1000OfDouble *channel ,Array1000OfDouble* buffer, size_t size);
	int read_non_blocking_Array1000OfDouble(circular_fifo_Array1000OfDouble* channel,Array1000OfDouble* dst );
	int read_blocking_Array1000OfDouble(circular_fifo_Array1000OfDouble* ptr,Array1000OfDouble* dst,spinlock *lock);
	int write_non_blocking_Array1000OfDouble(circular_fifo_Array1000OfDouble* ptr,Array1000OfDouble* src );
	int write_blocking_Array1000OfDouble(circular_fifo_Array1000OfDouble* ptr,Array1000OfDouble* src,spinlock *lock);				
#endif
