package model.map.areaeffect;

import model.entity.Entity;
import utility.decal.Decal;
import utility.decal.LevelUpAreaEffectDecal;

/**
 * @author Jean-Ralph Aviles
 */
public final class LevelUpAreaEffect extends AreaEffect {
	public LevelUpAreaEffect() {
		super(new LevelUpAreaEffectDecal());
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
