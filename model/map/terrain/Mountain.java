package model.map.terrain;

import utility.decal.Decal;

public final class Mountain extends Terrain {
	public Mountain() {
		super(new Decal(Decal.mountain));
	}

	public Mountain(Decal decal) {
		super(decal);
	}

	@Override
	public boolean isPassable() {
		return false;
	}
}
