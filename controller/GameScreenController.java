package controller;

import java.awt.Color;
import model.Model;
import utility.IntentComponentMap;
import utility.IntentComponentMap.Intent;
import javax.swing.*;
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
                toggleEquipped(icm);
                break;
        }
        super.action(icm);
    }
    
    private void toggleEquipped(IntentComponentMap icm) {
        Item item = (Item)icm.getObject();
        Avatar avatar = model.getGameObject().getAvatar();
        JButton button = (JButton)icm.getComponent();
        boolean equipped = avatar.unEquipItem(item); //unequips if possible
        if (!equipped) { //if it couldn't be uneqipped, then need to equip it
            avatar.equipItem(item);
            button.setBackground(Color.BLUE);
        } else {
            button.setBackground(Color.GRAY);
        }
    }

//    @Override
//    protected void processUserInput(Model model) {
//        int numICMs = icms.size();
//        IntentComponentMap icm;
//        for (int i = 0; i < numICMs; i++) {
//            icm = icms.get(i);
//            processUserInput(icm, model);
//        }
//    }
//
//    private void processUserInput(IntentComponentMap icm, Model model) {
//        Intent intent = icm.getIntent();
//        String details = icm.getDetails();
//        
//        if (intent == Intent.EQUIP_ITEM) {
//            Item item = (Item)icm.getObject();
//            
//            System.out.println( model.getGameObject().getAvatar().getEquippedInventory().getTotalItems() );
//            model.getGameObject().getAvatar().equipItem(item);
//            item.equipped = true;
//            
//        } else if (intent == Intent.UNEQUIP_ITEM) {
//            Item item = (Item)icm.getObject();
//            model.getGameObject().getAvatar().unequipItem(item);
//            item.equipped = false;
//        }
//    }

}
