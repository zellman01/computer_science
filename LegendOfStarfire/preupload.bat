@ECHO off
echo Removing all class files
del /S *.class
echo Delete the Docs/ folder
rmdir Docs /S /Q
echo Delete the files.lst file
del files.lst
echo Finished
PAUSE
