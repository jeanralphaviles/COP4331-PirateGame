package map;

import model.Entity;
import utility.Decal;

public abstract class AreaEffect {
	private Decal decal;
	
	public final Decal getDecal() {
		return decal;
	}

	public abstract void triggerProximityEffect(Entity entity);
}
