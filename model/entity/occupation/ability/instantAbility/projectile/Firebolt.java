package model.entity.occupation.ability.instantAbility.projectile;

import model.projectile.CollisionEffect;
import model.projectile.Projectile;
import utility.Course;
import utility.decal.Decal;

public final class Firebolt extends ProjectileAbility {
	
	public Firebolt() {
		super(10, "Firebolt", new Projectile(new Course(0, 0), new CollisionEffect(50, 1, 1), new Decal(Decal.fire)), new Decal(Decal.fire));
	}

}
