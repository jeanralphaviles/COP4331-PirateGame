/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.viewport;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import model.GameObject;
import model.Model;
import model.entity.Avatar;
import utility.IntentComponentMap;
import view.viewport.InventoryViewPort.MainInventoryPanel;

import java.util.ArrayList;

/**
 *
 * @author comcc_000
 */
public class GameScreenViewport extends ViewPort {

    // Default Constructor
    public GameScreenViewport() {
        generateView();
    }
    
    // Constructor I
    public GameScreenViewport(Model model){
        generateView();
    }
    
    public void generateView(){


        JPanel northernPanel = new JPanel();
        JPanel southernPanel = new JPanel();

        // Replace these colorPanel's with appropriate panels.



       
        
        interiorViewports.add(new AreaViewport());
        //interiorViewports.add(new InventoryViewport());
        /*interiorViewports.get(0)*/
        add(interiorViewports.get(0));
//        add(interiorViewports.get(0), BorderLayout.NORTH);
//        northernPanel.add(new MainInventoryPanel( new Avatar() ), BorderLayout.SOUTH);
//        
//        //southernPanel.add(new "DialogueViewPort", BorderLayout.NORTH);
//        //southernPanel.add(new "StatusViewPort", BorderLayout.SOUTH);
//         
//        this.add( northernPanel, BorderLayout.SOUTH );
        //this.add(southernPanel, BorderLayout.SOUTH);

    }
    
    @Override
    public void updateView(GameObject gameObject) {
        int numInteriorViewports = interiorViewports.size();
        ViewPort viewport;
        for (int i=0; i<numInteriorViewports; i++) {
            viewport = interiorViewports.get(i);
            viewport.updateView(gameObject);
        }
    }

    @Override
    public ArrayList<IntentComponentMap> generateIntentComponentMapping() {
        ArrayList<IntentComponentMap> icms = new ArrayList<IntentComponentMap>(1);
        //icms.add(new IntentComponentMap(pauseButton, IntentComponentMap.Intent.GOTO_PAUSE));
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
            .addGap(0, 885, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    // --------------------------------------------
    

}
