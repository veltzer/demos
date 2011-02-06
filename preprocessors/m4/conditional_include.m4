this trick of only doing the include if the macro is used does not seem to work.
I need to think of another solution...

define(foobar,
include(/etc/passwd)
)
foobar
======================
foobar
