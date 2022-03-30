@Echo off

echo Compiling classes
javac SettingNotifier.java
javac Settings.java
javac StartProgram.java

echo Starting program
java StartProgram

PAUSE