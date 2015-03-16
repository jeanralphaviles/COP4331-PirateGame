package model.entity.occupation.ability.toggleAbility;

import utility.decal.Decal;
import model.Level;
import model.entity.Entity;
import model.map.GridLocation;

public final class DetectTraps extends ToggleAbility {
	int radius;
	
	public DetectTraps() {
		super(10, "Detect Traps", new Decal(Decal.sneak));
		this.radius = 1;
	}
	
	public DetectTraps(int manaCost, int radius, Decal icon) {
		super(manaCost, "DetectTraps", icon);
		this.radius = radius;
	}
	
	@Override
	public void enterEffect(Entity caster, Level level) {
		doAction(caster, level);
	}
	
	@Override
	public void exitEffect(Entity caster, Level level) {
		for (int x = 0; x < level.getWidth(); ++x) {
			for (int y = 0; y < level.getHeight(); ++y) {
				level.getItem(new GridLocation(x, y)).setVisbility(true);
			}
		}
	}

	@Override
	public void doAction(Entity caster, Level level) {
		if (entityHasMana(caster)) {
			removeEntityMana(caster);
			GridLocation entityLocation = level.getEntityLocation(caster);
			for (int x = entityLocation.getX() - radius; x < entityLocation.getX() + radius; ++x) {
				for (int y = entityLocation.getY() - radius; y < entityLocation.getY() + radius; ++y) {
					level.getItem(new GridLocation(x, y)).setVisbility(false);
				}
			}
		} else {
			toggle();
		}
	}

}
