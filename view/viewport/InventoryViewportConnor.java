/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.viewport;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JButton;
import model.GameObject;
import model.entity.Avatar;
import model.item.Item;
import controller.controllerMap.IntentComponentMap;
import controller.Intent;

/**
 *
 * @author comcc_000
 *  and Carlos Vizcaino
 */
public class InventoryViewportConnor extends ViewPort {

    // Attributes
    private ArrayList<JButton> buttons = new ArrayList<JButton>(1);
    private ArrayList<IntentComponentMap> icms = new ArrayList<IntentComponentMap>(1);
    private int totalEquippedItems;
    private int totalInventoryItems;
    
    JButton equipButton;
    JButton unequipButton;
    JButton dropButton;
    
    

    public InventoryViewportConnor() {
       
    }

    @Override
    public void updateView(GameObject gameObject) {      
        
        Avatar avatar = gameObject.getAvatar();
        if (refreshNeeded(avatar)) {
            //Clear viewport
            removeAll(); //refresh screen
            clearButtonsAndICMs(); //clear existing state
            
            //Display again the gameObject
            displayEquippedInventory(avatar);
            displayInventory(avatar);
        }
    }
    
    /*  Determine if items were added or taken away (numTotalItems has changed).
        If so, then need to refresh the view AND controller (because new buttons were added).
        Else, no point in updating. The listeners effectively update existing buttons.
    */
    private boolean refreshNeeded(Avatar avatar) {
       
        
        int newTotalEquippedItems = avatar.getEquippedInventory().getItems().size();
        int newTotalInventoryItems = avatar.getInventory().getItems().size();
        
        if ( newTotalEquippedItems != totalEquippedItems) {
            
            totalEquippedItems = newTotalEquippedItems;
            refreshControllerNeeded = true;
        } 
        else if ( newTotalInventoryItems != totalInventoryItems ){
               
            totalInventoryItems = newTotalInventoryItems;
            refreshControllerNeeded =  true;
        }
        else {

            refreshControllerNeeded = false;
        }
        
        return refreshControllerNeeded;
    }
    
    private int calculateNumTotalItems(Avatar avatar) {
        ArrayList<Item> equippedItems = avatar.getEquippedInventory().getItems();
        ArrayList<Item> inventoryItems = avatar.getInventory().getItems();
        return equippedItems.size() + inventoryItems.size();
    }

    private void displayEquippedInventory(Avatar avatar) {
        
        // Testing
        addCommandButtons();
        
        
        ArrayList<Item> equippedItems = avatar.getEquippedInventory().getItems();
        for (int i = 0; i < equippedItems.size(); i++) {
            displayItem(equippedItems.get(i), true);
        }
    }

    private void displayInventory(Avatar avatar) {
        ArrayList<Item> inventoryItems = avatar.getInventory().getItems();
        for (int i=0; i< inventoryItems.size(); i++) {
            displayItem(inventoryItems.get(i), false);
        }
    }

    private void displayItem(Item item, boolean equipped) {
        //Create Button
        String itemName = item.getName();
        JButton button = new JButton(itemName);
        buttons.add(button);
        
        if (equipped) {
            button.setBackground(Color.BLUE);
            icms.add(new IntentComponentMap(button, item, Intent.TOGGLE_EQUIPPED));
            
        } else {
            button.setBackground(Color.GRAY);
            icms.add(new IntentComponentMap(button, item, Intent.INVENTORY_ITEM));
        }
        
        add(button);
    }
    
    private void addCommandButtons(){
        
        equipButton = new JButton("Equip");
        equipButton.setBackground(Color.RED);
        icms.add(new IntentComponentMap(equipButton, Intent.EQUIP_ITEM));
        
        unequipButton = new JButton("Unequip");
        unequipButton.setBackground(Color.RED);
        icms.add(new IntentComponentMap(unequipButton, Intent.UNEQUIP_ITEM));
    
        dropButton = new JButton("Drop");
        dropButton.setBackground(Color.RED);
        icms.add(new IntentComponentMap(dropButton, Intent.DROP_ITEM));
        
        this.add(equipButton);
        this.add(unequipButton);
        this.add(dropButton);
    }
    
    private void clearButtonsAndICMs() {
        buttons = new ArrayList<JButton>(1);
        icms = new ArrayList<IntentComponentMap>(1); 
    }

    @Override
    public ArrayList<IntentComponentMap> generateIntentComponentMapping() {
        /*  Because the only controls, buttons, directly represent items
            it made sense in this case to assign icms as the buttons were
            created. This method merely returns them.
        */
        return icms;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 417, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 165, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
