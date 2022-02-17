import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.lang.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.text.*;
import javax.swing.table.*;

public class Moves { // Needs to know how to store itself to a file
	private String name, effect, damagePool, type, accuracyMod;
	private int accuracy, critical;
	
	public Moves(String name, int accuracy, int critical, String effect, String damagePool, String type, String accuracyMod) {
		this.name = name;
		this.accuracy = accuracy;
		this.critical = critical;
		this.effect = effect;
		this.damagePool = damagePool;
		this.type = type;
		this.accuracyMod = accuracyMod;
	}
	
	public static Moves makeMove(String name, int accuracy, int critical, String effect, String damagePool, String type, String accuracyMod) {
		return new Moves(name, accuracy, critical, effect, damagePool, type, accuracyMod);
	}
	
	@Override
	public String toString() {
		return "Move name: " + name + " Accuracy: " + accuracy + " Critical: " + critical + "-" + accuracy + " Effect: " + effect + " Damage pool: " + damagePool;
	}
	
	public Moves(DataInputStream dis) throws IOException {
		type = dis.readUTF();
		name = dis.readUTF();
		accuracy = dis.readInt();
		critical = dis.readInt();
		effect = dis.readUTF();
		damagePool = dis.readUTF();
		accuracyMod = dis.readUTF();
	}
	
	public void store(DataOutputStream dos) throws IOException {
		dos.writeUTF(type);
		dos.writeUTF(name);
		dos.writeInt(accuracy);
		dos.writeInt(critical);
		dos.writeUTF(effect);
		dos.writeUTF(damagePool);
		dos.writeUTF(accuracyMod);
	}
	
	public static int getFieldCount() { return 7; }
	
	public String getType() { return type; }
	
	public String getName() { return name; }
	
	public int getAccuracy() { return accuracy; }
	
	public int getCritical() { return critical; }
	
	public String getDamagePool() { return damagePool; }
	
	public String getEffect() { return effect; }
	
	public String getAccuracyMod() { return accuracyMod; }
}