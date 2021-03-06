package model.entity;

import model.entity.occupation.Occupation;
import model.entity.occupation.Smasher;
import model.entity.occupation.ability.Ability;
import model.item.*;
import utility.decal.Decal;

/**
 * @author Carlos Vizcaino Date 2/14/2015
 */
public class Avatar extends Entity {

    private String name;
    private String nickname;
    private int booty = 100;

    public Avatar() {
        super();
        
        this.name = "Richard Bonehard";
        this.nickname = "Woodman";

        Weapon bazzuca = new Weapon(Decal.createDecal(Decal.item_default), "Bazzuca");
        Weapon pistol = new Weapon(Decal.createDecal(Decal.item_default), "Pistol");
        Weapon spade = new Weapon(Decal.createDecal(Decal.item_default), "Spade");
        Weapon stick = new Weapon(Decal.createDecal(Decal.item_default), "Stick");
        Weapon arch = new Weapon(Decal.createDecal(Decal.item_default), "Arch");
        this.storeItem(pistol);
        this.storeItem(spade);
        this.storeItem(stick);
        this.storeItem(arch);
        this.equipItem(bazzuca);
        this.setStatistics(new Statistics());
        
        Weapon sword = new Weapon(Decal.createDecal(Decal.defualt_sword), "Bazzuca");
        this.storeItem( sword );
        this.setStatistics(new Statistics());
    }   
    @Override
    public boolean storeItem(Item item) {
        return super.storeItem(item); //To change body of generated methods, choose Tools | Templates.
    }
    
    public boolean spendAbilityPoints(Ability ability, int points) {
        int abilityLevels = convertPointsToAbilityLevels(points);
        int price = convertPointsToAbilityLevelsPrice(points);
        if (changeBooty(price * -1)) {
            ability.setAbilityLevel(abilityLevels);
            return true;
        }
        return false;
    }
    
    private int convertPointsToAbilityLevelsPrice(int points) {
        //round down to nearest abilityLevelCost increment
        int multiple = convertPointsToAbilityLevels(points);
        int remainder = points - (multiple * Ability.abilityLevelCost);
        int price = points - remainder;
        return price;
    }
    
    private int convertPointsToAbilityLevels(int points) {
        return Math.abs(points/Ability.abilityLevelCost);
    }

    public Avatar(Occupation occupation, Decal decal) {
        super(decal);
        this.occupation = occupation;
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
    
    public int getBooty() {
        return this.booty;
    }
    
    //Returns whether the transaction was successful for not.
    //Put positive numbers to give booty, negative to take booty.
    public boolean changeBooty(int amount) {
        if (checkBooty(amount)) { //checks booty; returns true if not enough
            return false;
        } else {
            booty += amount;
        }
        return true;
    }
    
    private boolean checkBooty(int amount) {
        return amount < 0 && Math.abs(amount) > booty;
    }

    @Override
    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
        String occ = occupation.getOccupationName();
        switch (occ) {
            case "Summoner":
                this.decal = Decal.createDecal(Decal.summoner);
                break;
            case "Smasher":
                this.decal = Decal.createDecal(Decal.smasher);
                break;
            case "Sneak":
                this.decal = Decal.createDecal(Decal.sneak);
                break;
        }
    }

    public static void main(String[] args) {
        Avatar orig = new Avatar(new Smasher(), Decal.createDecal(Decal.water));
        orig.setName("Charles");
        orig.setNickname("Charlie");
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
    }
}
