/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.virtualController;

import java.util.ArrayList;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import model.Model;
import controller.IntentMap.IntentMap;
import controller.Intent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author darien
 */
public class LoadSaveVirtualController extends VirtualController {
    
    
    private boolean LOADED_SUCCESSFULLY;
    
    public LoadSaveVirtualController(Model model, ArrayList<IntentMap> ims) {
        super(model, ims);
        LOADED_SUCCESSFULLY = false;
    }
    
    @Override
    protected void action(IntentMap icm) {
        
        Intent intent = icm.getIntent();
        switch(intent) {
            
                case LOAD:
                load(icm);
                break; 
                case SAVE:
                save(icm);
                break;
                case CAN_NOT_SAVE:
                canNotSave(icm);
                break;
        }
        super.action(icm);
    }
    
    private void load(IntentMap icm){
            
       LoadSaveParams loadSaveComponents = (LoadSaveParams)icm.getObject();
       JTree tree = loadSaveComponents.getJTree();
        
       if (tree != null){
           
             if ( load(tree) ){
             
                 JButton continueButton = loadSaveComponents.getJButton();
                 JLabel instructionLabel = loadSaveComponents.getJLabel();
                 
                 if ( continueButton != null && instructionLabel != null){
                     
                     
                      instructionLabel.setText("Loading successfully");
                      instructionLabel.repaint();
                      
                      continueButton.setEnabled(true);
                      continueButton.setText("Continue Adventure");
                 }
               
             } 
       }
    }
    
    private boolean load(JTree tree) {

        DefaultMutableTreeNode node = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
        if ( node != null && node.isLeaf() && !node.isRoot() ){

             return model.load( node.toString() );
        }
        
        return false;
    }
    
    private void save(IntentMap icm){
    
        LoadSaveParams loadSaveComponents = (LoadSaveParams)icm.getObject();
        JTextField saveAddress = loadSaveComponents.getJTextField();
        JLabel instructionsLabel = loadSaveComponents.getJLabel();
        
        if ( saveAddress != null &&  instructionsLabel != null){
            
            checkSaveAddressInput(saveAddress,  instructionsLabel );
            
        }

    }
    
    private boolean checkSaveAddressInput(JTextField saveNameTextField, JLabel userInstructionLabel){
        
            
            if (saveNameTextField.getText().isEmpty() ){
            
                userInstructionLabel.setText("Nothing was saved. File name entry is empty.");
                return false;
            }
            else if (saveNameTextField.getText().contains(" ")){
                
                 userInstructionLabel.setText("Error: File name cannot contain spaces.");
                 return false;
            }
            else if (saveNameTextField.getText().contains("*%$")){
                
                 userInstructionLabel.setText("Error: File name cannot contain '*%$' characters sequence.");
                 return false; 
            }
            else if (saveNameTextField.getText().equals("DEFAULT") ){
                
                userInstructionLabel.setText("Saving to default file name.");
                model.save();
                
            }
            else {
                
                userInstructionLabel.setText("Saving to " + saveNameTextField.getText() + " file name." );
                model.save( saveNameTextField.getText() );
            }
            
            return true;
    }
    
    private void canNotSave(IntentMap icm){
        
        LoadSaveParams loadSaveComponents = (LoadSaveParams)icm.getObject();
        JLabel instructionLabel = loadSaveComponents.getJLabel();
        
        if ( instructionLabel != null ){
            
            instructionLabel.setText("Can not save on load mode.");
            instructionLabel.repaint();
        }
    }
    
}
