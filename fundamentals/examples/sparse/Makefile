mysparsefile: sparse
	./sparse

sparse: sparse.cc
	g++ -o $@ $<

show:
	ls -l mysparsefile
	du -s mysparsefile

clean:
	-rm -rf sparse mysparsefile
