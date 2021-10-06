import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.lang.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.text.*;

public class ListManip extends JFrame implements ActionListener, ListSelectionListener, MouseListener, Manager {
	private JList<WorkOrder> list;
	private DefaultListModel<WorkOrder> listViewer;
	private JFileChooser files;
	private File chosenFile;
	private JButton load;
	private JButton save;
	private JButton saveAs;
	private JButton add;
	private JButton edit;
	private JButton delete;
	private JButton exit;
	
	public ListManip() {
		JScrollPane scrollBar; //West
		JPanel textPanel; // CenterLayout
		JPanel buttonPanel; // South
		
		files = new JFileChooser(".");
		
		listViewer = new DefaultListModel<WorkOrder>();
		list = new JList<WorkOrder>(listViewer);
		list.addListSelectionListener(this);
		
		
		scrollBar = new JScrollPane(list);
		
		load = createButton("Load", "LOAD", this, "Load a new file into the list.");
		save = createButton("Save", "SAVE", this, "Save the contents of the list to a file.");
		saveAs = createButton("Save As", "SAVEAS", this, "Saves the contents of the list to a new file.");
		add = createButton("Add", "ADD", this, "Adds an item to the displayed list");
		edit = createButton("Edit", "EDIT", this, "Allows a selected item to be edited", false);
		delete = createButton("Delete", "DELETE", this, "Deletes a selected item", false);
		exit = createButton("Exit", "EXIT", this, "Terminates the program.");
		
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
		
		
		setJMenuBar(menus());
		start();
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
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
		
		menu.add(makeItem("New", "NEW", this, KeyEvent.VK_N, KeyEvent.VK_N, "Creates a new list"));
		menu.add(makeItem("Delete", "DELETE", this, KeyEvent.VK_D, KeyEvent.VK_D, "Deletes a selected item")); // Disabled, then re-enabled when there is something to delete
		menu.add(makeItem("Delete All", "DELETEALL", this, KeyEvent.VK_E, KeyEvent.VK_E, "Deletes everything in the list"));
		
		menuBar.add(menu);
		
		return menuBar;
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("ADD")) {
			new WorkOrderGUI(this);
		}
		
		if (e.getActionCommand().equals("LOAD")) { // Use DataInputStream, and load from a file, using the WorkOrder constructor
			// Open a file
			
			if (files.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				chosenFile = files.getSelectedFile();
				try {
					int objectAmount;
					DataInputStream dis = new DataInputStream(new FileInputStream(chosenFile));
					// Read in the number of objects there were
					objectAmount = dis.readInt();
					for (int i = 0; i < objectAmount; i++) {
						listViewer.addElement(new WorkOrder(dis)); // Should add in work orders from a file
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
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
		
		if (e.getActionCommand().equals("SAVEAS")) {
			// Save to a new file
			if (files.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
				chosenFile = files.getSelectedFile();
				saveObject();
			}
		}
		
		if (e.getActionCommand().equals("DELETE")) {
			// Delete a given index
			int indexList[] = list.getSelectedIndices();
			int length = indexList.length;
			for (int i = length-1; i >= 0; i--) {
				listViewer.remove(indexList[i]);
			}
		}
		
		if (e.getActionCommand().equals("EDIT")) {
			int index = list.getSelectedIndex();
			WorkOrder wo = listViewer.get(index);
			new WorkOrderGUI(this, wo, index);
			// Pull down Work Order
			// Grab individual itema
			// Make constructor of WorkOrderGUI to open and use it
		}
		
		if (e.getActionCommand().equals("DELETEALL")) {
			// Delete all elements of the list
			listViewer.removeAllElements();
		}
		
		if (e.getActionCommand().equals("EXIT")) {
			System.exit(0);
		}
	}
	
	private void saveObject() {
		try {
			DataOutputStream dos = new DataOutputStream(new FileOutputStream(chosenFile));
			Object allElements[] = listViewer.toArray();
			dos.writeInt(allElements.length);
			for (int i = 0; i < allElements.length; i++) {
				WorkOrder temp = (WorkOrder)(allElements[i]);
				temp.store(dos);
			}
			dos.flush();
			dos.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void valueChanged(ListSelectionEvent e) {
		delete.setEnabled(!list.isSelectionEmpty());
		edit.setEnabled(list.getSelectedIndices().length == 1);
	}
	
	public void mouseClicked(MouseEvent e) {
		
	}
	
	public void mouseEntered(MouseEvent e) {
		
	}
	
	public void mouseExited(MouseEvent e) {
		
	}
	
	public void mousePressed(MouseEvent e) {
		
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	public void addWorkOrder(WorkOrder wo) {
		listViewer.addElement(wo);
	}
	
	public void editWorkOrder(WorkOrder wo, int listPos) {
		listViewer.setElementAt(wo, listPos);
	}
}