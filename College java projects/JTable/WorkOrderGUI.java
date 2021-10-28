import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.lang.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.text.*;


public class WorkOrderGUI extends JDialog implements ActionListener {
	private Manager manager;
	private final String[] departments = {"SALES", "HARDWARE", "ELECTRONICS"};
	private JLabel name;
	private JLabel department;
	private JLabel date;
	private JLabel date1;
	private JLabel description;
	private JLabel rate;
	private JLabel nameError;
	private JLabel dateError;
	private JLabel descriptionError;
	private JLabel rateError;
	private final JLabel nullLabel = new JLabel("");
	private JTextField nameInput;
	private JTextField dateInput;
	private JTextField dateInput1;
	private JTextField descriptionInput;
	private JTextField rateInput;
	private JTextField[] allTextFields;
	private JComboBox<String> departmentSelector;
	private JButton save;
	private JButton cancel;
	private JButton addAnother;
	private int listPos;
	
	public WorkOrderGUI(Manager manager, WorkOrder wo, int listPos) {
		this.manager = manager;
		this.listPos = listPos;
		String name = wo.getName();
		String department = wo.getDepartment();
		String description = wo.getDescription();
		long initDate = wo.getInitialRequestDate();
		long finishDate = wo.getFinishedRequestDate();
		double rate = wo.getBillingRate();
		
		setGUI(true, name, department, description, initDate, finishDate, rate);
		start();
	}
	
	public WorkOrderGUI(Manager manager) {
		this.manager = manager;
		
		setGUI(false, "", "", "", 0, 0, 0.0);
		start();
	}
	
	private void setGUI(boolean isEdit, String name, String department, String description, long initDate, long finishDate, double rate) {
		JPanel panel = new JPanel();
		JPanel panel1 = new JPanel();
		this.name = new JLabel("Please input the person requesting the work.");
		this.department = new JLabel("Please select the department that the work order is for.");
		date = new JLabel("Please input the date that the request started.");
		date1 = new JLabel("Please input the date that the request was fulfilled.");
		this.description = new JLabel("Please input the description of the work order.");
		this.rate = new JLabel("Please input the billing rate of this work order.");
		nameError = new JLabel();
		dateError = new JLabel();
		descriptionError = new JLabel();
		rateError = new JLabel();
		
		nameInput = new JTextField(name, 20);
		
		if (initDate > 0) dateInput = new JTextField(convertLongToDate(initDate), 20);
		else dateInput = new JTextField(20);
		
		dateInput.setInputVerifier(new DateVerifier());
		
		if (finishDate > 0) dateInput1 = new JTextField(convertLongToDate(finishDate), 20);
		else dateInput1 = new JTextField(20);
		
		dateInput1.setInputVerifier(new DateVerifier());
		descriptionInput = new JTextField(description, 20);
		if (rate > 0.0) rateInput = new JTextField(String.valueOf(rate), 20);
		else rateInput = new JTextField(20);
		rateInput.setInputVerifier(new RateVerifier());
		
		allTextFields = new JTextField[]{nameInput, dateInput, dateInput1, descriptionInput, rateInput};
		
		departmentSelector = new JComboBox<>(departments);
		
		departmentSelector.setSelectedItem(department);
		
		if (isEdit) save = makeButton("Save");
		else save = makeButton("Add");
		
		cancel = makeButton("Cancel");
		cancel.setVerifyInputWhenFocusTarget(false);
		addAnother = makeButton("Add Another");
		
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		
		layout.setAutoCreateGaps(true);
		
		layout.setAutoCreateContainerGaps(true);
		
		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
		hGroup.addGroup(layout.createParallelGroup().addComponent(this.name).addComponent(this.description).addComponent(this.department).addComponent(date).addComponent(date1).addComponent(this.rate));
		hGroup.addGroup(layout.createParallelGroup().addComponent(nameInput).addComponent(descriptionInput).addComponent(departmentSelector).addComponent(dateInput).addComponent(dateInput1).addComponent(rateInput));
		hGroup.addGroup(layout.createParallelGroup().addComponent(nameError).addComponent(descriptionError).addComponent(nullLabel).addComponent(dateError).addComponent(nullLabel).addComponent(rateError));
		
		layout.setHorizontalGroup(hGroup);
		
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
		vGroup.addGroup(layout.createParallelGroup().addComponent(this.name).addComponent(nameInput).addComponent(nameError));
		vGroup.addGroup(layout.createParallelGroup().addComponent(this.description).addComponent(descriptionInput).addComponent(descriptionError));
		vGroup.addGroup(layout.createParallelGroup().addComponent(this.department).addComponent(departmentSelector).addComponent(nullLabel));
		vGroup.addGroup(layout.createParallelGroup().addComponent(date).addComponent(dateInput).addComponent(dateError));
		vGroup.addGroup(layout.createParallelGroup().addComponent(date1).addComponent(dateInput1).addComponent(nullLabel));
		vGroup.addGroup(layout.createParallelGroup().addComponent(this.rate).addComponent(rateInput).addComponent(rateError));
		
		layout.setVerticalGroup(vGroup);
		
		panel1.add(save);
		panel1.add(addAnother);
		panel1.add(cancel);
		
		add(panel1, BorderLayout.SOUTH);
		
		add(panel);
	}
	
