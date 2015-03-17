package model.map;

import java.util.ArrayList;

import utility.decal.Decal;
import model.Level;
import model.entity.Entity;
import model.map.areaeffect.AreaEffect;
import model.map.areaeffect.NullAreaEffect;
import model.map.terrain.Grass;
import model.map.terrain.Mountain;
import model.map.terrain.Terrain;
import model.projectile.Projectile;

/**
 * @author Jean-Ralph Aviles
 */
public class Maptile {
	Terrain terrain;
	AreaEffect areaEffect;
	
	public Maptile() {
		setTerrain(new Grass());
		setAreaEffect(new NullAreaEffect());
	}
	
	public ArrayList<Decal> getDecals() {
		ArrayList<Decal> decals = new ArrayList<Decal>();
		if (!(areaEffect instanceof NullAreaEffect)) {
			decals.add(areaEffect.getDecal());
		}
		decals.add(terrain.getDecal());
		return decals;
	}
	
	@Override
	public String toString() {
		return "[" + terrain.toString() + "," + areaEffect.toString() + "]";
	}
	
	public static Maptile fromString(String string) {
		String stripped = string.substring(1, string.length() - 1);
		Maptile maptile = new Maptile();
		int bracketCount = 0;
		int start = 0;
		for (int i = 0; i < stripped.length(); ++i) {
			if (bracketCount == 0 && stripped.charAt(i) == ',') {
				Terrain terrain = Terrain.fromString(stripped.substring(start, i));
				AreaEffect areaEffect = AreaEffect.fromString(stripped.substring(i + 1, stripped.length()));
				maptile.setTerrain(terrain);
				maptile.setAreaEffect(areaEffect);
				break;
			} else if (stripped.charAt(i) == '[') {
				++bracketCount;
			} else if (stripped.charAt(i) == ']') {
				--bracketCount;
			}
		}
		return maptile;
	}
	
	public void triggerProximityEffect(Entity entity, Level level) {
		areaEffect.triggerProximityEffect(entity, level);
	}
	
	public boolean isPassable(Entity entity) {
		return terrain.isPassable(entity);
	}
	
	public boolean isPassable(Projectile projectile) {
		return terrain.isPassable(projectile);
	}

	public Terrain getTerrain() {
		return terrain;
	}

	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
	}

	public AreaEffect getAreaEffect() {
		return areaEffect;
	}

	public void setAreaEffect(AreaEffect areaEffect) {
		this.areaEffect = areaEffect;
	}
	
	public static void main(String[] args) {
		Maptile orig = new Maptile();
		orig.setTerrain(new Mountain());
		Maptile restored = Maptile.fromString(orig.toString());
		if (orig.getTerrain().toString().equals(restored.getTerrain().toString()) == false) {
			System.out.println("Failed case #1");
		}
		if (orig.getAreaEffect().toString().equals(restored.getAreaEffect().toString()) == false) {
			System.out.println("Failed case #2");
		}
	}
}
