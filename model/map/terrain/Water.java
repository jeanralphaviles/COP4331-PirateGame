package model.map.terrain;

import utility.decal.Decal;
import utility.decal.WaterDecal;

public final class Water extends Terrain {
	public Water() {
		super(new WaterDecal());
	}
	public Water(Decal decal) {
		super(decal);
	}

	@Override
	public boolean isPassable() {
		return false;
	}
}
