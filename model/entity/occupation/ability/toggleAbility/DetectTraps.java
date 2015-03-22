package model.entity.occupation.ability.toggleAbility;

import utility.decal.Decal;
import model.Level;
import model.entity.Entity;
import model.item.Item;
import model.map.GridLocation;

public final class DetectTraps extends ToggleAbility {
	int radius;
	
	public DetectTraps() {
		super(1, "Detect Traps", new Decal(Decal.sneak));
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
				Item item = level.getItem(new GridLocation(x, y));
				if (item != null) {
					item.setVisbility(true);
				}
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
					Item item = level.getItem(new GridLocation(x, y));
					if (item != null) {
						item.setVisbility(true);
					}
				}
			}
		} else {
			toggle();
		}
	}

}
