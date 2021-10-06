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
	private JTextField nameInput;
	private JTextField dateInput;
	private JTextField dateInput1;
	private JTextField descriptionInput;
	private JTextField rateInput;
	private JComboBox<String> departmentSelector;
	private JButton save;
	private JButton cancel;
	int listPos;
	
	public WorkOrderGUI(Manager manager, WorkOrder wo, int listPos) {
		JPanel panel = new JPanel();
		JPanel panel1 = new JPanel();
		this.manager = manager;
		this.listPos = listPos;
		String name = wo.getName();
		String department = wo.getDepartment();
		String description = wo.getDescription();
		long initDate = wo.getInitialRequestDate();
		long finishDate = wo.getFinishedRequestDate();
		double rate = wo.getBillingRate();
		
		this.name = new JLabel("Please input the person requesting the work.");
		this.department = new JLabel("Please select the department that the work order is for.");
		date = new JLabel("Please input the date that the request started.");
		date1 = new JLabel("Please input the date that the request was fulfilled.");
		this.description = new JLabel("Please input the description of the work order.");
		this.rate = new JLabel("Please input the billing rate of this work order.");
		nameError = new JLabel();
		dateError = new JLabel();
		descriptionError = new JLabel();
		
		nameInput = new JTextField(name, 20);
		dateInput = new JTextField(convertLongToDate(initDate), 20);
		dateInput.setInputVerifier(new DateVerifier());
		if (finishDate == 0) {
			dateInput1 = new JTextField(20);
		} else {
			dateInput1 = new JTextField(convertLongToDate(finishDate), 20);
		}
		dateInput1.setInputVerifier(new DateVerifier());
		descriptionInput = new JTextField(description, 20);
		rateInput = new JTextField(String.valueOf(rate), 20);
		rateInput.setInputVerifier(new RateVerifier());
		
		departmentSelector = new JComboBox<>(departments);
		
		departmentSelector.setSelectedItem(department);
		
		save = makeButton("Save");
		cancel = makeButton("Cancel");
		cancel.setVerifyInputWhenFocusTarget(false);
		
		panel.add(this.name);
		panel.add(nameInput);
		panel.add(nameError);
		panel.add(this.description);
		panel.add(descriptionInput);
		panel.add(descriptionError);
		panel.add(this.department);
		panel.add(departmentSelector);
		panel.add(date);
		panel.add(dateInput);
		panel.add(dateError);
		panel.add(date1);
		panel.add(dateInput1);
		panel.add(this.rate);
		panel.add(rateInput);
		
		panel1.add(save);
		panel1.add(cancel);
		
		add(panel1, BorderLayout.SOUTH);
		
		add(panel);
		start();
	}
	
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
		nameError = new JLabel();
		dateError = new JLabel();
		descriptionError = new JLabel();
		
		nameInput = new JTextField(20);
		dateInput = new JTextField(20);
		dateInput.setInputVerifier(new DateVerifier());
		dateInput1 = new JTextField(20);
		dateInput1.setInputVerifier(new DateVerifier());
		descriptionInput = new JTextField(20);
		rateInput = new JTextField(20);
		rateInput.setInputVerifier(new RateVerifier());
		
		departmentSelector = new JComboBox<>(departments);
		
		save = makeButton("Add");
		cancel = makeButton("Cancel");
		cancel.setVerifyInputWhenFocusTarget(false);
		
		panel.add(name);
		panel.add(nameInput);
		panel.add(nameError);
		panel.add(description);
		panel.add(descriptionInput);
		panel.add(descriptionError);
		panel.add(department);
		panel.add(departmentSelector);
		panel.add(date);
		panel.add(dateInput);
		panel.add(dateError);
		panel.add(date1);
		panel.add(dateInput1);
		panel.add(rate);
		panel.add(rateInput);
		
		panel1.add(save);
		panel1.add(cancel);
		
		add(panel1, BorderLayout.SOUTH);
		
		add(panel);
		start();
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
		Date requested = changeToDate(dateInput.getText().trim());
		Date finished = changeToDate(dateInput1.getText().trim());
		if (finished == null || finished.after(requested)) {
			String nameField = nameInput.getText().trim();
			String descriptionField = descriptionInput.getText().trim();
			// If finished after requested
			if (!nameField.equals("")) {
				if (!descriptionField.equals("")) {
					String departmentField = (String)(departmentSelector.getSelectedItem());
					double billRate = Double.parseDouble(rateInput.getText().trim());
					if (finished != null) return new WorkOrder(nameField, departmentField, descriptionField, billRate, requested.getTime(), finished.getTime());
					else return new WorkOrder(nameField, departmentField, descriptionField, billRate, requested.getTime(), 0);
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
	}
}