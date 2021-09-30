package Module;

import java.util.*;

public class Node implements Comparable<Node> {
	private static HashMap<Vec2d, Node> instances = new HashMap<Vec2d, Node>();
	
	private Vec2d pos, parent;
	private double f, g, h;
	private HashMap<Vec2d, Double> edge_map;
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Node))
			return false;
		Node n = (Node) o;
			
		return pos.equals(n.pos) && f == n.f;
	}
	
	@Override
	public int compareTo(Node n) {
		if (this.f > n.f)
			return 1;
		else if (this.f == n.f)
			return 0;
		else
			return -1;
	}
	
	@Override
    public int hashCode() {
        return Objects.hash(pos);
    }
	
	@Override
	public String toString() {
		return pos.toString();
	}
	
	private Node(Vec2d pos) {
		this.pos = pos;
		this.parent = Vec2d.getInstance(-200, -200);
		this.f = 0;
		this.g = 0;
		this.h = 0;
		this.edge_map = new HashMap<Vec2d, Double>();
	}
	
	public static Node getInstance(Vec2d pos) {
		if (!(Node.instances.containsKey(pos)))
			Node.instances.put(pos, new Node(pos));
		return Node.instances.get(pos);
	}
	
	public Vec2d getPos() { return pos; }
	
	public void setPos(Vec2d pos) {
		Node.instances.remove(this.pos);
		this.pos = pos;
		Node.instances.put(this.pos, this);
	}

	public Vec2d getParent() { return parent; }

	public void setParent(Vec2d parent) { this.parent = parent; }

	public double getF() { return f; }

	public void setF(double f) { this.f = f; }

	public double getG() { return g; }

	public void setG(double g) { this.g = g; }

	public double getH() { return h; }

	public void setH(double h) { this.h = h; }
	
	public void AddEdge(Vec2d vec, double dist) { edge_map.put(vec, dist); }
	
	public void AddEdge(double x, double y, double dist) {
		Vec2d vec = Vec2d.getInstance(x, y);
		edge_map.put(vec, dist);
	}
	
	public void CleanEdge() { edge_map.clear(); }
	
	public void RemoveEdge(Vec2d vec) { edge_map.remove(vec); }
	
	public double GetDistanceToNode(Vec2d to) {
		if (edge_map.containsKey(to))
			return edge_map.get(to);
		else
			return -1.0;
	}
	
	public Set<Vec2d> GetEdgeList() { return edge_map.keySet(); }
	
	public int GetEdgeCount() { return edge_map.keySet().size(); }
}