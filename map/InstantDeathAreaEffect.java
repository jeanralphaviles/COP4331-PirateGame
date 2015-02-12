package map;

import utility.Decal;
import model.Entity;

/**
 * @author Jean-Ralph Aviles
 */
public final class InstantDeathAreaEffect extends AreaEffect {
	public InstantDeathAreaEffect() {
		super();
	}
	
	public InstantDeathAreaEffect(Decal decal) {
		super(decal);
	}

	@Override
	public void triggerProximityEffect(Entity entity) {
		// TODO ask about a entity.kill() function
		if (isActive()) {
			entity.getFullStatistics().changeHealth(-1*entity.getFullStatistics().getHealth());
			setActive(false);
		}
	}

}
