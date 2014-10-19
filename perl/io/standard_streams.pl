#!/usr/bin/perl -w

=head

This is a test script that outputs some data to standard output and some data to standard
error

=cut

use strict;
use diagnostics;

print STDOUT 'Hello Stdout!'."\n";
print STDERR 'Hello Stderr!'."\n";
exit(7);
