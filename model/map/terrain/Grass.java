package model.map.terrain;

import model.entity.Entity;
import model.projectile.Projectile;
import utility.decal.Decal;

/**
 * @author Jean-Ralph Aviles
 */
public final class Grass extends Terrain {

    public Grass() {
        super(new Decal(Decal.grass));
    }

    public Grass(Decal decal) {
        super(decal);
    }

	@Override
	public boolean isPassable(Entity entity) {
		return true;
	}

	@Override
	public boolean isPassable(Projectile projectile) {
		return true;
	}
}
