package model.entity;

/**
 * @author Jean-Ralph Aviles
 */
public class Statistics implements Cloneable {

    /*Properties*/
    protected int livesLeft;
    protected int strength;
    protected int agility;
    protected int intellect;
    protected int hardiness;
    protected int experience;
    protected int maxHealth;
    protected int currentHealth;
    protected int currentMana;


    /*Constructors*/
    public Statistics() {

        // Set values
        livesLeft = 3; strength = 10; agility = 10; intellect = 10;
        agility = 10; intellect = 10; hardiness = 10; experience = 10;
        maxHealth = 10; currentHealth = 10; currentMana = 10;
    }

    public Statistics(int livesLeft, int strength, int agility, int intellect,
            int hardiness, int experience, int maxHealth, int currentHealth,
            int currentMana) {
        super();
        this.livesLeft = livesLeft;
        this.strength = strength;
        this.agility = agility;
        this.intellect = intellect;
        this.hardiness = hardiness;
        this.experience = experience;
        this.maxHealth = maxHealth;
        this.currentHealth = currentHealth;
        this.currentMana = currentMana;
    }

    /*Methods*/
    public Statistics clone() {
        return new Statistics(livesLeft, strength, agility, intellect,
                hardiness, experience, maxHealth, currentHealth, currentMana);
    }

    public void kill() {
        --livesLeft;
        if (livesLeft > 0) {
            currentHealth = maxHealth;
        }
    }

    public void changeCurrentHealth(int difference) {
        currentHealth += difference;
        if (isDead()) {
            currentHealth = 0;
            kill();
        } else if (currentHealth > maxHealth) {
            currentHealth = maxHealth;
        }
    }

    public boolean isDead() {
        return currentHealth <= 0;
    }

    public void changeLivesLeft(int difference) {
        livesLeft += difference;
        if (livesLeft < 0) {
            livesLeft = 0;
        }
    }

    public void changeStrength(int difference) {
        strength += difference;
        if (strength < 0) {
            strength = 0;
        }
    }

    public void changeAgility(int difference) {
        agility += difference;
        if (agility < 0) {
            agility = 0;
        }
    }

    public void changeIntellect(int difference) {
        intellect += difference;
        if (intellect < 0) {
            intellect = 0;
        }
    }

    public void changeMaxHealth(int difference) {
        maxHealth += difference;
        if (maxHealth < 0) {
            maxHealth = 0;
            kill();
        }
    }

    public void changeHardiness(int difference) {
        hardiness += difference;
        if (hardiness < 0) {
            hardiness = 0;
        }
    }

    public void changeCurrentMana(int difference) {
        currentMana += difference;
        if (currentMana > getMaxMana()) {
            currentMana = getMaxMana();
        } else if (currentMana < 0) {
            currentMana = 0;
        }
    }

    /*Get-Sets*/
    public int getLevel() {
        return experience / 100 + 1;
    }

    public int getMaxMana() {
        return intellect;
    }

    public int getOffensiveRating() {
        return (int) (strength + agility * 0.5);
    }

    public int getDefensiveRating() {
        return (int) (hardiness + agility * 0.5);
    }

    public int getArmorRating() {
        return getDefensiveRating();
    }

    public int getMovement() {
        return (int) (agility - (maxHealth - currentHealth) * 0.5);
    }

    public int getLivesLeft() {
        return livesLeft;
    }

    public void setLivesLeft(int livesLeft) {
        this.livesLeft = livesLeft;
        if (this.livesLeft < 0) {
            this.livesLeft = 0;
            kill();
        }
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
        if (this.strength < 0) {
            this.strength = 0;
        }
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
        if (this.agility < 0) {
            this.agility = 0;
        }
    }

    public int getIntellect() {
        return intellect;
    }

    public void setIntellect(int intellect) {
        this.intellect = intellect;
        if (this.intellect < 0) {
            this.intellect = 0;
        }
    }

    public int getHardiness() {
        return hardiness;
    }

    public void setHardiness(int hardiness) {
        this.hardiness = hardiness;
        if (this.hardiness < 0) {
            this.hardiness = 0;
        }
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
        if (this.experience < 0) {
            this.experience = 0;
        }
    }

    public void addExperience(int expGain) {
        experience += expGain;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
        if (this.maxHealth < 0) {
            this.maxHealth = 0;
            kill();
        }
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
        if (this.currentHealth < 0) {
            currentHealth = 0;
            kill();
        } else if (this.currentHealth > maxHealth) {
            this.currentHealth = maxHealth;
        }
    }

    public int getCurrentMana() {
        return currentMana;
    }

    public void setCurrentMana(int currentMana) {
        this.currentMana = currentMana;
        if (this.currentMana < 0) {
            this.currentMana = 0;
        } else if (this.currentMana > getMaxMana()) {
            this.currentMana = getMaxMana();
        }
    }

    public int getInventoryCapacity(){

        // Increases 2 by level
        return 15 + getLevel()*2;
    }

}
