package map;

import utility.Decal;
import model.Entity;

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
		// TODO Auto-generated method stub
		if (isActive()) {
			// Level Entity up
			setActive(false);
		}
	}

}
