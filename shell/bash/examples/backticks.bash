#!/bin/bash -u

<<COMMENT

This example tries to show how backticks work in the shell.
Note that this also deals with nested backticks

COMMENT

# very simple: execute a command and replace `[cmd]` with its output
echo `date`
# the command can be a shell command with redirection, variables, pipe and all
echo `date | cut -f 2 -d " "`
# another example with dirname(1)
echo `dirname a/b/c`
# what about nesting things? this does not work because the shell doesnt understand which
# back ticks go with which...
echo `dirname `dirname a/b/c``
# this is better
echo `dirname \`dirname a/b/c\``
