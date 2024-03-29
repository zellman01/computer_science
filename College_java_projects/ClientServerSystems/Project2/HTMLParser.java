import javax.swing.text.*;
import javax.swing.text.html.*;
import javax.swing.text.html.parser.*;
import java.util.regex.*;
import javax.swing.DefaultListModel;

public class HTMLParser extends HTMLEditorKit.ParserCallback {
	private DefaultListModel<String> links;
	private DefaultListModel<String> emails;
	
	public HTMLParser() {
		links = new DefaultListModel<String>();
		emails = new DefaultListModel<String>();
	}
	
	@Override
	public void handleSimpleTag(HTML.Tag t, MutableAttributeSet a, int pos) {

	}
	
	@Override
	public void handleStartTag(HTML.Tag tag, MutableAttributeSet a, int pos) {

		if (tag == HTML.Tag.A) {
			String regexString = "[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})";
			Object attribute = a.getAttribute(HTML.Attribute.HREF);
			if (attribute != null) {
				String attr = attribute.toString();
				if (attr.toUpperCase().startsWith("MAILTO:")) {
					Pattern pat = Pattern.compile(regexString);
					Matcher match = pat.matcher(attr);
					boolean done = false;
					while (!done) {
						if (match.find()) {
							System.out.println("Found match: " + attr.substring(match.start(), match.end()));
							emails.addElement(attr.substring(match.start(), match.end()));
							match.region(match.end(), attr.length());
						} else {
							done = true;
						}
					}
				} else {
					System.out.println("Link found: " + attr);
					links.addElement(attr);
				}
			}
		}
	}
	
	@Override
	public void handleText(char[] data, int pos) {

	}
	
	public DefaultListModel<String> getEmails() { return emails; }
	
	public DefaultListModel<String> getLinks() { return links; }
}
