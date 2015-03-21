/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.viewport;

import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 *
 * @author darien
 */
public class ErrorMessageFrame extends JFrame{
    
    public ErrorMessageFrame(String message){
    
        initComponents(message);
    }
    
    private void initComponents(String message){
        
        this.setSize( 400, 300);
        ErrorMessagePanel panel = new ErrorMessagePanel();
        panel.sendMessage( message );
       
        this.add(panel, BorderLayout.CENTER );
    }
}