	private String convertLongToDate(long date) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(date);
		Date retValue = (Date)c.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
		return sdf.format(retValue);
	}
	
	private void start() {
		Toolkit tk;
		Dimension d;
		
		setTitle("Add/Edit WorkOrder");
		tk = Toolkit.getDefaultToolkit();
		d = tk.getScreenSize();
		setSize((d.width/4)+250, (d.height/4)+20);
		setLocation(d.width/4, d.height/4);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		setModal(true);
		setVisible(true);
		
	}
	
	private JButton makeButton(String name) {
		JButton b = new JButton(name);
		b.setActionCommand(name.toUpperCase());
		b.addActionListener(this);
		return b;
	}
	
	// Will only execute after it has been verified that it is a date
	private Date changeToDate(String input) {
		Date d;
		SimpleDateFormat df;
		ParsePosition pos;
		
		df = new SimpleDateFormat("MM/dd/yy");
		df.setLenient(false);
		
		pos = new ParsePosition(0);
		d = df.parse(input, pos);
		return d;
	}
	
	private WorkOrder verifyWorkOrder() {
		WorkOrder wo;
		descriptionError.setText("");
		nameError.setText("");
		dateError.setText("");
		if (dateInput.getText().trim().equals("")) {
			dateError.setText("The request date cannot be empty.");
		} else {
			Date requested = changeToDate(dateInput.getText().trim());
			Date finished = changeToDate(dateInput1.getText().trim());
			if (finished == null || finished.after(requested)) {
				String nameField = nameInput.getText().trim();
				String descriptionField = descriptionInput.getText().trim();
				String rateField = rateInput.getText().trim();
				// If finished after requested
				if (!nameField.equals("")) {
					if (!descriptionField.equals("")) {
						if (!rateField.equals("")) {
							String departmentField = (String)(departmentSelector.getSelectedItem());
							double billRate = Double.parseDouble(rateField);
							if (finished != null) return new WorkOrder(nameField, departmentField, descriptionField, billRate, requested.getTime(), finished.getTime());
							else return new WorkOrder(nameField, departmentField, descriptionField, billRate, requested.getTime(), 0);
						} else {
							rateError.setText("The rate of the work order cannot be blank.");
							rateInput.requestFocus();
						}
					} else {
						descriptionError.setText("The description of the work order cannot be blank.");
						descriptionInput.requestFocus();
					}
				} else {
					nameError.setText("The name of the requestor cannot be blank.");
					nameInput.requestFocus();
				}
			} else {
				dateError.setText("You cannot have the work order finished before it was requested.");
				dateInput1.requestFocus();
			}
		}
		return null;
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("ADD")) {
			WorkOrder wo = verifyWorkOrder();
			if (wo != null) {
				manager.addWorkOrder(wo);
				dispose();
			}
		}
		
		if (e.getActionCommand().equals("SAVE")) {
			WorkOrder wo = verifyWorkOrder();
			if (wo != null) {
				manager.editWorkOrder(wo, listPos);
				dispose();
			}
		}
		
		if (e.getActionCommand().equals("CANCEL")) {
			dispose();
		}
		
		if (e.getActionCommand().equals("ADD ANOTHER")) {
			WorkOrder wo = verifyWorkOrder();
			if (wo != null) {
				manager.addWorkOrder(wo);
				
				for (int i = 0; i < 5; i++) {
					allTextFields[i].setText("");
				}
				
				departmentSelector.setSelectedItem("SALES");
				
				nameInput.requestFocus();
			}
		}
	}
}