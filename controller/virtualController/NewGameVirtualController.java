package controller.virtualController;

import model.Model;
import model.entity.occupation.Occupation;
import controller.IntentMap.IntentMap;
import controller.Intent;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.util.ArrayList;
import utility.decal.Decal;
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
                setOccupation(icm);
                break;
            case SET_NICKNAME:
                setNickname(icm);
                break;
            case BEGIN:
                begin(icm);
                break;
        }
        super.action(icm);
    }
    
    private void setOccupation(IntentMap icm ) {
        
        // Get the buttons image and set it the the avatar's decal
        JButton button = (JButton)icm.getComponent();
        ImageIcon image = (ImageIcon)button.getIcon();
        model.getGameObject().getAvatar().setDecal( Decal.getImageIcon(image));
        Occupation  ocupation = (Occupation )icm.getObject();
        model.setAvatarOccupation(ocupation);
        
        enableBeginAdventureButton();
    }
    
    private void enableBeginAdventureButton(){
        
        for (IntentMap icm : ims){
            
            if (icm.getIntent().equals(Intent.BEGIN )){
                
                JButton button = (JButton)icm.getComponent();
                button.setEnabled(true);
        
            }
        }
    }
    
    private void setNickname(IntentMap icm) {
        JTextField field = (JTextField)icm.getComponent();
        String nickname = field.getText();
        setNickname(nickname);
    }
    
    private void setNickname(String nickname) {
        
        model.setAvatarNickname(nickname);
    }
    
    private void begin(IntentMap icm){
        
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
