@ECHO OFF
echo Generating javadocs...
dir /s /b *.java >files.lst
javadoc -d Docs/ @files.lst
echo Finished
PAUSE
