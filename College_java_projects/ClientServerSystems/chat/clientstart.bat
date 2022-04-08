@ECHO Off

javac shared/Talker.java

javac client/MessageHandler.java
javac client/ServerMessageHandler.java
javac client/Login.java
javac client/ClientMain.java

java client/ClientMain

PAUSE