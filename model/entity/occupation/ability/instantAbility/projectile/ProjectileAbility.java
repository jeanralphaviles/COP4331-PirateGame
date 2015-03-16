package model.entity.occupation.ability.instantAbility.projectile;


import utility.decal.Decal;
import model.Level;
import model.entity.Entity;
import model.entity.occupation.ability.instantAbility.InstantAbility;
import model.map.GridLocation;
import model.projectile.Projectile;

public abstract class ProjectileAbility extends InstantAbility {
	private Projectile projectile;
	
	public ProjectileAbility() {
		super();
		this.setProjectile(new Projectile());
	}
	
	public ProjectileAbility(int manaCost, String name, Projectile projectile, Decal icon) {
		super(manaCost, name, icon);
		this.projectile = projectile;
	}
	
	@Override
	public void activate(Entity caster, Level level) {
		if (entityHasMana(caster)) {
			GridLocation casterLocation = level.getEntityLocation(caster);
			Projectile projectile = this.projectile.clone();
			projectile.setCourse(caster.getDirectionFacing());
			GridLocation projectileLocation = casterLocation.nextGridLocation(caster.getDirectionFacing());
			level.addProjectile(projectile, projectileLocation);
			removeEntityMana(caster);
		}
	}

	public Projectile getProjectile() {
		return projectile;
	}

	public void setProjectile(Projectile projectile) {
		this.projectile = projectile;
	}
}
