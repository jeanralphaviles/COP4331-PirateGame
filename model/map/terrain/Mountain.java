package model.map.terrain;

import model.entity.Entity;
import model.projectile.Projectile;
import utility.decal.Decal;

/**
 * @author Jean-Ralph Aviles
 */
public final class Mountain extends Terrain {

	public Mountain() {
		super(Decal.createDecal(Decal.mountain));
	}

	public Mountain(Decal decal) {
		super(decal);
	}

	@Override
	public boolean isPassable(Entity entity) {
		return false;
	}

	@Override
	public boolean isPassable(Projectile projectile) {
		return false;
	}
}
