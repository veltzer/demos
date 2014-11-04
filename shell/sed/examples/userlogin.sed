#!/bin/sed -f

# this script show only real users that can login to your system.
# run it so:
# userlogin.sed < /etc/passwd

/:\/bin\/false$/ {
        h
        d
}
$g

/:\/usr\/sbin\/nologin$/ {
        h
        d
}
$g
