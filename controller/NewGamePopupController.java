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

//    @Override
//    protected void processUserInput(Model model) {
//        int numICMs = icms.size();
//        IntentComponentMap icm;
//        for (int i = 0; i < numICMs; i++) {
//            icm = icms.get(i);
//            processUserInput(icm, model);
//        }
//    }

//    private void processUserInput(IntentComponentMap icm, Model model) {
//        Intent intent = icm.getIntent();
//        String details = icm.getDetails();
//        if (intent == Intent.USER_INPUT) {
//
//
//                if ( details == NewGamePopupViewport.select_nickname){
//
//                    JTextField tf = (JTextField)icm.getObject();
//                    String nickname = tf.getText();
//                    model.setAvatarNickname(nickname);
//                }
//                else if ( details == NewGamePopupViewport.select_parrotmancer){
//
//                    setOccupationIfSelected(icm, new Summoner(), model);
//                }
//                else if ( details == NewGamePopupViewport.select_gankplanker){
//
//                    setOccupationIfSelected(icm, new Smasher(), model);
//                }
//                else if ( details == NewGamePopupViewport.select_turnhat){
//
//                    setOccupationIfSelected(icm, new Sneak(), model);
//                }
//
//        } // End of bigger if statement
//    }
//    
//    private void setOccupationIfSelected(IntentComponentMap icm, Occupation occupation, Model model) {
//        JRadioButton rb = (JRadioButton)icm.getObject();
//        setOccupationIfSelected(rb, occupation, model);
//    }
//    
//    private void setOccupationIfSelected(JRadioButton rb, Occupation occupation, Model model) {
//        if (rb.isSelected()) {
//            model.setAvatarOccupation(occupation);
//        }
//    }

}
