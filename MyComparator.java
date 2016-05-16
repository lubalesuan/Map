import java.util.Comparator;

public class MyComparator implements Comparator <Point> {

	public int compare(Point x, Point data) {
		if (x.dist < data.dist) {
			return -1;
		} else  {
			return 1;
		}
	}
	
}
