#include<stdio.h> // for printf(3)
#include<stdlib.h> // for malloc(3)

/*
	This application shows that every malloc
	requires 9 bytes+ the size that you need
	and exposes some of the layout of the memory...

		Mark Veltzer

EXTRA_LIBS=
*/

void do_debug(unsigned int size,unsigned int offset) {
	unsigned int* p=(unsigned int*)malloc(size*sizeof(unsigned int));
	for(unsigned int y=0;y<size;y++) {
		p[y]=253;
	}
	unsigned int* o=(unsigned int*)p;
	o-=offset;
	printf("guess value is %u\n",9+size*sizeof(unsigned int));
	for(unsigned int y=0;y<size+offset*2;y++) {
		printf("data is %u\n",*o);
		o++;
	}
}

int main(int argc,char** argv,char** envp) {
	for(unsigned int i=20;i<1000;i+=100) {
		do_debug(i,2);
	}
	return 0;
}
