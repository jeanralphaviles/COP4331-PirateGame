package map;

import model.Entity;
import utility.Decal;

/**
 * @author Jean-Ralph Aviles
 */
public final class TakeDamageAreaEffect extends AreaEffect {
	private int damageTaken = 3;
	
	public TakeDamageAreaEffect() {
		super();
	}
	
	public TakeDamageAreaEffect(Decal decal, int damageTaken) {
		setDecal(decal);
		setDamageTaken(damageTaken);
	}
	
	public final int getDamageTaken() {
		return damageTaken;
	}

	public void setDamageTaken(int damageTaken) {
		this.damageTaken = damageTaken;
	}

	@Override
	public void triggerProximityEffect(Entity entity) {
		if (isActive()) {
			entity.getFullStatistics().changeHealth(-1*damageTaken);
		}
	}
}
