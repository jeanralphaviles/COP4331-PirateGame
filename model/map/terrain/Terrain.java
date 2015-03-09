package model.map.terrain;

import model.entity.Entity;
import model.projectile.Projectile;
import utility.decal.Decal;

/**
 * @author Jean-Ralph Aviles
 */
public abstract class Terrain {
	private Decal decal;
	
	public Terrain() {

	}
	
	public Terrain(Decal decal) {
		setDecal(decal);
	}

	public abstract boolean isPassable(Entity entity);
	
	public abstract boolean isPassable(Projectile projectile);
	
	@Override
	public final String toString() {
		String className = this.getClass().getSimpleName();
		return "[" + className + "," + decal.toString() + "]";
	}
	
	public static final Terrain fromString(String string) {
		String stripped = string.substring(1, string.length() - 1);
		Decal decal = Decal.fromString(stripped.substring(stripped.indexOf(',') + 1, stripped.length()));
		Terrain terrain;
		String className = stripped.split(",")[0];
		switch(className) {
		case "Water":
			terrain = new Water(decal);
			break;
		case "Mountain":
			terrain = new Mountain(decal);
			break;
		default:
			terrain = new Grass(decal);
			break;
		}
		return terrain;
	}

	public final Decal getDecal() {
		return decal;
	}

	public final void setDecal(Decal decal) {
		this.decal = decal;
	}
	
	public static void main(String[] args) {
		Terrain[] originals = {
				new Grass(),
				new Mountain(),
				new Water()
		};
		Terrain[] restored = new Terrain[originals.length];
		for (int i = 0; i < originals.length; ++i) {
			restored[i] = Terrain.fromString(originals[i].toString());
			if (restored[i].getClass() != originals[i].getClass()) {
				System.out.println("Classes are wrong");
			}
			if (restored[i].getDecal().toString().equals(originals[i].getDecal().toString()) == false) {
				System.out.println("Decals not copied correctly");
			}
		}
	}
}
