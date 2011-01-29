#!/bin/sed -f
/:\/bin\/false *$/b
/:\/usr\/bin\/nologin *$/b
s/:[^:]\+$/:\/usr\/bin\/tcsh/
