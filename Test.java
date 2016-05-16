import java.io.File;
import javax.swing.JFrame;

/**
 * 
 * @author luba_les
 *
 */
public class Test extends JFrame {

	public static void main(String [] args) {
		/*String [] tokens = args.toString().split(" ");
		File file;
		file = new File(args[0]);
		Map testMap = new Map(file);
		for (int i = 1; i <tokens.length; i++)  {
			if (args[i].equals("[-show]")) {
				Test test = new Test();
				test.setVisible(true);	
				test.setTitle("Map");
				test.setSize(700,700);
				test.setDefaultCloseOperation(EXIT_ON_CLOSE);
				test.add(testMap);
			}
			if (args[i].equals( "[-meridianmap]")) {
				testMap.prims();
			}
			if (args[i].contains("[-directions")) {
				testMap.dijkstra(args[i+1],args[i+2]);
			}
		}*/
		
 Test test = new Test();
    Map testMap = new Map(new File("monroe.txt"));
	 test.add(testMap);
	 testMap.dijkstra( "i67241", "i71576");
	   // testMap.prims();
	test.setTitle("Map");
	 test.setSize(700,700);
	 test.setDefaultCloseOperation(EXIT_ON_CLOSE);
		test.setVisible(true);	

		 

	}
}