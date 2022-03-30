@ECHO off
echo Compiling classes

echo Compiling shared classes
javac shared/ConstVariables.java
javac shared/Talker.java

echo Done

echo Compiling client classes
javac client/Client.java
javac client/Handler.java
javac client/ClientStart.java

echo Finished

echo Attempting to run client program
java client/ClientStart

PAUSE