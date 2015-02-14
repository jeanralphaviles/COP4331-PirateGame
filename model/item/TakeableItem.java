package model.item;

import model.entity.Entity;
import model.entity.Statistics;
import model.inventory.SlotCategory;
import utility.decal.Decal;
import utility.decal.ItemDefaultDecal;

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
    public TakeableItem( SlotCategory slotCategory, Decal decal, Statistics statistics){

        super(decal, Category.TAKEABLE_ITEM, statistics);
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
    @Override
    // --------------------------------------------
    public void setAugmentStatistics( Statistics stats){

        // Todo-Code
    }
    // Abstract Methods:
    // --------------------------------------------
    public void triggerProximity(Entity entity){

        // Todo-Code
    }

}
