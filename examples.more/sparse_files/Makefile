make_sparse: make_sparse.cc
	g++ -O3 -Wall -o make_sparse make_sparse.cc

clean:
	-rm -f make_sparse my.sparse

demo: make_sparse
	./make_sparse
	du -s my.sparse
	ls -l my.sparse
