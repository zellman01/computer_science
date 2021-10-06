import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.lang.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.text.*;

public class DateVerifier extends InputVerifier {
	public boolean verify(JComponent input) {
		String str;
		Date d;
		SimpleDateFormat df;
		ParsePosition pos;
		boolean valid;
		
		str = ((JTextField)input).getText().trim();
		
		if (str.equals("")) return true;
		
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