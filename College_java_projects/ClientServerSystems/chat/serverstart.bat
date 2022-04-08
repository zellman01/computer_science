@ECHO off

javac shared/Talker.java

javac server/Server.java
javac server/User.java
javac server/UserList.java
javac server/ClientMEssageHandler.java
javac server/ServerMain.java

java server/ServerMain
PAUSE