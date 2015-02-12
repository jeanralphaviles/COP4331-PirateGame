package map;

import utility.Decal;

public final class Grass extends Terrain {
	public Grass() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Grass(Decal decal) {
		super(decal);
	}

	@Override
	public boolean isPassable() {
		return true;
	}
}
