package model.entity.occupation.ability.instantAbility.status;

import utility.decal.Decal;
import model.Level;
import model.entity.Entity;
import model.entity.Statistics;

public final class Boon extends StatusAbility {
	Statistics statistics;
	
	public Boon() {
		super(25, "Boon", new Decal(Decal.heal_damage));
		statistics = new Statistics(0, 0, 0, 0, 0, 0, 0, 15, 0);
	}

	@Override
	public void activate(Entity caster, Level level) {
		if (entityHasMana(caster)) {
			caster.getStatistics().changeCurrentHealth(statistics.getCurrentHealth());
			removeEntityMana(caster);
		}
	}

}
