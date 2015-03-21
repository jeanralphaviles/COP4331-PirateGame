/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.virtualController;

import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTree;

/**
 *
 * @author darien
 */
public class LoadSaveParams {
    
    // Attriubutes:
    public ArrayList<Component> components;
    
    public LoadSaveParams(ArrayList<Component> components){
        
        this.components = components;
    }
    
    public JTree getJTree(){
        
        for ( Component comps : components){
            
            if ( comps instanceof JTree){
                
                return (JTree)comps;
            }
        }
        return null;
    }
    
     public JLabel getJLabel(){
        
        for ( Component comps : components){
            
            if ( comps instanceof JLabel){
                
                return (JLabel)comps;
            }
        }
        return null;
    }
     
    public JTextField getJTextField(){
        
        for ( Component comps : components){
            
            if ( comps instanceof JTextField){
                
                return (JTextField)comps;
            }
        }
        return null;
    }
    
    public JButton getJButton(){
        
        for ( Component comps : components){
            
            if ( comps instanceof JButton){
                
                return (JButton)comps;
            }
        }
        return null;
    }
}
