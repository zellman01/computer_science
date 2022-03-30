@ECHO off
javac shared/Talker.java
javac server/Server.java
javac server/ClientMessageHandler.java
javac server/ServerMain.java

java server/ServerMain

PAUSE