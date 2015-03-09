package model.entity;

/**
 * @author Jean-Ralph Aviles
 */
public class Statistics implements Cloneable {
    protected int livesLeft;
    protected int strength;
    protected int agility;
    protected int intellect;
    protected int hardiness;
    protected int experience;
    protected int maxHealth;
    protected int currentHealth;
    protected int currentMana;

    public Statistics() {
        livesLeft = 3;
        strength = 10;
        agility = 10;
        intellect = 10;
        agility = 10;
        intellect = 10;
        hardiness = 10;
        experience = 10;
        maxHealth = 10;
        currentHealth = 10;
        currentMana = 10;
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

    @Override
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
        return 15;
    }
    
    @Override
	public String toString() {
		return "[" + livesLeft + "," + strength + "," + agility + ","
				+ intellect + "," + hardiness + "," + experience + ","
				+ maxHealth + "," + currentHealth + "," + currentMana + "]";
    }

	public static Statistics fromString(String string) {
		String stripped = string.substring(1, string.length() - 1);
		String[] components = stripped.split(",");
		Statistics statistics = new Statistics();
		statistics.livesLeft = Integer.parseInt(components[0]);
		statistics.strength = Integer.parseInt(components[1]);
		statistics.agility = Integer.parseInt(components[2]);
		statistics.intellect = Integer.parseInt(components[3]);
		statistics.hardiness = Integer.parseInt(components[4]);
		statistics.experience = Integer.parseInt(components[5]);
		statistics.maxHealth = Integer.parseInt(components[6]);
		statistics.currentHealth = Integer.parseInt(components[7]);
		statistics.currentMana = Integer.parseInt(components[8]);
		return statistics;
	}

	public void clear() {
		setLivesLeft(0);
		setStrength(0);
		setAgility(0);
		setIntellect(0);
		setHardiness(0);
		setExperience(0);
		setMaxHealth(0);
		setCurrentHealth(0);
		setCurrentMana(0);
	}
	
	public static void main(String[] args) {
		Statistics orig = new Statistics(1, 2, 3, 4, 5, 6, 7, 8, 9);
		Statistics restored = Statistics.fromString(orig.toString());
		if (orig.toString().equals(restored.toString()) == false) {
			System.out.println("Serialized Strings don't match");
		}
		if (orig.agility != restored.agility) {
			System.out.println("Agility is wrong");
		}
		if (orig.currentHealth != restored.currentHealth) {
			System.out.println("CurrentHealth is wrong");
		}
		if (orig.currentMana != restored.currentMana) {
			System.out.println("CurrentMana is wrong");
		}
		if (orig.experience != restored.experience) {
			System.out.println("Experience is wrong");
		}
		if (orig.hardiness != restored.hardiness) {
			System.out.println("Hardiness is wrong");
		}
		if (orig.intellect != restored.intellect) {
			System.out.println("Intellect is wrong");
		}
		if (orig.livesLeft != restored.livesLeft) {
			System.out.println("LivesLeft is wrong");
		}
		if (orig.maxHealth != restored.maxHealth) {
			System.out.println("MaxHealth is wrong");
		}
		if (orig.strength != restored.strength) {
			System.out.println("Strength is wrong");
		}
	}

}
