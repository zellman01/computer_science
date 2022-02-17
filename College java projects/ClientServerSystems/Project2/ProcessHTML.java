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
	
	public ProcessHTML(String URL) throws MalformedURLException, IOException {
		pageList = new DefaultListModel<Pages>();
		pageListGUI = new JList(pageList);
		add(pageListGUI);
		start();
		String tempURL = URL;
		int numberProcessed = 0;
		long start = System.currentTimeMillis();
		try {
			do {
				if (numberProcessed == 0) {
					pageList.addElement(new Pages(new URL(tempURL), 0));
					numberProcessed++;
				} else {
					Pages previous = pageList.get(numberProcessed-1);
					DefaultListModel<String> tempLinks = previous.getLinkList();
					for (int i = 0; i < tempLinks.getSize(); i++) {
						URL temp = null;
						try {
							temp = new URL(tempLinks.get(i));
						} catch (MalformedURLException e) { // Go in reverse order to get full working hostname
							String tempUrl = tempLinks.get(i);
							if (tempUrl.startsWith("www")) tempUrl = "https://" + tempUrl;
							else {
								boolean stop = false;
								for (int j = numberProcessed; j > 0 && !stop; j--) {
									Pages test = pageList.get(j);
									String testStr = test.getPageURLString();
									boolean slash = tempUrl.startsWith("/");
									if(testStr.contains("://")) {
										tempUrl = testStr;
										if (slash) {
											tempUrl += tempLinks.get(i);
											stop = true;
										} else {
											tempUrl += "/" + tempLinks.get(i);
											stop = true;
										}
									}
								}
							}
							// Keep going back until you get a base domain name, then append it onto the malformed URL string
							temp = new URL(tempUrl);
						}
						if (System.currentTimeMillis()-start < Param.MAXEXPANSIONTIME)
							pageList.addElement(new Pages(temp, previous.getNextDistance()));
					}
					numberProcessed++;
				}
			} while (numberProcessed < Param.MAXRADIUS || System.currentTimeMillis()-start > Param.MAXRUNTIME);
			pageList = Param.sortList(pageList, new SortURL());
		} catch(IndexOutOfBoundsException e) {
			System.out.println("No more pages can be processed.");
		}
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
			new ProcessHTML(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
