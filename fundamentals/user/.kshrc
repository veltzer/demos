#
# .kshrc
#
UNAME=`hostname`;export UNAME
ID=`id | grep root`
if [ "$ID" = "" ]                       # include command number in prompt
then
	PS1='$UNAME:$PWD {!} $ '
else
	PS1='$UNAME:$PWD {!} # '
fi
export PS1

#
# Emacs controls:
# ^P -previous command, ^N - next command, ^D - delete character
# ^B - cursor backward, ^F - cursor forward
#
#FCEDIT=emacs
#VISUAL=emacs

FCEDIT=vi
VISUAL=vi
export FCEDIT VISUAL

set -o vi
# Aliases
alias h=history
