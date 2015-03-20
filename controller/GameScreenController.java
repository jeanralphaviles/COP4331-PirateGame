package controller;

import java.awt.Color;
import model.Model;
import utility.IntentComponentMap;
import utility.IntentComponentMap.Intent;
import java.util.ArrayList;
import model.entity.Avatar;
import model.item.Item;

public final class GameScreenController extends ScreenController {
    
     Color SELECTED_COLOR;
     Color UNSELECTED_COLOR;
     
    public GameScreenController(Model model, ArrayList<IntentComponentMap> icms) {
        super(model, icms);
        // This color is equivalent to the InventoryPanel Background Color
        SELECTED_COLOR = new Color(101,79,57);
        UNSELECTED_COLOR = new Color(186,163,132);
    }
    
    @Override
    protected void action(IntentComponentMap icm) {
       
        Intent intent = icm.getIntent();
        switch(intent) {
            case TOGGLE_EQUIPPED:
                selectItem(icm);
                break;
            case INVENTORY_ITEM:
                selectItem(icm);
                break;
            case EQUIP_ITEM:
                   toggleEquip();
                    break;
            case UNEQUIP_ITEM:
                  toggleUnequip();
                break;
            case DROP_ITEM:
                toggleDrop();
                break;
            
        }
        super.action(icm);
    }
    
    private void selectItem(IntentComponentMap icm){}
    
    private void toggleEquip(){
        
       
        Item item;
        for ( IntentComponentMap i : this.icms ){
            
            // When the item is selected and belongs only to the inventory
            if ( i.getComponent().getBackground().equals( SELECTED_COLOR ) && i.getIntent().equals(Intent.INVENTORY_ITEM) ){
                 
                
                 item = (Item)i.getObject();
                 Avatar avatar = model.getGameObject().getAvatar();
                 
                 if ( avatar.getEquippedInventory().storeItem(item)){
                     
                     avatar.getInventory().removeItem(item);
                    i.getComponent().setBackground( UNSELECTED_COLOR );
                 }
             
            }
        }
    }
    
    private void toggleUnequip(){
        
        System.out.println("Attemp to unequip");
        Item item;
        for ( IntentComponentMap i : this.icms ){
            
            // When the item is selected and belongs only to the inventory
            if ( i.getComponent().getBackground().equals( SELECTED_COLOR) && i.getIntent().equals(Intent.TOGGLE_EQUIPPED) ){
                 
                
                 item = (Item)i.getObject();
                 Avatar avatar = model.getGameObject().getAvatar();
                 
                 // Attemp to store item in inventory
                 if ( !avatar.getInventory().hasItem(item) ){
                       
                       System.out.println("Here");
                       if ( avatar.getInventory().storeItem(item) ){
                           
                           i.getComponent().setBackground( UNSELECTED_COLOR );
                           avatar.getEquippedInventory().removeItem(item);
                       }
                       
                 }
            }
        }
        
    }
    
    private void toggleDrop(){
        
        Item item;
        for ( IntentComponentMap i : this.icms ){
            
            // When the item is selected and belongs only to the inventory
            if ( i.getComponent().getBackground().equals( SELECTED_COLOR) && i.getIntent().equals( Intent.INVENTORY_ITEM ) ){
                 
                
                 item = (Item)i.getObject();
                 Avatar avatar = model.getGameObject().getAvatar();
                 avatar.getInventory().removeItem(item);
                 i.getComponent().setBackground( UNSELECTED_COLOR );
            }
        }
    }

}
