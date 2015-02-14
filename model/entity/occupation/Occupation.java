package model.entity.occupation;

import model.entity.Statistics;

public abstract class Occupation {
	private String occupationName;
	
	public Occupation(String occupationName) {
		this.occupationName = occupationName;
	}
	
	public abstract void augmentStatistics(Statistics statistics);

	public String getOccupationName() {
		return occupationName;
	}

	public void setOccupationName(String occupationName) {
		this.occupationName = occupationName;
	}
	
}
