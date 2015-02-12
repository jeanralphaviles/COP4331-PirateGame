package map;

import utility.Decal;

public final class Mountain extends Terrain {
	public Mountain() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Mountain(Decal decal) {
		super(decal);
	}

	@Override
	public boolean isPassable() {
		return false;
	}
}
