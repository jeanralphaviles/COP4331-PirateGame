package model.entity.occupation.ability.toggleAbility;

import utility.decal.Decal;
import model.Level;
import model.entity.Entity;
import model.entity.occupation.ability.Ability;

public abstract class ToggleAbility extends Ability {
	private boolean isActive = false;
	
	public ToggleAbility() {
		super();
	}
	
	public ToggleAbility(int manaCost, String name, Decal icon) {
		super(manaCost, name, icon);
	}

	@Override
	public void activate(Entity caster, Level level) {
		toggle();
		if (isActive()) {
			enterEffect(caster, level);
		} else {
			exitEffect(caster, level);
		}
	}
	
	public final void tick(Entity caster, Level level) {
		if (isActive()) {
			doAction(caster, level);
		}
	}

	public void enterEffect(Entity caster, Level level) {
		
	}
	
	public void exitEffect(Entity caster, Level level) {
		
	}
	
	public abstract void doAction(Entity caster, Level level);
	
	public final void toggle() {
		isActive = !isActive;
	}

	public final boolean isActive() {
		return isActive;
	}

	public final void setActive(boolean isActive) {
		this.isActive = isActive;
	}
}
