import java.util.Comparator;

public class SortURL implements Comparator<Pages> {
	public int compare(Pages a, Pages b) {
		return a.getPageURLString().compareTo(b.getPageURLString());
	}
}