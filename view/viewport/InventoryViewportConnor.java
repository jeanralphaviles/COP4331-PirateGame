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
import model.entity.Statistics;
import model.item.Item;
import utility.IntentComponentMap;
import utility.IntentComponentMap.Intent;

/**
 *
 * @author comcc_000
 */
public class InventoryViewportConnor extends ViewPort {

    private ArrayList<JButton> buttons = new ArrayList<JButton>(1);
    //
    private ArrayList<IntentComponentMap> icms = new ArrayList<IntentComponentMap>(1);
    //
    private int numTotalItems;

    public InventoryViewportConnor() {
        //initComponents();

    }

    @Override
    public void updateView(GameObject gameObject) {
//        ArrayList<Item> equippedItems = gameObject.getAvatar().getEquippedInventoryItems();
//        ArrayList<Item> inventoryItems = gameObject.getAvatar().getInventory().getItems();
//        int newTotalItems = equippedItems.size() + inventoryItems.size();
//        int index = 0;
//        if (newTotalItems != numTotalItems) {
//            numTotalItems = newTotalItems;
//            removeAll(); //refresh
//        }
//        for (int i=0; i< equippedItems.size(); i++) {
//            displayItem(equippedItems.get(i), true, index);
//            index++;
//        }
//        for (int i=0; i< inventoryItems.size(); i++) {
//            displayItem(inventoryItems.get(i), false, index);
//            index++;
//        }
        
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
    
    private boolean refreshNeeded(Avatar avatar) {
        int newTotalItems = calculateNumTotalItems(avatar);
        if (newTotalItems != numTotalItems) {
            numTotalItems = newTotalItems;
            refreshControllerNeeded = true;
        } else {
            refreshControllerNeeded = false;
        }
        return refreshControllerNeeded;
    }
    
    private int calculateNumTotalItems(Avatar avatar) {
        ArrayList<Item> equippedItems = avatar.getEquippedInventoryItems();
        ArrayList<Item> inventoryItems = avatar.getInventory().getItems();
        return equippedItems.size() + inventoryItems.size();
    }

    private void displayEquippedInventory(Avatar avatar) {
        ArrayList<Item> equippedItems = avatar.getEquippedInventoryItems();
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
        } else {
            button.setBackground(Color.GRAY);
        }
        icms.add(new IntentComponentMap(button, item, Intent.TOGGLE_EQUIPPED));
        
        add(button);
    }

//    public void displayItem(Item item, boolean equipped, int index) {
////        String name = item.getName();
////        ArrayList<String> buttonItemNames = new ArrayList<String>(1);
////        for (int i=0; i< buttons.size(); i++) {
////            buttonItemNames.add(buttons.get(i).getText());
////        }
////        !buttonItemNames.contains(name)
//        if (buttons.size() < 5) {
//            JButton button = new JButton(item.getName());
//            buttons.add(button);
//            if (equipped) {
//                button.setBackground(Color.BLUE);
//                icms.add(new IntentComponentMap(button, item, Intent.UNEQUIP_ITEM));
//            } else {
//                icms.add(new IntentComponentMap(button, item, Intent.EQUIP_ITEM));
//            }
//            add(button);
//        }
//        if (index < 5) {
//            if (item.equipped) {
//                buttons.get(index).setBackground(Color.BLUE);
//            } else {
//                buttons.get(index).setBackground(Color.GRAY);
//            }
//        }
//    }
    
    private void clearButtonsAndICMs() {
        buttons = new ArrayList<JButton>(1);
        icms = new ArrayList<IntentComponentMap>(1); 
    }

    @Override
    public ArrayList<IntentComponentMap> generateIntentComponentMapping() {
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
