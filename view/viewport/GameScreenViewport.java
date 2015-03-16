/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.viewport;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import model.GameObject;
import model.Model;


/**
 *
 * @author Carlos Vizcaino
 */
public class GameScreenViewport extends ViewPort {
    
    public static void main(String[] args){
        
        JFrame frame = new JFrame();
        frame.setSize(2500,2500);
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.add( new GameScreenViewport() );
        
        System.out.println("This is running...");
        frame.setVisible(true);
       
        
    }
    
    // Attributes:
    InventoryViewPort inventoryViewport;
    AreaViewport areaViewport;
    
    // Default Constructor
    public GameScreenViewport() {
        
        initComponents();
        generateView();
    }
    
    // Constructor I
    public GameScreenViewport(Model model){
        initComponents();
        generateView();
    }
   
    
    public void generateView(){
        
        areaViewport = new AreaViewport();
        inventoryViewport = new InventoryViewPort();
        
        this.addViewport(areaViewport);
        GamePanel.setLayout( new BorderLayout() );
        GamePanel.add( areaViewport , BorderLayout.CENTER );
        
        StatisticsPanel.setLayout( new BorderLayout() );
        StatisticsPanel.add( new StatusViewport() , BorderLayout.CENTER);
        
        this.addViewport( inventoryViewport );
        InventoryMainPanel.setLayout( new BorderLayout() );
        InventoryMainPanel.add( inventoryViewport , BorderLayout.CENTER );
        
        
        //GamePanel.setLayout( new BorderLayout() );
        //GamePanel.add ( new AreaViewport() , BorderLayout.WEST );
       
        StatisticsPanel.revalidate();
        StatisticsPanel.repaint();
        
        this.revalidate();
        this.repaint();
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

        GamePanel = new javax.swing.JPanel();
        DialoguePanel = new javax.swing.JPanel();
        StatisticsPanel = new javax.swing.JPanel();
        InventoryMainPanel = new javax.swing.JPanel();
        AbilitiesPanel = new javax.swing.JPanel();

        setBackground(new java.awt.Color(42, 30, 127));
        setMaximumSize(new java.awt.Dimension(2000, 2500));
        setMinimumSize(new java.awt.Dimension(2000, 2500));
        setPreferredSize(new java.awt.Dimension(2000, 2500));

        javax.swing.GroupLayout GamePanelLayout = new javax.swing.GroupLayout(GamePanel);
        GamePanel.setLayout(GamePanelLayout);
        GamePanelLayout.setHorizontalGroup(
            GamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 963, Short.MAX_VALUE)
        );
        GamePanelLayout.setVerticalGroup(
            GamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout DialoguePanelLayout = new javax.swing.GroupLayout(DialoguePanel);
        DialoguePanel.setLayout(DialoguePanelLayout);
        DialoguePanelLayout.setHorizontalGroup(
            DialoguePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 963, Short.MAX_VALUE)
        );
        DialoguePanelLayout.setVerticalGroup(
            DialoguePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        StatisticsPanel.setMaximumSize(new java.awt.Dimension(425, 425));
        StatisticsPanel.setMinimumSize(new java.awt.Dimension(425, 425));
        StatisticsPanel.setPreferredSize(new java.awt.Dimension(425, 425));

        javax.swing.GroupLayout StatisticsPanelLayout = new javax.swing.GroupLayout(StatisticsPanel);
        StatisticsPanel.setLayout(StatisticsPanelLayout);
        StatisticsPanelLayout.setHorizontalGroup(
            StatisticsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        StatisticsPanelLayout.setVerticalGroup(
            StatisticsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 425, Short.MAX_VALUE)
        );

        InventoryMainPanel.setMaximumSize(new java.awt.Dimension(425, 425));
        InventoryMainPanel.setMinimumSize(new java.awt.Dimension(425, 425));
        InventoryMainPanel.setPreferredSize(new java.awt.Dimension(425, 425));

        javax.swing.GroupLayout InventoryMainPanelLayout = new javax.swing.GroupLayout(InventoryMainPanel);
        InventoryMainPanel.setLayout(InventoryMainPanelLayout);
        InventoryMainPanelLayout.setHorizontalGroup(
            InventoryMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 995, Short.MAX_VALUE)
        );
        InventoryMainPanelLayout.setVerticalGroup(
            InventoryMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1865, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout AbilitiesPanelLayout = new javax.swing.GroupLayout(AbilitiesPanel);
        AbilitiesPanel.setLayout(AbilitiesPanelLayout);
        AbilitiesPanelLayout.setHorizontalGroup(
            AbilitiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        AbilitiesPanelLayout.setVerticalGroup(
            AbilitiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DialoguePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 963, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(GamePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(InventoryMainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 995, Short.MAX_VALUE)
                    .addComponent(StatisticsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 995, Short.MAX_VALUE)
                    .addComponent(AbilitiesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 995, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(StatisticsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(InventoryMainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1865, Short.MAX_VALUE))
                    .addComponent(GamePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(AbilitiesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(DialoguePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AbilitiesPanel;
    private javax.swing.JPanel DialoguePanel;
    private javax.swing.JPanel GamePanel;
    private javax.swing.JPanel InventoryMainPanel;
    private javax.swing.JPanel StatisticsPanel;
    // End of variables declaration//GEN-END:variables

    // --------------------------------------------
    

}
