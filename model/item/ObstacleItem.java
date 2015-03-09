package model.item;

import model.entity.Entity;
import utility.decal.Decal;
import model.entity.Statistics;

public class ObstacleItem extends Item {
    public ObstacleItem() {
       super(new Decal(Decal.item_default), Category.OBSTACLE_ITEM);
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
	public void triggerProximity(Entity entity) {
    	
    }
}
