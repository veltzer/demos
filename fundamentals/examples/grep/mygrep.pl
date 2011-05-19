#!/usr/bin/perl

use strict;

my($expression)=$ARGV[0];
my($file)=$ARGV[1];

open(FILE,$file) || die("unable to open");
my($line);
while($line=<FILE>) {
	if($line=~/$expression/) {
		print($line);
	}
}
close(FILE) || die("unable to close");
