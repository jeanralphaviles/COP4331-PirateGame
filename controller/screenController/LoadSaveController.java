/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.screenController;

import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import model.Model;
import controller.controllerMap.IntentComponentMap;
import controller.controllerMap.IntentComponentMap.Intent;

/**
 *
 * @author darien
 */
public class LoadSaveController extends ScreenController {
    
    public LoadSaveController(Model model, ArrayList<IntentComponentMap> icms) {
        super(model, icms);
    }
    
    @Override
    protected void action(IntentComponentMap icm) {
        
        Intent intent = icm.getIntent();
        switch(intent) {
            
                case LOAD:
                load();
                break; 
        }
        super.action(icm);
    }
    
    private void load(){
        
        // Get label
        for (IntentComponentMap i : icms){
            
            // Change label text
            if ( i.getIntent() == Intent.TREE_PANEL ){
                
                JTree tree = (JTree)i.getComponent();
                DefaultMutableTreeNode node = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
                if ( node != null && node.isLeaf() ){
                    
                    model.load( node.toString() );
                }
                   
            }
            
        }
    }      
    
}
