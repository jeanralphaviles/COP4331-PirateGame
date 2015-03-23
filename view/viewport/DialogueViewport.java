/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.viewport;

import controller.Intent;

import java.awt.Component;
import java.util.ArrayList;


import model.GameObject;
import controller.IntentMap.IntentMap;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.SwingConstants;

import model.Level;
import utility.ImageUtil;

/**
 *
 * @author comcc_000
 */
public class DialogueViewport extends ViewPort {

    
    public static boolean continueButtonVisible = true;
    public static boolean yesButtonVisible = false;
    public static boolean noButtonVisible = false;
    
    /**
     * Creates new form DialogueViewport
     */
    public DialogueViewport() {
        initComponents();
        yesButton.setVisible(yesButtonVisible);
        noButton.setVisible(noButtonVisible);
    }

    @Override
    public void updateView(GameObject gameObject) {
        String dialogue = Level.getResponse().getDialogue(); //gameObject.getLevel().getCurrentDialogue();
        dialogueTextView.setText(dialogue);
    }

    @Override
    public ArrayList<IntentMap> generateIntentMapping() {
        // subviewports have all the components...
        ArrayList<IntentMap> ims = new ArrayList<IntentMap>(1);
        ims.add(new IntentMap(continueButton, Intent.SHOW_DIALOGUE));
        return ims;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image image = ImageUtil.getImage( ImageUtil.dialogue_viewport_background, this.getWidth(), this.getHeight()).getImage();
        g.drawImage( image, 0, 0, this);
    }
    
    public void setVisibilities(boolean continueVisible, boolean yesVisible, boolean noVisible) {
        DialogueViewport.continueButtonVisible = continueVisible;
        DialogueViewport.yesButtonVisible = yesVisible;
        DialogueViewport.noButtonVisible = noVisible;
    }
    
    public void updateButtonVisibilities() {
        continueButton.setVisible(continueButtonVisible);
        yesButton.setVisible(yesButtonVisible);
        noButton.setVisible(noButtonVisible);
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
        dialogueTextView = new javax.swing.JTextField();
        yesButton = new javax.swing.JButton();
        noButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 51, 51));
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setAlignmentY(Component.CENTER_ALIGNMENT);
        setPreferredSize(new java.awt.Dimension(689, 35));

        continueButton.setText("Continue");
        continueButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        continueButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        continueButton.setBorder(null);
        continueButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        continueButton.setPreferredSize(new java.awt.Dimension(75, 20));

        dialogueTextView.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        dialogueTextView.setForeground(new java.awt.Color(255, 255, 255));
        dialogueTextView.setHorizontalAlignment(SwingConstants.CENTER);
        dialogueTextView.setText("Dialogue");
        dialogueTextView.setAlignmentX(Component.LEFT_ALIGNMENT);
        dialogueTextView.setAlignmentY(Component.CENTER_ALIGNMENT);
        dialogueTextView.setBorder(null);
        dialogueTextView.setOpaque(false);
        dialogueTextView.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                dialogueTextViewActionPerformed(evt);
            }
        });

        yesButton.setText("Yes");
        yesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        yesButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        yesButton.setBorder(null);
        yesButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        yesButton.setPreferredSize(new java.awt.Dimension(75, 20));

        noButton.setText("No");
        noButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        noButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        noButton.setBorder(null);
        noButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        noButton.setPreferredSize(new java.awt.Dimension(75, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dialogueTextView, javax.swing.GroupLayout.DEFAULT_SIZE, 689, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(continueButton, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(yesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(noButton, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(dialogueTextView, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(continueButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void dialogueTextViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dialogueTextViewActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dialogueTextViewActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton continueButton;
    private javax.swing.JTextField dialogueTextView;
    private javax.swing.JButton noButton;
    private javax.swing.JButton yesButton;
    // End of variables declaration//GEN-END:variables
}
