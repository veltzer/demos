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

sub show_yes_no_dialog($) {
	my($question)=$_[0];
	my($stop)=0;
	my($result);
	while(!$stop) {
		print STDOUT $question;
		flush STDOUT;
		my($response);
		$response=<STDIN>;
		chomp($response);
		if($response ne 'y' and $response ne 'n') {
			next;
		}
		$stop=1;
		if($response eq 'y') {
			$result=1;
		} else {
			$result=0;
		}
	}
	return $result;
}

my($result)=show_yes_no_dialog('proceed(y/n)? ');
print 'result is ['.$result.']'."\n";
