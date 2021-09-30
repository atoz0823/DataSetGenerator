package Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import Main.DataSetGenerator;
import Module.Node;
import Module.Vec2d;

public class JSONReader {
	public static void getEdgeData(File f) {
		try {
			FileReader f_Reader = new FileReader(f);
			BufferedReader buf_Reader = new BufferedReader(f_Reader);
			String line = "";
			
			while ((line = buf_Reader.readLine()) != null) {
				JSONObject data = new JSONObject(line);
				double dist = data.getJSONObject("properties").getDouble("length");
				JSONArray edge = data.getJSONObject("geometry").getJSONArray("coordinates");
				ArrayList<Vec2d> vec_List = new ArrayList<Vec2d>();
				
				for (Object node : edge) {
					if (node instanceof JSONArray) {
						JSONArray loc = (JSONArray) node;
						double x = loc.getDouble(0);
						double y = loc.getDouble(1);
						Vec2d vec = Vec2d.getInstance(x, y);
						
						vec_List.add(vec);
					}
				}
				
				for (Vec2d vec : vec_List) {
					Node node = Node.getInstance(vec);
					
					for (Vec2d vec2 : vec_List) {
						if (vec != vec2) node.AddEdge(vec2, dist);
					}
					
					DataSetGenerator.node_List.add(node);
				}
			}
			
			buf_Reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}