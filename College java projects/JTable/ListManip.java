//import java.awt.*;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.dnd.*;
import java.awt.datatransfer.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.lang.*;
import java.io.*;
import java.util.*;
import java.text.*;

public class ListManip extends JFrame implements ActionListener, ListSelectionListener, MouseListener, DropTargetListener, WindowListener, DirtyData {
	private ProjectTableModel ptm;
	private JTable table;
	private TableRowSorter<ProjectTableModel> trs;
	private JFileChooser files;
	private File chosenFile;
	private JButton load;
	private JButton save;
	private JButton saveAs;
	private JButton add;
	private JButton edit;
	private JButton delete;
	private JButton exit;
	private JPopupMenu popupMenu;
	private DropTarget dropTarget;
	private boolean dirty;
	
	public ListManip() {
		JScrollPane scrollBar; //West
		JPanel textPanel; // CenterLayout
		JPanel buttonPanel; // South
		
		files = new JFileChooser(".");
		dirty = false;
		
		ptm = new ProjectTableModel(this);
		table = new JTable(ptm);
		table.setFont(new Font("Courier New", Font.BOLD, 14));
		trs = new TableRowSorter<ProjectTableModel>(ptm);
		table.setRowSorter(trs);
		
		table.setMinimumSize(new Dimension(400, 200));
		
		table.setColumnModel(getColumnModel());
		
		table.getSelectionModel().addListSelectionListener(this);
		table.addMouseListener(this);
		
		ptm.addTableModelListener(table);
		
		scrollBar = new JScrollPane(table);
		
		load = createButton("Load", "LOAD", this, "Load a new file into the list.");
		save = createButton("Save", "SAVE", this, "Save the contents of the list to a file.");
		saveAs = createButton("Save As", "SAVEAS", this, "Saves the contents of the list to a new file.");
		add = createButton("Add", "ADD", this, "Adds an item to the displayed list");
		edit = createButton("Edit", "EDIT", this, "Allows a selected item to be edited", false);
		delete = createButton("Delete", "DELETE", this, "Deletes a selected item", false);
		exit = createButton("Exit", "EXIT", this, "Terminates the program.");
		
		popupMenu = newPopupMenu();
		
		dropTarget = new DropTarget(scrollBar, this);
		
		buttonPanel = new JPanel();
		buttonPanel.add(load);
		buttonPanel.add(save);
		buttonPanel.add(saveAs);
		buttonPanel.add(add);
		buttonPanel.add(edit);
		buttonPanel.add(delete);
		buttonPanel.add(exit);
		
		add(buttonPanel, BorderLayout.SOUTH);
		add(scrollBar);
		
		addWindowListener(this);
		
		setJMenuBar(menus());
		start();
	}
	
	private JPopupMenu newPopupMenu() {
		JMenuItem menu;
		JPopupMenu tmp = new JPopupMenu();
		
		menu = makeItem("Edit");
		tmp.add(menu);
		
		menu = makeItem("Mark Completed");
		tmp.add(menu);
		
		menu = makeItem("Delete");
		tmp.add(menu);
		
		return tmp;
	}
	
	private TableColumnModel getColumnModel() {
		DefaultTableColumnModel colModel;
		
		colModel = new DefaultTableColumnModel();
		String names[] = {"Name", "Description", "Department", "Rate", "Date Started", "Date Completed"};
		int pWidth[] = {50, 50, 50, 10, 30, 30};
		for (int i = 0; i < 6; i++) {
			colModel.addColumn(createTableColumn(i, pWidth[i], pWidth[i], names[i]));
		}
		
		return colModel;
		
	}
	
	private TableColumn createTableColumn(int index, int preferredWidth, int minWidth, String name) {
		TableColumn c = new TableColumn(index);
		c.setPreferredWidth(preferredWidth);
		c.setMinWidth(minWidth);
		c.setHeaderValue(name);
		return c;
	}
	
	private JButton createButton(String label, String cmd, ActionListener listener, String toolTip) {
		JButton b = new JButton(label);
		b.setActionCommand(cmd);
		b.addActionListener(listener);
		b.setToolTipText(toolTip);
		
		return b;
	}
	
