package map;

import utility.decal.Decal;
import utility.decal.MountainDecal;

public final class Mountain extends Terrain {
	public Mountain() {
		super(new MountainDecal());
	}

	public Mountain(Decal decal) {
		super(decal);
	}

	@Override
	public boolean isPassable() {
		return false;
	}
}
