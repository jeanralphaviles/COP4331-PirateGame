package model.map.areaeffect;

import utility.decal.Decal;
import utility.decal.InstantDeathAreaEffectDecal;
import model.entity.Entity;

/**
 * @author Jean-Ralph Aviles
* decrements lives left by one and resets health bar
 */
public final class InstantDeathAreaEffect extends AreaEffect {
	public InstantDeathAreaEffect() {
		super(new InstantDeathAreaEffectDecal());
	}
	
	public InstantDeathAreaEffect(Decal decal) {
		super(decal);
	}

	@Override
	public void triggerProximityEffect(Entity entity) {
		if (isActive()) {
			entity.getStatistics().kill();
			setActive(false);
		}
	}

}
