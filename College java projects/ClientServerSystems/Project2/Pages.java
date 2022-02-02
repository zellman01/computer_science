import java.net.*;
import javax.swing.DefaultListModel;

public class Pages {
	private URL pageId;
	private int spaceFromSeed;
	private DefaultListModel<String> emails;
	
	public Pages(URL pageId, int spaceFromSeed) {
		this.pageId = pageId;
		this.spaceFromSeed = spaceFromSeed;
		emails = new DefaultListModel<String>();
	}
	
	public int getNextDistance() { return spaceFromSeed++; }
	
	public void addEmail(String email) {
		emails.addElement(email);
	}
	
	public DefaultListModel<String> getEmaiList() { return emails; }
	
	public String getPageURLString() { return ""; } // Figure out how to get page URL string
	
	@Override
	public String toString() {
		String star = "****************************";
		String ret = star + "\n";
		ret += getPageURLString() + "\n";
		for (int i = 0; i < emails.getSize(); i++) {
			ret += emails.get(i).toString() + "\n";
		}
		ret += star + "\n";
		return ret;
	}
}