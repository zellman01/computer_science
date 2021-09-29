import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.lang.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.text.*;

public class ListManipulationProject {
	public static void main(String[] args) {
		new ListManip();
	}
}

class WorkOrder {
	private String name, dept, description;
	private double billingRate;
	private long initRequest, finishedRequest;

	public WorkOrder() {}
	
	public WorkOrder(String name, String dept, String desc, double billRate, long initRequest, long finishedRequest) {
		this.name = name;
		this.dept = dept;
		description = desc;
		billingRate = billRate;
		this.initRequest = initRequest;
		this.finishedRequest = finishedRequest;
	}
	
	public WorkOrder(DataInputStream dis) throws IOException {
		name = dis.readUTF();
		dept = dis.readUTF();
		description = dis.readUTF();
		billingRate = dis.readDouble();
		initRequest = dis.readLong();
		finishedRequest = dis.readLong();
	}
	
	public static WorkOrder getRandom() {
		WorkOrder randWO = new WorkOrder();
		// name
		// dept
		randWO.description = "Random work order";
		// billingRate
		// random start date
		// random end date
		return randWO;
	}
	
	private void consolePrint() {
		System.out.println("-------Work Order debug-------");
		System.out.println(name + "\n" + dept + "\n" + description);
		System.out.println(billingRate);
		// Format the bottom two to a date
		System.out.println(initRequest);
		System.out.println(finishedRequest);
	}
	
	public void store(DataOutputStream dos) throws IOException {
		dos.writeUTF(name);
		dos.writeUTF(dept);
		dos.writeUTF(description);
		dos.writeDouble(billingRate);
		dos.writeLong(initRequest);
		dos.writeLong(finishedRequest);
	}
}

interface Manager {
	public void addWorkOrder(WorkOrder wo);
	public void editWorkOrder();
}

class WorkOrderGUI extends JDialog implements ActionListener {
	// TODO: Display a GUI for whenever someone selects add or edit
	private Manager manager;
	private final String[] departments = {"SALES", "HARDWARE", "ELECTRONICS"};
	private JLabel name;
	private JLabel department;
	private JLabel date;
	private JLabel date1;
	private JLabel description;
	private JLabel rate;
	private JTextField nameInput;
	private JTextField dateInput;
	private JTextField dateInput1;
	private JTextField descriptionInput;
	private JTextField rateInput;
	private JComboBox<String> departmentSelector;
	private JButton save;
	private JButton cancel;
	
	public WorkOrderGUI(Manager manager) {
		JPanel panel = new JPanel();
		JPanel panel1 = new JPanel();
		this.manager = manager;
		
		name = new JLabel("Please input the person requesting the work.");
		department = new JLabel("Please select the department that the work order is for.");
		date = new JLabel("Please input the date that the request started.");
		date1 = new JLabel("Please input the date that the request was fulfilled.");
		description = new JLabel("Please input the description of the work order.");
		rate = new JLabel("Please input the billing rate of this work order.");
		
		nameInput = new JTextField(20);
		dateInput = new JTextField(20);
		dateInput.setInputVerifier(new DateVerifier());
		dateInput1 = new JTextField(20);
		dateInput1.setInputVerifier(new DateVerifier());
		descriptionInput = new JTextField(20);
		rateInput = new JTextField(20);
		rateInput.setInputVerifier(new RateVerifier());
		
		departmentSelector = new JComboBox<>(departments);
		
		save = makeButton("Save");
		cancel = makeButton("Cancel");
		
		panel.add(name);
		panel.add(nameInput);
		panel.add(department);
		panel.add(departmentSelector);
		panel.add(date);
		panel.add(dateInput);
		panel.add(date1);
		panel.add(dateInput1);
		panel.add(description);
		panel.add(descriptionInput);
		panel.add(rate);
		panel.add(rateInput);
		
		panel1.add(save);
		panel1.add(cancel);
		
		add(panel1, BorderLayout.SOUTH);
		
		add(panel);
		start();
	}
	
	private void start() {
		Toolkit tk;
		Dimension d;
		
		setTitle("Add/Edit WorkOrder");
		tk = Toolkit.getDefaultToolkit();
		d = tk.getScreenSize();
		setSize(d.width/4, (d.height/4)+83);
		setLocation(d.width/4, d.height/4);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		setVisible(true);
		
	}
	
	private JButton makeButton(String name) {
		JButton b = new JButton(name);
		b.setActionCommand(name.toUpperCase());
		b.addActionListener(this);
		return b;
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("SAVE")) {
			// Make sure that it is all correct
			// Create a WorkOrder object
			// Use manager.addWorkOrder(WorkOrder)
		}
		// Close window without doing anything when cancel is clicked
	}
}

class RateVerifier extends InputVerifier {
	public boolean verify(JComponent input) {
		double rate;
		boolean valid;
		String str;
		
		str = ((JTextField)input).getText().trim();
		
		try {
			rate = Double.parseDouble(str);
			valid = true;
		} catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(input.getParent(), "The billing rate field must be a double.", "Error", JOptionPane.ERROR_MESSAGE);
			valid = false;
		}
		
		return valid;
	}
}

class DateVerifier extends InputVerifier {
	public boolean verify(JComponent input) {
		String str;
		Date d;
		SimpleDateFormat df;
		ParsePosition pos;
		boolean valid;
		
		str = ((JTextField)input).getText().trim();
		
		df = new SimpleDateFormat("MM/dd/yy");
		df.setLenient(false);
		
		pos = new ParsePosition(0);
		d = df.parse(str, pos);
		valid = pos.getIndex() == str.length() && d != null;
		if (!valid) {
			JOptionPane.showMessageDialog(input.getParent(), "The date needs to be in the format MM/dd/yy", "Error", JOptionPane.ERROR_MESSAGE);
		}
		return valid;
	}
}

class ListManip extends JFrame implements ActionListener, ListSelectionListener, MouseListener, Manager {
	private JList<WorkOrder> list;
	private DefaultListModel<WorkOrder> listViewer;
	private JFileChooser files;
	private File chosenFile;
	private JButton load;
	private JButton save;
	private JButton saveAs;
	private JButton add;
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
		delete = createButton("Delete", "DELETE", this, "Deletes a selected item", false);
		exit = createButton("Exit", "EXIT", this, "Terminates the program.");
		
		buttonPanel = new JPanel();
		buttonPanel.add(load);
		buttonPanel.add(save);
		buttonPanel.add(saveAs);
		buttonPanel.add(add);
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
					//BufferedReader br = new BufferedReader(new FileReader(chosenFile));
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
	
	public void editWorkOrder() {}
}
