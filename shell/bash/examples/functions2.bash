#!/bin/bash -u

<<COMMENT

An example that shows that variables in bash functions are global by default

COMMENT

function a {
	pid_by_name[6]=8
	FOO="bar"
}

function b {
	echo ${pid_by_name[6]}
	echo $FOO
}

a
b
