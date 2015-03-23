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
import view.viewport.InventoryViewPort;

public final class TradeVirtualController extends VirtualController {
    
    // This color is equivalent to the InventoryPanel Background Color
    private Color SELECTED_COLOR = InventoryViewPort.lightColor;
    private Color UNSELECTED_COLOR = InventoryViewPort.darkColor;

    public TradeVirtualController(Model model, ArrayList<IntentMap> ims) {
        super(model, ims);
    }

    @Override
    protected void action(IntentMap im) {

        if (im == null) {
            return;
        }

        Intent intent = im.getIntent();

        switch (intent) {
            case PURCHASE:
                purchase(im);
                break;
            default:
                break;
        }
        super.action(im);
    }

    private void purchase(IntentMap im) {
        performPurchase(im);
        updateButton(im);
    }
    
    private void performPurchase(IntentMap im) {
        PurchaseParams params = (PurchaseParams) im.getObject();
        Item item = params.item;
        int price = params.price;
        //forward item and purchase request to model
        model.purchaseAvatarItem(item, price);
    }
    
    private void updateButton(IntentMap im) {
        
            if (im.getComponent() instanceof JButton ){
                
                JButton button = (JButton)im.getComponent();
                
                if (button.getBackground().equals(UNSELECTED_COLOR)){
                    
                    button.setBackground(SELECTED_COLOR);
                } 
                else {
                    
                    button.setBackground(UNSELECTED_COLOR);
                }
                button.repaint();
            }
    }

}
