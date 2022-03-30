import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.sound.sampled.*;
import com.sun.mail.imap.*;

public class StartProgram implements ActionListener, SettingNotifier {
	private Settings set;
	private PopupMenu popupMenu;
	private SystemTray tray;
	private TrayIcon icon;
	private Session session;
	private Authenticator auth;
	private Store store;
	private Folder inbox;
	private Message[] messageList;
	private Properties props;
	private javax.swing.Timer timer;
	private Clip clip;
	private boolean playSound;
	
	public StartProgram() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
		props = new Properties();
		try {
			props.load(new FileInputStream(new File("Settings.imp")));
		} catch (IOException e) {
			System.out.println("Cannot load settings file - Maybe have not ran before?");
			set = new Settings(props);
			set = null;
		}
		
		AudioInputStream ais = AudioSystem.getAudioInputStream(new File("ding.wav"));
		
		clip = AudioSystem.getClip();
		
		clip.open(ais);
		
		if (props.getProperty("secure.soundPlay").equals("true")) playSound = true;
		else playSound = false;
		
		popupMenu = new PopupMenu();
		popupMenu.add(createItem("Settings"));
		popupMenu.add(createItem("Turn On/Off Sound", "SOUND"));
		popupMenu.add(createItem("Exit"));
		
		if (SystemTray.isSupported()) {
			tray = SystemTray.getSystemTray();
			icon = new TrayIcon(Toolkit.getDefaultToolkit().getImage("tray.png"), "Email Notifier program");
			icon.setPopupMenu(popupMenu);
			icon.setImageAutoSize(true);
			try {
				tray.add(icon);
			} catch (AWTException e) {
				System.out.println("TrayIcon cannot be added.");
				return;
			}
		}
		
		// Check mail first
		timerFired();
		
		// Timer to check for mail
		timer = new javax.swing.Timer(Integer.parseInt(props.getProperty("secure.timeInput")), this);
		timer.setActionCommand("TIMER");
		timer.start();
	}
	
	private void timerFired() {
		try {
			Properties prop = new Properties();
			auth = null;
			session = Session.getInstance(prop, auth);
			store = session.getStore(props.getProperty("mail.transport.protocol")); // Cange to a property
			store.connect(props.getProperty("mail.smtp.host"), props.getProperty("secure.username"), props.getProperty("secure.password"));
			
			inbox = store.getFolder("INBOX");
			inbox.open(Folder.READ_WRITE);
			processFolder(inbox);
			
			inbox.close(false);
			store.close();
		} catch (NoSuchProviderException nspe) {
			System.out.println("No such email protocol provider: " + "imaps");
		} catch (MessagingException me) {
			System.out.println("Messaging Exception");
			me.printStackTrace();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException uil) {
			uil.printStackTrace();
		}
	}
	
	private void processFolder(Folder f) throws MessagingException, UnsupportedAudioFileException, IOException, LineUnavailableException {
		int numMessages;
		Message message;
		
		numMessages = f.getMessageCount();
		boolean tmpa = false;
		
		if (f.hasNewMessages()) {
			if (playSound) {
				clip.start();
				tmpa = true;
			}
			message = f.getMessage(numMessages);
			Properties tmp = processMessage(message);
			JOptionPane.showMessageDialog(null, "New messages recieved. Most recent is from " + tmp.getProperty("from") + ", with the subject " + tmp.getProperty("subject"));
		}
		
		if (tmpa) {
			clip.stop();
			clip.close();
			resetAudio();
			clip.setMicrosecondPosition(0);
		}
	}
	
	private void resetAudio() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		AudioInputStream ais = AudioSystem.getAudioInputStream(new File("ding.wav"));
		
		clip = AudioSystem.getClip();
		
		clip.open(ais);
	}
	
	private Properties processMessage(Message m) {
		Properties tmp = new Properties();
		try {
			tmp.setProperty("from", m.getFrom()[0].toString());
			tmp.setProperty("subject", m.getSubject());
			return tmp;
		} catch (MessagingException e) {
			System.out.println("Bad message");
			e.printStackTrace();
		}
		return null;
	}
	
	private MenuItem createItem(String name) {
		MenuItem b = new MenuItem(name);
		b.addActionListener(this);
		b.setActionCommand(name.toUpperCase());
		return b;
	}
	
	private MenuItem createItem(String name, String acmd) {
		MenuItem b = createItem(name);
		b.setActionCommand(acmd);
		return b;
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("TIMER")) {
			timerFired();
		}
		
		if (e.getActionCommand().equals("EXIT"))
			System.exit(0);
		
		if (e.getActionCommand().equals("SOUND")) {
			if (props.getProperty("secure.soundPlay").toString().equals("true")) {
				props.setProperty("secure.soundPlay", "false");
				playSound = false;
			} else {
				props.setProperty("secure.soundPlay", "true");
				playSound = true;
			}
			try {
				props.store(new FileOutputStream(new File("Settings.imp")), "");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		if (e.getActionCommand().equals("SETTINGS")) {
			if (set == null) {
				set = new Settings(props, this);
			}
		}
	}
	
	public void changeSettings() {
		set = null;
		try {
			timer.setDelay(Integer.parseInt(props.getProperty("secure.timeInput")));
		} catch (NullPointerException e) {
			timer = new javax.swing.Timer(Integer.parseInt(props.getProperty("secure.timeInput")), this);
			timer.setActionCommand("TIMER");
			timer.start();
		}
		
		if (props.getProperty("secure.soundPlay").equals("true")) playSound = true;
		else playSound = false;
		// Only called from the Settings dialog
	}
	
	public void cancelChange() {
		set = null;
	}
	
	public static void main(String[] args) {
		try {
			new StartProgram();
		} catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
			e.printStackTrace();
		}
	}
}