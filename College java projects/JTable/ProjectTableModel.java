import javax.swing.table.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.lang.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.text.*;
import java.time.*;

class ProjectTableModel extends AbstractTableModel implements Manager {
	DefaultListModel<WorkOrder> list;
	DirtyData dd;
	
	ProjectTableModel(DirtyData dd) {
		list = new DefaultListModel<WorkOrder>();
		this.dd = dd;
	}
	
	public void addElement(WorkOrder wo) {
		list.addElement(wo);
		dd.markDirty();
		fireTableDataChanged();
	}
	
	// Delete a single element and update the table
	public void removeElementAt(int index) {
		list.removeElementAt(index);
		dd.markDirty();
		fireTableDataChanged();
	}
	
	public void removeAllElements() {
		list.clear();
		fireTableDataChanged();
	}
	
	public WorkOrder getDataAt(int index) {
		return (WorkOrder)list.getElementAt(index);
	}
	
	public int getRowCount() { return list.getSize(); }
	
	public int getColumnCount() { return WorkOrder.getFieldCount(); }
	
	@Override
	public Object getValueAt(int row, int col) {
		WorkOrder wo;
		wo = getDataAt(row);
		
		switch (col) {
		case 0:
			return wo.getName();
		case 1:
			return wo.getDescription();
		case 2:
			return wo.getDepartment();
		case 3:
			return wo.getBillingRate();
		case 4:
			return wo.getInitialRequestDate();
		case 5:
			return wo.getFinishedRequestDate();
		}
		
		return null; // Defualt case only
	}
	
	public Class getColumnClass(int col) {
		switch (col) {
			case 0:
			case 1:
			case 2:
				return String.class;
			case 3:
				return Double.class;
			case 4:
			case 5:
				return Date.class;
		}
		return null; // Default case
	}
	
	public void addWorkOrder(WorkOrder wo) {
		addElement(wo);
	}
	public void editWorkOrder(WorkOrder wo, int listPos) {
		list.setElementAt(wo, listPos);
		dd.markDirty();
		fireTableDataChanged();
	}
	
	public void loadElements(DataInputStream dis) throws IOException {
		int objectAmount = dis.readInt();
		for (int i = 0; i < objectAmount; i++) {
			list.addElement(new WorkOrder(dis)); // Should add in work orders from a file
		}
		fireTableDataChanged();
	}
	
	public void saveElements(DataOutputStream dos) throws IOException {
		Object allElements[] = list.toArray();
		int arrayLength = allElements.length;
		dos.writeInt(arrayLength);
		for (int i = 0; i < arrayLength; i++) {
			WorkOrder temp = (WorkOrder)(allElements[i]);
			temp.store(dos);
		}
		dos.flush();
	}
}