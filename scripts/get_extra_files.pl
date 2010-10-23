#!/usr/bin/perl -w

# this script lists all extra files not known to git
# how does it do it?
# - list all files in the folder recursivly (File::Find).
# 	disregard the .git subfolder.
# - list all files which git knows about (git ls-files).
# - subtract the second from the first.

use strict;
use diagnostics;
use File::Find qw();

# do you want to debug the script ?!?
my($debug)=1;
my(%hash)=();

sub handler() {
	my($file)=$File::Find::name;
	if(!(-f $file)) {
		return;
	}
	if($file=~/^\.\/\.git\//) {
		return;
	}
	if($debug) {
		print 'saw file ['.$file.']'."\n";
	}
	$hash{$file}=defined;
}

# find all files in the folder...
File::Find::find({"no_chdir"=>1,"wanted"=>\&handler},".");
