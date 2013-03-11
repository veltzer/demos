#!/bin/sh
cd bin
# both of the next lines work but the second is better (no agent!)
#java -javaagent:../lib/aspectjweaver.jar -classpath .:../lib/aspectjrt.jar org.meta.aspectj.Main
java -classpath .:../lib/aspectjrt.jar org.meta.aspectj.Main
