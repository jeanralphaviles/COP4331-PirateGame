package map;

import utility.Decal;
import utility.MountainDecal;

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
