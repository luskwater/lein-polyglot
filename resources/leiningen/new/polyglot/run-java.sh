#!/bin/sh
# Execute the main class of the Java code
LEINCP=`lein classpath`
lein javac
java -cp $LEINCP {{namespace}}.{{java-class-name}}
