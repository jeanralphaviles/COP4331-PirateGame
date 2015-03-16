package model.projectile;

import model.entity.Entity;
import utility.Course;
import utility.decal.Decal;

/**
 * @author Jean-Ralph Aviles
 */
public class Projectile implements Cloneable {
	Course course;
	CollisionEffect collisionEffect;
	Decal decal;
	
	public Projectile() {
		setCourse(new Course(0, 1));
		setCollisionEffect(new CollisionEffect());
		setDecal(new Decal(Decal.take_damage));
	}
	
	public Projectile(Course course, CollisionEffect collisionEffect, Decal decal) {
		setCourse(course);
		setCollisionEffect(collisionEffect);
		setDecal(decal);
	}
	
	public void triggerEffect(Entity entity, int distance) {
		collisionEffect.triggerEffect(entity, distance);
	}
	
	@Override
	public Projectile clone() {
		return new Projectile(this.course, this.collisionEffect, this.decal);
	}
	
	@Override
	public String toString() {
		return "[" + course.toString() + "," + collisionEffect.toString() + "," + decal.toString() + "]";
	}
	
	public static Projectile fromString(String string) {
		String stripped = string.substring(1, string.length() - 1);
		if (stripped.equals("null")) {
			return null;
		}
		Projectile projectile = new Projectile();
		int bracketCount = 0;
		int start = 0;
		int itemCount = 0;
		for (int i = 0; i < stripped.length(); ++i) {
			if (bracketCount == 0 && stripped.charAt(i) == ',') {
				if (itemCount == 0) {
					// Course
					projectile.setCourse(Course.fromString(stripped.substring(start, i)));
				} else if (itemCount == 1) {
					// CollisionEffect
					projectile.setCollisionEffect(CollisionEffect.fromString(stripped.substring(start, i)));
					// Decal
					projectile.setDecal(Decal.fromString(stripped.substring(i + 1, stripped.length())));
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
		return projectile;
	}
	
	public int getEffectRadius() {
		return collisionEffect.getRadius();
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	
	public CollisionEffect getCollisionEffect() {
		return collisionEffect;
	}
	
	public void setCollisionEffect(CollisionEffect collisionEffect) {
		this.collisionEffect = collisionEffect;
	}

	public Decal getDecal() {
		return decal;
	}

	public void setDecal(Decal decal) {
		this.decal = decal;
	}
	
	public static void main(String[] args) {
		Projectile orig = new Projectile();
		orig.setCourse(new Course(1, 1));
		orig.setCollisionEffect(new CollisionEffect(2, 2, 2));
		Projectile restored = Projectile.fromString(orig.toString());
		
		if (orig.toString().equals(restored.toString()) == false) {
			System.out.println("Failed case #1");
		}
		if (orig.getCollisionEffect().toString().equals(restored.getCollisionEffect().toString()) == false) {
			System.out.println("Collision Effects Differ");
		}
		if (orig.getCourse().toString().equals(restored.getCourse().toString()) == false) {
			System.out.println("Courses differ");
		}
		if (orig.getDecal().toString().equals(restored.getDecal().toString()) == false) {
			System.out.println("Decals differ");
		}
	}

}
