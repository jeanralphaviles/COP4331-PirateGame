package model.entity;

import model.entity.occupation.Occupation;
import model.entity.occupation.Smasher;
import model.item.*;
import utility.decal.Decal;

/**
 * @author Carlos Vizcaino Date 2/14/2015
 */
public class Avatar extends Entity {
    private String name;
    private String nickname;

    public Avatar() {
        super();
        this.name = "Richard Bonehard";

        // This specifically
        Weapon pistol = new Weapon(new Decal(Decal.item_default), "Pistol", 5, 5);
        Weapon spade = new Weapon(new Decal(Decal.item_default), "Spade", 5, 5);
        Weapon stick = new Weapon(new Decal(Decal.item_default), "Stick", 5, 5);
        Weapon arch = new Weapon(new Decal(Decal.item_default), "Arch", 5, 5);
        this.storeItem(pistol);
        this.storeItem(spade);
        this.storeItem(stick);
        this.storeItem(arch);
        this.equipItem(pistol);
    }

    public Avatar(Occupation occupation, Decal decal) {
        super(decal);
        this.occupation = occupation;
    }
    
    @Override
    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
        String occ = occupation.getOccupationName();
        switch(occ) {
            case "Summoner":
                this.decal = new Decal(Decal.summoner);
                break;
            case "Smasher":
                this.decal = new Decal(Decal.smasher);
                break;
            case "Sneak":
                this.decal = new Decal(Decal.sneak);
                break;
        }
    }
    
    @Override
	public String toString() {
    	return "[avatar" + super.toString() + "," + name + "," + nickname + "]";
    }
    
    public static Avatar fromString(String string) {
    	String stripped = string.substring(7, string.length() - 1);
    	Avatar avatar = new Avatar();
    	int bracketCount = 0;
    	int start = 0;
    	for (int i = 0; i < stripped.length(); ++i) {
    		if (bracketCount == 0 && stripped.charAt(i) == ',') {
    			Entity entity = Entity.fromString(stripped.substring(start, i));
    			avatar.decal = entity.decal;
    			avatar.equippedInventory = entity.equippedInventory;
    			avatar.inventory = entity.inventory;
    			avatar.occupation = entity.occupation;
    			avatar.statistics = entity.statistics;
    			start = i + 1;
    			break;
    		} else if (stripped.charAt(i) == '[') {
    			++bracketCount;
    		} else if (stripped.charAt(i) == ']') {
    			--bracketCount;
    		}
    	}
    	String[] rest = stripped.substring(start, stripped.length()).split(",");
    	avatar.setName(rest[0]);
    	avatar.setNickname(rest[1]);
    	return avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    // ------------ TESTING AVATAR -----------------
    // ------------                -----------------
    public static void main(String[] args) {
    	Avatar orig = new Avatar(new Smasher(), new Decal(Decal.water));
    	orig.setName("Jean-Ralph");
    	orig.setNickname("JR");
    	Avatar restored = Avatar.fromString(orig.toString());
    	
    	if (orig.toString().equals(restored.toString()) == false) {
    		System.out.println("Serialized strings are different");
    	}
    	if (orig.getName().equals(restored.getName()) == false) { 
    		System.out.println("Name are different");
    	}
    	if (orig.getNickname().equals(restored.getNickname()) == false) {
    		System.out.println("Nicknames are different");
    	}
    	

/*
        System.out.println("Testing Avatar");

        // -------- TESTING AVATAR AND AREA OF EFFECT ---------
        // --------                                   ---------
        // Create Avatar
        Avatar richard = new Avatar();

        // Create LevelUpEffect
        AreaEffect levelUp = new LevelUpAreaEffect();
        levelUp.setActive(true);

        // Create Instant death
        AreaEffect instantDeath = new InstantDeathAreaEffect();
        levelUp.setActive(true);

        // Create Deal Damage
        AreaEffect dealDamage = new TakeDamageAreaEffect();
        dealDamage.setActive(true);

        // Create Health Damage
        AreaEffect healDamage = new HealDamageAreaEffect();
        healDamage.setActive(true);

        // Results:
        levelUp.triggerProximityEffect(richard);
        if (richard.getStatistics().getLevel() != 2) {
            System.out.println("Error: level up is wrong");
        }

        int richardLives = richard.getStatistics().getLivesLeft();
        instantDeath.triggerProximityEffect(richard);
        if (richard.getStatistics().getLivesLeft() != richardLives - 1) {
            System.out.println("Error: instant death is wrong");
        }

        int richardHealth = richard.getStatistics().getCurrentHealth();
        dealDamage.triggerProximityEffect(richard);
        if (richard.getStatistics().getCurrentHealth() == richardHealth) {
            System.out.println("Error: take damage is wrong");
        }

        healDamage.triggerProximityEffect(richard);
        if (richard.getStatistics().getCurrentHealth() != richardHealth) {
            System.out.println("Error: heal damage is wrong");
        }

        // -------- TESTING AVATAR AND TAKEABLE ITEM  ---------
        // --------                                   ---------
        richard = new Avatar();
        richard.getStatistics().setHardiness(2);

        // Crate a takeable item
        int richardHardiness = richard.getStatistics().getHardiness();
        Item pistol = new Weapon(new Decal(Decal.item_default), "Pistol", 5, 5);
        pistol.triggerProximity(richard);
        if (richard.getStatistics().getHardiness() == richardHardiness) {
            System.out.println("Error: takeable item is wrong");
        }

        // -------- TESTING AVATAR AND ONE SHOT ITEM  ---------
        // --------                                   ---------
        richard = new Avatar();
        richard.getStatistics().setCurrentHealth(2);

        // Create a OneShotItem
        OneShotItem banana = new Food(new Decal(Decal.item_default), "banana", 5);

        richardHealth = richard.getStatistics().getCurrentHealth();
        banana.triggerProximity(richard);
        if (richard.getStatistics().getCurrentHealth() == richardHealth) {
            System.out.println("Error: one shot item is wrong");
        }

        // -------- TESTING AVATAR AND INTERACTIBLE ITEM  ---------
        // --------                                       ---------
        richard = new Avatar();
        richard.getStatistics().setAgility(2);
        int richardAgility = richard.getStatistics().getAgility();

        // Create a chest
        PowerUP chest = new PowerUP(new Decal(Decal.item_default), "Magic Box", 5, 5, 5);
        chest.triggerProximity(richard);
        if (richard.getStatistics().getAgility() == richardAgility) {
            System.out.println("Error: interactible item is wrong");
        }
        */
    }
}
