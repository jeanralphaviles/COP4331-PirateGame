package model.projectile;

import model.entity.Entity;

/**
 * @author Jean-Ralph Aviles
 */
public class CollisionEffect {
	int damage;
	int radius;
	double damageDistanceScaler;
	
	public CollisionEffect() {
		damage = 10;
		radius = 1;
		damageDistanceScaler = 1;
	}
	
	public CollisionEffect(int damage, int radius, double damageDistanceScaler) {
		setDamage(damage);
		setRadius(radius);
		setDamageDistanceScaler(damageDistanceScaler);
	}
	
	public void triggerEffect(Entity entity, int distance) {
		if (distance <= radius) {
			int damageDealt = (int) (damage * Math.pow(damageDistanceScaler, distance));
			entity.getStatistics().changeCurrentHealth(-1 * damageDealt);
		}
	}
	
	@Override
	public String toString() {
		return "[" + damage + "," + radius + "," + damageDistanceScaler + "]";
	}
	
	public static CollisionEffect fromString(String string) {
		String stripped = string.substring(1, string.length() - 1);
		String[] parts = stripped.split(",");
		int damage = Integer.parseInt(parts[0]);
		int radius = Integer.parseInt(parts[1]);
		double damageDistanceScaler = Double.parseDouble(parts[2]);
		return new CollisionEffect(damage, radius, damageDistanceScaler);
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public double getDamageDistanceScaler() {
		return damageDistanceScaler;
	}

	public void setDamageDistanceScaler(double damageDistanceScaler) {
		this.damageDistanceScaler = damageDistanceScaler;
	}
	
	public static void main(String[] args) {
		CollisionEffect orig = new CollisionEffect(4, 4, 4);
		orig.setRadius(2);
		CollisionEffect restored = CollisionEffect.fromString(orig.toString());
		if (restored.toString().equals(orig.toString()) == false) {
			System.out.println("Serialized string differ");
		}
		if (restored.getDamage() != orig.getDamage()) {
			System.out.println("Damages differ");
		}
		if (restored.getDamageDistanceScaler() != orig.getDamageDistanceScaler()) {
			System.out.println("DistanceScalers differ");
		}
		if (restored.getRadius() != orig.getRadius()) {
			System.out.println("Radii differ");
		}
	}
}
