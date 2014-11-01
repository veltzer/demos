#!/bin/bash

<<COMMENT

This is an example of how to add to a PATH like variable. If the variable is empty
then this method just puts a value. If the value is already set then we catenate
to the existing variable possible with a delimiter. 

COMMENT

PATH=iaddedit${PATH:+:$PATH}
echo PATH is $PATH
DOESNTEXIST=iaddedit${DOESNTEXIST:+:$DOESNTEXIST}
echo DOESNTEXIST is $DOESNTEXIST
