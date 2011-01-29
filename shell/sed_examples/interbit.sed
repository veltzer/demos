#!/bin/sed -f
/^interbit/ {
	h
	d
}
$g
