package model.item;

import model.entity.Entity;
import model.entity.Statistics;
import utility.decal.Decal;

public class OneShotItem extends Item implements Cloneable {
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

	@Override
	public OneShotItem clone() {
		OneShotItem item = new OneShotItem();
		item.category = this.category;
		item.decal = this.decal;
		item.isVisibile = this.isVisibile;
		item.name = this.name;
		item.statistics = this.statistics;
		return item;
	}
}
