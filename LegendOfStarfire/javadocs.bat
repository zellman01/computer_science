@ECHO OFF
echo Generating javadocs...
dir /s /b *.java >files.lst
javadoc -d Docs/ -html5 @files.lst
:: Private javadocs only for development. Remove after.
echo Finished
PAUSE
