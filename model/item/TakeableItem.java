package model.item;

import model.Level;
import model.entity.Entity;
import model.entity.Statistics;
import model.inventory.SlotCategory;
import utility.decal.Decal;

/**
 * Takeable item goes into inventory upon activation
 */
public class TakeableItem extends Item implements Cloneable {
    protected SlotCategory slotCategory;

    public TakeableItem() {
        super();
        this.category = Category.TAKEABLE_ITEM;
        slotCategory = SlotCategory.ANY_SLOT;
    }

    public TakeableItem(Decal decal) {
        super(decal, Category.TAKEABLE_ITEM);
        this.slotCategory = SlotCategory.ANY_SLOT;
    }

    public TakeableItem(Decal decal, String name) {
        super(decal, Category.TAKEABLE_ITEM, name);
        this.slotCategory = SlotCategory.ANY_SLOT;
    }

    public TakeableItem(SlotCategory slotCategory) {
        super(new Decal(Decal.takeable_item), Category.TAKEABLE_ITEM);
        this.slotCategory = slotCategory;
    }

    public TakeableItem(SlotCategory slotCategory, Decal decal) {
        super(decal, Category.TAKEABLE_ITEM);
        this.slotCategory = slotCategory;
    }

    public TakeableItem(SlotCategory slotCategory, Decal decal, String name, Statistics statistics) {
        super(decal, Category.TAKEABLE_ITEM, name, statistics);
        this.slotCategory = slotCategory;
    }
    
    @Override
	public void triggerProximity(Entity entity, Level level) {
	
	}

	@Override
    public String toString() {
		int visibility = isVisibile ? 1 : 0;
        return "[" + category.toString() + "," + decal.toString() + "," + statistics.toString() + "," + name + "," + slotCategory.ordinal() + "," + visibility + "]";
    }

    public static TakeableItem fromString(String string) {
    	string = string.trim();
        String stripped = string.substring(1, string.length() - 1);
        int bracketCount = 0;
        int start = 0;
        int itemCount = 0;
        TakeableItem item = new TakeableItem();
        for (int j = 0; j < stripped.length(); ++j) {
            if (stripped.charAt(j) == ',') {
                item.category = Category.valueOf(stripped.substring(start, j));
                start = j + 1;
                break;
            }
        }

        for (int i = start; i < stripped.length(); ++i) {
            if (bracketCount == 0 && stripped.charAt(i) == ',') {
                if (itemCount == 0) {
                    Decal decal = Decal.fromString(stripped.substring(start, i));
                    item.decal = decal;
                } else if (itemCount == 1) {
                    Statistics statistics = Statistics.fromString(stripped.substring(start, i));
                    item.statistics = statistics;
                    start = i + 1;
                    break;
                }
                ++itemCount;
                start = i + 1;
            } else if (stripped.charAt(i) == '[') {
                ++bracketCount;
            } else if (stripped.charAt(i) == ']') {
                --bracketCount;
            }
        }
        String[] rest = stripped.substring(start, stripped.length()).split(",");
        item.name = rest[0];
        item.slotCategory = SlotCategory.values()[Integer.parseInt(rest[1])];
        item.isVisibile = Integer.parseInt(rest[2]) == 1;

        return item;
    }
    
    public SlotCategory getSlotCategory() {
	    return slotCategory;
	}

	public void setSlotCategory(SlotCategory slotCategory) {
	    this.slotCategory = slotCategory;
	}

	public static void main(String[] args) {
    	TakeableItem orig = new TakeableItem(SlotCategory.EYEPATCH, new Decal(Decal.item_default), "Eyepatch", new Statistics());
    	orig.setVisbility(false);
    	TakeableItem restored = fromString(orig.toString());
    	if (orig.toString().equals(restored.toString()) == false) {
    		System.out.println("Serialized Strings are different");
    	}
    	if (orig.getStatistics().toString().equals(restored.getStatistics().toString()) == false) {
    		System.out.println("Statistics diff");
    	}
    	if (!orig.getCategory().equals(restored.getCategory()) || !restored.getCategory().equals(Category.TAKEABLE_ITEM)) {
    		System.out.println("Categories are not the same");
    	}
    	if (!orig.getSlotCategory().equals(restored.getSlotCategory())) {
    		System.out.println("Slot categories are different");	
    	}
    	if (orig.getName().equals(restored.getName()) == false) {
    		System.out.println("Names are different");	
    	}
    	if (orig.isVisibile != restored.isVisibile) {
    		System.out.println("Visibilities differ");
    	}
    }

	@Override
	public TakeableItem clone() {
		TakeableItem item = new TakeableItem();
		item.category = this.category;
		item.decal = this.decal;
		item.isVisibile = this.isVisibile;
		item.name = this.name;
		item.statistics = this.statistics;
		item.slotCategory = this.slotCategory;
		return item;
	}
}
