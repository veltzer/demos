#!/usr/bin/perl -w

use Net::GitHub;
use Net::GitHub::V2::Repositories;

=head
my $github = Net::GitHub->new(
	owner => 'veltzer',
	repo => 'xmeltdown',
);
$github->repos->show();
=cut

my $repos = Net::GitHub::V2::Repositories->new(
	owner => 'veltzer',
	repo => 'nosuchrepo',
);
my $repositories = $repos->list();
for(my($i)=0;$i<$#$repositories;$i++) {
	my($curr)=$repositories->[$i];
	print $curr->{name}."\n";
}
