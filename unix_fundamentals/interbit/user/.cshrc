# @(#)cshrc 1.11 89/11/29 SMI
umask 022
set path=(/opt/SUNWspro/bin /usr/ccs/bin /export/local/java/bin /usr/openwin/bin /usr/sbin /usr/local/bin /bin /usr/bin /usr/ucb /etc .)
set filec
alias cd 'cd \!*;set prompt="`uname -n`:$cwd > "'
cd $cwd
setenv OPENWINHOME /usr/openwin
setenv LD_LIBRARY_PATH /opt/SUNWspro/lib:$OPENWINHOME/lib:/usr/dt/lib:/usr/lib
setenv MANPATH /opt/SUNWspro/man:/usr/man:/usr/share/man:/usr/local/man:/usr/dt/man:/usr/openwin/man
if ( $?prompt ) then
	set history=32
endif
