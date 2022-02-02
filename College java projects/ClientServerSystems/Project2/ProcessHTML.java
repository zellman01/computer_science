import java.io.*;
import java.net.*;
import javax.swing.*;
import javax.swing.text.html.parser.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;
import java.awt.BorderLayout;

public class ProcessHTML extends JFrame {
	private URL pageURL;
	private InputStreamReader urlReader;
	private HTMLParser tagHandle;
	private DefaultListModel<Pages> pageList;
	private JList pageListGUI;
	
	/*public ProcessHTML(String pageURL) {
		tagHandle = new HTMLParser();
		try {
			this.pageURL = new URL(pageURL);
			urlReader = new InputStreamReader(this.pageURL.openStream());
			new ParserDelegator().parse(urlReader, tagHandle, true);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error opening URL or input stream reader");
		}
	}*/
	
	public ProcessHTML() {
		pageList = new DefaultListModel<Pages>();
		pageListGUI = new JList(pageList);
		add(pageListGUI);
		start();
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
	
	public static void main(String[] args) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Input the full URL of the website you want to use (including HTTPS://): ");
		String url = "";
		try {
			url = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//new ProcessHTML(url);
		new ProcessHTML();
	}
}
