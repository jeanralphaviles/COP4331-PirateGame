package view.viewport.InventoryViewPort;

import model.entity.Avatar;
import model.item.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by darien on 2/15/15.
 */
public class InventoryItemsPanel extends JPanel{

    //Attributes
    private ViewInventoryCategory category;
    private Avatar avatar;
    private Item selectedItem;
    private JButton selectedButton;


    //Default Constructor
    public InventoryItemsPanel(){

        selectedButton = null;
        selectedItem = null;
        this.avatar = new Avatar();
        this.category = ViewInventoryCategory.INVENTORY_VIEW;
    }
    //Constructor I
    public InventoryItemsPanel(Avatar avatar){

        selectedButton = null;
        selectedItem = null;
        this.avatar = avatar;
        this.category = ViewInventoryCategory.INVENTORY_VIEW;
        createInventoryView();
    }
    //Constructor III
    public InventoryItemsPanel( Avatar avatar, ViewInventoryCategory category){

        selectedItem = null;
        this.avatar = avatar;
        this.category = category;
        createInventoryView();
    }

    // ----------- METHODS IMPLEMENTATION -------------------
    // -----------                        -------------------

    // -----------------------------------
    public Item getSelectedItem(){ return selectedItem; }

    // -----------------------------------
    public void updateView(){

        if ( selectedButton != null && selectedItem != null){
            this.remove( selectedButton );
            selectedButton = null;
            selectedItem = null;
            createInventoryView();
        }
    }

    // -----------------------------------
    private void createInventoryView(){

        this.setLayout( new FlowLayout(5,5,5));
        this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Get all items name
        ArrayList<String> itemsName = new ArrayList<String>();
        ArrayList<Item> items;

        // Only for Iventory
        if (category == ViewInventoryCategory.INVENTORY_VIEW){

            items = avatar.getInventory().getItems();

        }else{

            items= avatar.getEquippedInventoryItems();
        }

        // Add the items to the panel
        for ( Item i : items ){

            ImageIcon icon = new ImageIcon( i.getDecal().getImage() );
            JButton button = new JButton(icon);
            button.addActionListener(new Action(button,i) );
            this.add(button);
            button.setVisible(true);
        }

    } // End of function


    // ------- ACTION LISTENER ------------
    // -------                 ------------
    private class Action implements ActionListener{
        // Constructor
        private Item itemHolder;
        private JButton buttonHolder;

        public Action(JButton button, Item item){

            buttonHolder = button;
            itemHolder = item;
        }
        @Override
        public void actionPerformed(ActionEvent e){

            // Change it to its original color
            if ( selectedButton != null )
                selectedButton.setBackground( new JButton().getBackground() );

            selectedItem = itemHolder;
            selectedButton = buttonHolder;
            selectedButton.setBackground(Color.BLUE);

            System.out.println(selectedItem.getName());
        }
    }


} // End of class
