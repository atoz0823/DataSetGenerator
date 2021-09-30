package Utils;

import java.util.Objects;

import Module.Vec2d;

public class Pair<T, S> {
	public T first;
	public S second;
	
	public Pair() {
		this.first = null;
		this.second = null;
	}
	
	@SuppressWarnings("unchecked")
	public Pair(T first, S second) {
		if (first instanceof Vec2d && second instanceof Vec2d) {
			Vec2d v1 = (Vec2d) first;
			Vec2d v2 = (Vec2d) second;
			
			if (v1.getX() > v2.getX()) {
				this.first = (T) second;
				this.second = (S) first;
				return;
			} else if (v1.getX() == v2.getX()) {
				if (v1.getY() > v2.getY()) {
					this.first = (T) second;
					this.second = (S) first;
					return;
				}
			}
		}
		
		this.first = first;
		this.second = second;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Pair))
			return false;
		Pair<?, ?> p = (Pair<?, ?>) o;
			
		return first.equals(p.first) && second.equals(p.second);
	}
	
	@Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}