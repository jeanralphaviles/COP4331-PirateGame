package model.map.areaeffect;

import utility.decal.Decal;
import model.Level;
import model.entity.Entity;
import model.map.GridLocation;

public final class TeleportAreaEffect extends AreaEffect {
	private GridLocation destination;
	
	public TeleportAreaEffect() {
		super(new Decal(Decal.map));
		destination = new GridLocation();
	}
	
	public TeleportAreaEffect(GridLocation destination) {
		super(new Decal(Decal.map));
		this.destination = destination;
	}

	@Override
	public void triggerProximityEffect(Entity entity, Level level) {
		level.moveEntity(entity, getDestination());
	}

	@Override
	public String toString() {
		int isActive = isActive() ? 1 : 0;
		return "[TeleportAreaEffect," + destination.toString() + "," + getDecal().toString() + "," + isActive + "]";
	}
	
	public static TeleportAreaEffect fromString(String string) {
		String stripped = string.substring(20, string.length() - 1);
		TeleportAreaEffect teleportAreaEffect = new TeleportAreaEffect();
		int start = 0;
		int itemCount = 0;
		int bracketCount = 0;
		for (int i = 0; i < stripped.length(); ++i) {
			if (bracketCount == 0 && stripped.charAt(i) == ',') {
				if (itemCount == 0) {
					teleportAreaEffect.setDestination(GridLocation.fromString(stripped.substring(start, i)));
				} else if (itemCount == 1) {
					teleportAreaEffect.setDecal(Decal.fromString(stripped.substring(start, i)));
					teleportAreaEffect.setActive(Integer.parseInt(stripped.substring(i + 1)) == 1);
					break;
				}
				++itemCount;
				start = i + 1;
			} else if (stripped.charAt(i) == '[') {
				++bracketCount;
			} else if (stripped.charAt(i) == ']') {
				--bracketCount;
			}
		}
		return teleportAreaEffect;
	}

	public GridLocation getDestination() {
		return destination;
	}

	public void setDestination(GridLocation destination) {
		this.destination = destination;
	}
	
	public static void main(String[] args) {
		TeleportAreaEffect original = new TeleportAreaEffect(new GridLocation(1, 2));
		original.setActive(false);
		TeleportAreaEffect restored = TeleportAreaEffect.fromString(original.toString());
		if (original.toString().equals(restored.toString()) == false) {
			System.out.println("Serialized Strings differ");
		}
		if (original.getDestination().toString().equals(restored.getDestination().toString()) == false) {
			System.out.println("Destinations differ");
		}
		if (original.isActive() != restored.isActive()) {
			System.out.println("Active flags differ");
		}
		if (original.getDecal().toString().equals(restored.getDecal().toString()) == false) {
			System.out.println("Decals differ");
		}
	}

}
