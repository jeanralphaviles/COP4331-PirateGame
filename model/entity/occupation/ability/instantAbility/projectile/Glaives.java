package model.entity.occupation.ability.instantAbility.projectile;

import model.Level;
import model.entity.Entity;
import model.map.GridLocation;
import model.projectile.CollisionEffect;
import model.projectile.Projectile;
import utility.Course;
import utility.decal.Decal;

public final class Glaives extends ProjectileAbility {
	
	public Glaives() {
		super(15, "Glaives", new Projectile(new Course(0, 0), new CollisionEffect(50, 1, 1), Decal.createDecal(Decal.glaives)), Decal.createDecal(Decal.glaives));
	}
	
	@Override
	public void activate(Entity caster, Level level) {
		if (entityHasMana(caster)) {
			GridLocation casterLocation = level.getEntityLocation(caster);
			
			Course directionFacing = caster.getDirectionFacing();
			Course projectile1Course;
			Course projectile2Course;
			Course projectile3Course = directionFacing;
			int xDisplacment = directionFacing.getXDisplacement();
			int yDisplacement = directionFacing.getYDisplacement();
			if (xDisplacment == 0) {
				// Facing Up or Down
				projectile1Course = new Course(1, yDisplacement);
				projectile2Course = new Course(-1, yDisplacement);
			} else if (yDisplacement == 0) {
				// Facing Left or Right
				projectile1Course = new Course(xDisplacment, 1);
				projectile2Course = new Course(xDisplacment, -1);
			} else {
				// Corners
				projectile1Course = new Course(xDisplacment, 0);
				projectile2Course = new Course(0, yDisplacement);
			}
			
			Projectile projectile1 = this.getProjectile().clone();
			Projectile projectile2 = this.getProjectile().clone();
			Projectile projectile3 = this.getProjectile().clone();
			
			projectile1.setCourse(projectile1Course);
			projectile2.setCourse(projectile2Course);
			projectile3.setCourse(projectile3Course);
			
			GridLocation projectile1Location = casterLocation.nextGridLocation(projectile1Course);
			GridLocation projectile2Location = casterLocation.nextGridLocation(projectile2Course);
			GridLocation projectile3Location = casterLocation.nextGridLocation(projectile3Course);
			level.addProjectile(projectile1, projectile1Location);
			level.addProjectile(projectile2, projectile2Location);
			level.addProjectile(projectile3, projectile3Location);
			removeEntityMana(caster);
		}
	}

}
