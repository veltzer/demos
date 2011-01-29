#!/bin/bash -u

function a {
	pid_by_name[6]=8
}

function b {
	echo ${pid_by_name[6]}
}

a
b
