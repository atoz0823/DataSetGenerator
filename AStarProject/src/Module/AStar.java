package Module;

import java.util.*;

public class AStar {
	public int m_weight;
	public Vec2d m_dimensions;
	public Vec2d m_startPos;
	public Vec2d m_targetPos;
	public PriorityQueue<Node> m_openList = new PriorityQueue<Node>();
	public HashMap<Vec2d, Boolean> m_closedList = new HashMap<Vec2d, Boolean>();
	public HashMap<Vec2d, Node> m_cameFrom = new HashMap<Vec2d, Node>();
	public HeuristicFunction m_heuristic;
	
	public AStar() {
		m_weight = 1;
		m_dimensions = Vec2d.getInstance();
		m_startPos = Vec2d.getInstance();
		m_targetPos = Vec2d.getInstance();
	}
	
	public void findPath(Vec2d startPos, Vec2d targetPos, HeuristicFunction heuristicFunc, int weight) {
		m_startPos = startPos;
		m_targetPos = targetPos;
		m_weight = weight;
		
		Node.getInstance(m_startPos).setParent(m_startPos);

		m_cameFrom.put(m_startPos, Node.getInstance(m_startPos));
		m_openList.add(Node.getInstance(m_startPos));

		double fNew, gNew, hNew;
		Vec2d currentPos;

		while (!m_openList.isEmpty()) {
			// Get the node with the least f value
			currentPos = m_openList.peek().getPos();

			if (currentPos == m_targetPos)
				break;

			m_openList.remove(Node.getInstance(currentPos));
			m_closedList.put(currentPos, true);

			// Check the neighbors of the current node
			for (Vec2d vec : Node.getInstance(currentPos).GetEdgeList()) {
				Node cur = m_cameFrom.get(currentPos);
				Node neighbor = Node.getInstance(vec);
				
				if (neighbor.GetEdgeList().isEmpty() ||
						(m_closedList.containsKey(vec) && m_closedList.get(vec) == true)) continue;

				gNew = cur.getG() + cur.GetDistanceToNode(vec);
				hNew = 0;
				fNew = gNew + hNew;

				if (!m_cameFrom.containsKey(vec) || m_cameFrom.get(vec).getF() == 0 || fNew < m_cameFrom.get(vec).getF()) {
					neighbor.setParent(currentPos);
					neighbor.setF(fNew);
					neighbor.setG(gNew);
					neighbor.setH(hNew);
					m_openList.add(neighbor);
					m_cameFrom.put(vec, neighbor);
				}
			}
		}
		
		buildPath();
	}
	
	public Vector<Vec2d> buildPath() {
		Vector<Vec2d> path = new Vector<Vec2d>();
		Vec2d currentPos = m_targetPos;
		boolean canFind = true;
		
		do {
			path.add(currentPos);
			if (!m_cameFrom.containsKey(currentPos)) {
				canFind = false;
				break;
			}
			
			currentPos = m_cameFrom.get(currentPos).getParent();

			if (currentPos.getX() <= -200)
				break;

		} while (!(m_cameFrom.get(currentPos).getParent() == currentPos));
		
		if (canFind) path.add(m_startPos);
		Collections.reverse(path);
		
		for (Vec2d vec : path) {
			if (path.lastElement() == vec) 
				System.out.print(vec);
			else
				System.out.print(vec + " -> ");
		}
		System.out.println();
		
		return path;
	}
}