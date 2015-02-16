package controller;

import javax.swing.*;
import java.util.ArrayList;
import model.Model;
import model.entity.occupation.*;
import utility.IntentComponentMap;
import utility.IntentComponentMap.Intent;
import view.viewport.NewGamePopupViewport;

public final class NewGamePopupController extends ScreenController {

    public NewGamePopupController(Model model, ArrayList<IntentComponentMap> icms) {
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
        if (intent == Intent.USER_INPUT) {
            switch (details) {
                case NewGamePopupViewport.select_nickname:
                    JTextField tf = (JTextField)icm.getObject();
                    String nickname = tf.getText();
                    model.setAvatarNickname(nickname);
                    break;
                case NewGamePopupViewport.select_parrotmancer:
                    setOccupationIfSelected(icm, new Summoner(), model);
                    break;
                case NewGamePopupViewport.select_gankplanker:
                    setOccupationIfSelected(icm, new Smasher(), model);
                    break;
                case NewGamePopupViewport.select_turnhat:
                    setOccupationIfSelected(icm, new Sneak(), model);
                    break;
                default:
                //
            }
        }
    }
    
    private void setOccupationIfSelected(IntentComponentMap icm, Occupation occupation, Model model) {
        JRadioButton rb = (JRadioButton)icm.getObject();
        setOccupationIfSelected(rb, occupation, model);
        
    }
    
    private void setOccupationIfSelected(JRadioButton rb, Occupation occupation, Model model) {
        if (rb.isSelected()) {
            model.setAvatarOccupation(occupation);
        }
    }

}
