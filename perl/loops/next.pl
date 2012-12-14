#!/usr/bin/perl -w

use strict;
use diagnostics;

=head

This is a demo of the next statement in perl.

=cut

for(my($i)=0;$i<10;$i++) {
	if($i%3==1) {
		next;
	}
	print "i is $i\n";
}
