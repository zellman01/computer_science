@Echo off
echo Compiling classes
echo Compiling RateVerifier
javac RateVerifier.java
echo Compiling DateVerifier
javac DateVerifier.java
echo Compiling Manager
javac Manager.java
echo Compiling WorkOrder
javac WorkOrder.java
echo Compiling ProjectTableModel
javac ProjectTableModel.java
echo Compiling WorkOrderGUI
javac WorkOrderGUI.java
echo Compiling ListManip
javac ListManip.java
echo Compiling ListManipulationProject
javac ListManipulationProject.java
echo Launching ListManipulationProject
java ListManipulationProject
PAUSE