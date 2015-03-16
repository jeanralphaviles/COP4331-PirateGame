package model.entity.occupation.ability.instantAbility.itemAbility;

import utility.decal.Decal;
import model.Level;
import model.entity.Entity;
import model.entity.Statistics;
import model.inventory.SlotCategory;
import model.item.TakeableItem;

public final class ConjureShoes extends ItemAbility {

	public ConjureShoes() {
		super(new TakeableItem(SlotCategory.FOOT, new Decal(Decal.item_default), "Conjured Shoes", new Statistics(0, 2, 2, 0, 1, 0 ,0 ,0, 0)), "Conjure Shoes", new Decal(Decal.item_default));
		this.setManaCost(100);
	}

	@Override
	public void activate(Entity caster, Level level) {
		if (entityHasMana(caster)) {
			caster.getInventory().storeItem(getItem().clone());
		}
	}

}
