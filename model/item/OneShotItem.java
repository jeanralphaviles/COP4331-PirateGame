package model.item;

import model.Level;
import model.entity.Entity;
import model.entity.Statistics;
import utility.decal.Decal;

public class OneShotItem extends Item implements Cloneable {
    public OneShotItem() {
        super(new Decal(Decal.one_shot_item), Category.ONE_SHOT_ITEM);
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
	public void triggerProximity(Entity entity, Level level) {
    	entity.getStatistics().merge(getStatistics());
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
