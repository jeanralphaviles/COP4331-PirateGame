package model.map.areaeffect;

import model.entity.Entity;
import utility.decal.Decal;
import utility.decal.TakeDamageAreaEffectDecal;

/**
 * @author Jean-Ralph Aviles
 */
public final class TakeDamageAreaEffect extends AreaEffect {
	private int damageTaken = 3;
	
	public TakeDamageAreaEffect() {
		super(new TakeDamageAreaEffectDecal());
	}
	
	public TakeDamageAreaEffect(Decal decal, int damageTaken) {
		super(decal);
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
