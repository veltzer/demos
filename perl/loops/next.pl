#!/usr/bin/perl -w

=head

This is a demo of the next statement in perl.

=cut

use strict;
use diagnostics;

for(my($i)=0;$i<10;$i++) {
	if($i%3==1) {
		next;
	}
	print 'i is '.$i."\n";
}
