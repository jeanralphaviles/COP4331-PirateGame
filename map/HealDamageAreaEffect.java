package map;

public abstract class HealDamageAreaEffect extends AreaEffect {
	private int damageHealed;

	public final int getDamageHealed() {
		return damageHealed;
	}

	public final void setDamageHealed(int damageHealed) {
		this.damageHealed = damageHealed;
	}
}
