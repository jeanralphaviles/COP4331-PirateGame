package model.item;

import model.Level;
import model.entity.Entity;
import utility.decal.Decal;
import model.entity.Statistics;

public class ObstacleItem extends Item implements Cloneable {
    public ObstacleItem() {
       super(new Decal(Decal.obstacle_item), Category.OBSTACLE_ITEM);
    }
    
    public ObstacleItem(Decal decal) {
      super(decal, Category.OBSTACLE_ITEM);
    }
   
    public ObstacleItem(Decal decal, String name) {
      super(decal, Category.OBSTACLE_ITEM, name);
    }
    
    public ObstacleItem(Decal decal, String name, Statistics statistics) {
      super(decal, Category.OBSTACLE_ITEM, name, statistics);
    }

    @Override
	public void triggerProximity(Entity entity, Level level) {
    	
    }

	@Override
	public ObstacleItem clone() {
		ObstacleItem item = new ObstacleItem();
		item.category = this.category;
		item.decal = this.decal;
		item.isVisibile = this.isVisibile;
		item.name = this.name;
		item.statistics = this.statistics;
		return item;
	}
}
