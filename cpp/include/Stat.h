#ifndef __Stat_h
#define __Stat_h

#include<stdio.h>
#include<math.h>
#include<values.h>

/*
	Statistics collecting object
*/

class Stat {
	private:
		// these are supplied by the user
		unsigned int binnum;
		double binsize;
		double mean;

		// these are managed by us
		unsigned int* bins;
		unsigned int bin_max;
		unsigned int bin_min;
		double minbin;
		double maxbin;
		double max;
		double min;
		double maxabs;
		double minabs;
		double ex;
		double ex2;
		unsigned int counter;

	public:
		Stat(unsigned int ibinnum,double ibinsize,double imean) {
			binnum=ibinnum;
			binsize=ibinsize;
			mean=imean;
			bins=new unsigned int[binnum]; 
			for(unsigned int i=0;i<binnum;i++) {
				bins[i]=0;
			}
			minbin=imean-ibinsize*double(binnum)/2.0;
			maxbin=imean+ibinsize*double(binnum)/2.0;
			bin_max=0;
			bin_min=0;
			max=MINDOUBLE;
			min=MAXDOUBLE;
			maxabs=MINDOUBLE;
			minabs=MAXDOUBLE;
			ex=0;
			ex2=0;
			counter=0;
		}
		~Stat(void) {
			delete bins;
		}

		void accept(double val) {
			// calculate the bin and add one to it
			if(val<minbin) {
				bin_min++;
			} else {
				if(val>maxbin) {
					bin_max++;
				} else {
					double distance=val-minbin;
					unsigned int bin=(unsigned int)(floor(distance/binsize));
					bins[bin]++;
				}
			}
			// update min,max,minabs,maxabs
			if(val>max) {
				max=val;
			}
			if(val<min) {
				min=val;
			}
			double valabs=fabs(val);
			if(valabs>maxabs) {
				maxabs=valabs;
			}
			if(valabs<minabs) {
				minabs=valabs;
			}
			// handle stats
			ex+=val;
			ex2+=val*val;
			counter++;
		}
		void print(void) {
			printf("counter is %u\n",counter);
			// print the bins
			printf("bin_min is %u\n",bin_min);
			double runner=minbin;
			for(unsigned int i=0;i<binnum;i++) {
				printf("bin[%u] (%lf,%lf) => %u\n",i,runner,runner+binsize,bins[i]);
				runner+=binsize;
			}
			printf("bin_max is %u\n",bin_max);
			printf("min is %lf\n",min);
			printf("max is %lf\n",max);
			printf("minabs is %lf\n",minabs);
			printf("maxabs is %lf\n",maxabs);
			ex/=counter;
			ex2/=counter;
			ex2-=ex;
			printf("ex is %lf\n",ex);
			printf("ex2 is %lf\n",ex2);
		}
		double getMaxAbs(void) {
			return maxabs;
		}
};

#endif // __Stat_h
