#!/usr/bin/perl -w

use strict;
use diagnostics;

my($line);
my($sum)=0;
while($line=<>) {
	chop($line);
	$sum+=$line;
}
print $sum."\n";
