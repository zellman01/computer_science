package otherThings;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.text.DecimalFormat;

public class Wages extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JTextField inputHours, inputRate, inputState, inputFederal, display, display2, display3, display4;
	private DecimalFormat money = new DecimalFormat("$0.00");

	public Wages() {
		super("Wages Calculator");

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2,3));

		panel.add(new JLabel("   Hours worked:"));
		inputHours = new JTextField(5);
		inputHours.setHorizontalAlignment(JTextField.RIGHT);
		panel.add(inputHours);

		panel.add(new JLabel("   Hourly rate:"));
		inputRate = new JTextField(5);
		inputRate.setHorizontalAlignment(JTextField.RIGHT);
		panel.add(inputRate);
		
		panel.add(new JLabel("   State tax:"));
		inputState = new JTextField(5);
		inputState.setHorizontalAlignment(JTextField.RIGHT);
		panel.add(inputState);
		
		panel.add(new JLabel("   Federal tax:"));
		inputFederal = new JTextField(5);
		inputFederal.setHorizontalAlignment(JTextField.RIGHT);
		panel.add(inputFederal);

		panel.add(new JLabel("   Total wages:"));
		display = new JTextField(20);
		display.setHorizontalAlignment(JTextField.RIGHT);
		display.setEditable(false);
		display.setBackground(Color.yellow);
		panel.add(display);
		
		panel.add(new JLabel("   Gross Earnings:"));
		display2 = new JTextField(20);
		display2.setHorizontalAlignment(JTextField.RIGHT);
		display2.setEditable(false);
		display2.setBackground(Color.yellow);
		panel.add(display2);
		
		panel.add(new JLabel("   Soc. Sec.:"));
		display3 = new JTextField(20);
		display3.setHorizontalAlignment(JTextField.RIGHT);
		display3.setEditable(false);
		display3.setBackground(Color.yellow);
		panel.add(display3);
		
		panel.add(new JLabel("   Medicare:"));
		display4 = new JTextField(20);
		display4.setHorizontalAlignment(JTextField.RIGHT);
		display4.setEditable(false);
		display4.setBackground(Color.yellow);
		panel.add(display4);

		JButton calc = new JButton("Calculate wages");
		calc.addActionListener(this);

		Container c = getContentPane();
		c.add(panel, BorderLayout.CENTER);
		c.add(calc, BorderLayout.SOUTH);
	}

	/**
	 * 
	 * @param hours Amount of hours worked
	 * @param rate Rate at which how much money is given per hour
	 * @param state State tax
	 * @param fed Federal tax
	 * @return wages The amount of money earned
	 */
	public double totalWages(double hours, double rate, double state, double fed) {
		double wages = 0;

		if (hours - 40 > 0) {
			wages = (((hours - 40) * (rate * 1.5)) + 40 * rate);
			wages -= wages * 0.0145;
			wages -= wages * 0.0620;
		} else {
			wages = hours * rate;
			wages -= wages * 0.0765;
		}
		wages -= (state + fed);

		return wages;
	}

	public void actionPerformed(ActionEvent e) {
		String s = inputHours.getText();
		double hours = Double.parseDouble(s);
		s = inputRate.getText();
		double rate = Double.parseDouble(s);
		s = inputState.getText();
		double state = Double.parseDouble(s);
		s = inputFederal.getText();
		double fed = Double.parseDouble(s);
		double wages = totalWages(hours, rate, state, fed);
		display.setText(money.format(wages));
		if (hours - 40 > 0) {
			display2.setText(money.format((((hours - 40) * (rate * 1.5)) + 40 * rate)));
			display3.setText(money.format((((hours - 40) * (rate * 1.5)) + 40 * rate) * 0.0620));
			display4.setText(money.format((((hours - 40) * (rate * 1.5)) + 40 * rate) * 0.0145));
		} else {
			display2.setText(money.format(hours * rate));
			display3.setText(money.format((hours * rate) * 0.0620 ));
			display4.setText(money.format((hours * rate) * 0.0145));
		}
		
	}

	public static void main(String[] args) {
		Wages window = new Wages();
		window.setBounds(300, 300, 823 , 150);
		window.setDefaultCloseOperation(EXIT_ON_CLOSE);
		window.setVisible(true);
		window.setResizable(false);
	}
}

