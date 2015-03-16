package controller;

import model.Model;
import model.entity.occupation.Occupation;
import utility.IntentComponentMap;
import utility.IntentComponentMap.Intent;
import javax.swing.*;
import java.util.ArrayList;
import view.screen.DialogueScreen;
import view.screen.Screen;

public final class NewGamePopupController extends ScreenController {

    public NewGamePopupController(Model model, ArrayList<IntentComponentMap> icms) {
        super(model, icms);
    }
    
    @Override
    protected void action(IntentComponentMap icm) {
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
    
    private void setNickname(IntentComponentMap icm) {
        JTextField field = (JTextField)icm.getComponent();
        String nickname = field.getText();
        setNickname(nickname);
    }
    
    private void setNickname(String nickname) {
        
        model.setAvatarNickname(nickname);
    }
    
    private void begin() {
        
        for (IntentComponentMap i : icms){
            
           if ( i.getIntent() == Intent.SET_NICKNAME ){
                    
                this.setNickname(i);
                break;
            }
        }
        Screen screen = new DialogueScreen(model);
        model.launchScreen(screen);
        
    }

}
