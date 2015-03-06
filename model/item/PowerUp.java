package model.item;


import model.entity.Entity;
import model.entity.Statistics;
import utility.decal.Decal;

/**
 * Created by Calos Vizcaino
 *  2/14/15.
 */
public class PowerUp extends InteractiveItem {
    public PowerUp() {
        super();
    }
    
    public PowerUp(Decal decal) {
        super(decal);
    }

    public PowerUp(Decal decal, String name, Statistics statistics) {
        super(decal, name, statistics);
    }
    
    @Override
    public void triggerProximity(Entity entity) {
    	if (entity.getDerivedStatistics().getLevel() >= 2) {
    		entity.getStatistics().changeAgility(getStatistics().getAgility());
    		entity.getStatistics().changeIntellect(getStatistics().getIntellect());
    		entity.getStatistics().addExperience(getStatistics().getExperience());
        }
    }

}
