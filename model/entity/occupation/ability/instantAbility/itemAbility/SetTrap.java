package model.entity.occupation.ability.instantAbility.itemAbility;

import utility.decal.Decal;
import model.Level;
import model.entity.Entity;
import model.entity.Statistics;
import model.item.OneShotItem;
import model.map.GridLocation;

public final class SetTrap extends ItemAbility {
	
	public SetTrap() {
		super(new OneShotItem(Decal.createDecal(Decal.take_damage), "Trap", new Statistics(0, 0, 0, 0, 0, 0, 0, -20, 0)), "Set Trap", Decal.createDecal(Decal.take_damage));
		this.getItem().setVisbility(false);
		this.setManaCost(5);
	}

	@Override
	public void activate(Entity caster, Level level) {
		if (entityHasMana(caster)) {
			GridLocation entityLocation = level.getEntityLocation(caster);
			GridLocation trapLocation = entityLocation.nextGridLocation(caster.getDirectionFacing());
			level.addItem(getItem().clone(), trapLocation);
			removeEntityMana(caster);
		}
	}

}
