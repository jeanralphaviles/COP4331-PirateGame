package model.map.areaeffect;

import model.entity.Entity;
import utility.decal.BlankDecal;
import utility.decal.Decal;

/**
 * @author Jean-Ralph Aviles
 */
public abstract class AreaEffect {
	private Decal decal;
	private boolean isActive = true;

	public AreaEffect() {
		setDecal(new BlankDecal());
	}

	public AreaEffect(Decal decal) {
		setDecal(decal);
	}

	/**
	 * @param entity Entity to be affected by AreaEffect
	 */
	public abstract void triggerProximityEffect(Entity entity);

	public final Decal getDecal() {
		return decal;
	}

	public final void setDecal(Decal decal) {
		this.decal = decal;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	@Override
	public String toString() {
		return "[]";
	}

	public static AreaEffect fromString(String substring) {
		// TODO Actually set the effect type
		return null;
	}
}
