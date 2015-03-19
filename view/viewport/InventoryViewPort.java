/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.viewport;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.GameObject;
import model.entity.Avatar;
import model.item.Item;
import utility.IntentComponentMap;
import view.viewport.ViewPort;

/**
 *
 * @author darien
 */
public class InventoryViewPort extends ViewPort {
    
    // Attributes
     // Attributes
    private ArrayList<JButton> buttons = new ArrayList<JButton>(1);
    private ArrayList<IntentComponentMap> icms = new ArrayList<IntentComponentMap>(1);
    private int totalEquippedItems;
    private int totalInventoryItems;
    Color SELECTED_BUTTON_COLOR;
    
    /**
     * Creates new form InventoryViewPort
     */
    
    public InventoryViewPort() {
        initComponents();
        SELECTED_BUTTON_COLOR = Color.BLUE;
    }
    
    public static void main(String[] args){
        
        JFrame frame = new JFrame();
        frame.setSize(2500,2500);
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.add ( new InventoryViewPort()  );
        
        frame.setVisible(true);
        
    }
    // -----------------------------------------------------
   @Override
    public void updateView(GameObject gameObject) {      
        
        
        Avatar avatar = gameObject.getAvatar();
         
        if (refreshNeeded(avatar)) {
            //Clear viewport
            System.out.println("Updating the view");
            clearAllItems();
            clearButtonsAndICMs(); 
            
            //Display again the gameObject
            displayEquippedInventory(avatar);
            displayInventory(avatar);
            addCommandButtonsToICMs();
        }
    }
   
