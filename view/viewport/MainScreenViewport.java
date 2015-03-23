/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.viewport;

import java.util.ArrayList;
import model.GameObject;
import controller.IntentMap.IntentMap;
import controller.Intent;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import utility.decal.Decal;

/**
 *
 * @author comcc_000
 */
public class MainScreenViewport extends ViewPort {
    
    /*Properties*/
    Image backgroundImage;

    /*Constructors*/
    public MainScreenViewport() {
        initComponents();
        setVisible(true);
        
        backgroundImage = Toolkit.getDefaultToolkit().createImage("./Sprites/backgrounds/MainMenuBackground.jpg");
        
        ImageIcon img = new ImageIcon("./Sprites/backgrounds/plank.jpg");
        newGameButton.setIcon(img);
        exitButton.setIcon(img);
        loadButton.setIcon(img);
    }

    /*Methods*/
   
    
    @Override
    public ArrayList<IntentMap> generateIntentMapping() {
        ArrayList<IntentMap> ims = new ArrayList<IntentMap>(1);
        ims.add(new IntentMap(newGameButton, Intent.NEW));
        ims.add(new IntentMap(loadButton, Intent.GOTO_LOAD));
        ims.add(new IntentMap(exitButton, Intent.EXIT));
        return ims;
    }

    @Override
    public void updateView(GameObject gameObject) {
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
    }
    

    /*Get-Sets*/
    
    /*Inner class*/

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        exitButton = new javax.swing.JButton();
        loadButton = new javax.swing.JButton();
        newGameButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        jLabel2.setText("jLabel2");

        setBackground(new java.awt.Color(0, 0, 0));
        setMinimumSize(new java.awt.Dimension(25, 25));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        exitButton.setFont(new java.awt.Font("Luminari", 3, 18)); // NOI18N
        exitButton.setText("Exit");
        add(exitButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 410, 420, 50));

        loadButton.setFont(new java.awt.Font("Luminari", 3, 18)); // NOI18N
        loadButton.setText("Load Game");
        add(loadButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 350, 420, 50));

        newGameButton.setFont(new java.awt.Font("Luminari", 3, 18)); // NOI18N
        newGameButton.setText("New Game");
        add(newGameButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 290, 420, 50));

        jLabel3.setBackground(new java.awt.Color(255, 0, 0));
        jLabel3.setFont(new java.awt.Font("Luminari", 3, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 60, 0));
        jLabel3.setText("Welcome Aboard!");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 200, 310, 90));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exitButton;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton loadButton;
    private javax.swing.JButton newGameButton;
    // End of variables declaration//GEN-END:variables
}
