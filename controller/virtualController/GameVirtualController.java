package controller.virtualController;

import java.awt.Color;
import model.Model;
import controller.IntentMap.IntentMap;
import controller.Intent;
import java.util.ArrayList;
import javax.swing.JButton;
import model.entity.Avatar;
import model.item.Item;
import utility.Course;

public final class GameVirtualController extends VirtualController {
    
     Color SELECTED_COLOR;
     Color UNSELECTED_COLOR;
     
    public GameVirtualController(Model model, ArrayList<IntentMap> ims) {
        super(model, ims);
        // This color is equivalent to the InventoryPanel Background Color
        SELECTED_COLOR = new Color(101,79,57);
        UNSELECTED_COLOR = new Color(186,163,132);
    }
    
    @Override
    protected void action(IntentMap im) {
       
        if ( im == null) return;
        Intent intent = im.getIntent();
        
        switch(intent) {
            case MOVE:
                Course course = (Course)im.getObject();
                model.moveAvatar(course);
                break;
            case TOGGLE_EQUIPPED:
                selectItem(im);
                break;
            case INVENTORY_ITEM:
                selectItem(im);
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
        super.action(im);
    }
    
    private void selectItem(IntentMap icm){}
    
    private void toggleEquip(){
        
       
        Item item;
        for ( IntentMap i : this.ims ){
            
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
         
        Item item;
        for ( IntentMap i : this.ims ){
            
            // When the item is selected and belongs only to the inventory
            if ( i.getComponent().getBackground().equals( SELECTED_COLOR) && i.getIntent().equals(Intent.TOGGLE_EQUIPPED) ){
                 
                
                 item = (Item)i.getObject();
                 Avatar avatar = model.getGameObject().getAvatar();
                 
                 // Attemp to store item in inventory
                 if ( !avatar.getInventory().hasItem(item) ){
                       
                      
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
        for ( IntentMap i : this.ims ){
            
            
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
