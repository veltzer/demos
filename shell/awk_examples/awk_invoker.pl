#!/usr/bin/perl -w

use strict;
use diagnostics;

my(@arr)=split(",",$ARGV[0]);
push(@arr,"awk");
push(@arr,"-f");
push(@arr,$0);
print join(" ",["awk","-f",$0@arr
#system(join(" ",@arr));
