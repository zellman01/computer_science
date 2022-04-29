@Echo off

set classpath=activation-1.1.1.jar;mail.jar;

echo Compiling classes
javac SettingNotifier.java
javac Settings.java
javac StartProgram.java

echo Starting program
java StartProgram

PAUSE