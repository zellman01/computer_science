import java.io.*;
import java.net.*;
import javax.swing.*;
import javax.swing.text.html.parser.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;
import java.awt.BorderLayout;

public class Pages {
	private URL pageId;
	private int spaceFromSeed;
	private DefaultListModel<String> emails;
	private HTMLParser tagHandler;
	
	public Pages(URL pageId, int spaceFromSeed) throws IOException {
		this.pageId = pageId;
		this.spaceFromSeed = spaceFromSeed;
		emails = new DefaultListModel<String>();
		tagHandler = new HTMLParser();
		new ParserDelegator().parse(new InputStreamReader(pageId.openStream()), tagHandler, true);
		emails = tagHandler.getEmails();
		emails = Param.sortList(emails);
	}
	
	public int getNextDistance() { return spaceFromSeed++; }
	
	public void addEmail(String email) {
		emails.addElement(email);
	}
	
	public DefaultListModel<String> getEmaiList() { return emails; }
	
	public String getPageURLString() { return pageId.toString(); }
	
	public DefaultListModel<String> getLinkList() { return tagHandler.getLinks(); }
	
	@Override
	public String toString() {
		String star = "<html>****************************";
		String ret = star + "<br />";
		ret += getPageURLString() + "<br />";
		for (int i = 0; i < emails.getSize(); i++) {
			ret += emails.get(i).toString() + "<br />";
		}
		ret += star + "\n</html>";
		return ret;
	}
}