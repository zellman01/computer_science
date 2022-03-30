@ECHO off
javac shared/Talker.java
javac client/Client.java
javac client/ServerMessageHandler.java
javac client/ClientMain.java

java client/ClientMain

PAUSE