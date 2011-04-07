#!/usr/bin/python

# a more efficient solution to the exercise since we are iterating items
# and getting BOTH the key AND the value immediately without the need to preform
# a second lookup...

def my_apply(f,seq):
	for x in seq:
		f(x)

orig = {"Israel":"Jerusalem","France":"Paris","Italy":"Rome","Egypt":"Cairo"}
target={}
u=map(lambda k: target.__setitem__(orig[k],k),orig)
# u is unused
print target
tg2={}
my_apply(lambda k: tg2.__setitem__(orig[k],k),orig)
print tg2
