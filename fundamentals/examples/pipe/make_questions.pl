#!/usr/bin/perl

use strict;
use diagnostics;

for(my($i)=0;$i<100000000;$i++) {
	my($j)=$i+1;
	print($i."+".$j."\n");
}
