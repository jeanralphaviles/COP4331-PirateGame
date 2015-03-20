/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.viewport;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.Timer;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import model.GameObject;
import model.Model;
import controller.controllerMap.IntentComponentMap;
import controller.controllerMap.IntentComponentMap.Intent;
import view.viewport.ViewPort;

/**
 *
 * @author darien
 */
public class LoadSavePopUpViewport extends ViewPort {

    /**
     * Creates new form LoadSavePopUpViewport
     */
    Model model;
    private ArrayList<IntentComponentMap> icms = new ArrayList<IntentComponentMap>(1);
    boolean savePanelActivated;
    
    public LoadSavePopUpViewport() {
        initComponents();
        createTreePanelGUI();
        savePanelActivated = false;
        hideSavePanel();
    }
    
    public LoadSavePopUpViewport(Model model){
        
       
           this.model = model;
           initComponents();
           createTreePanelGUI(); 
           savePanelActivated = false;
           hideSavePanel();
    }
    
    public static void main(String[] args){
        
        JFrame frame = new JFrame();
        frame.setSize(2500,2500);
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.add ( new LoadSavePopUpViewport() );
        
        frame.setVisible(true);
        
    }
    
    /*
    Note: This functions is intially called in the constructor(s).
    Is meant to do hide the save panel or pop the save panel whenever is showed or hidden
    respectively.
    */
     private void hideSavePanel(){
            
            savePanel.setVisible(savePanelActivated);
            Sliding slide = new Sliding(jScrollPane3, playerRecordsTreePanel);
             savePanelActivated = !savePanelActivated;
     }
    
    @Override
    public void updateView(GameObject gameObject) {
    
        //createTreePanelGUI();
    }
    
    @Override
    public ArrayList<IntentComponentMap> generateIntentComponentMapping() {
        
        System.out.println("Intent Componenet is being called");
        icms.add ( new IntentComponentMap(loadButton, Intent.LOAD) );
        icms.add ( new IntentComponentMap(userInstructionLabel, Intent.LABEL));
        //icms.add ( new IntentComponentMap(playerRecordsTreePanel, Intent.TREE_PANEL));
        icms.add ( new IntentComponentMap(continueAdventureButton, Intent.GOTO_GAME));
        icms.add ( new IntentComponentMap(backButton, Intent.GOTO_MAIN));
       
        return icms;
    }

    private void createTreePanelGUI(){
        
        // This is the root of the tree
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Avatars");
        
        File folder = new File("LoadSave/");
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            
            if (file.isDirectory()) {
               
                // Create Directory Node
                DefaultMutableTreeNode directoryName = new DefaultMutableTreeNode(file.getName());
                top.add(directoryName);

                File avatarFiles = new File( "LoadSave/" + file.getName() + "/" );
                File [] listOfAvatarFiles = avatarFiles.listFiles();
                System.out.println( listOfAvatarFiles.length );
                
                if ( avatarFiles != null && avatarFiles.exists()){
                
                    for ( File i : listOfAvatarFiles){

                        if (i.isFile() ){

                            // Create File Nodes
                            System.out.println( i.getName() );
                            DefaultMutableTreeNode fileName = new DefaultMutableTreeNode(i.getName());
                            directoryName.add(fileName);
                        } 
                    }
                
                }
            }
        }
       
