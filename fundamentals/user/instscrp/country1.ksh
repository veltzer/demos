#!/bin/ksh
echo "Welcome $LOGNAME"
echo "Please Enter Country Name:"
read COUNTRY
echo "Please Enter the Fields of Interest:"
read a b c d e f g h i
grep -i $COUNTRY country.txt | cut -d, -f$a,$b,$c,$d,$e,$f,$g,$h,$i

