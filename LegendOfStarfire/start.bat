@Echo OFF
echo Compiling classes

echo Compiling StatName.java
javac stat/StatName.java

echo Compiling Stat.java
javac stat/Stat.java

echo Compiling StatFactory.java
javac stat/StatFactory.java

echo Compiling GameObject.java
javac inventory/GameObject.java

echo Compiling InventoryBlock.java
javac inventory/InventoryBlock.java

echo Compiling Inventory.java
javac inventory/Inventory.java

echo Compiling Equipment.java
javac equipment/Equipment.java

echo Compiling Headgear.java
javac equipment/Headgear.java

echo Compiling Breastplate.java
javac equipment/Breastplate.java

echo Compiling Weapon.java
javac equipment/Weapon.java

echo Compiling EquipmentPage.java
javac equipment/EquipmentPage.java

echo Compiling EquipmentManager.java
javac character/EquipmentManager.java

echo Compiling Character.java
javac character/Character.java

echo Compiling NPC.java
javac character/NPC.java

echo Compiling Slime.java
javac character/Enemies/Slime.java

echo Compiling PC.java
javac character/PC.java

echo Compiling Main.java
javac main/Main.java

echo Running program
java main/Main

PAUSE
