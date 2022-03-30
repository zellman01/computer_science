import java.util.Properties;
import javax.swing.*;
import javax.swing.JSpinner.DefaultEditor;
import java.awt.event.*;
import java.io.*;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.text.ParseException;

public class Settings extends JDialog implements ActionListener{
	private Properties props;
	private JLabel serverName;
	private JLabel port;
	private JLabel username;
	private JLabel password;
	private JLabel time;
	private JTextField serverNameInput;
	private JSpinner portInput;
	private JTextField usernameInput;
	private JPasswordField passwordInput;
	private JSpinner timeInput;
	private JCheckBox sound;
	private JButton submit;
	private JButton cancel;
	private SettingNotifier sn;
	
	public Settings(Properties props) {
		this.props = props;
		setup(true);
	}
	
	public Settings(Properties props, SettingNotifier sn) {
		this.sn = sn;
		this.props = props;
		setup(false);
	}
	
	private void setup(boolean started) {
		serverName = new JLabel("IMAP server name: ");
		port = new JLabel("Port of the server: ");
		username = new JLabel("Username: ");
		password = new JLabel("Password: ");
		time = new JLabel("Time: ");
		serverNameInput = new JTextField(30);
		portInput = new JSpinner();
		usernameInput = new JTextField(30);
		passwordInput = new JPasswordField(30);
		timeInput = new JSpinner();
		sound = new JCheckBox("Sound?");
		submit = new JButton("Accept");
		cancel = new JButton("Cancel");
		
		submit.addActionListener(this);
		cancel.addActionListener(this);
		cancel.setActionCommand("CANCEL");
		portInput.setValue(465);
		
		if (!props.isEmpty()) {
			serverNameInput.setText(props.getProperty("mail.smtp.host"));
			portInput.setValue(Integer.parseInt(props.getProperty("mail.smtp.socketFactory.port")));
			usernameInput.setText(props.getProperty("secure.username"));
			passwordInput.setText(props.getProperty("secure.password"));
			int millis = Integer.parseInt(props.getProperty("secure.timeInput")) / 1000 / 60;
			timeInput.setValue(millis);
			// Set sound
			if (props.getProperty("secure.soundPlay").equals("true"))
				sound.setSelected(true);
		}
		
		JPanel panel = new JPanel();
		panel.add(serverName);
		panel.add(serverNameInput);
		panel.add(port);
		panel.add(portInput);
		panel.add(username);
		panel.add(usernameInput);
		panel.add(password);
		panel.add(passwordInput);
		panel.add(time);
		panel.add(timeInput);
		panel.add(sound);
		panel.add(submit);
		if (!started) panel.add(cancel);
		
		add(panel);
		
		start(started);
	}
	
	private void start(boolean started) {
		Toolkit tk;
		Dimension d;
		
		setTitle("Mail Listener Settings");
		tk = Toolkit.getDefaultToolkit();
		d = tk.getScreenSize();
		setSize(1100, 100);
		setLocation(d.width/4, d.height/4);
		
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		if (started) setModal(true);
		
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("CANCEL")) {
			if (sn != null) {
				sn.cancelChange();
			}
			dispose();
			return;
		}
		// Validate information
		try {
			timeInput.commitEdit();
		} catch (ParseException pe) {
			JComponent editor = timeInput.getEditor();
			if (editor instanceof DefaultEditor) {
				((DefaultEditor)editor).getTextField().setValue(timeInput.getValue());
			}
		}
		
		try {
			portInput.commitEdit();
		} catch (ParseException pe) {
			JComponent editor = portInput.getEditor();
			if (editor instanceof DefaultEditor) {
				((DefaultEditor)editor).getTextField().setValue(portInput.getValue());
			}
		}
		
		props.setProperty("mail.smtp.host", serverNameInput.getText());
		props.setProperty("mail.smtp.socketFactory.port", portInput.getValue().toString());
		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.port", portInput.getValue().toString());
		props.setProperty("mail.smtp.ssl.enable", "true");
		props.setProperty("mail.transport.protocol", "imaps");
		props.setProperty("secure.username", usernameInput.getText());
		String pwd = "";
		char pwdChar[] = passwordInput.getPassword();
		for (int i = 0; i < pwdChar.length; i++) {
			pwd += pwdChar[i];
			pwdChar[i] = '0';
		}
		props.setProperty("secure.password", pwd);
		pwd = "";
		props.setProperty("secure.soundPlay", Boolean.toString(sound.isSelected()));
		int millis = Integer.parseInt(timeInput.getValue().toString()) * 1000 * 60;
		props.setProperty("secure.timeInput", Integer.toString(millis));
		try {
			props.store(new FileOutputStream(new File("Settings.imp")), "");
			dispose();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		if (sn != null) {
			sn.changeSettings();
		}
	}
}
