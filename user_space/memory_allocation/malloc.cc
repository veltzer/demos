#include <stdio.h>  // for printf(3)
#include <stdlib.h> // for malloc(3)
#include <assert.h> // for assert(3)

/*
 *      This application shows that every malloc
 *      requires 9 bytes+ the size that you need
 *      and exposes some of the layout of the memory...
 *
 *              Mark Veltzer
 *
 * EXTRA_LIBS=
 *
 * TODO:
 * 	- show how to extract the size of block that you allocated from the extra
 * 	data that the malloc library saves.
 */

char* do_alloc_and_fill(unsigned int size, char fill) {
	printf("allocating %d...\n",size);
	char *p = (char*)malloc(size * sizeof(char));

	// lets set the entire allocated space to some constant value...
	for (unsigned int y = 0; y < size; y++) {
		p[y] = fill;
	}
	return p;
}
void do_debug(char* p,unsigned int size, unsigned int before_offset,unsigned int after_offset,char fill) {
	if(p!=NULL) {
		// print the entire memory area surrounding p...
		printf("now search for the %c (%d)\n",fill,(int)fill);
		printf("guess value is %u\n", 9 + size);
		bool before=false;
		unsigned int count=0;
		// the bofore loop
		//int* pint=(int*)p;
		for (int y = (int)-before_offset; y < (int)(size + after_offset); y++) {
			char val=p[y];
			if(val!=fill) {
				if(before) {
					printf("got consecutive %d of data of the right fill (%c)\n",count,fill);
					before=false;
					count=0;
				}
				printf("data at %d is %d\n", y,p[y]);
			} else {
				before=true;
				count++;
			}
		}
	}
}


int main(int argc, char **argv, char **envp) {
	char* p1=NULL;
	char* p2=NULL;
	unsigned int p1size=0;
	unsigned int p2size=0;
	unsigned int p1fill=0;
	unsigned int p2fill=0;
	const unsigned int before_offset=5;
	const unsigned int after_offset=5;
	//for (unsigned int i = 20; i < 1000; i += 100) {
	for (unsigned int i = 0; i < 10; i++) {
		//const unsigned int size_to_alloc=20;
		unsigned int size_to_alloc=20+i*100;
		p2fill=97+i;
		p2size=size_to_alloc;
		p2=do_alloc_and_fill(p2size, p2fill);
		if(p1!=NULL) {
			unsigned int extra_space=p2-p1-p1size;
			// assert that there are indeed 4 extra bytes
			assert(extra_space==4 || extra_space==8);
			printf("extra_space is %u\n",extra_space);
		}

		do_debug(p2, p2size, before_offset,after_offset, p2fill);
		//do_debug(p1, p1size, before_offset,after_offset, p1fill);

		// swap the data
		p1=p2;
		p1size=p2size;
		p1fill=p2fill;
	}
	return(0);
}
