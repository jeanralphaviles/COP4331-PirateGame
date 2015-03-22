package model.entity.occupation;

import java.util.ArrayList;

import utility.decal.Decal;
import model.entity.Statistics;
import model.entity.occupation.ability.Ability;
import model.entity.occupation.ability.instantAbility.Melee;
import model.entity.occupation.ability.instantAbility.Talk;

/**
 * @author Jean-Ralph Aviles
 */
public abstract class Occupation {
	private String occupationName;
	private ArrayList<Ability> abilities = new ArrayList<Ability>(1);
	private Decal picture;
	
	public Occupation(String occupationName) {                
                this.occupationName = occupationName;
		this.abilities = new ArrayList<Ability>(1);
                
		this.abilities.add(new Melee());
                this.abilities.add(new Talk());
                
		this.picture = new Decal(Decal.summonerPicture);
	}
	
	/**
	 * @param statistics - the occupation augments statistics depending on which occupation it is.
	 */
	public abstract void augmentStatistics(Statistics statistics);

	@Override
	public String toString() {
		return "[" + occupationName + "]";
	}

	public static Occupation fromString(String string) {
		String stripped = string.substring(1, string.length() - 1);
		Occupation occupation;
		
		if (stripped.equals("Smasher")) {
			occupation = new Smasher();
		} else if (stripped.equals("Sneak")) {
			occupation = new Sneak();
		} else {
			occupation = new Summoner();
		}
		return occupation;
	}

	public String getOccupationName() {
		return occupationName;
	}

	public void setOccupationName(String occupationName) {
		this.occupationName = occupationName;
	}
	
	public ArrayList<Ability> getAbilities() {
		return abilities;
	}

	public void setAbilities(ArrayList<Ability> abilities) {
		this.abilities = abilities;
	}
	
	public Decal getPicture() {
		return picture;
	}

	public void setPicture(Decal picture) {
		this.picture = picture;
	}

	public static void main(String[] args) {
		Occupation[] originals = {
				new Smasher(),
				new Sneak(),
				new Summoner()
		};
		Occupation[] restored = new Occupation[originals.length];
		for (int i = 0; i < originals.length; ++i) {
			restored[i] = Occupation.fromString(originals[i].toString());
			if (restored[i].toString().equals(originals[i].toString()) == false) {
				System.out.println("Serialized Strings differ");
			}
			if (restored[i].getClass() != originals[i].getClass()) {
				System.out.println("Classes differ");
			}
		}
	}
       
	
}
