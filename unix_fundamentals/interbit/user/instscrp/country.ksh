#!/bin/ksh
grep -i $1 country.txt | cut -d, -f$2,$3,$4,$5,$6,$7  
