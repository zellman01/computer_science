import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;
import java.awt.BorderLayout;

// https://docs.oracle.com/en/java/javase/16/docs/api/java.desktop/javax/swing/text/html/HTMLDocument.HTMLReader.html#handleSimpleTag(javax.swing.text.html.HTML.Tag,javax.swing.text.MutableAttributeSet,int)

public class HTMLParser extends JFrame implements ActionListener {
	private DefaultListModel<String> dlm;
	private JList<String> jList;
	private JTextField urlAddress;
	private JLabel urlLabel;
	private JButton acceptUrl;
	private URL url;
	private BufferedReader webReader;
	
	public HTMLParser() {
		JPanel panel = new JPanel();
		
		dlm = new DefaultListModel<String>();
		jList = new JList<String>(dlm);
		urlAddress = new JTextField(46);
		acceptUrl = new JButton("[Go]");
		acceptUrl.addActionListener(this);
		urlLabel = new JLabel("URL:");
		add(jList, BorderLayout.CENTER);
		panel.add(urlLabel);
		panel.add(urlAddress);
		panel.add(acceptUrl);
		add(panel, BorderLayout.SOUTH);
		
		getRootPane().setDefaultButton(acceptUrl);
		
		start();
	}
	
	private void readWebpage(String urlAddress) throws MalformedURLException, IOException, UnknownHostException {
		url = new URL(urlAddress);
		if (webReader != null) webReader = null;
		webReader = new BufferedReader(new InputStreamReader(url.openStream()));
	}
	
	private void start() {
		Toolkit tk;
		Dimension d;
		
		setTitle("HTML Parser");
		tk = Toolkit.getDefaultToolkit();
		d = tk.getScreenSize();
		setSize(d.width/2, d.height/2);
		setLocation(d.width/4, d.height/4);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) { // Only one button goes here, no need for an if check
		dlm.clear();
		String urli = urlAddress.getText().trim();
		try {
			readWebpage(urli);
		} catch (MalformedURLException e1) {
			JOptionPane.showMessageDialog(this, "The URL is malformed.");
			return; // To leave function to not run any code below this
		} catch (IOException e2) {
			System.out.println("BufferReader error");
			e2.printStackTrace();
			return;
		}
		try {
			String str = webReader.readLine();
			while (str != null) {
				dlm.addElement(str);
				str = webReader.readLine();
			}
			webReader.close();
		} catch (IOException e1) {
			System.out.println("Error while reading from website file");
			e1.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new HTMLParser();
	}
}