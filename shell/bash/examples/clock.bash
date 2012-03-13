#!/bin/bash
let "x=180"; while [[ $x -gt 0 ]]; do echo -en $x "\r"; let "x=x-1" ; sleep 1; done
