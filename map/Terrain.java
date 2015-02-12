package map;

import utility.Decal;

public abstract class Terrain {
	private Decal decal;
	
	public Terrain() {

	}
	
	public Terrain(Decal decal) {
		setDecal(decal);
	}

	public abstract boolean isPassable();

	public final Decal getDecal() {
		return decal;
	}

	public final void setDecal(Decal decal) {
		this.decal = decal;
	}
}