	private JButton createButton(String label, String cmd, ActionListener listener, String toolTip, boolean enabled) {
		JButton b = createButton(label, cmd, listener, toolTip);
		b.setEnabled(enabled);
		return b;
	}
	
	private void start() {
		Toolkit tk;
		Dimension d;
		
		setTitle("JList Project");
		tk = Toolkit.getDefaultToolkit();
		d = tk.getScreenSize();
		setSize(d.width/2, d.height/2);
		setLocation(d.width/4, d.height/4);
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		setVisible(true);
	}
	
	private JMenuItem makeItem(String label, String actionCommand, ActionListener listener, int mnemonic, int keyCode, String toolTip) {
		JMenuItem m;
		
		m = new JMenuItem(label, mnemonic);
		m.setAccelerator(KeyStroke.getKeyStroke(keyCode, KeyEvent.ALT_DOWN_MASK));
		m.setToolTipText(toolTip);
		m.setActionCommand(actionCommand);
		m.addActionListener(listener);
		
		return m;
	}
	
	private JMenuItem makeItem(String label) {
		JMenuItem m;
		
		m = new JMenuItem(label);
		m.setActionCommand(label.toUpperCase());
		m.addActionListener(this);
		
		return m;
	}
	
	private JMenuBar menus() {
		JMenuBar menuBar;
		JMenu menu;
		
		menuBar = new JMenuBar();
		
		menu = new JMenu("File", true);
		menu.setMnemonic('F');
		
		menu.add(makeItem("Load", "LOAD", this, KeyEvent.VK_L, KeyEvent.VK_L, "Load a new file into the list."));
		menu.add(makeItem("Save", "SAVE", this, KeyEvent.VK_S, KeyEvent.VK_S, "Save the contents of the list to a file."));
		menu.add(makeItem("Save As", "SAVEAS", this, KeyEvent.VK_A, KeyEvent.VK_A, "Saves the contents of the list to a new file."));
		
		menuBar.add(menu);
		
		menu = new JMenu("Item");
		menu.setMnemonic('I');
		
		menu.add(makeItem("New", "DELETEALL", this, KeyEvent.VK_N, KeyEvent.VK_N, "Creates a new list"));
		menu.add(makeItem("Delete", "DELETE", this, KeyEvent.VK_D, KeyEvent.VK_D, "Deletes a selected item")); // Disabled, then re-enabled when there is something to delete
		menu.add(makeItem("Delete All", "DELETEALL", this, KeyEvent.VK_E, KeyEvent.VK_E, "Deletes everything in the list"));
		
		menuBar.add(menu);
		
		return menuBar;
	}
	
