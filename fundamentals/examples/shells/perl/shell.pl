#!/usr/bin/perl -w

use strict;

sub show_prompt() {
	print "> ";
}

show_prompt();
my($line);
while($line=<>) {
	system($line);
	show_prompt();
}
