package model.entity.occupation.ability.instantAbility.itemAbility;

import utility.decal.Decal;
import model.entity.occupation.ability.instantAbility.InstantAbility;
import model.item.Item;
import model.item.TakeableItem;

public abstract class ItemAbility extends InstantAbility {
	private Item item;
	
	public ItemAbility() {
		super();
		item = new TakeableItem();
	}
	
	public ItemAbility(Item item, String name, Decal decal) {
		super(name);
		this.setItem(item);
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

}
