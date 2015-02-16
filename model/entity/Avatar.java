package model.entity;

import java.io.IOException;

import model.entity.occupation.Occupation;
import model.item.*;
import model.map.Maptile;
import model.map.areaeffect.*;
import utility.decal.Decal;
import utility.decal.ItemDefaultDecal;

/**
 * @author Jean-Ralph Aviles
 * @author Carlos Vizcaino
 * Date 2/14/2015
 */
public class Avatar extends Entity {

    private String name;
    private String nickname;

    public Avatar() {
        super();
        this.name = "Richard Bonehard";

        // This specifically
        Weapon pistol = new Weapon( new ItemDefaultDecal(), "Pistol",  5, 5);
        Weapon spade = new Weapon( new ItemDefaultDecal(), "Spade", 5, 5);
        Weapon stick = new Weapon( new ItemDefaultDecal(), "Stick", 5, 5);
        Weapon arch = new Weapon( new ItemDefaultDecal(), "Arch", 5, 5);
        this.storeItem( pistol );
        this.storeItem( spade );
        this.storeItem( stick );
        this.storeItem( arch );
        this.equipItem( pistol );

    }

    public Avatar(Occupation occupation, Decal decal) {

        super(decal);
        this.occupation = occupation;
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

    public static void main(String[] args){

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
        if ( richard.getStatistics().getLevel() != 2 )
            System.out.println("Error: level up is wrong");

        int richardLives = richard.getStatistics().getLivesLeft();
        instantDeath.triggerProximityEffect(richard);
        if ( richard.getStatistics().getLivesLeft() != richardLives - 1 )
            System.out.println("Error: instant death is wrong");

        int richardHealth = richard.getStatistics().getCurrentHealth();
        dealDamage.triggerProximityEffect(richard);
        if (richard.getStatistics().getCurrentHealth() == richardHealth )
            System.out.println("Error: take damage is wrong");

        healDamage.triggerProximityEffect(richard);
        if (richard.getStatistics().getCurrentHealth() != richardHealth )
            System.out.println("Error: heal damage is wrong");


        // -------- TESTING AVATAR AND TAKEABLE ITEM  ---------
        // --------                                   ---------
        richard = new Avatar();
        richard.getStatistics().setHardiness(2);

        // Crate a takeable item
        int richardHardiness = richard.getStatistics().getHardiness();
        Item pistol = new Weapon( new ItemDefaultDecal(), "Pistol", 5, 5);
        pistol.triggerProximity(richard);
        if ( richard.getStatistics().getHardiness() == richardHardiness)
             System.out.println("Error: takeable item is wrong");

        // -------- TESTING AVATAR AND ONE SHOT ITEM  ---------
        // --------                                   ---------

        richard = new Avatar();
        richard.getStatistics().setCurrentHealth(2);

        // Create a OneShotItem
        OneShotItem banana = new Food( new ItemDefaultDecal(), "banana", 5);

        richardHealth = richard.getStatistics().getCurrentHealth();
        banana.triggerProximity(richard);
        if ( richard.getStatistics().getCurrentHealth() == richardHealth )
            System.out.println("Error: one shot item is wrong");

        // -------- TESTING AVATAR AND INTERACTIBLE ITEM  ---------
        // --------                                       ---------

        richard = new Avatar();
        richard.getStatistics().setAgility(2);
        int richardAgility = richard.getStatistics().getAgility();

        // Create a chest
        PowerUP chest  = new PowerUP ( new ItemDefaultDecal(), "Magic Box", 5, 5, 5);
        chest.triggerProximity(richard);
        if ( richard.getStatistics().getAgility() == richardAgility )
            System.out.println("Error: interactible item is wrong");

        // -------- TESTING MapTile and Obstacle Item     ---------
        // --------                                       ---------
        Maptile tile = new Maptile();
        tile.storeItem(new ObstacleItem(new ItemDefaultDecal(), "Palm Tree"));

        if ( tile.isPassable() )
            System.out.println("Error: obstacle item is wrong");

    }
    
    @Override
	public String toString() {
    	return "[" + super.toString() + "," + name + "," + nickname + "]";
    }

	public static Avatar fromString(String string) throws IOException {
		String stripped = string.substring(1, string.length() - 1);
		Avatar avatar = new Avatar();
		int bracketCount = 0;
		int start = 0;
		for (int i = 0; i < stripped.length(); ++i) {
			if (bracketCount == 0 && stripped.charAt(i) == ',') {
				Entity entity = Entity.fromString(stripped.substring(start, i));
				avatar.decal = entity.decal;
				avatar.equippedInventory = entity.equippedInventory;
				avatar.maptile = entity.maptile;
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
}