    /*  Determine if items were added or taken away (numTotalItems has changed).
        If so, then need to refresh the view AND controller (because new buttons were added).
        Else, no point in updating. The listeners effectively update existing buttons.
    */
    private boolean refreshNeeded(Avatar avatar) {
       
        
        int newTotalEquippedItems = avatar.getEquippedInventory().getItems().size();
        int newTotalInventoryItems = avatar.getInventory().getItems().size();
        
        if ( newTotalEquippedItems != totalEquippedItems) {
            
            totalEquippedItems = newTotalEquippedItems;
            refreshControllerNeeded = true;
        } 
        else if ( newTotalInventoryItems != totalInventoryItems ){
               
            totalInventoryItems = newTotalInventoryItems;
            refreshControllerNeeded =  true;
        }
        else {

            refreshControllerNeeded = false;
        }
        
        return refreshControllerNeeded;
    }
    // -----------------------------------------------------
     private void displayEquippedInventory(Avatar avatar) {

        ArrayList<Item> equippedItems = avatar.getEquippedInventory().getItems();
        for (int i = 0; i < equippedItems.size(); i++) {
            displayItem(equippedItems.get(i), true);
        }
    }
    // -----------------------------------------------------
    private void displayInventory(Avatar avatar) {
      
        ArrayList<Item> inventoryItems = avatar.getInventory().getItems();
        for (int i=0; i< inventoryItems.size(); i++) {
            displayItem(inventoryItems.get(i), false);
        }
    }
    // -----------------------------------------------------
    private void displayItem(Item item, boolean equipped) {
        
        if (equipped) {
            
            for (Component comp : EquippedInventoryPanel.getComponents() ){
                
                // Cast
                JButton tempButton = (JButton)comp;
                // If Button Available
                if ( tempButton.getIcon() == null ){

                    tempButton.setIcon( new ImageIcon ( item.getDecal().getImage()) );
                    icms.add(new IntentComponentMap(tempButton, item, IntentComponentMap.Intent.TOGGLE_EQUIPPED));
                    buttons.add(tempButton);
                    return;
                }
            }
            
        } else {
            
            for (Component comp : InventoryPanel.getComponents() ){
                
                // Cast
                JButton tempButton = (JButton)comp;
                // If Button Available
                if ( tempButton.getIcon() == null ){

                    tempButton.setIcon( new ImageIcon ( item.getDecal().getImage()) );
                    icms.add(new IntentComponentMap(tempButton, item, IntentComponentMap.Intent.INVENTORY_ITEM));
                    buttons.add(tempButton);
                    return;
                }
            }
        }
        
        
    }
    // -----------------------------------------------------
    private void addCommandButtonsToICMs(){
        
        icms.add(new IntentComponentMap(equipButton, IntentComponentMap.Intent.EQUIP_ITEM));
        icms.add(new IntentComponentMap(unequipButton, IntentComponentMap.Intent.UNEQUIP_ITEM));
        icms.add(new IntentComponentMap(dropButton, IntentComponentMap.Intent.DROP_ITEM)); 
    }
    // -----------------------------------------------------
    private void incrementButtonDimension(JButton button, boolean rightSideOfThePanel){
        
        if ( rightSideOfThePanel){
            
             Point location = button.getLocation();
            button.setLocation( location.x - 25, location.y - 5);
            button.setSize(new Dimension (100,70) );
            button.setFocusable(true);
        }
        else{
            
            Point location = button.getLocation();
            button.setLocation( location.x - 10, location.y - 5);
            button.setSize(new Dimension (100,70) );
            button.setFocusable(true);
        }
        
    }
    // -----------------------------------------------------    
    private void decrementButtonDimension(JButton button, boolean rightSideOfThePanel){
       
        if ( rightSideOfThePanel ){
        
            Point location = button.getLocation();
            button.setLocation( location.x + 25, location.y + 5);
            button.setSize(new Dimension (72,55) );
            button.repaint();
        }
        else {
            
            Point location = button.getLocation();
            button.setLocation( location.x + 10, location.y + 5);
            button.setSize(new Dimension (72,55) );
            button.repaint();
            
            
        }
    }
    // -----------------------------------------------------
    public void clearAllItems(){
        
        // Reset Equipped Inventory
        for (Component i : EquippedInventoryPanel.getComponents() ){
            
            JButton tempButton = (JButton)i;
            if ( tempButton.getIcon() != null ){
                
                tempButton.setIcon(null);
            }
        }
        
        // Reset Inventory
        for (Component i : InventoryPanel.getComponents() ){
            
            JButton tempButton = (JButton)i;
            if ( tempButton.getIcon() != null ){
                
                tempButton.setIcon(null);
            }
        }
    }
    // -----------------------------------------------------
    private void clearButtonsAndICMs() {
        buttons = new ArrayList<JButton>(1);
        icms = new ArrayList<IntentComponentMap>(1); 
    }
    // -----------------------------------------------------
    @Override
    public ArrayList<IntentComponentMap> generateIntentComponentMapping() {
        /*  Because the only controls, buttons, directly represent items
            it made sense in this case to assign icms as the buttons were
            created. This method merely returns them.
        */
        return icms;
    }
    // -----------------------------------------------------
    public void performActionOnButton( JButton button ){
        
       
        if ( button.getIcon() != null ){
            
            System.out.println("Performing Action");
            if ( button.getBackground() == new JButton().getBackground() ){

                button.setBackground( SELECTED_BUTTON_COLOR ) ;
            }
            else{

                button.setBackground( new JButton().getBackground());
                
            }
            
            // Let the 
            refreshControllerNeeded = true;
            button.repaint();
        }
        
    }
    
