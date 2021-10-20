@ECHO OFF
echo Generating javadocs...
dir /s /b *.java >files.lst
javadoc -d Docs/ -html5 @files.lst
echo Finished
PAUSE