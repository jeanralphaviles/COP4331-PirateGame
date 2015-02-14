package model.entity.occupation;

import model.entity.Statistics;

/**
 * @author Jean-Ralph Aviles
 */
public class Sneak extends Occupation {
	
	public Sneak() {
		super("Sneak");
	}

	@Override
	public void augmentStatistics(Statistics statistics) {
		int agility = statistics.getAgility() * 4;
		statistics.setAgility(agility);
	}

}