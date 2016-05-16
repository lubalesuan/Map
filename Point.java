import java.util.ArrayList;

/**
 * 
 * @param x
 * @param y
 */
public class Point {
Point next;	
public double x,y;
//these three for djikstra
public double dist; //dist at every node
public Point parent; //tracing path
public ArrayList <Edge> allEdges = new ArrayList <> (); //TODO careful with this!
//only adds to beginEdges if point is the start of edge (not to draw line twice)
public ArrayList <Edge> beginEdges = new ArrayList <> ();
public String name;

public Point (double x, double y) {
	this.x = x;
	this.y = y;
	}

public void print() {
	System.out.printf(" name  %s ",name);
	
}



}

