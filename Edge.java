/**
 * 
 * @author luba_les
 *
 */
public class Edge {

	public Point p1;
	public Point p2;
	public String name;
	public double length; //length of edge

	public Edge(Point p1, Point p2,String name) {
		this.p1 = p1;
		this.p2 = p2;
		this.name = name;
		length = setLength(p1.y,p1.x,p2.y,p2.x);
	}

	public void print () {
		System.out.print("Edge: ");
		p1.print();
		p2.print();
		System.out.println();
	}

	//set distance between neighbors
	public double setLength(double p1y,double p1x,double p2y,double p2x) {
		double R = 6371; // Radius of the earth in km
		double dY = deg2rad(p2y-p1y);  // deg2rad below
		double dX = deg2rad(p2x-p1x); 
		double a = 
				Math.sin(dY/2) * Math.sin(dY/2) +
				Math.cos(deg2rad(p1y)) * Math.cos(deg2rad(p2y)) * 
				Math.sin(dX/2) * Math.sin(dX/2)
				; 
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
		double d = R * c*0.621371; // Distance in miles
		return d;
	}

	public double deg2rad(double deg) {
		return deg * (Math.PI/180);
	}
}
