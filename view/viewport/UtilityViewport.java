/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.viewport;

import java.util.ArrayList;
import model.GameObject;
import utility.IntentComponentMap;

/**
 *
 * @author comcc_000
 */
public class UtilityViewport extends ViewPort {

    /**
     * Creates new form DialogueViewport
     */
    public UtilityViewport() {
        initComponents();
    }

    @Override
    public void updateView(GameObject gameObject) {
        //
    }

    @Override
    public ArrayList<IntentComponentMap> generateIntentComponentMapping() {
        // subviewports have all the components...
        ArrayList<IntentComponentMap> icms = new ArrayList<IntentComponentMap>(1);
        icms.add(new IntentComponentMap(pauseButton, IntentComponentMap.Intent.GOTO_PAUSE));
        return icms;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pauseButton = new javax.swing.JButton();

        pauseButton.setText("Pause");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pauseButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pauseButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton pauseButton;
    // End of variables declaration//GEN-END:variables
}
