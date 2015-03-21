package controller.virtualController;

import model.Model;
import model.entity.occupation.Occupation;
import controller.IntentMap.IntentMap;
import controller.Intent;
import javax.swing.*;
import java.util.ArrayList;
import view.screen.DialogueScreen;
import view.screen.Screen;

public final class NewGameVirtualController extends VirtualController {

    public NewGameVirtualController(Model model, ArrayList<IntentMap> ims) {
        super(model, ims);
    }
    
    @Override
    protected void action(IntentMap icm) {
        Intent intent = icm.getIntent();
        switch(intent) {
            case SET_OCCUPATION:
                Occupation occupation = (Occupation)icm.getObject();
                setOccupation(occupation);
                break;
            case SET_NICKNAME:
                setNickname(icm);
                break;
            case BEGIN:
                begin();
                break;
        }
        super.action(icm);
    }
    
    private void setOccupation(Occupation occupation) {
        model.setAvatarOccupation(occupation);
    }
    
    private void setNickname(IntentMap icm) {
        JTextField field = (JTextField)icm.getComponent();
        String nickname = field.getText();
        setNickname(nickname);
    }
    
    private void setNickname(String nickname) {
        
        model.setAvatarNickname(nickname);
    }
    
    private void begin() {
        
        for (IntentMap i : ims){
            
           if ( i.getIntent() == Intent.SET_NICKNAME ){
                    
                this.setNickname(i);
                break;
            }
        }
        Screen screen = new DialogueScreen(model);
        model.launchScreen(screen);
        
    }

}