       playerRecordsTreePanel = new JTree( top );
       playerRecordsTreePanel.setBackground( new Color (123,38,38));
       playerRecordsTreePanel.setModel(new javax.swing.tree.DefaultTreeModel(top));
       jScrollPane3.setViewportView(playerRecordsTreePanel);
    }
    
    private class Sliding implements ActionListener{
        
        Timer time;
        JScrollPane panel;
        JTree panel1;
        
        public Sliding(JScrollPane panel, JTree panel1){
            
            this.panel = panel;
            this.panel1 = panel1;
            time = new Timer(15,this);
            time.start();
        }
        
        //new Timer(15, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          
            if ( !savePanelActivated ){
                
                panel.setSize(panel.getWidth(), panel.getHeight() - 2);
                panel1.setSize(panel1.getWidth(), panel1.getHeight() - 2);
                if ( panel.getHeight() + panel.getY() <= savePanel.getY() + 20 ) {

                    ((Timer) e.getSource()).stop();
                     System.out.println("Timer stopped");
                }
                
            }
            else {
                
                panel.setSize(panel.getWidth(), panel.getHeight() + 2);
                panel1.setSize(panel1.getWidth(), panel1.getHeight() + 2);
                if ( panel.getHeight() + panel.getY() >= savePanel.getY() + savePanel.getHeight() ) {

                    ((Timer) e.getSource()).stop();
                     System.out.println("Timer stopped");
                }
               
            }
        } // End of-actionPerfomed
        
    }
 
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loadSavePanel = new javax.swing.JPanel();
        loadSaveFunctionalitiesPanel = new javax.swing.JPanel();
        loadButton = new javax.swing.JButton();
        continueAdventureButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        columnNamesPanel = new javax.swing.JPanel();
        userInstructionLabel = new javax.swing.JLabel();
        savePanel = new javax.swing.JPanel();
        saveNameLabel = new javax.swing.JLabel();
        saveNameTextField = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        playerRecordsTreePanel = new javax.swing.JTree();

        setBackground(new java.awt.Color(148, 61, 78));
        setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 1, 1, new java.awt.Color(0, 0, 0)));
        setMaximumSize(new java.awt.Dimension(1000, 1000));
        setMinimumSize(new java.awt.Dimension(1000, 1000));
        setPreferredSize(new java.awt.Dimension(1000, 1000));
        setVerifyInputWhenFocusTarget(false);

        loadSavePanel.setBackground(new java.awt.Color(70, 52, 35));
        loadSavePanel.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 1, 1, new java.awt.Color(0, 0, 0)));

        loadSaveFunctionalitiesPanel.setBackground(new java.awt.Color(151, 99, 47));
        loadSaveFunctionalitiesPanel.setMaximumSize(new java.awt.Dimension(572, 101));
        loadSaveFunctionalitiesPanel.setMinimumSize(new java.awt.Dimension(572, 101));
        loadSaveFunctionalitiesPanel.setPreferredSize(new java.awt.Dimension(572, 101));

        loadButton.setText("Load");

        continueAdventureButton.setText("Continue Adventure");

        backButton.setText("Back");

        saveButton.setText("Save");
        saveButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout loadSaveFunctionalitiesPanelLayout = new javax.swing.GroupLayout(loadSaveFunctionalitiesPanel);
        loadSaveFunctionalitiesPanel.setLayout(loadSaveFunctionalitiesPanelLayout);
        loadSaveFunctionalitiesPanelLayout.setHorizontalGroup(
            loadSaveFunctionalitiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loadSaveFunctionalitiesPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(loadSaveFunctionalitiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loadButton, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(continueAdventureButton, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        loadSaveFunctionalitiesPanelLayout.setVerticalGroup(
            loadSaveFunctionalitiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loadSaveFunctionalitiesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(loadSaveFunctionalitiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loadButton)
                    .addComponent(continueAdventureButton)
                    .addComponent(backButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(saveButton)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        columnNamesPanel.setBackground(new java.awt.Color(226, 177, 127));
        columnNamesPanel.setMaximumSize(new java.awt.Dimension(572, 55));
        columnNamesPanel.setMinimumSize(new java.awt.Dimension(572, 55));

        userInstructionLabel.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        userInstructionLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        userInstructionLabel.setText("Select desired game by the Avatar's Name, Level and Date/Time");
        userInstructionLabel.setMaximumSize(new java.awt.Dimension(572, 31));
        userInstructionLabel.setMinimumSize(new java.awt.Dimension(572, 31));
        userInstructionLabel.setPreferredSize(new java.awt.Dimension(572, 31));

        javax.swing.GroupLayout columnNamesPanelLayout = new javax.swing.GroupLayout(columnNamesPanel);
        columnNamesPanel.setLayout(columnNamesPanelLayout);
        columnNamesPanelLayout.setHorizontalGroup(
            columnNamesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(columnNamesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(userInstructionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        columnNamesPanelLayout.setVerticalGroup(
            columnNamesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(columnNamesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(userInstructionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        savePanel.setBackground(new java.awt.Color(151, 99, 47));
        savePanel.setMaximumSize(new java.awt.Dimension(572, 101));
        savePanel.setMinimumSize(new java.awt.Dimension(572, 101));
        savePanel.setPreferredSize(new java.awt.Dimension(572, 101));

        saveNameLabel.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        saveNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        saveNameLabel.setText("Save Name:");
        saveNameLabel.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 1, 1, new java.awt.Color(0, 0, 0)));

        saveNameTextField.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        saveNameTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        saveNameTextField.setText("DEFAULT");

        javax.swing.GroupLayout savePanelLayout = new javax.swing.GroupLayout(savePanel);
        savePanel.setLayout(savePanelLayout);
        savePanelLayout.setHorizontalGroup(
            savePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(savePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(saveNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(saveNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        savePanelLayout.setVerticalGroup(
            savePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(savePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(savePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saveNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(53, Short.MAX_VALUE))
        );

        playerRecordsTreePanel.setBackground(new java.awt.Color(116, 24, 24));
        jScrollPane3.setViewportView(playerRecordsTreePanel);

        javax.swing.GroupLayout loadSavePanelLayout = new javax.swing.GroupLayout(loadSavePanel);
        loadSavePanel.setLayout(loadSavePanelLayout);
        loadSavePanelLayout.setHorizontalGroup(
            loadSavePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(loadSaveFunctionalitiesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)
            .addGroup(loadSavePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(loadSavePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(savePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3))
                .addContainerGap())
            .addComponent(columnNamesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        loadSavePanelLayout.setVerticalGroup(
            loadSavePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loadSavePanelLayout.createSequentialGroup()
                .addComponent(columnNamesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(savePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(loadSaveFunctionalitiesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(407, 407, 407)
                .addComponent(loadSavePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(153, 153, 153)
                .addComponent(loadSavePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(261, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void saveButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveButtonMouseClicked
        // TODO add your handling code here:
       
        if ( !savePanelActivated && model != null ){
            
            System.out.println(saveNameTextField.getText());
            
            if (saveNameTextField.getText().isEmpty() ){
            
                userInstructionLabel.setText("Nothing was saved. File name entry is empty.");
                return;
            }
            else if (saveNameTextField.getText().contains(" ")){
                
                 userInstructionLabel.setText("Error: File name cannot contain spaces.");
                 return;
            }
            else if (saveNameTextField.getText().contains("*%$")){
                
                 userInstructionLabel.setText("Error: File name cannot contain '*%$' characters sequence.");
                 return; 
            }
            else if (saveNameTextField.getText().equals("DEFAULT") ){
                
                userInstructionLabel.setText("Saving to default file name.");
                model.save();
            }
            else {
                
                 userInstructionLabel.setText("Saving to " + saveNameTextField.getText() + " file name." );
                model.save( saveNameTextField.getText() );
            }
           
            
        }
        
        hideSavePanel();
                 
        
        
    }//GEN-LAST:event_saveButtonMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JPanel columnNamesPanel;
    private javax.swing.JButton continueAdventureButton;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton loadButton;
    private javax.swing.JPanel loadSaveFunctionalitiesPanel;
    private javax.swing.JPanel loadSavePanel;
    private javax.swing.JTree playerRecordsTreePanel;
    private javax.swing.JButton saveButton;
    private javax.swing.JLabel saveNameLabel;
    private javax.swing.JTextField saveNameTextField;
    private javax.swing.JPanel savePanel;
    private javax.swing.JLabel userInstructionLabel;
    // End of variables declaration//GEN-END:variables
}
