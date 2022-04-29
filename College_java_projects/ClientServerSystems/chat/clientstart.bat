@ECHO Off

javac shared/Talker.java

javac client/MessageHandler.java
javac client/Client.java
javac client/MenuInterface.java
javac client/ChatWindow.java
javac client/ServerMessageHandler.java
javac client/Login.java
javac client/MainMenu.java
javac client/ClientMain.java

java client/ClientMain

PAUSE