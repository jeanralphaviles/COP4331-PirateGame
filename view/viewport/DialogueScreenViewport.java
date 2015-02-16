/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.viewport;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.GameObject;
import utility.IntentComponentMap;

/**
 *
 * @author comcc_000
 */
public class DialogueScreenViewport extends ViewPort {

    public DialogueScreenViewport() {
        initComponents();
//        JPanel panel = new JPanel();
//        this.add(panel);
//        panel.setBackground(Color.red);
//        
//        int width = this.getWidth();
//        int height = this.getHeight();
//        panel.setBounds(0, 0, width, height); //x,y top right coordinate, then width, height
//        
//        panel.setVisible(true);
//        
//        this.add(new JLabel("thing"));
//        
//        this.setVisible(true);
//        initInteriorViewports();
//        addInteriorViewports();
    }
    
    private void initInteriorViewports() {
        interiorViewports.add(new FlavorImageViewport());
        //interiorViewports.add(new DialogueViewport());
    }
    
    private void addInteriorViewports() {
        int numInteriorViewports = interiorViewports.size();
        ViewPort view;
        for (int i=0; i<numInteriorViewports; i++) {
            view = interiorViewports.get(i);
            //addViewport(view);
            this.add(view);
            this.revalidate();
            this.repaint();
        }
    }
    
    @Override
    public void updateView(GameObject gameObject) {
        
    }

    @Override
    public ArrayList<IntentComponentMap> generateIntentComponentMapping() {
        //subviewports have all the components...
        ArrayList<IntentComponentMap> icms = new ArrayList<IntentComponentMap>(1);
        //icms.add(new IntentComponentMap(continuePastDialogueButton, IntentComponentMap.Intent.GOTO_GAME));
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    
}