    private void resetPreviousSelectedButtonToDefaultColor(JPanel panel){
            
            for ( Component i : panel.getComponents() ){
            
                if ( i.getBackground() == SELECTED_BUTTON_COLOR ){

                    i.setBackground( new JButton().getBackground());
                    i.repaint();
                }
            }
             panel.repaint();
     }
    
    
       
        
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        EquippedInventoryPanel = new javax.swing.JPanel();
        Equip_Inv_Item1 = new javax.swing.JButton();
        Equip_Inv_Item2 = new javax.swing.JButton();
        Equip_Inv_Item4 = new javax.swing.JButton();
        Equip_Inv_Item3 = new javax.swing.JButton();
        IventoryCommandPanel = new javax.swing.JPanel();
        dropButton = new javax.swing.JButton();
        equipButton = new javax.swing.JButton();
        unequipButton = new javax.swing.JButton();
        InventoryPanel = new javax.swing.JPanel();
        Inv_Item1 = new javax.swing.JButton();
        Inv_Item2 = new javax.swing.JButton();
        Inv_Item3 = new javax.swing.JButton();
        Inv_Item4 = new javax.swing.JButton();
        Inv_Item5 = new javax.swing.JButton();
        Inv_Item6 = new javax.swing.JButton();
        Inv_Item7 = new javax.swing.JButton();
        Inv_Item8 = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(32, 231, 84));
        setMaximumSize(new java.awt.Dimension(425, 425));
        setMinimumSize(new java.awt.Dimension(425, 425));
        setPreferredSize(new java.awt.Dimension(425, 425));
        setRequestFocusEnabled(false);

        EquippedInventoryPanel.setBackground(new java.awt.Color(67, 67, 168));
        EquippedInventoryPanel.setMaximumSize(new java.awt.Dimension(346, 401));
        EquippedInventoryPanel.setMinimumSize(new java.awt.Dimension(346, 401));
        EquippedInventoryPanel.setPreferredSize(new java.awt.Dimension(346, 401));

        Equip_Inv_Item1.setForeground(new java.awt.Color(209, 1, 1));
        Equip_Inv_Item1.setMaximumSize(new java.awt.Dimension(72, 55));
        Equip_Inv_Item1.setMinimumSize(new java.awt.Dimension(72, 55));
        Equip_Inv_Item1.setPreferredSize(new java.awt.Dimension(72, 55));
        Equip_Inv_Item1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Equip_Inv_Item1MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Equip_Inv_Item1MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Equip_Inv_Item1MouseEntered(evt);
            }
        });

        Equip_Inv_Item2.setMaximumSize(new java.awt.Dimension(72, 55));
        Equip_Inv_Item2.setMinimumSize(new java.awt.Dimension(72, 55));
        Equip_Inv_Item2.setPreferredSize(new java.awt.Dimension(72, 55));
        Equip_Inv_Item2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Equip_Inv_Item2MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Equip_Inv_Item2MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Equip_Inv_Item2MouseEntered(evt);
            }
        });

        Equip_Inv_Item4.setMaximumSize(new java.awt.Dimension(72, 55));
        Equip_Inv_Item4.setMinimumSize(new java.awt.Dimension(72, 55));
        Equip_Inv_Item4.setPreferredSize(new java.awt.Dimension(72, 55));
        Equip_Inv_Item4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Equip_Inv_Item4MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Equip_Inv_Item4MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Equip_Inv_Item4MouseEntered(evt);
            }
        });

        Equip_Inv_Item3.setMaximumSize(new java.awt.Dimension(72, 55));
        Equip_Inv_Item3.setMinimumSize(new java.awt.Dimension(72, 55));
        Equip_Inv_Item3.setPreferredSize(new java.awt.Dimension(72, 55));
        Equip_Inv_Item3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Equip_Inv_Item3MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Equip_Inv_Item3MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Equip_Inv_Item3MouseEntered(evt);
            }
        });

        javax.swing.GroupLayout EquippedInventoryPanelLayout = new javax.swing.GroupLayout(EquippedInventoryPanel);
        EquippedInventoryPanel.setLayout(EquippedInventoryPanelLayout);
        EquippedInventoryPanelLayout.setHorizontalGroup(
            EquippedInventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EquippedInventoryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Equip_Inv_Item1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Equip_Inv_Item2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Equip_Inv_Item3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Equip_Inv_Item4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        EquippedInventoryPanelLayout.setVerticalGroup(
            EquippedInventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EquippedInventoryPanelLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(EquippedInventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Equip_Inv_Item4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Equip_Inv_Item1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Equip_Inv_Item3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Equip_Inv_Item2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        IventoryCommandPanel.setBackground(new java.awt.Color(42, 10, 205));
        IventoryCommandPanel.setMaximumSize(new java.awt.Dimension(272, 100));

        dropButton.setText("Drop");
        dropButton.setMaximumSize(new java.awt.Dimension(88, 30));
        dropButton.setMinimumSize(new java.awt.Dimension(88, 30));
        dropButton.setPreferredSize(new java.awt.Dimension(88, 30));
        dropButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dropButtonMouseClicked(evt);
            }
        });

        equipButton.setText("Equip");
        equipButton.setMaximumSize(new java.awt.Dimension(88, 30));
        equipButton.setMinimumSize(new java.awt.Dimension(88, 30));
        equipButton.setPreferredSize(new java.awt.Dimension(88, 30));
        equipButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                equipButtonMouseClicked(evt);
            }
        });

        unequipButton.setText("Unequip");
        unequipButton.setMaximumSize(new java.awt.Dimension(88, 30));
        unequipButton.setMinimumSize(new java.awt.Dimension(88, 30));
        unequipButton.setPreferredSize(new java.awt.Dimension(88, 30));
        unequipButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                unequipButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout IventoryCommandPanelLayout = new javax.swing.GroupLayout(IventoryCommandPanel);
        IventoryCommandPanel.setLayout(IventoryCommandPanelLayout);
        IventoryCommandPanelLayout.setHorizontalGroup(
            IventoryCommandPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(IventoryCommandPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(IventoryCommandPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(unequipButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(IventoryCommandPanelLayout.createSequentialGroup()
                        .addGroup(IventoryCommandPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(equipButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dropButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        IventoryCommandPanelLayout.setVerticalGroup(
            IventoryCommandPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(IventoryCommandPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dropButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(equipButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(unequipButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        InventoryPanel.setBackground(new java.awt.Color(67, 67, 168));
        InventoryPanel.setMaximumSize(new java.awt.Dimension(346, 401));
        InventoryPanel.setMinimumSize(new java.awt.Dimension(346, 401));
        InventoryPanel.setPreferredSize(new java.awt.Dimension(346, 401));

        Inv_Item1.setForeground(new java.awt.Color(209, 1, 1));
        Inv_Item1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Inv_Item1.setMaximumSize(new java.awt.Dimension(72, 55));
        Inv_Item1.setMinimumSize(new java.awt.Dimension(72, 55));
        Inv_Item1.setPreferredSize(new java.awt.Dimension(72, 55));
        Inv_Item1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Inv_Item1MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Inv_Item1MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Inv_Item1MouseEntered(evt);
            }
        });

        Inv_Item2.setMaximumSize(new java.awt.Dimension(72, 55));
        Inv_Item2.setMinimumSize(new java.awt.Dimension(72, 55));
        Inv_Item2.setPreferredSize(new java.awt.Dimension(72, 55));
        Inv_Item2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Inv_Item2MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Inv_Item2MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Inv_Item2MouseEntered(evt);
            }
        });

        Inv_Item3.setMaximumSize(new java.awt.Dimension(72, 55));
        Inv_Item3.setMinimumSize(new java.awt.Dimension(72, 55));
        Inv_Item3.setPreferredSize(new java.awt.Dimension(72, 55));
        Inv_Item3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Inv_Item3MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Inv_Item3MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Inv_Item3MouseEntered(evt);
            }
        });

        Inv_Item4.setMaximumSize(new java.awt.Dimension(72, 55));
        Inv_Item4.setMinimumSize(new java.awt.Dimension(72, 55));
        Inv_Item4.setPreferredSize(new java.awt.Dimension(72, 55));
        Inv_Item4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Inv_Item4MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Inv_Item4MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Inv_Item4MouseEntered(evt);
            }
        });

        Inv_Item5.setForeground(new java.awt.Color(209, 1, 1));
        Inv_Item5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Inv_Item5.setMaximumSize(new java.awt.Dimension(72, 55));
        Inv_Item5.setMinimumSize(new java.awt.Dimension(72, 55));
        Inv_Item5.setPreferredSize(new java.awt.Dimension(72, 55));
        Inv_Item5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Inv_Item5MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Inv_Item5MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Inv_Item5MouseEntered(evt);
            }
        });

        Inv_Item6.setMaximumSize(new java.awt.Dimension(72, 55));
        Inv_Item6.setMinimumSize(new java.awt.Dimension(72, 55));
        Inv_Item6.setPreferredSize(new java.awt.Dimension(72, 55));
        Inv_Item6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Inv_Item6MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Inv_Item6MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Inv_Item6MouseEntered(evt);
            }
        });

        Inv_Item7.setMaximumSize(new java.awt.Dimension(72, 55));
        Inv_Item7.setMinimumSize(new java.awt.Dimension(72, 55));
        Inv_Item7.setPreferredSize(new java.awt.Dimension(72, 55));
        Inv_Item7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Inv_Item7MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Inv_Item7MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Inv_Item7MouseEntered(evt);
            }
        });

        Inv_Item8.setMaximumSize(new java.awt.Dimension(72, 55));
        Inv_Item8.setMinimumSize(new java.awt.Dimension(72, 55));
        Inv_Item8.setPreferredSize(new java.awt.Dimension(72, 55));
        Inv_Item8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Inv_Item8MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Inv_Item8MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Inv_Item8MouseEntered(evt);
            }
        });

        javax.swing.GroupLayout InventoryPanelLayout = new javax.swing.GroupLayout(InventoryPanel);
        InventoryPanel.setLayout(InventoryPanelLayout);
        InventoryPanelLayout.setHorizontalGroup(
            InventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InventoryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(InventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(InventoryPanelLayout.createSequentialGroup()
                        .addComponent(Inv_Item1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(Inv_Item2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(Inv_Item3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Inv_Item4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(InventoryPanelLayout.createSequentialGroup()
                        .addComponent(Inv_Item5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(Inv_Item6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(Inv_Item7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Inv_Item8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        InventoryPanelLayout.setVerticalGroup(
            InventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InventoryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(InventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Inv_Item2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Inv_Item3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Inv_Item4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Inv_Item1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(InventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Inv_Item6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Inv_Item7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Inv_Item8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Inv_Item5, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(267, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(InventoryPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(IventoryCommandPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(EquippedInventoryPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(InventoryPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(EquippedInventoryPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(IventoryCommandPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void Equip_Inv_Item1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Equip_Inv_Item1MouseClicked
        // TODO add your handling code here:
        performActionOnButton((JButton)evt.getComponent() );
    }//GEN-LAST:event_Equip_Inv_Item1MouseClicked

    private void Equip_Inv_Item1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Equip_Inv_Item1MouseExited
        // TODO add your handling code here:
        decrementButtonDimension( (JButton)evt.getComponent(), false );
    }//GEN-LAST:event_Equip_Inv_Item1MouseExited

    private void Equip_Inv_Item1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Equip_Inv_Item1MouseEntered
        // TODO add your handling code here:
        incrementButtonDimension( (JButton)evt.getComponent(), false );
    }//GEN-LAST:event_Equip_Inv_Item1MouseEntered

    private void Inv_Item1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inv_Item1MouseClicked
        // TODO add your handling code here:
        performActionOnButton((JButton)evt.getComponent() );
    }//GEN-LAST:event_Inv_Item1MouseClicked

    private void Inv_Item1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inv_Item1MouseExited
        // TODO add your handling code here:
         decrementButtonDimension( (JButton)evt.getComponent(), false );
    }//GEN-LAST:event_Inv_Item1MouseExited

    private void Inv_Item1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inv_Item1MouseEntered
        // TODO add your handling code here:
        incrementButtonDimension( (JButton)evt.getComponent(), false );
    }//GEN-LAST:event_Inv_Item1MouseEntered

    private void dropButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dropButtonMouseClicked
        // TODO add your handling code here:
        
        resetPreviousSelectedButtonToDefaultColor( InventoryPanel );
    }//GEN-LAST:event_dropButtonMouseClicked

    private void Inv_Item2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inv_Item2MouseClicked
        // TODO add your handling code here:
        performActionOnButton((JButton)evt.getComponent() );
    }//GEN-LAST:event_Inv_Item2MouseClicked

    private void Inv_Item2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inv_Item2MouseExited
        // TODO add your handling code here:
        decrementButtonDimension( (JButton)evt.getComponent(), false );
    }//GEN-LAST:event_Inv_Item2MouseExited

    private void Inv_Item2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inv_Item2MouseEntered
        // TODO add your handling code here:
         incrementButtonDimension( (JButton)evt.getComponent(), false );
    }//GEN-LAST:event_Inv_Item2MouseEntered

    private void Inv_Item3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inv_Item3MouseClicked
        // TODO add your handling code here:
        performActionOnButton((JButton)evt.getComponent() );
    }//GEN-LAST:event_Inv_Item3MouseClicked

    private void Inv_Item3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inv_Item3MouseExited
        // TODO add your handling code here:
        decrementButtonDimension( (JButton)evt.getComponent(), false );
    }//GEN-LAST:event_Inv_Item3MouseExited

    private void Inv_Item3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inv_Item3MouseEntered
        // TODO add your handling code here:
         incrementButtonDimension( (JButton)evt.getComponent(), false );
    }//GEN-LAST:event_Inv_Item3MouseEntered

    private void Inv_Item4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inv_Item4MouseClicked
        // TODO add your handling code here:
        performActionOnButton((JButton)evt.getComponent() );
    }//GEN-LAST:event_Inv_Item4MouseClicked

    private void Inv_Item4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inv_Item4MouseExited
        // TODO add your handling code here:
        decrementButtonDimension( (JButton)evt.getComponent(), true );
    }//GEN-LAST:event_Inv_Item4MouseExited

    private void Inv_Item4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inv_Item4MouseEntered
        // TODO add your handling code here:
         incrementButtonDimension( (JButton)evt.getComponent(), true );
    }//GEN-LAST:event_Inv_Item4MouseEntered

    private void Equip_Inv_Item2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Equip_Inv_Item2MouseClicked
        // TODO add your handling code here:
        performActionOnButton((JButton)evt.getComponent() );
    }//GEN-LAST:event_Equip_Inv_Item2MouseClicked

    private void Equip_Inv_Item2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Equip_Inv_Item2MouseEntered
        // TODO add your handling code here:
        incrementButtonDimension( (JButton)evt.getComponent(), false );
    }//GEN-LAST:event_Equip_Inv_Item2MouseEntered

    private void Equip_Inv_Item2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Equip_Inv_Item2MouseExited
        // TODO add your handling code here:
        decrementButtonDimension( (JButton)evt.getComponent(), false );
    }//GEN-LAST:event_Equip_Inv_Item2MouseExited

    private void Equip_Inv_Item3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Equip_Inv_Item3MouseClicked
        // TODO add your handling code here:
        performActionOnButton((JButton)evt.getComponent() );
    }//GEN-LAST:event_Equip_Inv_Item3MouseClicked

    private void Equip_Inv_Item3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Equip_Inv_Item3MouseEntered
        // TODO add your handling code here:
        incrementButtonDimension( (JButton)evt.getComponent(), false );
    }//GEN-LAST:event_Equip_Inv_Item3MouseEntered

    private void Equip_Inv_Item3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Equip_Inv_Item3MouseExited
        // TODO add your handling code here:
        decrementButtonDimension( (JButton)evt.getComponent(), false );
    }//GEN-LAST:event_Equip_Inv_Item3MouseExited

    private void Equip_Inv_Item4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Equip_Inv_Item4MouseClicked
        // TODO add your handling code here:
        performActionOnButton((JButton)evt.getComponent() );
    }//GEN-LAST:event_Equip_Inv_Item4MouseClicked

    private void Equip_Inv_Item4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Equip_Inv_Item4MouseEntered
        // TODO add your handling code here:
        incrementButtonDimension( (JButton)evt.getComponent(), true );
    }//GEN-LAST:event_Equip_Inv_Item4MouseEntered

    private void Equip_Inv_Item4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Equip_Inv_Item4MouseExited
        // TODO add your handling code here:
        decrementButtonDimension( (JButton)evt.getComponent(), true );
    }//GEN-LAST:event_Equip_Inv_Item4MouseExited

    private void unequipButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_unequipButtonMouseClicked
        // TODO add your handling code here:
        resetPreviousSelectedButtonToDefaultColor( EquippedInventoryPanel );
    }//GEN-LAST:event_unequipButtonMouseClicked

    private void Inv_Item5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inv_Item5MouseClicked
        // TODO add your handling code here:
         performActionOnButton((JButton)evt.getComponent() );
    }//GEN-LAST:event_Inv_Item5MouseClicked

    private void Inv_Item5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inv_Item5MouseExited
        // TODO add your handling code here:
        decrementButtonDimension( (JButton)evt.getComponent(), false );
    }//GEN-LAST:event_Inv_Item5MouseExited

    private void Inv_Item5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inv_Item5MouseEntered
        // TODO add your handling code here:
        incrementButtonDimension( (JButton)evt.getComponent(), false );
    }//GEN-LAST:event_Inv_Item5MouseEntered

    private void Inv_Item6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inv_Item6MouseClicked
        // TODO add your handling code here:
         performActionOnButton((JButton)evt.getComponent() );
    }//GEN-LAST:event_Inv_Item6MouseClicked

    private void Inv_Item6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inv_Item6MouseExited
        // TODO add your handling code here:
        decrementButtonDimension( (JButton)evt.getComponent(), false );
    }//GEN-LAST:event_Inv_Item6MouseExited

    private void Inv_Item6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inv_Item6MouseEntered
        // TODO add your handling code here:
        incrementButtonDimension( (JButton)evt.getComponent(), false );
    }//GEN-LAST:event_Inv_Item6MouseEntered

    private void Inv_Item7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inv_Item7MouseClicked
        // TODO add your handling code here:
         performActionOnButton((JButton)evt.getComponent() );
    }//GEN-LAST:event_Inv_Item7MouseClicked

    private void Inv_Item7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inv_Item7MouseExited
        // TODO add your handling code here:
        decrementButtonDimension( (JButton)evt.getComponent(), false );
    }//GEN-LAST:event_Inv_Item7MouseExited

    private void Inv_Item7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inv_Item7MouseEntered
        // TODO add your handling code here:
        incrementButtonDimension( (JButton)evt.getComponent(), false );
    }//GEN-LAST:event_Inv_Item7MouseEntered

    private void Inv_Item8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inv_Item8MouseClicked
        // TODO add your handling code here:
         performActionOnButton((JButton)evt.getComponent() );
    }//GEN-LAST:event_Inv_Item8MouseClicked

    private void Inv_Item8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inv_Item8MouseExited
        // TODO add your handling code here:
        decrementButtonDimension( (JButton)evt.getComponent(), true );
    }//GEN-LAST:event_Inv_Item8MouseExited

    private void Inv_Item8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inv_Item8MouseEntered
        // TODO add your handling code here:
        incrementButtonDimension( (JButton)evt.getComponent(), true );
    }//GEN-LAST:event_Inv_Item8MouseEntered

    private void equipButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_equipButtonMouseClicked
        // TODO add your handling code here:
        resetPreviousSelectedButtonToDefaultColor( InventoryPanel );
        resetPreviousSelectedButtonToDefaultColor( EquippedInventoryPanel );
    }//GEN-LAST:event_equipButtonMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Equip_Inv_Item1;
    private javax.swing.JButton Equip_Inv_Item2;
    private javax.swing.JButton Equip_Inv_Item3;
    private javax.swing.JButton Equip_Inv_Item4;
    private javax.swing.JPanel EquippedInventoryPanel;
    private javax.swing.JButton Inv_Item1;
    private javax.swing.JButton Inv_Item2;
    private javax.swing.JButton Inv_Item3;
    private javax.swing.JButton Inv_Item4;
    private javax.swing.JButton Inv_Item5;
    private javax.swing.JButton Inv_Item6;
    private javax.swing.JButton Inv_Item7;
    private javax.swing.JButton Inv_Item8;
    private javax.swing.JPanel InventoryPanel;
    private javax.swing.JPanel IventoryCommandPanel;
    private javax.swing.JButton dropButton;
    private javax.swing.JButton equipButton;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton unequipButton;
    // End of variables declaration//GEN-END:variables
}