	private void saveObject() {
		try {
			DataOutputStream dos = new DataOutputStream(new FileOutputStream(chosenFile));
			ptm.saveElements(dos);
			dos.close();
			dirty = false;
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	private void loadObject() {
		if (files.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			if (!isDirty()) {
				chosenFile = files.getSelectedFile();
				try {
					DataInputStream dis = new DataInputStream(new FileInputStream(chosenFile));
					ptm.loadElements(dis);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private void loadObject(File file) {
		if (!isDirty()) {
			try {
				DataInputStream dis = new DataInputStream(new FileInputStream(file));
				ptm.loadElements(dis);
				chosenFile = file;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	// Returns true if the data is dirty, and if the user does not want to perform the action.
	private boolean isDirty() {
		if (!dirty) return false;
		else {
			//System.out.println(JOptionPane.showConfirmDialog(this, "Testing"));
			return JOptionPane.showConfirmDialog(this, "Unsaved changed may be lost.") > 0;
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("ADD")) {
			new WorkOrderGUI(ptm);
		}
		
		if (e.getActionCommand().equals("LOAD")) {
			// Load file in different function to get it to work with drop event
			loadObject();
		}
		
		if (e.getActionCommand().equals("SAVE")) {
			// Save the contents to a file
			try {
				if (chosenFile.exists()) {
					saveObject();
				} else {
					saveAs.doClick();
				}
			} catch(NullPointerException e1) {
				saveAs.doClick();
			}
		}
		
		if(e.getActionCommand().equals("MARK COMPLETED")) {
			WorkOrder wo = ptm.getDataAt(table.convertRowIndexToModel(table.getSelectedRow()));
			if (wo.getFinishedRequestDate() != 0) {
				// Warn user and allow to cancel
			}
			
			// Assumes that they pressed continue above (no else statement)
			wo.updateFinishedDate(new Date().getTime());
			dirty = true;
			ptm.fireTableDataChanged();
		}
		
		if (e.getActionCommand().equals("SAVEAS")) {
			// Save to a new file
			if (files.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
				chosenFile = files.getSelectedFile();
				saveObject();
			}
		}
		
		if (e.getActionCommand().equals("DELETE")) {
			// Delete a given index
			int[] selectedIndex = table.getSelectedRows();
			for (int i = selectedIndex.length-1; i >= 0; i--) {
				ptm.removeElementAt(table.convertRowIndexToModel(selectedIndex[i]));
			}
		}
		
		if (e.getActionCommand().equals("EDIT")) {
			int index = table.getSelectedRow();
			index = table.convertRowIndexToModel(index);
			WorkOrder wo = ptm.getDataAt(index);
			new WorkOrderGUI(ptm, wo, index);
		}
		
		if (e.getActionCommand().equals("DELETEALL")) {
			if (!isDirty()) {
				ptm.removeAllElements();
			}
		}
		
		if (e.getActionCommand().equals("EXIT")) {
			System.exit(0);
		}
	}
	
	public void valueChanged(ListSelectionEvent e) {
		delete.setEnabled(table.getSelectedRowCount()>0);
		edit.setEnabled(table.getSelectedRowCount()== 1);
	}
	
	public void mouseClicked(MouseEvent e) {
		showPopup(e);
	}
	
	public void mouseEntered(MouseEvent e) {
		
	}
	
	public void mouseExited(MouseEvent e) {
		
	}
	
	public void mousePressed(MouseEvent e) {
		if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
			edit.doClick();
		}
		showPopup(e);
	}
	
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON3) {
			int r = table.rowAtPoint(e.getPoint());
			if (r >= 0 && r < table.getRowCount()) {
				table.setRowSelectionInterval(r, r);
			} else {
				table.clearSelection();
			}
		}
		showPopup(e);
	}
	
	private void showPopup(MouseEvent e) {
		if(e.isPopupTrigger()) popupMenu.show(e.getComponent(), e.getX(), e.getY());
	}
	
	public void dragEnter(DropTargetDragEvent e) {
		
	}
	
	public void dragExit(DropTargetEvent e) {
		
	}
	
	public void dragOver(DropTargetDragEvent e) {
		
	}
	
	public void drop(DropTargetDropEvent e) {
		if (!isDirty()) {
			List<File> fileList;
			Transferable data;
		
			data = e.getTransferable();
		
			try {
				if (data.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
					e.acceptDrop(DnDConstants.ACTION_COPY);
					fileList = (List<File>)(data.getTransferData(DataFlavor.javaFileListFlavor));
					for (int i = 0; i < fileList.size(); i++) {
						loadObject(fileList.get(i));
					}
				}
			} catch (UnsupportedFlavorException e1) {
				System.out.println("Unsupported file flavor.");
				e1.printStackTrace();
			} catch (IOException e2) {
				System.out.println("IOException caught while getting transferable data.");
				e2.printStackTrace();
			}
		}
	}
	
	public void dropActionChanged(DropTargetDragEvent e) {
		
	}
	
	public void markDirty() {
		dirty = true;
	}
	
	public void windowActivated(WindowEvent e) {
		
	}
	
	public void windowClosed(WindowEvent e) {
		
	}
	
	public void windowClosing(WindowEvent e) {
		if (!isDirty()) System.exit(0);
	}
	
	public void windowDeactivated(WindowEvent e) {
		
	}
	
	public void windowDeiconified(WindowEvent e) {
		
	}
	
	public void windowIconified(WindowEvent e) {
		
	}
	
	public void windowOpened(WindowEvent e) {
		
	}
}