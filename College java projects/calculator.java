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
		System.out.println("C a l culator has started.");
		new MyCalculator();
		System.out.println("Finished starting application.");
	}
}

class MyCalculator extends JFrame implements ActionListener, DocumentListener {
	JPanel panelOne;
	JRadioButton squareRoot, sin, cos, ln;
	ButtonGroup bg;
	
	public MyCalculator() {
		panelOne = new JPanel();
		squareRoot = new JRadioButton("Square Root");
		sin = new JRadioButton("sin");
		cos = new JRadioButton("cos");
		ln = new JRadioButton("ln");
		bg = new ButtonGroup();
		
		squareRoot.setActionCommand("SQUAREROOT");
		sin.setActionCommand("SIN");
		cos.setActionCommand("COS");
		ln.setActionCommand("LN");
		
		squareRoot.addActionListener(this);
		sin.addActionListener(this);
		cos.addActionListener(this);
		ln.addActionListener(this);
		
		bg.add(squareRoot);
		bg.add(sin);
		bg.add(cos);
		bg.add(ln);
		
		panelOne.add(squareRoot);
		panelOne.add(sin);
		panelOne.add(cos);
		panelOne.add(ln);
		add(panelOne);
		start();
	}
	
	private void start() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		
		setSize(d.width/4, d.height/4);
		setLocation(d.width/4, d.height/4);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Calculator");
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		System.out.println("Action found.");
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