package map;

import utility.Decal;

public final class Water extends Terrain {
	public Water() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Water(Decal decal) {
		super(decal);
	}

	@Override
	public boolean isPassable() {
		return false;
	}
}
