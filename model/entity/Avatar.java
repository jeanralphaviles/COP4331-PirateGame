package model.entity;

import utility.decal.Decal;
import model.entity.occupation.Occupation;

/**
 * @author Jean-Ralph Aviles
 */
public class Avatar extends Entity {
	private String name;
	
	public Avatar() {
		super();
		this.name = "Richard";
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
}