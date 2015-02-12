package map;

public final class Water extends Terrain {
	@Override
	public boolean isPassable() {
		return false;
	}
}
