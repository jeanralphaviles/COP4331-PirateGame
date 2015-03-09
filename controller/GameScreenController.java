package controller;

import java.awt.Color;
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
        boolean equipped = avatar.unequipItem(item); //unequips if possible
        if (!equipped) { //if it couldn't be uneqipped, then need to equip it
            avatar.equipItem(item);
            button.setBackground(Color.BLUE);
        } else {
            button.setBackground(Color.GRAY);
        }
    }

}
