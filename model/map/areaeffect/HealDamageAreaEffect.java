package model.map.areaeffect;

import utility.decal.Decal;
import model.Level;
import model.entity.Entity;

/**
 * @author Jean-Ralph Aviles
 */
public final class HealDamageAreaEffect extends AreaEffect {
    private int damageHealed = 3;

    public HealDamageAreaEffect() {
        super(Decal.createDecal(Decal.heal_damage));
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
    public void triggerProximityEffect(Entity entity, Level level) {
        if (isActive()) {
            entity.getStatistics().changeCurrentHealth(damageHealed);
        }
    }

	@Override
	public String toString() {
		int isActive = isActive() ? 1 : 0;
		return "[HealDamageAreaEffect," + damageHealed + "," + isActive + "," + getDecal().toString() + "]";
	}
	
	public static HealDamageAreaEffect fromString(String string) {
		String stripped = string.substring(1, string.length() - 1);
		String[] parts = stripped.split(",");
		int damageHealed = Integer.parseInt(parts[1]);
		boolean isActive = Integer.parseInt(parts[2]) == 1;
		StringBuilder decalString = new StringBuilder();
		for (int i = 3; i < parts.length; ++i) {
			decalString.append(parts[i]);
		}
		Decal decal = Decal.fromString(decalString.toString());
		HealDamageAreaEffect healDamageAreaEffect = new HealDamageAreaEffect(decal);
		healDamageAreaEffect.setDamageHealed(damageHealed);
		healDamageAreaEffect.setActive(isActive);
		return healDamageAreaEffect;
	}
	
	public static void main(String[] args) {
		HealDamageAreaEffect healDamageAreaEffect = new HealDamageAreaEffect();
		healDamageAreaEffect.setDamageHealed(20);
		healDamageAreaEffect.setActive(false);
		HealDamageAreaEffect restoredHealDamageAreaEffect = HealDamageAreaEffect.fromString(((AreaEffect)healDamageAreaEffect).toString());
		String orignalDecalString = healDamageAreaEffect.getDecal().toString();
		String restoredDecalString = restoredHealDamageAreaEffect.getDecal().toString();
		if (!orignalDecalString.equals(restoredDecalString)) {
			System.out.println("Failed case #1: Decals are not the same");
		}
		if (healDamageAreaEffect.getDamageHealed() != restoredHealDamageAreaEffect.getDamageHealed()) {
			System.out.println("Failed case #2: Damage Healed is not the same");
		}
		if (healDamageAreaEffect.isActive() != restoredHealDamageAreaEffect.isActive()) {
			System.out.println("Failed case #3");
		}
	}
}
