package controller.virtualController;

import application.RunGame;
import java.awt.Color;
import model.Model;
import controller.IntentMap.IntentMap;
import controller.Intent;
import java.util.ArrayList;
import javax.swing.JButton;
import model.Level;
import model.entity.Avatar;
import model.entity.occupation.ability.Ability;
import model.item.Item;
import utility.Course;

public final class TradeVirtualController extends VirtualController {
     
    private JButton selectedButton;
    
    public TradeVirtualController(Model model, ArrayList<IntentMap> ims) {
        super(model, ims);
        // This color is equivalent to the InventoryPanel Background Color
    }
    
    @Override
    protected void action(IntentMap im) {
       
        if ( im == null) {
            return;
        }
        
        Intent intent = im.getIntent();
        
        switch(intent) {
            
            default:
                break;
        }
        super.action(im);
        
    }
}
