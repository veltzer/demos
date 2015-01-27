MYNAME=${BASH_SOURCE[0]}
MYDIR=$(dirname $MYNAME)
MYNAME_ABS=$(readlink -f $MYNAME)
MYDIR_ABS=$(readlink -f $MYDIR)
echo "I am [$MYNAME] and I am in [$MYDIR]" 
echo "I am [$MYNAME_ABS] and I am in [$MYDIR_ABS]" 
