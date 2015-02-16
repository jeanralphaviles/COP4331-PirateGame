package model.entity.occupation;

import model.entity.Statistics;

/**
 * @author Jean-Ralph Aviles
 */
public abstract class Occupation {
	private String occupationName;
	
	public Occupation(String occupationName) {
		this.occupationName = occupationName;
	}
	
	/**
	 * @param statistics - the occupation augments statistics depending on which occupation it is.
	 */
	public abstract void augmentStatistics(Statistics statistics);

	public String getOccupationName() {
		return occupationName;
	}

	public void setOccupationName(String occupationName) {
		this.occupationName = occupationName;
	}
	
	public String toString() {
		return "[" + occupationName + "]";
	}

	public static Occupation fromString(String string) {
		String stripped = string.substring(1, string.length() - 1);
		
		Occupation occupation = null;
		
		if (stripped.equals("Smasher")) {
			occupation = new Smasher();
		} else if (stripped.equals("Sneak")) {
			occupation = new Sneak();
		} else if (stripped.equals("Summoner")) {
			occupation = new Summoner();
		}
		
		return occupation;
	}
	
}
