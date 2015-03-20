package model.map.areaeffect;

import model.Level;
import model.entity.Entity;
import utility.decal.Decal;

/**
 * @author Jean-Ralph Aviles
 */

public abstract class AreaEffect {
	private Decal decal;
	private boolean isActive = true;

	public AreaEffect() {
		setDecal(new Decal(Decal.blank));
	}

	public AreaEffect(Decal decal) {
		setDecal(decal);
	}

	public abstract void triggerProximityEffect(Entity entity, Level level);

	@Override
	public abstract String toString();
	
	public static AreaEffect fromString(String string) {
		if (string.startsWith("[HealDamageAreaEffect")) {
			return HealDamageAreaEffect.fromString(string);
		} else if (string.startsWith("[InstantDeathAreaEffect")) {
			return InstantDeathAreaEffect.fromString(string);
		} else if (string.startsWith("[LevelUpAreaEffect")) {
			return LevelUpAreaEffect.fromString(string);
		} else if (string.startsWith("[TakeDamageAreaEffect")) {
			return TakeDamageAreaEffect.fromString(string);
		} else if (string.startsWith("[TeleportAreaEffect")) {
			return TeleportAreaEffect.fromString(string);
		} else { 
			return NullAreaEffect.fromString(string);
		}
	}

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
	
	public static void main(String[] args) {
		AreaEffect[] originals = {
				new HealDamageAreaEffect(),
				new InstantDeathAreaEffect(),
				new LevelUpAreaEffect(),
				new NullAreaEffect(),
				new TakeDamageAreaEffect(),
				new TeleportAreaEffect()
		};
		AreaEffect[] copies = new AreaEffect[originals.length];
		for (int i = 0; i < originals.length; ++i) {
			copies[i] = fromString(originals[i].toString());
			if (copies[i].getDecal().toString().equals(originals[i].getDecal().toString()) == false) {
				System.out.println("Decals don't match up");
			}
			if (copies[i].getClass() != originals[i].getClass()) {
				System.out.println("Classes don't match up");
			}
		}
	}
}
