package model.map.terrain;

import model.entity.Entity;
import model.projectile.Projectile;
import utility.decal.Decal;

/**
 * @author Jean-Ralph Aviles
 */
public final class Water extends Terrain {

    public Water() {
        super(Decal.createDecal(Decal.water));
    }

    public Water(Decal decal) {
        super(decal);
    }

	@Override
	public boolean isPassable(Entity entity) {
		return false;
	}

	@Override
	public boolean isPassable(Projectile projectile) {
		return true;
	}
}
