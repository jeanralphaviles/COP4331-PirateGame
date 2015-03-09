package model.item;

import model.entity.Entity;
import model.entity.Statistics;
import utility.decal.Decal;

public class OneShotItem extends Item{
    public OneShotItem() {
        super(new Decal(Decal.item_default), Category.ONE_SHOT_ITEM);
    }
    
    public OneShotItem(Decal decal) {
        super(decal, Category.ONE_SHOT_ITEM);
    }
    
    public OneShotItem(Decal decal, String name) {
        super(decal, Category.ONE_SHOT_ITEM, name);
    }
   
    public OneShotItem(Decal decal, String name, Statistics statistics){
        super(decal, Category.ONE_SHOT_ITEM, name, statistics);
    }

    @Override
	public void triggerProximity(Entity entity) {
    	entity.getStatistics().changeCurrentHealth(100);
    }
}
