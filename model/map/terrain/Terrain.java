package model.map.terrain;

import java.io.IOException;

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

	public String toString() {
		return "[" + decal.toString() + "]";
	}
	
	public static Terrain fromString(String string) throws IOException {
		String stripped = string.substring(1, string.length() - 1);
		// TODO: Actually set the terrain type
		Terrain terrain = new Grass();
		terrain.setDecal(Decal.fromString(stripped));
		
		return terrain;
	}
}
