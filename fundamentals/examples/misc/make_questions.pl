#!/usr/bin/perl -w

use strict;
use diagnostics;

for(my($i)=0;$i<10000000;$i++) {
	print $i."+".$i."\n";
}
