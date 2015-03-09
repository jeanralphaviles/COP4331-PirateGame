/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.viewport;

import java.awt.GridLayout;
import model.GameObject;
import model.Model;

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
//        addViewport(new AreaViewport(), BorderLayout.NORTH);
//        addViewport(new StatusViewport(), BorderLayout.SOUTH);
//        addViewport(new UtilityViewport(), BorderLayout.EAST);
//        this.
        GridLayout grid = new GridLayout(3, 2, 0, 0);
        setLayout(grid);
        
        addViewport(new AreaViewport());
        addViewport(new StatusViewport());
        addViewport(new UtilityViewport());
        addViewport(new DialogueViewport());
        addViewport(new InventoryViewportConnor());
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
