package model.map.areaeffect;

import application.RunGame;
import model.Level;
import model.entity.Entity;
import utility.decal.Decal;

/**
 * @author Jean-Ralph Aviles
 */
public final class LevelUpAreaEffect extends AreaEffect {
	public LevelUpAreaEffect() {
		super(Decal.createDecal(Decal.level_up));
	}

	public LevelUpAreaEffect(Decal decal) {
		super(decal);
	}

	@Override
	public void triggerProximityEffect(Entity entity, Level level) {
		if (isActive()) {
			int expToNextLevel = 100 - entity.getStatistics().getExperience() % 100;
			entity.getStatistics().addExperience(expToNextLevel);
			setActive(false);
                        RunGame.gotoLevelUpScreen();
		}
	}

	@Override
	public String toString() {
		int isActive = isActive() ? 1 : 0;
		return "[LevelUpAreaEffect," + isActive + "," + getDecal().toString() + "]";
	}
	
	public static LevelUpAreaEffect fromString(String string) {
		String stripped = string.substring(19, string.length() - 1);
		boolean isActive = Integer.parseInt(stripped.substring(0, 1)) == 1;
		stripped = stripped.substring(2);
		Decal decal = Decal.fromString(stripped.substring(stripped.indexOf(',') + 1));
		LevelUpAreaEffect levelUpAreaEffect = new LevelUpAreaEffect(decal);
		levelUpAreaEffect.setActive(isActive);
		return levelUpAreaEffect;
	}
	
	public static void main(String[] args) {
		LevelUpAreaEffect orig = new LevelUpAreaEffect();
		orig.setActive(false);
		LevelUpAreaEffect restored = LevelUpAreaEffect.fromString(((AreaEffect)orig).toString());
		if (!orig.getDecal().toString().equals(restored.getDecal().toString())) {
			System.out.println("Failed case #1");
		}
		if (orig.isActive() != restored.isActive()) {
			System.out.println("Failed case #2");
		}
	}
}
