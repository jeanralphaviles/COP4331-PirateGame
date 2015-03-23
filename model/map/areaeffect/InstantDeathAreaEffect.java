package model.map.areaeffect;

import utility.decal.Decal;
import model.Level;
import model.entity.Entity;

/**
 * @author Jean-Ralph Aviles
 */
public final class InstantDeathAreaEffect extends AreaEffect {
	public InstantDeathAreaEffect() {
		super(Decal.createDecal(Decal.instant_death));
	}
	
	public InstantDeathAreaEffect(Decal decal) {
		super(decal);
	}

	@Override
	public void triggerProximityEffect(Entity entity, Level level) {
		if (isActive()) {
			entity.getStatistics().kill();
		}
	}

	@Override
	public String toString() {
		int isActive = isActive() ? 1 : 0;
		return "[InstantDeathAreaEffect," + isActive + "," + getDecal().toString() + "]";
	}
	
	public static InstantDeathAreaEffect fromString(String string) {
		String stripped = string.substring(24, string.length() - 1);
		boolean isActive = Integer.parseInt(stripped.substring(0, 1)) == 1;
		stripped = stripped.substring(2);
		Decal decal = Decal.fromString(stripped.substring(stripped.indexOf(',') + 1));
		InstantDeathAreaEffect instantDeathAreaEffect = new InstantDeathAreaEffect(decal);
		instantDeathAreaEffect.setActive(isActive);
		return instantDeathAreaEffect;
	}
	
	public static void main(String[] args) {
		InstantDeathAreaEffect orig = new InstantDeathAreaEffect();
		orig.setActive(false);
		InstantDeathAreaEffect restored = InstantDeathAreaEffect.fromString(((AreaEffect)orig).toString());
		if (!orig.getDecal().toString().equals(restored.getDecal().toString())) {
			System.out.println("Failed case #1");
		}
		if (orig.isActive() != restored.isActive()) {
			System.out.println("Failed case #2");
		}
	}
}
