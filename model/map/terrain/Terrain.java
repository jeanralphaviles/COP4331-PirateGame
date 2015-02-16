package model.map.terrain;

import utility.decal.Decal;
/** Determines if tile is passable or not depending on terrain type*/

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
