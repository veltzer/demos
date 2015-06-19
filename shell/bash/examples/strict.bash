#!/bin/bash -u

<<COMMENT

This shows how to make bash more strict
If you remove the "set -e" and "set +e" then the script
will continue even though the "false" command fails.

More thing to make bash more strict:
set -u / set +u
set -o pipefail
or in short:
set -euo pipefail

References:
http://redsymbol.net/articles/unofficial-bash-strict-mode/

COMMENT

echo before
set -e
false
set +e
echo after
