package controller.virtualController;

import application.RunGame;
import model.Model;
import controller.IntentMap.IntentMap;
import controller.Intent;
import controller.physicalController.KeyboardController;
import controller.physicalController.RebindInfo;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JTextField;
import model.entity.Avatar;
import model.entity.occupation.ability.Ability;

public final class AbilitiesAllocateVirtualController extends VirtualController {
    
    /*Properties*/
     
    /*Constructors*/
    
    public AbilitiesAllocateVirtualController(Model model, ArrayList<IntentMap> ims) {
        super(model, ims);
    }
    
    /*Methods*/
    
    @Override
    protected void action(IntentMap im) {
        
        Intent intent = im.getIntent();
        switch(intent) {
            case ALLOCATE_ABILITY_POINTS:
                allocateAbilityPoints(im);
                break;
            default:
                break;
            
        }
        super.action(im);
    }
    
    private void allocateAbilityPoints(IntentMap im) {
        Ability ability = (Ability)im.getObject();
        int points = getPoints(im);
        Avatar avatar = model.getGameObject().getAvatar();
        if (!avatar.spendAbilityPoints(ability, points * -1)) {
            RunGame.showErrorMessage("Need 25 booty to buy an ability points!");
        }
    }
    
    private int getPoints(IntentMap im) {
        JTextField textField = (JTextField)im.getComponent();
        String content = textField.getText().toString();
        int points = Integer.parseInt(content);
        return points;
    }

    /*Get-Sets*/
    
}
