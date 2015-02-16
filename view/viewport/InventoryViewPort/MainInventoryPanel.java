package view.viewport.InventoryViewPort;

import model.entity.Avatar;

import javax.swing.*;
import java.awt.*;

/**
 * Created by darien on 2/15/15.
 */
public class MainInventoryPanel extends JPanel {

    // Attributes
    private InventoryPanel inventory;
    private InventoryPanel equippedInventory;
    private Avatar avatar;

    // Default Constructor
    public MainInventoryPanel(){

        this.avatar = new Avatar();
        this.inventory = new InventoryPanel(avatar);
        this.equippedInventory = new InventoryPanel( avatar, ViewInventoryCategory.EQUIPPED_INVENTORY_VIEW);
        createMainInventoryPanel();

    }
    // Constructor I
    public MainInventoryPanel(Avatar avatar){

        this.avatar = avatar;
        this.inventory = new InventoryPanel(avatar);
        this.equippedInventory = new InventoryPanel(avatar, ViewInventoryCategory.EQUIPPED_INVENTORY_VIEW);
        createMainInventoryPanel();
    }

    // ----------- METHODS IMPLEMENTATION -------------------
    // -----------                        -------------------
    private void createMainInventoryPanel(){

        this.setLayout( new GridLayout(1,1));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel bottom = new JPanel(new GridLayout(1,1));
        bottom.setPreferredSize(new Dimension(400,140));
        bottom.setBackground(Color.BLUE);


        JPanel top1 = new JPanel(new GridLayout(1,1));
        top1.setPreferredSize(new Dimension(400,260));
        top1.setBackground(Color.YELLOW);


        JPanel inside = inventory.getItemsPanel();
        inside.setBackground(Color.RED);

        JPanel inside2 = equippedInventory.getItemsPanel();
        inside2.setBackground(Color.GREEN);

        JPanel inside3 = inventory.getSelectionPanel();
        inside3.setBackground(Color.ORANGE);

        JPanel inside4 = equippedInventory.getSelectionPanel();
        inside4.setBackground(Color.GRAY);

        top1.add(inside);
        top1.add(inside2);

        bottom.add( inside3 );
        bottom.add( inside4 );

        this.add( top1 );
        this.add( bottom );

    }

    /*
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


    // ------- ACTION LISTENER ------------
    // -------                 ------------
    private class Action implements ActionListener {

        JButton buttonHolder;

        public Action(JButton button) {

            buttonHolder = button;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            if (buttonHolder == dropButton) {

                inventoryPanel.dropItem();

            }
            else if ( buttonHolder == equipButton){

                System.out.println("Inside here");
                inventoryPanel.equipItem();
            }
            else if ( buttonHolder == unquipButton ){

                inventoryPanel.unequipItem();
            }
            inventoryPanel.updateItemsView();
        }
    }
    */
}
