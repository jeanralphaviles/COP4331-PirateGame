package model.map.areaeffect;

import model.entity.Entity;

public final class NullAreaEffect extends AreaEffect {

	public NullAreaEffect() {
		super();
		setActive(false);
	}

	@Override
	public void triggerProximityEffect(Entity entity) {

	}
	
	@Override
	public String toString() {
		return "[NullAreaEffect]";
	}
	
	public static NullAreaEffect fromString(String string) {
		return new NullAreaEffect();
	}
	
	public static void main(String[] args) {
		NullAreaEffect orig = new NullAreaEffect();
		NullAreaEffect restored = NullAreaEffect.fromString(((AreaEffect)orig).toString());
		if (orig.isActive() != restored.isActive()) {
			System.out.println("Failed case #1");
		}
	}
}
