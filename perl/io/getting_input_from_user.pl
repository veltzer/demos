#!/usr/bin/perl -w

=head

This script shows how to get input from the user...

Notes:
- you must declare the variable on another line than then <STDIN>
since otherwise it will attempt to read more than one line...
- the input includes the newline. chop/chomp it if you like.

=cut

use strict;
use diagnostics;

print STDOUT 'What is your name: ';
flush STDOUT;
my($name);
$name=<STDIN>;
chomp($name);
print 'Your name is ['.$name.']'."\n";
