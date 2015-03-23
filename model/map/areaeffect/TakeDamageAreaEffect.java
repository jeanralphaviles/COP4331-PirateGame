package model.map.areaeffect;

import model.Level;
import model.entity.Entity;
import utility.decal.Decal;

/**
 * @author Jean-Ralph Aviles
 */
public final class TakeDamageAreaEffect extends AreaEffect {
	private int damageTaken = 3;
	
	public TakeDamageAreaEffect() {
		super(Decal.createDecal(Decal.take_damage));
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
	public void triggerProximityEffect(Entity entity, Level level) {
		if (isActive()) {
			entity.getStatistics().changeCurrentHealth(-1*damageTaken);
		}
	}

	@Override
	public String toString() {
		int isActive = isActive() ? 1 : 0;
		return "[TakeDamageAreaEffect," + damageTaken + "," + isActive + "," + getDecal().toString() + "]";
	}
	
	public static TakeDamageAreaEffect fromString(String string) {
		String stripped = string.substring(1, string.length() - 1);
		String[] parts = stripped.split(",");
		int damageTaken = Integer.parseInt(parts[1]);
		boolean isActive = Integer.parseInt(parts[2]) == 1;
		StringBuilder decalString = new StringBuilder();
		for (int i = 3; i < parts.length; ++i) {
			decalString.append(parts[i]);
		}
		Decal decal = Decal.fromString(decalString.toString());
		TakeDamageAreaEffect takeDamageAreaEffect = new TakeDamageAreaEffect(decal, damageTaken);
		takeDamageAreaEffect.setActive(isActive);
		return takeDamageAreaEffect;
	}
	
	public static void main(String[] args) {
		TakeDamageAreaEffect orig = new TakeDamageAreaEffect();
		orig.setActive(false);
		orig.setDamageTaken(100);
		TakeDamageAreaEffect restored = TakeDamageAreaEffect.fromString(((AreaEffect)orig).toString());
		if (restored.getDecal().toString().equals(orig.getDecal().toString()) == false) {
			System.out.println("Failed case #1");
		}
		if (restored.isActive() != orig.isActive()) {
			System.out.println("Failed case #2");
		}
		if (restored.getDamageTaken() != orig.getDamageTaken()) {
			System.out.println("Failed case #3");
		}
	}
}
