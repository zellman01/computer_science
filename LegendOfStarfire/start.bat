@Echo OFF
echo Compiling classes

echo Compiling StatName.java
javac stat/StatName.java

echo Compiling AttackState.java
javac attack/AttackState.java

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

echo Compiling AttackPercent.java
javac attack/AttackPercent.java

echo Compiling Character.java
javac character/Character.java

echo Compiling Item.java
javac item/Item.java

echo Compiling Potion.java
javac item/Potion.java

echo Compiling HealthPotion.java
javac item/HealthPotion.java

echo Compiling Attack.java
javac attack/Attack.java

echo Compiling CommonAttack.java
javac attack/CommonAttack.java

echo Compiling NPC.java
javac character/NPC.java

echo Compiling Raguel.java
javac character/bosses/Raguel.java

echo Compiling Slime.java
javac character/enemies/Slime.java

echo Compiling Bat.java
javac character/enemies/Bat.java

echo Compiling PC.java
javac character/PC.java

echo Compiling Main.java
javac main/Main.java

echo Running program
java main/Main

PAUSE
