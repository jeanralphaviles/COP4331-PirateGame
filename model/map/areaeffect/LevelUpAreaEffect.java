package model.map.areaeffect;

import model.entity.Entity;
import utility.decal.Decal;

/**
 * @author Jean-Ralph Aviles
* increases experience by enough so the entity can level up.
 */
public final class LevelUpAreaEffect extends AreaEffect {
	public LevelUpAreaEffect() {
		super(new Decal(Decal.level_up));
	}

	public LevelUpAreaEffect(Decal decal) {
		super(decal);
	}

	@Override
	public void triggerProximityEffect(Entity entity) {
		if (isActive()) {

			int expToNextLevel = 100 - entity.getStatistics().getExperience() % 100;
			entity.getStatistics().addExperience(expToNextLevel);
			setActive(false);
		}
	}

}
