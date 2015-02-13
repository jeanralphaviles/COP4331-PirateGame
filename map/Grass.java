package map;

import utility.Decal;
import utility.GrassDecal;

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
