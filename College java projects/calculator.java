import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

/**
 * A calculator app, able to calculate the square root, sin, cos, ln, and their inverses of any number.
 * @author Zachary Wellman
*/

public class calculator {
	public static void main(String[] args) {
		System.out.println("Calculator has started.");
		new MyCalculator();
		System.out.println("Finished starting application.");
	}
}

class MyCalculator extends JFrame implements ActionListener, DocumentListener {
	JPanel panelOne, panelTwo;
	JRadioButton squareRoot, sin, cos, ln;
	JTextField input, output;
	JLabel inputLabel, outputLabel;
	JButton clear;
	JCheckBox inverse;
	ButtonGroup bg;
	
	// Constructor
	public MyCalculator() {
		panelOne = new JPanel();
		panelTwo = new JPanel();
		squareRoot = createButton("Square Root", "SQUAREROOT", true);
		sin = createButton("Sin", "SIN");
		cos = createButton("Cos", "COS");
		ln = createButton("ln", "LN");
		bg = new ButtonGroup();
		input = new JTextField(20);
		inputLabel = new JLabel("Input:");
		output = new JTextField(20);
		outputLabel = new JLabel("Output:");
		clear = new JButton("Clear");
		inverse = new JCheckBox("Inverse");
		
		
		clear.setActionCommand("CLEAR");
		inverse.setActionCommand("INVERSE");
		
		clear.addActionListener(this);
		inverse.addActionListener(this);
		input.getDocument().addDocumentListener(this);
		
		bg.add(squareRoot);
		bg.add(sin);
		bg.add(cos);
		bg.add(ln);
		
		panelOne.add(squareRoot);
		panelOne.add(sin);
		panelOne.add(cos);
		panelOne.add(ln);
		panelOne.add(inverse);
		panelOne.setBackground(Color.RED);
		add(panelOne);
		
		output.setEditable(false);
		
		panelTwo.add(inputLabel);
		panelTwo.add(input);
		panelTwo.add(outputLabel);
		panelTwo.add(output);
		add(panelTwo, BorderLayout.NORTH);
		
		add(clear, BorderLayout.SOUTH);
		
		start();
	}
	
	// Create a radio button from the label and action command
	private JRadioButton createButton(String label, String command) {
		JRadioButton a = new JRadioButton(label);
		a.setActionCommand(command);
		a.addActionListener(this);
		return a;
	}
	
	// Create a radio button from the label and action command
	// Override from above to be able to specify selected status of the button
	private JRadioButton createButton(String label, String command, boolean selected) {
		JRadioButton a = new JRadioButton(label, selected);
		a.setActionCommand(command);
		a.addActionListener(this);
		return a;
	}
	
	// Start up the GUI aspect of the calculator
	private void start() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		
		setSize(d.width/3, d.height/4);
		setLocation(d.width/2, d.height/2);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Calculator");
		setVisible(true);
	}
	
	// Does the calculation and displays it, based on the function.
	// 1 = Square root, 2 = sin, 3 = cos, 4 = ln
	// 5, 6, 7, 8 are reserved for only inside the function. They are the inverses of the above
	private void calculate(int function, String number) {
		if (!number.equals("")) {
			double num = 0.0;
			boolean cont = true, inverseOn = inverse.isSelected();
			String outOfRange = "Number out of range.";
			try {
				num = Double.parseDouble(number);
			} catch (NumberFormatException e) {
				cont = false;
				output.setText("Invalid characters found.");
			}
			
			if (cont) {
				if (inverseOn) function += 4;
				double ans;
				switch (function) {
					case 1:
						if (num < 1) {
							output.setText(outOfRange);
						} else {
							ans = Math.sqrt(num);
							output.setText(String.valueOf(ans));
						}
						break;
					case 2: // Convert to radians
						if (num < -1 || num > 1) {
							output.setText(outOfRange);
						} else {
							ans = Math.sin(num);
							output.setText(String.valueOf(ans));
						}
						break;
					/*case 3:
						//TODO: Look up
						break;
					case 4:
						//TODO: Look up
						break;*/
					case 5:
						ans = Math.pow(num, 2);
						output.setText(String.valueOf(ans));
						break;
					default:
						output.setText("Function not implemented.");
						break;
				}
			}
		} else {
			output.setText("");
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		System.out.println("actionPerformed:");
		String inputString = input.getText().trim();
		switch(e.getActionCommand()) {
			case "CLEAR":
				System.out.println("Clear found.");
				input.setText("");
				break;
			case "SQUAREROOT":
				System.out.println("Square Root found.");
				calculate(1, inputString);
				break;
			case "SIN":
				System.out.println("Sin found.");
				calculate(2, inputString);
				break;
			case "COS":
				System.out.println("Cos found.");
				calculate(3, inputString);
				break;
			case "LN":
				System.out.println("ln found.");
				calculate(4, inputString);
				break;
			case "INVERSE":
				System.out.println("Inverse found.");
				input.setText(inputString);
				break;
			default:
				System.out.println("No action found.");
				break;
		}
	}
	
	public void changedUpdate(DocumentEvent e) {
		System.out.println("changedUpdate");
	}
	
	public void insertUpdate(DocumentEvent e) {
		System.out.println("insertUpdate");
		String inputString = input.getText().trim();
		switch (bg.getSelection().getActionCommand()) {
			case "SQUAREROOT":
				calculate(1, inputString);
				break;
			case "SIN":
				calculate(2, inputString);
				break;
			case "COS":
				calculate(3, inputString);
				break;
			case "LN":
				calculate(4, inputString);
				break;
			default:
				System.out.println("No action found.");
				break;
		}
	}
	
	public void removeUpdate(DocumentEvent e) {
		System.out.println("removeUpdate");String inputString = input.getText().trim();
		switch (bg.getSelection().getActionCommand()) {
			case "SQUAREROOT":
				calculate(1, inputString);
				break;
			case "SIN":
				calculate(2, inputString);
				break;
			case "COS":
				calculate(3, inputString);
				break;
			case "LN":
				calculate(4, inputString);
				break;
			default:
				System.out.println("No action found.");
				break;
		}
	}
}
