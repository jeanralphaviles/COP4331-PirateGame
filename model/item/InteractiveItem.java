package model.item;

import model.entity.Entity;
import model.entity.Statistics;
import utility.decal.Decal;

public class InteractiveItem extends Item {

    public InteractiveItem() {
        super(new Decal(Decal.item_default), Category.INTERACTIVE_ITEM);
    }

    public InteractiveItem(Decal decal) {
        super(decal, Category.INTERACTIVE_ITEM);
    }
    
    public InteractiveItem(Decal decal, String name) {
        super(decal, Category.INTERACTIVE_ITEM, name);
    }
    
    public InteractiveItem(Decal decal, String name, Statistics statistics) {
        super(decal, Category.INTERACTIVE_ITEM, name, statistics);
    }

    @Override
	public void triggerProximity(Entity entity) {
    	if (entity.getDerivedStatistics().getCurrentHealth() > 15) {
    		entity.getStatistics().changeCurrentHealth(15);
    	}
	}

}
