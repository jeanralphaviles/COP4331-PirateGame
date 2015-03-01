package model.map.areaeffect;

import model.entity.Entity;
import utility.decal.Decal;

/**
 * @author Jean-Ralph Aviles
* Decrements health by 3 points
 */
public final class TakeDamageAreaEffect extends AreaEffect {
	private int damageTaken = 3;
	
	public TakeDamageAreaEffect() {
		super(new Decal(Decal.take_damage));
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
			entity.getStatistics().changeCurrentHealth(-1*damageTaken);
		}
	}
}
