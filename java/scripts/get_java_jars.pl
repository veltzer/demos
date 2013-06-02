#!/usr/bin/perl -w

use strict;
use diagnostics;
use File::Basename;

=head ABOUT

This script reads an Eclipse .classpath file, finds all lib entries
in it and outputs the classpath in various forms needed for various
tools.

=cut

=head TODO

- turn this into python.
- use a standard XML parser instead of cat/grep combo.

=cut

if(scalar(@ARGV)!=1) {
	die("usage: prog [base_jars|jnlp|jars|classpath]");
}

my($cmd)=$ARGV[0];
#my($str)=`cat .classpath | grep kind=\"lib\" | cut -d \\\" -f 4`;
my(@array)=`cat .classpath | grep kind=\\\"lib | cut -d \\\" -f 4`;

for(my($i)=0;$i<@array;$i++) {
	chop($array[$i]);
	if($cmd eq "base_jars") {
		my($name,$path,$suffix)=fileparse($array[$i],[".jar"]);
		$array[$i]=$name;
	}
	if($cmd eq "jnlp") {
		my($name,$path,$suffix)=fileparse($array[$i],[".jar"]);
		$array[$i]="<jar href=\\\"".$name."\\\"/>";
	}
	if($cmd eq "classpath") {
		# don't do anything
		#$array[$i]=$array[$i];
	}
}

if($cmd eq "jars" or $cmd eq "base_jars") {
	print join(" ",@array);
}
if($cmd eq "classpath") {
	print join(":",@array);
}
if($cmd eq "jnlp") {
	print join("\n",@array);
}
