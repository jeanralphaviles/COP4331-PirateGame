/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.viewport;

import java.util.ArrayList;
import model.GameObject;
import controller.IntentMap.IntentMap;

/**
 *
 * @author comcc_000
 */
public class TradeViewport extends ViewPort {

    /**
     * Creates new form FlavorImageViewport
     */
    public TradeViewport() {
        initComponents();
    }
    
    @Override
    public void updateView(GameObject gameObject) {
        
    }

    @Override
    public ArrayList<IntentMap> generateIntentMapping() {
        ArrayList<IntentMap> ims = new ArrayList<IntentMap>(1);
        
        
        
        return ims;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        continueButton = new javax.swing.JButton();

        continueButton.setText("Continue");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 505, Short.MAX_VALUE)
                .addComponent(continueButton))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 267, Short.MAX_VALUE)
                .addComponent(continueButton))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton continueButton;
    // End of variables declaration//GEN-END:variables

    
}