package map;

public abstract class TakeDamageAreaEffect extends AreaEffect {
	private int damageTaken;
	
	public final int getDamageTaken() {
		return damageTaken;
	}

	public void setDamageTaken(int damageTaken) {
		this.damageTaken = damageTaken;
	}
}
