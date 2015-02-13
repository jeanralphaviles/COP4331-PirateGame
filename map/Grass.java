package map;

import utility.decal.Decal;
import utility.decal.GrassDecal;

public final class Grass extends Terrain {
	public Grass() {
		super(new GrassDecal());
	}

	public Grass(Decal decal) {
		super(decal);
	}

	@Override
	public boolean isPassable() {
		return true;
	}
}
