/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.map.areaeffect;

import model.Level;
import model.entity.Entity;
import utility.decal.Decal;

/**
 *
 * @author jrisberg
 */
public class OneUpAreaEffect extends AreaEffect {
    
    public OneUpAreaEffect() {
	super(Decal.createDecal(Decal.level_up));
    }
    
    public OneUpAreaEffect(Decal decal) {
	super(decal);
    }

    @Override
    public void triggerProximityEffect(Entity entity, Level level) {
        if (isActive()) {
            entity.getStatistics().changeLivesLeft(1);
            setActive(false);
        }
    }

    @Override
    public String toString() {
        int isActive = isActive() ? 1 : 0;
	return "[OneUpAreaEffect," + isActive + "," + getDecal().toString() + "]";
    }
    
    public static OneUpAreaEffect fromString(String string) {
		String stripped = string.substring(17, string.length() - 1);
		boolean isActive = Integer.parseInt(stripped.substring(0, 1)) == 1;
		stripped = stripped.substring(2);
		Decal decal = Decal.fromString(stripped.substring(stripped.indexOf(',') + 1));
		OneUpAreaEffect oneUpAreaEffect = new OneUpAreaEffect(decal);
		oneUpAreaEffect.setActive(isActive);
		return oneUpAreaEffect;
	}
    
    public static void main(String[] args) {
		OneUpAreaEffect orig = new OneUpAreaEffect();
		orig.setActive(false);
		OneUpAreaEffect restored = OneUpAreaEffect.fromString(((AreaEffect)orig).toString());
		if (!orig.getDecal().toString().equals(restored.getDecal().toString())) {
			System.out.println("Failed case #1");
		}
		if (orig.isActive() != restored.isActive()) {
			System.out.println("Failed case #2");
		}
	}
}
