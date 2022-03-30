@ECHO off
echo Compiling classes

echo Compiling shared classes
javac shared/ConstVariables.java
javac shared/Talker.java

echo Done

echo Compiling server classes
javac server/Server.java
javac server/Handler.java
javac server/ServerStart.java

echo Finished

echo Attempting to run server program
java server/ServerStart

PAUSE