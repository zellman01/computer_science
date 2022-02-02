@echo off
javac Param.java
javac Pages.java
javac HTMLParser.java
javac ProcessHTML.java
java ProcessHTML
del *.class
PAUSE