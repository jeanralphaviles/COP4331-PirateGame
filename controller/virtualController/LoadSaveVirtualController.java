/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.virtualController;

import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import model.Model;
import controller.IntentMap.IntentMap;
import controller.Intent;

/**
 *
 * @author darien
 */
public class LoadSaveVirtualController extends VirtualController {
    
    public LoadSaveVirtualController(Model model, ArrayList<IntentMap> ims) {
        super(model, ims);
    }
    
    @Override
    protected void action(IntentMap icm) {
        
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
        for (IntentMap i : ims){
            
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
