package model.item;

import model.Level;
import model.entity.Entity;
import model.entity.Statistics;
import utility.decal.Decal;

/**
 * Created by Carlos Vizcaino
 * Date 2/14/2015
 */

public class Food extends OneShotItem {

    public Food() {
        super();
        Statistics statistics = new Statistics();
        statistics.clear();
        statistics.setCurrentHealth(10);
    }
    
    public Food(Decal decal) {
    	this();
    	this.decal = decal;
    }
    
    public Food(Decal decal, String name) {
    	this(decal);
    	this.name = name;
    }
    
    public Food(Decal decal, String name, int health) {
        this(decal,name);
        getStatistics().setCurrentHealth(health);
    }

    public Food(Decal decal, int health) {
        this(decal);
        getStatistics().setCurrentHealth(health);
    }

    @Override
	public void triggerProximity(Entity entity, Level level) {
	    entity.getStatistics().changeCurrentHealth(getStatistics().getCurrentHealth());
	}

	public int getHealth() {
        return getStatistics().getCurrentHealth();
    }

    public void setHealth(int health) {
        getStatistics().setCurrentHealth(health);
    }

}
