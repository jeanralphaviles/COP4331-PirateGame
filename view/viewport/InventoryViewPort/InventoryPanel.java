package view.viewport.InventoryViewPort;

import model.entity.Avatar;
import model.item.Item;

import javax.swing.*;


/**
 * Created by darien on 2/15/15.
 */
public class InventoryPanel extends JPanel{

    // Attributes
    private ViewInventoryCategory category;
    protected InventoryItemsPanel inventoryItemsPanel;
    protected InventorySelectionPanel inventorySelectedPanel;
    private Avatar avatar;


    // Default Constructor
    public InventoryPanel(){

        this.avatar = new Avatar();
        category = ViewInventoryCategory.INVENTORY_VIEW;
        inventoryItemsPanel = new InventoryItemsPanel(avatar, category);
        inventorySelectedPanel = new InventorySelectionPanel( this ,category);

    }
    // Constructor I
    public InventoryPanel(Avatar avatar){

        this.avatar = avatar;
        category = ViewInventoryCategory.INVENTORY_VIEW;
        inventoryItemsPanel = new InventoryItemsPanel(avatar, category);
        inventorySelectedPanel = new InventorySelectionPanel( this ,category);

    }
    // Constructor II
    public InventoryPanel(Avatar avatar , ViewInventoryCategory category){

        this.avatar = avatar;
        this.category = category;
        inventoryItemsPanel = new InventoryItemsPanel(avatar, category);
        inventorySelectedPanel = new InventorySelectionPanel( this ,category);
    }

    // -----------------------------------------------------
    public InventoryItemsPanel getItemsPanel() {return inventoryItemsPanel;}

    // -----------------------------------------------------
    public InventorySelectionPanel getSelectionPanel(){ return inventorySelectedPanel; }


    // -----------------------------------------------------
    public void updateItemsView(){

        inventoryItemsPanel.updateView();
    }

    // -----------------------------------------------------
    public Item dropItem() {

        Item item = inventoryItemsPanel.getSelectedItem();

        if (item != null){

            if (avatar.getInventory().removeItem(item)) {

                return item;
            }
        }
            return null;
    }
    // -----------------------------------------------------
    public boolean equipItem(){

        Item item = inventoryItemsPanel.getSelectedItem();

        if ( item != null){

            avatar.getInventory().removeItem(item);
            avatar.equipItem(item);
            return true;
        }
        return false;
    }
    // -----------------------------------------------------
    public boolean unequipItem(){

        Item item = inventoryItemsPanel.getSelectedItem();

        if (item != null){

            avatar.unequipItem(item);
            return true;
        }

        return false;
    }



}
