package model.item;

import java.io.IOException;

import model.entity.Entity;
import model.entity.Statistics;
import model.inventory.SlotCategory;
import utility.decal.Decal;
import utility.decal.ItemDefaultDecal;
/**
*Takeable item goes into inventory upon activation
*/
public class TakeableItem extends Item{

    // Atrributes
    protected SlotCategory slotCategory;

    // Default Constructor
    public TakeableItem(){

        super();
        this.category = Category.TAKEABLE_ITEM;
        slotCategory = SlotCategory.ANY_SLOT;

    }
    // Constructor II
    public TakeableItem( Decal decal){

        super(decal, Category.TAKEABLE_ITEM);
        this.slotCategory = SlotCategory.ANY_SLOT;
    }
    // Constructor III
    public TakeableItem( Decal decal, String name){

        super(decal, Category.TAKEABLE_ITEM, name);
        this.slotCategory = SlotCategory.ANY_SLOT;
    }
    // Constructor III
    public TakeableItem( SlotCategory slotCategory){

        super( new ItemDefaultDecal(), Category.TAKEABLE_ITEM);
        this.slotCategory = slotCategory;
    }
    // Constructor IV
    public TakeableItem( SlotCategory slotCategory, Decal decal ){

        super(decal, Category.TAKEABLE_ITEM);
        this.slotCategory = slotCategory;
    }
    // Constructor V
    public TakeableItem( SlotCategory slotCategory, Decal decal, String name, Statistics statistics){

        super(decal, Category.TAKEABLE_ITEM, name, statistics);
        this.slotCategory = slotCategory;

    }

    // ---------- METHODS IMPLEMENTATION ---------
    // ----------                         ---------

    // Accessor Methods:
    // --------------------------------------------
    public SlotCategory getSlotSCategory(){

        return slotCategory;
    }
    // Mutator Methods:
    // --------------------------------------------
    public void setSlotCategory( SlotCategory slotCategory ){

        this.slotCategory = slotCategory;
    }

    // Abstract Methods:
    // --------------------------------------------
    @Override
	public void triggerProximity(Entity entity){

        // Todo-Code
    }

    @Override
	public String toString() {
    	return "[" + category.toString() + "," + decal.toString() + "," + statistics.toString() + "," + name + "," + slotCategory +  "]";
    }

    public static TakeableItem fromString(String string) throws IOException {
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
		item.slotCategory = SlotCategory.valueOf(rest[1]);
    
    	return item;
    }
}
