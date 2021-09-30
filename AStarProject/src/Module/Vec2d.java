package Module;

import java.util.HashMap;
import java.util.Objects;

import Utils.Pair;

public class Vec2d {
	private static HashMap<Pair<Double, Double>, Vec2d> instances = new HashMap<Pair<Double, Double>, Vec2d>();
	
	private double coord_x;
	private double coord_y;
	
	private Vec2d() {
		coord_x = 0.0;
		coord_y = 0.0;
	}
	
	private Vec2d(double x, double y) {
		coord_x = x;
		coord_y = y;
	}
	
	public static Vec2d getInstance() {
		Pair<Double, Double> p = new Pair<Double, Double>(0.0, 0.0);
		if (!Vec2d.instances.containsKey(p))
			Vec2d.instances.put(p, new Vec2d());
		return Vec2d.instances.get(p);
	}
	
	public static Vec2d getInstance(double x, double y) {
		Pair<Double, Double> p = new Pair<Double, Double>(x, y);
		if (!Vec2d.instances.containsKey(p))
			Vec2d.instances.put(p, new Vec2d(x, y));
		return Vec2d.instances.get(p);
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Vec2d))
			return false;
		Vec2d v = (Vec2d) o;
			
		return coord_x == v.coord_x && coord_y == v.coord_y;
	}
	
	@Override
    public int hashCode() {
        return Objects.hash(coord_x, coord_y);
    }
	
	@Override
	public String toString() {
		return "(" + coord_x + ", " + coord_y + ")";
	}
	
	public double getX() { return coord_x; }
	public double getY() { return coord_y; }
	
	public void setX(double x) { coord_x = x; }
	public void setY(double y) { coord_y = y; }

	public void setLoc(double x, double y) {
		coord_x = x;
		coord_y = y;
	}
}