package server.gui;

import javax.swing.table.*;
import javax.swing.DefaultListModel;

public class CustomTable extends AbstractTableModel {
	private DefaultListModel<Handler> handlers;
	
	public CustomTable() {
		handlers = new DefaultListModel<Handler>();
	}
	
	public void addHandler(Handler h) {
		handlers.addElement(h);
		fireTableDataChanged();
	}
	
	public void removeHandler(Handler h) {
		if (handlers.contains(h)) {
			handlers.removeElement(h);
			fireTableDataChanged();
		}
	}
	
	public int getRowCount() { return handlers.getSize(); }
	
	public int getColumnCount() { return Handler.getFieldCount(); }
	
	@Override
	public Object getValueAt(int row, int col) {
		Handler h;
		h = getDataAt(row);
		
		switch (col) {
		case 0:
			return h.getAddress(); // Return IP address
		case 1:
			return h.getAuthorized(); // Return authorized status
		case 2:
			return h.getUserAuthorized(); // Return username authorized under
		}
		
		return null; // Incorrect column number
	}
	
	public Handler getDataAt(int index) {
		return (Handler)handlers.getElementAt(index);
	}
	
	public Class getColumnClass(int col) {
		switch (col) {
		case 0:
		case 2:
			return String.class;
		case 1:
			return Boolean.class;
		}
		return null;
	}
}