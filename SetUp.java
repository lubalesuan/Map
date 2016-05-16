import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class SetUp {
	public double maxX = Integer.MIN_VALUE; //keep the range of points distribution
	public double minX = Integer.MAX_VALUE;
	public double maxY = Integer.MIN_VALUE; 
	public double minY = Integer.MAX_VALUE;
	HashMap <String,Point> map;
	public double rangeX,rangeY;

	public SetUp(File file) {
		scan(file);
		setRange();
	}

	public void scan (File file){
		try {
			map = new LinkedHashMap <> ();
			Scanner scanner = new Scanner (file);
			while (scanner.hasNext()) {
				String[] tokens = scanner.nextLine().split("\t");
				// do the processing for tokens here  
				for (int i = 0; i < tokens.length; ) {
					if (tokens[i].equals("i")) {
						i++;
						String key = tokens[i++];
						double y = Double.parseDouble(tokens[i++]); //width
						double x = Double.parseDouble(tokens[i++]); //length
						//set min and max for finding the range, disctribution of points
						if (x > maxX) 
							maxX = x;
						if (x < minX)
							minX = x;
						if (y > maxY)
							maxY = y;
						if (y < minY)
							minY = y;
						Point p = k = new Point(x,y);
						p.name = key;
						map.put(key,p);

					} else if (tokens[i].equals("r")) {
						i++;
						String key = tokens[i++];
						Point begin = map.get(tokens[i++]); 
						Point end = map.get(tokens[i++]);
						Edge edge = new Edge(begin,end,key);
						edge.name = key;
						begin.beginEdges.add (edge); 
						begin.allEdges.add(edge); //
						end.allEdges.add(edge);				
					}
				}
			}
			scanner.close ();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	//print map
	public void printMap () {
		for(HashMap.Entry<String, Point> entry : map.entrySet()){
			System.out.printf(" %s \t: %s\t %s %n", entry.getKey(), entry.getValue().x,entry.getValue().y);
			for (Edge e :entry.getValue().beginEdges) {
				e.print();
			}
		}
	}


	//conversion to panel size
	public void setRange() {
		rangeX = maxX - minX;
		rangeY = maxY - minY;
	}



	HashMap <String, Point> vis = new HashMap <> ();
	PriorityQueue <Point> unvis = new PriorityQueue<>(new MyComparator());
	ArrayList <Point> path = new ArrayList <> ();
	Point curr;

	public void fillQueue (){
		for(HashMap.Entry<String, Point> entry : map.entrySet()){
			entry.getValue().dist = Double.MAX_VALUE;
			unvis.add(entry.getValue());//for prims
		}
	}

	public void dijkstra (Point source, Point dest) {
		System.out.println("Dijkstra");
		fillQueue();
		curr = source;
		unvis.remove(source);
		curr.dist = 0;
		curr.parent = null;
		unvis.add(curr);
		while (!unvis.isEmpty()) {
			curr =  unvis.poll(); //does it update the queue?
			vis.put(curr.name,curr);//mark as visited
			for (Edge e:curr.allEdges) { 		
				if (curr == e.p1 && vis.containsKey(e.p2.name) == false) {//if current is begin of edge
					if (curr.dist+e.length < e.p2.dist) {
						Point update = e.p2; //TODO check
						update.dist = curr.dist+e.length;
						update.parent = curr;	
						unvis.remove(e.p2);
						unvis.add(update);  			
					}
				} else if (curr == e.p2 && vis.containsKey(e.p1.name) == false) {//if current is end of edge
					if (curr.dist+e.length < e.p1.dist) {
						Point update = e.p1;
						unvis.remove(e.p1);
						update.dist = curr.dist+e.length;
						update.parent = curr;
						unvis.add(update); 				
					}
				} 
			}
		}
		Point output = vis.get(dest.name);
		while (output.parent != null) { //will not add the source node - can add in the end
			path.add(output);
			output = output.parent;
		}
		path.add(source);
		Collections.reverse(path);
		for (Point p: path) {
			System.out.println(p.name);
		}
		System.out.println("Total distance: "+curr.dist);
	}



	Point k;
	ArrayList <Edge> shortest = new ArrayList <> ();
	public void prims () {
		System.out.println("Prims");
		fillQueue();
		vis = new HashMap <> ();
		path = new ArrayList <> ();
		//curr is initial node
		curr = k;
		unvis.remove(k);
		curr.dist = 0;
		curr.parent = null;
		unvis.add(curr);
		while (!unvis.isEmpty()) {		
			Point beg = curr;
			curr =  unvis.poll(); //does it update the queue?
			for (Edge e: beg.allEdges) {
				//if (shortest.contains(e) == false) {
				if (beg == e.p1 && curr == e.p2) {
					shortest.add(e);
					// System.out.println("add");
				}
				if (beg == e.p2 && curr == e.p1) {
					shortest.add(e);
					//System.out.println("add");
				}
				//	}
			}
			vis.put(curr.name,curr);//mark as visited

			for (Edge e:curr.allEdges) { 		
				if (curr == e.p1 && vis.containsKey(e.p2.name) == false) {//if current is begin of edge
					if (e.length < e.p2.dist) {
						Point update = e.p2; 
						update.dist = e.length;

						update.parent = curr;	
						//unvis.remove(e.p2);
						unvis.add(update);  			
					}
				} else if (curr == e.p2 && vis.containsKey(e.p1.name) == false) {//if current is end of edge
					if (e.length < e.p1.dist) {
						Point update = e.p1;
						//unvis.remove(e.p1);
						update.dist = e.length;

						update.parent = curr;
						unvis.add(update); 				
					}
				} 
			}
		}
		for(Edge e: shortest){
			System.out.println(e.name);//for prims
		}
	}
}
