package model.map.areaeffect;

import utility.decal.Decal;
import utility.decal.HealDamageAreaEffectDecal;
import model.entity.Entity;

/**
 * @author Jean-Ralph Aviles
 */
public final class HealDamageAreaEffect extends AreaEffect {
	private int damageHealed = 3;
	
	public HealDamageAreaEffect() {
		super(new HealDamageAreaEffectDecal());
	}
	
	public HealDamageAreaEffect(Decal decal) {
		super(decal);
	}

	public final int getDamageHealed() {
		return damageHealed;
	}

	public final void setDamageHealed(int damageHealed) {
		this.damageHealed = damageHealed;
	}

	@Override
	public void triggerProximityEffect(Entity entity) {
		if (isActive()) {
			entity.getFullStatistics().changeHealth(damageHealed);
			setActive(false);
		}
	}
}
