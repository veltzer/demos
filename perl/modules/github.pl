#!/usr/bin/perl -w

use Net::GitHub;
use Net::GitHub::V2::Repositories;

my $github=Net::GitHub->new(
	login => 'veltzer',
	pass => '7PEpAqxvse',
);
my(@repos)=$github->repos->list;
for(my($i)=0;$i<$#repos;$i++) {
	my($curr)=$repos[$i];
	print $curr->{name}.": ".$curr->{description}."\n";
=head debug code
	while(my($key,$val)=each(%$curr)) {
		print 'key is '.$key."\n";
		if(defined($val)) {
			#print 'val is '.$val."\n";
		}
	}
=cut
}
