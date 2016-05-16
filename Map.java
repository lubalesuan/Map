import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.File;
import java.util.HashMap;
import javax.swing.JPanel;
/**
 * 
 * @author luba_les
 *
 */
public class Map extends JPanel implements ComponentListener { 

	public int width, height;
	public SetUp set;



	public Map(File file) {
		setFocusable(true);
		setSize(getWidth(),getHeight());
		width = getWidth();
		height = getHeight();
		addComponentListener(this);
		set = new SetUp(file);

	}

	@Override
	protected void paintComponent(Graphics g) {
		//loop through map of points
		super.paintComponent(g);
		for(HashMap.Entry<String, Point> p : set.map.entrySet()){ 
			//loop through map of beginEdges - so that didn't draw lines twice	
			if (!p.getValue().beginEdges.isEmpty()) {
				for (Edge e : p.getValue().beginEdges ) {
					g.setColor(Color.BLACK);
					//draw edges
					Point begin = e.p1; //get start point for edge
					Point end = e.p2;
					int x1 = (int)setCoord(begin).x;
					int y1 = (int)setCoord(begin).y;
					int x2 = (int)setCoord(end).x;
					int y2 = (int)setCoord(end).y;
					if (lineSmall(x1,y1,x2,y2) == false)
						g.drawLine(x1,y1,x2,y2);
				}

			}
		}
		for (int i = 0; i < set.path.size()-1;i++) {
			g.setColor(Color.RED);
			int x1 = (int)setCoord(set.path.get(i)).x;
			int y1 = (int)setCoord(set.path.get(i)).y;
			int x2 = (int)setCoord(set.path.get(i+1)).x;
			int y2 = (int)setCoord(set.path.get(i+1)).y;
			if (lineSmall(x1,y1,x2,y2) == false)
				g.drawLine(x1,y1,x2,y2);
		}
		for (Edge e: set.shortest) {
			g.setColor(Color.BLUE);
			int x1 = (int)setCoord(e.p1).x;
			int y1 = (int)setCoord(e.p1).y;
			int x2 = (int)setCoord(e.p2).x;
			int y2 = (int)setCoord(e.p2).y;
			if (lineSmall(x1,y1,x2,y2) == false)
				g.drawLine(x1,y1,x2,y2);
		}

	}




	//if line is less than 1 pixel - don't draw it
	public boolean lineSmall (int x1, int y1, int x2, int y2) {
		int dist = (int)(x2-x1)*(x2-x1)+(y2-y1)*(y2-y1);
		if (dist <1) {
			return true;
		} else {
			return false;
		}
	}

	//scale the coordinates up to size of JPanel
	public Point setCoord(Point point) {
		double ratioX = (point.x - set.minX)/set.rangeX;
		double ratioY = (point.y - set.minY)/set.rangeY;
		double coordY = height-height*ratioY; //check if this works
		double coordX = width*ratioX;
		return new Point(coordX,coordY);
	}




	public void dijkstra (String source, String destination) {
		Point begin = set.map.get(source);
		Point end = set.map.get(destination);
		set.dijkstra(begin, end);

	}

	public void prims () {
		set.prims();
	}

	@Override
	public void componentResized(ComponentEvent e) {
		width = getWidth();
		height = getHeight();
		repaint();

	}




	@Override
	public void componentMoved(ComponentEvent e) {
	}


	@Override
	public void componentShown(ComponentEvent e) {
	}


	@Override
	public void componentHidden(ComponentEvent e) {

	}
}
