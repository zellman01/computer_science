@Echo OFF
echo Compiling classes

echo Compiling StatName.java
javac Stat/StatName.java

echo Compiling Stat.java
javac Stat/Stat.java

echo Compiling StatFactory.java
javac Stat/StatFactory.java

echo Compiling Equipment.java
javac Equipment/Equipment.java

echo Compiling Headgear.java
javac Equipment/Headgear.java

echo Compiling Breastplate.java
javac Equipment/Breastplate.java

echo Compiling Weapon.java
javac Equipment/Weapon.java

echo Compiling EquipmentPage.java
javac Equipment/EquipmentPage.java

echo Compiling EquipmentManager.java
javac Character/EquipmentManager.java

echo Compiling Character.java
javac Character/Character.java

echo Compiling NPC.java
javac Character/NPC.java

echo Compiling PC.java
javac Character/PC.java

echo Compiling Main.java
javac Main/Main.java

echo Running program
java Main/Main

PAUSE