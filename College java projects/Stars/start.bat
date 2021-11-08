@Echo OFF
echo Compiling classes
echo Compiling LifeEventListener
javac LifeEventListener.java

echo Compiling DrawPanelSize.java
javac DrawPanelSize.java

echo Compiling LivingObject
javac LivingObject.java

echo Compiling LivingSquare
javac LivingSquare.java

echo Compiling DrawPanel
javac DrawPanel.java

echo Compiling Primary
javac Primary.java


echo Starting project
java Primary

PAUSE