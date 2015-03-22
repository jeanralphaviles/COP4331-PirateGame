package model.entity;

import java.util.ArrayList;

import utility.Course;
import utility.decal.Decal;
import model.Level;
import model.entity.occupation.Occupation;
import model.entity.occupation.Smasher;
import model.entity.occupation.Summoner;
import model.entity.occupation.ability.Ability;
import model.entity.occupation.ability.toggleAbility.ToggleAbility;
import model.inventory.EquippedInventory;
import model.inventory.Inventory;
import model.item.Item;

/**
 * @author Jean-Ralph Aviles
 */
public class Entity {
	protected Statistics statistics;
	protected Occupation occupation;
	protected Inventory inventory;
	protected EquippedInventory equippedInventory;
	protected Decal decal;
	protected Course directionFacing;
	protected static String[] dialog = {"Arrrg! Friend or foe?", "Arrg, pleasure to speak to ya", "ARRRRGGG!!!1"};
	protected boolean friendly = true;
	
	public Entity() {
		setStatistics(new Statistics());
		setOccupation(new Smasher());
		setInventory(new Inventory(statistics.getInventoryCapacity()));
		setEquippedInventory(new EquippedInventory());
		setDecal(new Decal(Decal.smasher));
		setDirectionFacing(new Course(1, 1));
		getStatistics().setLivesLeft(1);
	}
	
	public Entity(Decal decal) {
		this();
		setDecal(decal);
	}
	
	public Entity(Occupation occupation, Statistics statistics, Decal decal) {
		this();
		setOccupation(occupation);
		setStatistics(statistics);
		setDecal(decal);
	}
	
	public void activateAbility(Ability ability, Level level) {
		if (hasAbility(ability)) {
			ability.activate(this, level);
		}
	}
	
	public boolean hasAbility(Ability ability) {
		return getAbilities().contains(ability);
	}
	
	public boolean storeItem(Item item) {
		return inventory.storeItem(item);
	}
	
	public boolean equipItem(Item item) {
		return equippedInventory.storeItem(item);
	}
	
	public boolean unEquipItem(Item item) {
		return equippedInventory.removeItem(item);
	}
	
	public boolean hasItem(Item item) {
		return inventory.hasItem(item) || equippedInventory.hasItem(item);
	}
	
	public void gameStep(Level level) {
		ArrayList<Ability> abilities = getAbilities();
		for (Ability ability : abilities) {
			if (ability instanceof ToggleAbility) {
				((ToggleAbility)ability).tick(this, level);
			}
		}
	}
	
	public boolean isDead() {
		return statistics.isDead();
	}

	public Statistics getStatistics() {
		return statistics;
	}
	
	public Statistics getDerivedStatistics() {
		Statistics statistics = this.statistics.clone();
		occupation.augmentStatistics(statistics);
		equippedInventory.augmentStatistics(statistics);
		return statistics;
	}
	
	public void setDirectionFacing(Course course) {
		this.directionFacing = course.getUnitCourse();
	}
	
	public Course getDirectionFacing() {
		return directionFacing;
	}
	
	public ArrayList<Ability> getAbilities() {
		return this.occupation.getAbilities();
	}
	
	public String getGreeting() {
		return dialog[0];
	}
	
	public String getAffirmative() {
		return dialog[1];
	}
	
	public String getNegative() {
		return dialog[2];
	}
	
	@Override
	public String toString() {
		int friendly = this.friendly ? 1 : 0;
		return "[" + statistics.toString() + "," + occupation.toString() + ","
				+ inventory.toString() + "," + equippedInventory.toString()
				+ "," + decal.toString() + "," + directionFacing.toString() + "," + friendly + "]";
	}
	
	public static Entity fromString(String string) {
		if (string.startsWith("[avatar")) {
			return Avatar.fromString(string);
		} else if (string.equals("[null]")) {
			return null;
		}
		String stripped = string.substring(1, string.length() - 1);
		int bracketCount = 0;
		int start = 0;
		int itemCount = 0;
		Entity entity = new Entity();
		for (int i = 0; i < stripped.length(); ++i) {
			if (bracketCount == 0 && stripped.charAt(i) == ',') {
				if (itemCount == 0) {
					// Statistics
					entity.statistics = Statistics.fromString(stripped.substring(start, i));
				} else if (itemCount == 1) {
					// Occupation
					entity.occupation = Occupation.fromString(stripped.substring(start, i));
				} else if (itemCount == 2) {
					// Inventory
					entity.inventory = Inventory.fromString(stripped.substring(start, i));
				} else if (itemCount == 3) {
					// EquippedInventory
					entity.equippedInventory = EquippedInventory.fromString(stripped.substring(start, i));
				} else if (itemCount == 4) {
					// Decal
					entity.decal = Decal.fromString(stripped.substring(start, i));
				} else if (itemCount == 5) {
					// Direction Facing
					entity.directionFacing = Course.fromString((stripped.substring(start, i)));
					entity.friendly = Integer.parseInt((stripped.substring(i + 1))) == 1;
					break;
				}
				start = i + 1;
				++itemCount;
			} else if (stripped.charAt(i) == '[') {
				++bracketCount;
			} else if (stripped.charAt(i) == ']') {
				--bracketCount;
			}
		}
		return entity;
	}

	public void setStatistics(Statistics statistics) {
		this.statistics = statistics;
	}

	public Occupation getOccupation() {
		return occupation;
	}

	public void setOccupation(Occupation occupation) {
		this.occupation = occupation;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public EquippedInventory getEquippedInventory() {
		return equippedInventory;
	}

	public void setEquippedInventory(EquippedInventory equippedInventory) {
		this.equippedInventory = equippedInventory;
	}

	public Decal getDecal() {
		return decal.getRotatedDecal(getDirectionFacing());
	}

	public void setDecal(Decal decal) {
		this.decal = decal;
	}
	
	public boolean isFriendly() {
		return friendly;
	}

	public void setFriendly(boolean friendly) {
		this.friendly = friendly;
	}

	public static void main(String[] args) {
		Entity orig = new Entity(new Decal(Decal.summoner));
		orig.setOccupation(new Summoner());
		orig.setFriendly(false);
		Entity restored = Entity.fromString(orig.toString());
		
		if (orig.toString().equals(restored.toString()) == false) {
			System.out.println("Serialized Strings differ");
		}
		if (orig.getDecal().toString().equals(restored.getDecal().toString()) == false) {
			System.out.println("Decals differ");
		}
		if (orig.getStatistics().toString().equals(restored.getStatistics().toString()) == false) {
			System.out.println("Statistics differ");
		}
		if (orig.getEquippedInventory().toString().equals(restored.getEquippedInventory().toString()) == false) {
			System.out.println("EquippedInventories differ");
		}
		if (orig.getInventory().toString().equals(restored.getInventory().toString()) == false) {
			System.out.println("Inventories differ");
		}
		if (orig.getOccupation().toString().equals(restored.getOccupation().toString()) == false) {
			System.out.println("Occupation differ");	
		}
		if (orig.getDirectionFacing().equals(restored.getDirectionFacing()) == false) {
			System.out.println("Courses differ");
		}
		if (orig.isFriendly() != restored.isFriendly()) {
			System.out.println("Friendliness differs");
		}
		
		Entity avatar = new Avatar(new Summoner(), new Decal(Decal.summoner));
		Entity restoredAvatar = Entity.fromString(avatar.toString());
		
		if (restoredAvatar.getClass().getSimpleName().equals("Avatar") == false) {
			System.out.println("Avatar not correctly reconstructed");
		}
	}
}
