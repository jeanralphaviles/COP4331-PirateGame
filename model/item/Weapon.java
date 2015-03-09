package model.item;


import model.entity.Statistics;
import model.inventory.SlotCategory;

import utility.decal.Decal;

/**
 * Created by Carlos:
 * Date: 2/14/2014
 */
public class Weapon extends TakeableItem {
    
    public Weapon() {
    	super(SlotCategory.HAND);
    	Statistics statistics = new Statistics();
    	statistics.clear();
    	statistics.setStrength(10);
    	statistics.setHardiness(5);
    	setStatistics(new Statistics());
    }
    
    public Weapon(Decal decal,String name) {
        super(SlotCategory.HAND, decal, name, new Statistics(0, 10, 0, 0, 5, 0, 0, 0, 0));
    }
    
    public Weapon(Decal decal, String name, Statistics statistics) {
        this(decal,name);
        setStatistics(statistics);
    }
}
