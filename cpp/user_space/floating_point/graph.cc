#include<stdio.h> // for printf(3), snprintf(3)
#include<stdlib.h> // for atof(3), atoi(3)
#include<values.h> // for MAXINT, MININT, MAXFLOAT, MINFLOAT
#include<math.h> // for pow(3)

#include<Stat.h> // our own statistics gathering object

// this example explores the representational issues of the floating point
// system in the mathematical co-processor.
//
// On a 32 bit system try the number 16777216 as the limit of presentation of
// floating point...
// Up to this number the floating point system representat integers precisely
// using the mantisa. After this number it goes into using the exponent and therefor
// becomes un-exact.
//
// Conclusions:
// atof behaves differently from float=int.
//
// TODO:
// - this program currently prints the output in gnuplot format but does not plot it on
// the screen. Make it be self contained and call gnuplot correctly.
//
// 	Mark Veltzer

#define DO_FLOAT
//#define DO_DOUBLE
//#define DO_ATOF
//#define DO_PROGRESS
//#define DO_PRINT

int main(int argc,char** argv,char** envp) {
	#ifdef DO_PRINT
	// first lets print the limits
	printf("MAXINT is %d\n",MAXINT);
	printf("MININT is %d\n",MININT);
	printf("MAXFLOAT is %f\n",MAXFLOAT);
	printf("MINFLOAT is %f\n",MINFLOAT);
	printf("MAXDOUBLE is %lf\n",MAXDOUBLE);
	printf("MINDOUBLE is %lf\n",MINDOUBLE);
	#endif // DO_PRINT
	unsigned int num_bins=1000;
	//unsigned long min_range=(unsigned long)MININT;
	//unsigned long max_range=(unsigned long)MAXINT;
	long min_range=-powf(2,26);
	long max_range=+powf(2,26);
	int imin_range=min_range;
	int imax_range=max_range;
	unsigned long range=max_range-min_range;
	double dbin_size=(double)range/(double)num_bins;
	#ifdef DO_PRINT
	printf("num_bins is %u\n",num_bins);
	printf("min_range is %lu\n",min_range);
	printf("max_range is %lu\n",max_range);
	printf("range is %lu\n",range);
	printf("dbin_size is %lf\n",dbin_size);
	#endif // DO_PRINT
	#ifdef DO_PROGRESS
	unsigned long bin_size=dbin_size;
	unsigned int bin_counter=0;
	#endif // DO_PROGRESS
	// collectors - one for float and one for double
#ifdef DO_FLOAT
	Stat s_f(num_bins,dbin_size,0);
#endif // DO_FLOAT
#ifdef DO_DOUBLE
	Stat s_d(num_bins,dbin_size,0);
#endif // DO_DOUBLE
#ifdef DO_ATOF
	Stat s_atof(num_bins,dbin_size,0);
#endif // DO_ATOF
	for(int i=imin_range;i<imax_range;i++) {
		#ifdef DO_FLOAT
		// direct conversion of int to float
		// the 'volatile' is necessary in order that the compiler
		// will not optimize the next two assignments and will do away
		// with the intermediate float...
		volatile float f=i;
		int back_i_f=f;
		if(i!=back_i_f) {
			s_f.accept(i);
		}
		#endif // DO_FLOAT
		#ifdef DO_DOUBLE
		// same thing for double although double covers the range of int
		// pretty well...
		volatile double d=i;
		int back_i_d=d;
		if(i!=back_i_d) {
			s_d.accept(i);
		}
		#endif // DO_DOUBLE
		#ifdef DO_ATOF
		// now print i into a string buffer, atof it back to float and convert
		char string_of_i[128];
		snprintf(string_of_i,128,"%d",i);
		float f2=atof(string_of_i);
		int back_i_atof=f2;
		if(i!=back_i_atof) {
			s_atof.accept(i);
		}
		#endif // DO_ATOF
		#ifdef DO_PROGRESS
		// progress report...
		bin_counter++;
		if(bin_counter==bin_size) {
			printf("new bin %d\n",i);
			// reset counters
			bin_counter=0;
		}
		#endif // DO_PROGRESS
	}
	#ifdef DO_FLOAT
	s_f.print_gnuplot();
	#endif // DO_FLOAT
	#ifdef DO_DOUBLE
	s_d.print_gnuplot();
	#endif // DO_DOUBLE
	#ifdef DO_ATOF
	s_atof.print_gnuplot();
	#endif // DO_ATOF
	return 0;
}
