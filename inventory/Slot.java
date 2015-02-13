package inventory;

import item.ObstacleItem;
import item.Category;
import item.Item;

public class Slot extends Inventory {
	private Category category;
	
	public Slot(Category category) {
		setCategory(category);
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void removeItem() {
		// TODO Auto-generated method stub
	}

	public Item getItem() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean storeItem(Item item) {
		// TODO Auto-generated method stub
		return false;
	}

}
