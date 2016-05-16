import java.util.Collection;
import java.util.PriorityQueue;

public class Queue {
public static void main(String[] args) {
	PriorityQueue <Point> q = new PriorityQueue<>(new MyComparator());
	double x=0;
	double y = 0 ;
    Point c = new Point(0,0);
    c.dist = 6;
    Point b = new Point (7,8);
    b.dist = 6;
    Point a = new Point (0,0);
    a.dist = 7;
    q.add(a);
    q.add(b);
    q.add(c);
  /*  Point d = c;
    d.dist = 5;
    Point t = d;
    t.dist = 9;
    c.dist = 3;
    q.remove(c);
    q.add(c);*/
    while (!q.isEmpty()) {
    	System.out.println(q.peek().dist+" "+q.peek().x+" "+q.poll().y);
    }
}

public static Point get(Point p,PriorityQueue <Point> q) {
	if (q.isEmpty()) { //in case empty do nothing
		return null;
	} 
	else { 
		Point c= q.poll(); //first element in queue
		while (c!= p){
			c = q.poll();
		}
	//	q.addAll( c);
		return (c); //check if this works
	}
}
}
