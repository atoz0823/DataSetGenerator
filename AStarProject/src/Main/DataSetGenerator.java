package Main;

import java.io.File;
import java.util.*;

import Module.AStar;
import Module.Node;
import Utils.JSONReader;

public class DataSetGenerator {
	public static ArrayList<Node> node_List = new ArrayList<Node>();
	
	public static void main(String[] args) {
		File f = new File("Edge_Data.json");
		JSONReader.getEdgeData(f);
		
		Node n1, n2;
		AStar astar = new AStar();
		
		Collections.shuffle(node_List);
		n1 = node_List.get(0);
		n2 = node_List.get(1);
		
		astar.findPath(n1.getPos(), n2.getPos(), null, 0);
	}
}