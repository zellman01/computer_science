import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.lang.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.text.*;

public class RateVerifier extends InputVerifier {
	public boolean verify(JComponent input) {
		double rate;
		boolean valid;
		String str;
		
		str = ((JTextField)input).getText().trim();
		
		if (str.equals("")) return true;
		
		try {
			rate = Double.parseDouble(str);
			if (rate < 0.0 || rate > 50.0) {
				JOptionPane.showMessageDialog(input.getParent(), "The billing rate must be between 0.0 and 50.0, inclusive.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			valid = (rate > 0.0 && rate < 50.0);
		} catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(input.getParent(), "The billing rate field must be a double.", "Error", JOptionPane.ERROR_MESSAGE);
			valid = false;
		}
		
		return valid;
	}
}