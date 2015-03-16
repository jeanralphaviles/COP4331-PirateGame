package model.entity.occupation.ability.instantAbility.status;

import utility.decal.Decal;
import model.entity.occupation.ability.instantAbility.InstantAbility;

public abstract class StatusAbility extends InstantAbility {
	
	public StatusAbility() {
		super();
	}

	public StatusAbility(int manaCost, String name, Decal icon) {
		super(manaCost, name, icon);
	}

}
