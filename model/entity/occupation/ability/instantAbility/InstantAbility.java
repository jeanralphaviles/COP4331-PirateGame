package model.entity.occupation.ability.instantAbility;

import model.entity.occupation.ability.Ability;
import utility.decal.Decal;

public abstract class InstantAbility extends Ability {

	public InstantAbility() {

	}

	public InstantAbility(String name) {
		super(name);
	}

	public InstantAbility(int manaCost, String name, Decal icon) {
		super(manaCost, name, icon);
	}

}
