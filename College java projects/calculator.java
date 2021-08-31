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
	
	public MyCalculator() {
		panelOne = new JPanel();
		panelTwo = new JPanel();
		squareRoot = new JRadioButton("Square Root");
		sin = new JRadioButton("sin");
		cos = new JRadioButton("cos");
		ln = new JRadioButton("ln");
		bg = new ButtonGroup();
		input = new JTextField(20);
		inputLabel = new JLabel("Input:");
		output = new JTextField(20);
		outputLabel = new JLabel("Output:");
		clear = new JButton("Clear");
		inverse = new JCheckBox("Inverse");
		
		squareRoot.setActionCommand("SQUAREROOT");
		sin.setActionCommand("SIN");
		cos.setActionCommand("COS");
		ln.setActionCommand("LN");
		clear.setActionCommand("CLEAR");
		
		squareRoot.addActionListener(this);
		sin.addActionListener(this);
		cos.addActionListener(this);
		ln.addActionListener(this);
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
	
	private void start() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		
		setSize(d.width/3, d.height/4);
		setLocation(d.width/2, d.height/2);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Calculator");
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		System.out.println("actionPerformed:");
		System.out.print(e.getActionCommand() + "\n");
		switch(e.getActionCommand()) {
			default:
			System.out.println("No action found.");
		}
	}
	
	public void changedUpdate(DocumentEvent e) {
		System.out.println("changedUpdate");
	}
	
	public void insertUpdate(DocumentEvent e) {
		System.out.println("insertUpdate");
	}
	
	public void removeUpdate(DocumentEvent e) {
		System.out.println("removeUpdate");
	}
}
