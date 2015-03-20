package controller.screenController;

import model.Model;
import controller.controllerMap.IntentComponentMap;
import controller.Intent;
import java.util.ArrayList;
import model.entity.Avatar;
import model.item.Item;

public final class GameScreenController extends ScreenController {

    public GameScreenController(Model model, ArrayList<IntentComponentMap> icms) {
        super(model, icms);
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
        
        System.out.println("Attemp to equip");
         Item item;
        for ( IntentComponentMap i : this.icms ){
            
            // When the item is selected and belongs only to the inventory
            if ( i.getComponent().getBackground() == SELECTED_COLOR && i.getIntent() == Intent.INVENTORY_ITEM ){
                 
                
                 item = (Item)i.getObject();
                 Avatar avatar = model.getGameObject().getAvatar();
                 
                 if ( avatar.getEquippedInventory().storeItem(item)){
                     
                     System.out.println("Category: " + item.getCategory());
                     avatar.getInventory().removeItem(item);
                 }
             
            }
        }
       
        System.out.println("Avatar's Iventory: ");
        
        for (Item i : model.getGameObject().getAvatar().getInventory().getItems()){
            
             System.out.println( i.getName() );
        }
        
        
        System.out.println("Avatar's  Equipped Iventory: ");
        
        for (Item i : model.getGameObject().getAvatar().getEquippedInventory().getItems()){
            
             System.out.println( i.getName() );
        }
    }
    
    private void toggleUnequip(){
        
        System.out.println("Attemp to unequip");
        Item item;
        for ( IntentComponentMap i : this.icms ){
            
            // When the item is selected and belongs only to the inventory
            if ( i.getComponent().getBackground() == SELECTED_COLOR && i.getIntent() == Intent.TOGGLE_EQUIPPED ){
                 
                
                 item = (Item)i.getObject();
                 Avatar avatar = model.getGameObject().getAvatar();
                 
                 // Attemp to store item in inventory
                 if ( !avatar.getInventory().hasItem(item) ){
                       
                       System.out.println("Here");
                       if ( avatar.getInventory().storeItem(item) ){
                           
                           System.out.println("Removing from equip inventory: " + item.getName());
                           avatar.getEquippedInventory().removeItem(item);
                       }
                       
                 }
            }
        }
       
        System.out.println("Avatar's  Iventory: ");
        
        for (Item i : model.getGameObject().getAvatar().getInventory().getItems()){
            
             System.out.println( i.getName() );
        }
        
        System.out.println("Avatar's  Equipped Iventory: ");
        
        for (Item i : model.getGameObject().getAvatar().getEquippedInventory().getItems()){
            
             System.out.println( i.getName() );
        }
        
    }
    
    private void toggleDrop(){
        
        System.out.println("Attemp to drop");
        Item item;
        for ( IntentComponentMap i : this.icms ){
            
            // When the item is selected and belongs only to the inventory
            if ( i.getComponent().getBackground() == SELECTED_COLOR && i.getIntent() == Intent.INVENTORY_ITEM ){
                 
                
                 item = (Item)i.getObject();
                 System.out.println("Droping Item: " + item.getName() );
                 Avatar avatar = model.getGameObject().getAvatar();
                 avatar.getInventory().removeItem(item);
            }
        }
       
        System.out.println("Avatar's Iventory: ");
        
        for (Item i : model.getGameObject().getAvatar().getInventory().getItems()){
            
             System.out.println( i.getName() );
        }
    }

}
