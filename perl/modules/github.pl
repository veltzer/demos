#!/usr/bin/perl -w

=head

Example of how to use a third party module, in this case the GitHub module.

=cut

use Net::GitHub;
use Config::IniFiles;
use File::HomeDir;

my($cfg)=Config::IniFiles->new( -file => File::HomeDir->my_home.'/.githubrc' ) or die('unable to access ini file');
$param_login=$cfg->val('github', 'login');
$param_pass=$cfg->val('github', 'pass');

my $github=Net::GitHub->new(
	login => $param_login,
	pass => $param_pass,
);
my(@repos)=$github->repos->list;
for(my($i)=0; $i<$#repos; $i++) {
	my($curr)=$repos[$i];
	print $curr->{name}.': '.$curr->{description}."\n";
}
