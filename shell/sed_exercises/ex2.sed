#!/bin/sed -f
/:\/bin\/false *$/b
/:\/usr\/bin\/nologin *$/b
/^[^:]\+:[^:]\+:1[0-9]\{3\}:/s/:[^:]\+$/:\/bin\/tcsh/
