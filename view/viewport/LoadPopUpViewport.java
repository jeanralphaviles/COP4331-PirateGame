/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.viewport;

import controller.Intent;
import controller.IntentMap.IntentMap;
import controller.virtualController.LoadSaveParams;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.Timer;
import model.GameObject;
import model.Model;

/**
 *
 * @author darien
 */
public class LoadPopUpViewport extends LoadSavePopUpViewport {
    
    
    
    public LoadPopUpViewport(){
        
        super();

    }
    
    public LoadPopUpViewport(Model model){
         
         super(model);
     }
     
    @Override
    public void updateView(GameObject gameObject) {
        
       if (errorMessageFrame != null){ 
           
           errorMessageFrame.setVisible(true);
           errorMessageFrame = null;
       }
    }
    
    @Override
    public ArrayList<IntentMap> generateIntentMapping() {
        
        // loadSave:Component // userInstruction:Object // LOAD:Intent
        ArrayList<Component> components = new ArrayList<Component>(1);
        components.add(userInstructionLabel);
        components.add(playerRecordsTreePanel);
        continueAdventureButton.setText("[Load to go to Game]");
        continueAdventureButton.setEnabled(false);
        
        components.add(continueAdventureButton);
        
        LoadSaveParams params = new LoadSaveParams(components);
        
        icms.add (new IntentMap(loadButton, params, Intent.LOAD) );
        icms.add (new IntentMap(saveButton, params, Intent.CAN_NOT_SAVE ));
        
        icms.add (new IntentMap(continueAdventureButton, Intent.GOTO_GAME));
        icms.add (new IntentMap(backButton, Intent.GOTO_MAIN));
        
       
        return icms;
    }
    
}
