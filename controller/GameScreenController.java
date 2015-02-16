package controller;

import model.Model;
import model.entity.occupation.Occupation;
import model.entity.occupation.Smasher;
import model.entity.occupation.Sneak;
import model.entity.occupation.Summoner;
import utility.IntentComponentMap;
import utility.IntentComponentMap.Intent;
import view.viewport.NewGamePopupViewport;

import javax.swing.*;
import java.util.ArrayList;
import model.item.Item;

public final class GameScreenController extends ScreenController {

    public GameScreenController(Model model, ArrayList<IntentComponentMap> icms) {
        super(model, icms);
    }
    
    @Override
    protected void action(Intent intent) {
        processUserInput(model);
        super.action(intent);
    }

    @Override
    protected void processUserInput(Model model) {
        int numICMs = icms.size();
        IntentComponentMap icm;
        for (int i = 0; i < numICMs; i++) {
            icm = icms.get(i);
            processUserInput(icm, model);
        }
    }

    private void processUserInput(IntentComponentMap icm, Model model) {
        Intent intent = icm.getIntent();
        String details = icm.getDetails();
        
        if (intent == Intent.EQUIP_ITEM) {
            Item item = (Item)icm.getObject();
            
            System.out.println( model.getGameObject().getAvatar().getEquippedInventory().getTotalItems() );
            model.getGameObject().getAvatar().equipItem(item);
            item.equipped = true;
            
        } else if (intent == Intent.UNEQUIP_ITEM) {
            Item item = (Item)icm.getObject();
            model.getGameObject().getAvatar().unequipItem(item);
            item.equipped = false;
        }
    }

}
