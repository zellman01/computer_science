import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.lang.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.text.*;
import javax.swing.table.*;

public class TableModel extends AbstractTableModel implements Manager {
	private DefaultListModel<Moves> moveList;
	
	public TableModel() {
		moveList = new DefaultListModel<Moves>();
	}
	
	public void addElement(Moves m) {
		moveList.addElement(m);
		fireTableDataChanged();
	}
	
	public void removeElementAt(int index) {
		moveList.removeElementAt(index);
		fireTableDataChanged();
	}
	
	public void removeAllElements() {
		moveList.clear();
		fireTableDataChanged();
	}
	
	public Moves getDataAt(int index) {
		return (Moves)moveList.getElementAt(index);
	}
	
	public int getRowCount() { return moveList.getSize(); }
	
	public int getColumnCount() { return Moves.getFieldCount(); }
	
	@Override
	public Object getValueAt(int row, int col) {
		Moves m;
		m = getDataAt(row);
		
		switch (col) {
		case 0:
			return m.getType();
		case 1:
			return m.getName();
		case 2:
			return m.getAccuracy();
		case 3:
			return m.getCritical();
		case 4:
			return m.getDamagePool();
		case 5:
			return (Integer.parseInt(m.getAccuracyMod()) * 3) + "";
		case 6:
			return m.getEffect();
		}
		return null;
	}
	
	public Class getColumnClass(int col) {
		switch (col) {
		case 0:
		case 1:
		case 4:
		case 5:
		case 6:
			return String.class;
		case 2:
		case 3:
			return Integer.class;
		}
		return null;
	}
	
	public void addMoves(Moves m) {
		addElement(m);
	}
	
	public void editMoves(Moves m, int pos) {
		// Allow a move to be edited
	}
	
	public void loadElements(DataInputStream dis) throws IOException {
		int objectAmount = dis.readInt();
		for (int i = 0; i < objectAmount; i++) {
			moveList.addElement(new Moves(dis));
		}
		fireTableDataChanged();
	}
	
	public void saveElements(DataOutputStream dos) throws IOException {
		Object allElements[] = moveList.toArray();
		int arrayLength = allElements.length;
		dos.writeInt(arrayLength);
		for (int i = 0; i < arrayLength; i++) {
			Moves temp = (Moves)(allElements[i]);
			temp.store(dos);
		}
		dos.flush();
	}
}
