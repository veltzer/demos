#!/usr/bin/python

"""
This example shows how to call popen and get the return text.

	Mark Veltzer <mark@veltzer.net>
"""

"""
A function that runs a command in a shell, check that it succeeded and returns the output of that command
in case of success. In case of error it will throw an exception
"""
import subprocess
def system_check_output(cmd, input=None, cwd=None, env=None): 
	pipe=subprocess.Popen(cmd, shell=True, cwd=cwd, env=env, stdout=subprocess.PIPE, stderr=subprocess.STDOUT) 
	(output,errout)=pipe.communicate(input=input) 
	status=pipe.returncode 
	if status:
		raise ValueError('error in executing',cmd)
	return output

print system_check_output('ls -l | grep .')
print system_check_output('ll -l | grep .')
