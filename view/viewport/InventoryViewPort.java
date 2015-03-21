/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.viewport;


import controller.Intent;
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
import controller.controllerMap.IntentComponentMap;
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
    Color BUTTONS_COLOR;
    
    /**
     * Creates new form InventoryViewPort
     */
    
    public InventoryViewPort() {
        initComponents();
        setButtonsColorAndBorder();
        SELECTED_BUTTON_COLOR = InventoryPanel.getBackground();
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
                    icms.add(new IntentComponentMap(tempButton, item, Intent.TOGGLE_EQUIPPED));
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
                    icms.add(new IntentComponentMap(tempButton, item, Intent.INVENTORY_ITEM));
                    buttons.add(tempButton);
                    return;
                }
            }
        }
        
        
    }
    // -----------------------------------------------------
    private void addCommandButtonsToICMs(){
        
        icms.add(new IntentComponentMap(equipButton, Intent.EQUIP_ITEM));
        icms.add(new IntentComponentMap(unequipButton, Intent.UNEQUIP_ITEM));
        icms.add(new IntentComponentMap(dropButton, Intent.DROP_ITEM)); 
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
            
            if ( i instanceof JButton ){
                
                JButton tempButton = (JButton)i;
                if (tempButton.getIcon() != null ){
                
                    tempButton.setIcon(null);
                }
            }
        }
        
        // Reset Inventory
        for (Component i : InventoryPanel.getComponents() ){
            
            
            if ( i instanceof JButton ){
                
                JButton tempButton = (JButton)i;
                if (tempButton.getIcon() != null ){
                
                    tempButton.setIcon(null);
                }
            }
        }
    }
    // -----------------------------------------------------
    private void clearButtonsAndICMs() {
        buttons = new ArrayList<JButton>(1);
        icms = new ArrayList<IntentComponentMap>(1); 
    }
    // -----------------------------------------------------
    /*
        Base on Inv_Item1 the variable BUTTONS_COLOR will have a value.
        In this manner we only have to change the color of one button and 
        through this function all the other colors will change as well.
    
    Note: the same apply for Border and Forground()
    */
    private void setButtonsColorAndBorder(){
        
        BUTTONS_COLOR = Inv_Item1.getBackground();
        ArrayList<JButton> buttons = new ArrayList<JButton>();
        for (Component i : InventoryPanel.getComponents() ){
            
             if ( i instanceof JButton){
            
                buttons.add( (JButton)i);
             }
        }
        
        for (Component i : EquippedInventoryPanel.getComponents() ){
            
             if ( i instanceof JButton){
            
                buttons.add( (JButton)i);
             }
        }
        
        for (JButton button : buttons ){
            
                button.setBackground( BUTTONS_COLOR );
                button.setForeground( Inv_Item1.getForeground() );
                button.setBorder( Inv_Item1.getBorder() );
                button.repaint();
        }
        
        this.repaint();
    }
    @Override
    public ArrayList<IntentComponentMap> generateIntentComponentMapping() {
        /*  Because the only controls, buttons, directly represent items
            it made sense in this case to assign icms as the buttons were
            created. This method merely returns them.
        */
        System.out.print("Intent componenets: " + icms.size() );
        return icms;
    }
    // -----------------------------------------------------
    public void performActionOnButton( JButton button ){
        
       
            // If decal ok and selected
            if ( button.getIcon() != null && button.getBackground().equals(SELECTED_BUTTON_COLOR)){

                button.setBackground( BUTTONS_COLOR) ;
            }
            else if (button.getIcon() != null){
                
                button.setBackground( SELECTED_BUTTON_COLOR) ;
            }
            else{

                button.setBackground(BUTTONS_COLOR);
                
            }
            
            // Let the 
            refreshControllerNeeded = true;
            button.repaint();
        //}
        
    }
    
    private void resetPreviousSelectedButtonToDefaultColor(JPanel panel){
            
            for ( Component i : panel.getComponents() ){
            
                if ( i.getBackground() == SELECTED_BUTTON_COLOR ){

                    i.setBackground( BUTTONS_COLOR);
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
        Equip_Inv_Item5 = new javax.swing.JButton();
        Equip_Inv_Item7 = new javax.swing.JButton();
        Equip_Inv_Item9 = new javax.swing.JButton();
        Equip_Inv_Item11 = new javax.swing.JButton();
        Equip_Inv_Item6 = new javax.swing.JButton();
        Equip_Inv_Item8 = new javax.swing.JButton();
        Equip_Inv_Item10 = new javax.swing.JButton();
        Equip_Inv_Item14 = new javax.swing.JButton();
        Equip_Inv_Item12 = new javax.swing.JButton();
        Equip_Inv_Item13 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
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
        Inv_Item9 = new javax.swing.JButton();
        Inv_Item10 = new javax.swing.JButton();
        Inv_Item11 = new javax.swing.JButton();
        Inv_Item12 = new javax.swing.JButton();
        Inv_Item13 = new javax.swing.JButton();
        Inv_Item14 = new javax.swing.JButton();
        Inv_Item15 = new javax.swing.JButton();
        Inv_Item16 = new javax.swing.JButton();
        Inv_Item17 = new javax.swing.JButton();
        Inv_Item18 = new javax.swing.JButton();
        Inv_Item19 = new javax.swing.JButton();
        Inv_Item20 = new javax.swing.JButton();
        inventoryLabel = new javax.swing.JLabel();
        equippedInventoryLabel = new javax.swing.JLabel();

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

        setBackground(new java.awt.Color(60, 52, 41));
        setMaximumSize(new java.awt.Dimension(850, 425));
        setMinimumSize(new java.awt.Dimension(850, 425));
        setPreferredSize(new java.awt.Dimension(850, 425));
        setRequestFocusEnabled(false);

        EquippedInventoryPanel.setBackground(new java.awt.Color(101, 79, 57));
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

        Equip_Inv_Item5.setForeground(new java.awt.Color(209, 1, 1));
        Equip_Inv_Item5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Equip_Inv_Item5.setMaximumSize(new java.awt.Dimension(72, 55));
        Equip_Inv_Item5.setMinimumSize(new java.awt.Dimension(72, 55));
        Equip_Inv_Item5.setPreferredSize(new java.awt.Dimension(72, 55));
        Equip_Inv_Item5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Equip_Inv_Item5MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Equip_Inv_Item5MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Equip_Inv_Item5MouseEntered(evt);
            }
        });

        Equip_Inv_Item7.setForeground(new java.awt.Color(209, 1, 1));
        Equip_Inv_Item7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Equip_Inv_Item7.setMaximumSize(new java.awt.Dimension(72, 55));
        Equip_Inv_Item7.setMinimumSize(new java.awt.Dimension(72, 55));
        Equip_Inv_Item7.setPreferredSize(new java.awt.Dimension(72, 55));
        Equip_Inv_Item7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Equip_Inv_Item7MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Equip_Inv_Item7MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Equip_Inv_Item7MouseEntered(evt);
            }
        });

        Equip_Inv_Item9.setForeground(new java.awt.Color(209, 1, 1));
        Equip_Inv_Item9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Equip_Inv_Item9.setMaximumSize(new java.awt.Dimension(72, 55));
        Equip_Inv_Item9.setMinimumSize(new java.awt.Dimension(72, 55));
        Equip_Inv_Item9.setPreferredSize(new java.awt.Dimension(72, 55));
        Equip_Inv_Item9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Equip_Inv_Item9MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Equip_Inv_Item9MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Equip_Inv_Item9MouseEntered(evt);
            }
        });

        Equip_Inv_Item11.setForeground(new java.awt.Color(209, 1, 1));
        Equip_Inv_Item11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Equip_Inv_Item11.setMaximumSize(new java.awt.Dimension(72, 55));
        Equip_Inv_Item11.setMinimumSize(new java.awt.Dimension(72, 55));
        Equip_Inv_Item11.setPreferredSize(new java.awt.Dimension(72, 55));
        Equip_Inv_Item11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Equip_Inv_Item11MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Equip_Inv_Item11MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Equip_Inv_Item11MouseEntered(evt);
            }
        });

        Equip_Inv_Item6.setForeground(new java.awt.Color(209, 1, 1));
        Equip_Inv_Item6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Equip_Inv_Item6.setMaximumSize(new java.awt.Dimension(72, 55));
        Equip_Inv_Item6.setMinimumSize(new java.awt.Dimension(72, 55));
        Equip_Inv_Item6.setPreferredSize(new java.awt.Dimension(72, 55));
        Equip_Inv_Item6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Equip_Inv_Item6MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Equip_Inv_Item6MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Equip_Inv_Item6MouseEntered(evt);
            }
        });

        Equip_Inv_Item8.setForeground(new java.awt.Color(209, 1, 1));
        Equip_Inv_Item8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Equip_Inv_Item8.setMaximumSize(new java.awt.Dimension(72, 55));
        Equip_Inv_Item8.setMinimumSize(new java.awt.Dimension(72, 55));
        Equip_Inv_Item8.setPreferredSize(new java.awt.Dimension(72, 55));
        Equip_Inv_Item8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Equip_Inv_Item8MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Equip_Inv_Item8MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Equip_Inv_Item8MouseEntered(evt);
            }
        });

        Equip_Inv_Item10.setForeground(new java.awt.Color(209, 1, 1));
        Equip_Inv_Item10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Equip_Inv_Item10.setMaximumSize(new java.awt.Dimension(72, 55));
        Equip_Inv_Item10.setMinimumSize(new java.awt.Dimension(72, 55));
        Equip_Inv_Item10.setPreferredSize(new java.awt.Dimension(72, 55));
        Equip_Inv_Item10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Equip_Inv_Item10MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Equip_Inv_Item10MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Equip_Inv_Item10MouseEntered(evt);
            }
        });

        Equip_Inv_Item14.setForeground(new java.awt.Color(209, 1, 1));
        Equip_Inv_Item14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Equip_Inv_Item14.setMaximumSize(new java.awt.Dimension(72, 55));
        Equip_Inv_Item14.setMinimumSize(new java.awt.Dimension(72, 55));
        Equip_Inv_Item14.setPreferredSize(new java.awt.Dimension(72, 55));
        Equip_Inv_Item14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Equip_Inv_Item14MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Equip_Inv_Item14MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Equip_Inv_Item14MouseEntered(evt);
            }
        });

        Equip_Inv_Item12.setMaximumSize(new java.awt.Dimension(72, 55));
        Equip_Inv_Item12.setMinimumSize(new java.awt.Dimension(72, 55));
        Equip_Inv_Item12.setPreferredSize(new java.awt.Dimension(72, 55));
        Equip_Inv_Item12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Equip_Inv_Item12MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Equip_Inv_Item12MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Equip_Inv_Item12MouseEntered(evt);
            }
        });

        Equip_Inv_Item13.setMaximumSize(new java.awt.Dimension(72, 55));
        Equip_Inv_Item13.setMinimumSize(new java.awt.Dimension(72, 55));
        Equip_Inv_Item13.setPreferredSize(new java.awt.Dimension(72, 55));
        Equip_Inv_Item13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Equip_Inv_Item13MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Equip_Inv_Item13MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Equip_Inv_Item13MouseEntered(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(186, 163, 132));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 186, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout EquippedInventoryPanelLayout = new javax.swing.GroupLayout(EquippedInventoryPanel);
        EquippedInventoryPanel.setLayout(EquippedInventoryPanelLayout);
        EquippedInventoryPanelLayout.setHorizontalGroup(
            EquippedInventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EquippedInventoryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(EquippedInventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EquippedInventoryPanelLayout.createSequentialGroup()
                        .addComponent(Equip_Inv_Item1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Equip_Inv_Item2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Equip_Inv_Item3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Equip_Inv_Item4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(EquippedInventoryPanelLayout.createSequentialGroup()
                        .addGroup(EquippedInventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(EquippedInventoryPanelLayout.createSequentialGroup()
                                .addComponent(Equip_Inv_Item11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Equip_Inv_Item12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Equip_Inv_Item13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(EquippedInventoryPanelLayout.createSequentialGroup()
                                .addGroup(EquippedInventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Equip_Inv_Item9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Equip_Inv_Item5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Equip_Inv_Item7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(EquippedInventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Equip_Inv_Item6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Equip_Inv_Item8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Equip_Inv_Item10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Equip_Inv_Item14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(EquippedInventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EquippedInventoryPanelLayout.createSequentialGroup()
                        .addComponent(Equip_Inv_Item5, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Equip_Inv_Item7, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Equip_Inv_Item9, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Equip_Inv_Item11, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(EquippedInventoryPanelLayout.createSequentialGroup()
                        .addComponent(Equip_Inv_Item6, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Equip_Inv_Item8, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Equip_Inv_Item10, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(EquippedInventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Equip_Inv_Item14, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Equip_Inv_Item13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Equip_Inv_Item12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        IventoryCommandPanel.setBackground(new java.awt.Color(101, 79, 57));
        IventoryCommandPanel.setMaximumSize(new java.awt.Dimension(272, 100));

        dropButton.setBackground(new java.awt.Color(101, 79, 57));
        dropButton.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        dropButton.setForeground(new java.awt.Color(186, 163, 132));
        dropButton.setText("Drop");
        dropButton.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 4, 4, new java.awt.Color(186, 163, 132)));
        dropButton.setMaximumSize(new java.awt.Dimension(88, 44));
        dropButton.setMinimumSize(new java.awt.Dimension(88, 44));
        dropButton.setPreferredSize(new java.awt.Dimension(88, 44));
        dropButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dropButtonMouseClicked(evt);
            }
        });

        equipButton.setBackground(new java.awt.Color(101, 79, 57));
        equipButton.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        equipButton.setForeground(new java.awt.Color(186, 163, 132));
        equipButton.setText("Equip");
        equipButton.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 4, 4, new java.awt.Color(186, 163, 132)));
        equipButton.setMaximumSize(new java.awt.Dimension(88, 30));
        equipButton.setMinimumSize(new java.awt.Dimension(88, 30));
        equipButton.setPreferredSize(new java.awt.Dimension(88, 44));
        equipButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                equipButtonMouseClicked(evt);
            }
        });

        unequipButton.setBackground(new java.awt.Color(101, 79, 57));
        unequipButton.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        unequipButton.setForeground(new java.awt.Color(186, 163, 132));
        unequipButton.setText("Unequip");
        unequipButton.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 4, 4, new java.awt.Color(186, 163, 132)));
        unequipButton.setMaximumSize(new java.awt.Dimension(88, 44));
        unequipButton.setMinimumSize(new java.awt.Dimension(88, 44));
        unequipButton.setPreferredSize(new java.awt.Dimension(88, 44));
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
                    .addGroup(IventoryCommandPanelLayout.createSequentialGroup()
                        .addComponent(dropButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(IventoryCommandPanelLayout.createSequentialGroup()
                        .addGap(0, 1, Short.MAX_VALUE)
                        .addGroup(IventoryCommandPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(unequipButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(equipButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );
        IventoryCommandPanelLayout.setVerticalGroup(
            IventoryCommandPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(IventoryCommandPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(dropButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(equipButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(unequipButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        InventoryPanel.setBackground(new java.awt.Color(101, 79, 57));
        InventoryPanel.setMaximumSize(new java.awt.Dimension(346, 401));
        InventoryPanel.setMinimumSize(new java.awt.Dimension(346, 401));
        InventoryPanel.setPreferredSize(new java.awt.Dimension(346, 401));

        Inv_Item1.setBackground(new java.awt.Color(186, 163, 132));
        Inv_Item1.setForeground(new java.awt.Color(209, 1, 1));
        Inv_Item1.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 1, 1, new java.awt.Color(60, 52, 41)));
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

        Inv_Item9.setForeground(new java.awt.Color(209, 1, 1));
        Inv_Item9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Inv_Item9.setMaximumSize(new java.awt.Dimension(72, 55));
        Inv_Item9.setMinimumSize(new java.awt.Dimension(72, 55));
        Inv_Item9.setPreferredSize(new java.awt.Dimension(72, 55));
        Inv_Item9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Inv_Item9MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Inv_Item9MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Inv_Item9MouseEntered(evt);
            }
        });

        Inv_Item10.setMaximumSize(new java.awt.Dimension(72, 55));
        Inv_Item10.setMinimumSize(new java.awt.Dimension(72, 55));
        Inv_Item10.setPreferredSize(new java.awt.Dimension(72, 55));
        Inv_Item10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Inv_Item10MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Inv_Item10MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Inv_Item10MouseEntered(evt);
            }
        });

        Inv_Item11.setMaximumSize(new java.awt.Dimension(72, 55));
        Inv_Item11.setMinimumSize(new java.awt.Dimension(72, 55));
        Inv_Item11.setPreferredSize(new java.awt.Dimension(72, 55));
        Inv_Item11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Inv_Item11MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Inv_Item11MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Inv_Item11MouseEntered(evt);
            }
        });

        Inv_Item12.setMaximumSize(new java.awt.Dimension(72, 55));
        Inv_Item12.setMinimumSize(new java.awt.Dimension(72, 55));
        Inv_Item12.setPreferredSize(new java.awt.Dimension(72, 55));
        Inv_Item12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Inv_Item12MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Inv_Item12MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Inv_Item12MouseEntered(evt);
            }
        });

        Inv_Item13.setForeground(new java.awt.Color(209, 1, 1));
        Inv_Item13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Inv_Item13.setMaximumSize(new java.awt.Dimension(72, 55));
        Inv_Item13.setMinimumSize(new java.awt.Dimension(72, 55));
        Inv_Item13.setPreferredSize(new java.awt.Dimension(72, 55));
        Inv_Item13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Inv_Item13MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Inv_Item13MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Inv_Item13MouseEntered(evt);
            }
        });

        Inv_Item14.setMaximumSize(new java.awt.Dimension(72, 55));
        Inv_Item14.setMinimumSize(new java.awt.Dimension(72, 55));
        Inv_Item14.setPreferredSize(new java.awt.Dimension(72, 55));
        Inv_Item14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Inv_Item14MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Inv_Item14MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Inv_Item14MouseEntered(evt);
            }
        });

        Inv_Item15.setMaximumSize(new java.awt.Dimension(72, 55));
        Inv_Item15.setMinimumSize(new java.awt.Dimension(72, 55));
        Inv_Item15.setPreferredSize(new java.awt.Dimension(72, 55));
        Inv_Item15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Inv_Item15MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Inv_Item15MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Inv_Item15MouseEntered(evt);
            }
        });

        Inv_Item16.setMaximumSize(new java.awt.Dimension(72, 55));
        Inv_Item16.setMinimumSize(new java.awt.Dimension(72, 55));
        Inv_Item16.setPreferredSize(new java.awt.Dimension(72, 55));
        Inv_Item16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Inv_Item16MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Inv_Item16MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Inv_Item16MouseEntered(evt);
            }
        });

        Inv_Item17.setForeground(new java.awt.Color(209, 1, 1));
        Inv_Item17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Inv_Item17.setMaximumSize(new java.awt.Dimension(72, 55));
        Inv_Item17.setMinimumSize(new java.awt.Dimension(72, 55));
        Inv_Item17.setPreferredSize(new java.awt.Dimension(72, 55));
        Inv_Item17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Inv_Item17MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Inv_Item17MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Inv_Item17MouseEntered(evt);
            }
        });

        Inv_Item18.setMaximumSize(new java.awt.Dimension(72, 55));
        Inv_Item18.setMinimumSize(new java.awt.Dimension(72, 55));
        Inv_Item18.setPreferredSize(new java.awt.Dimension(72, 55));
        Inv_Item18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Inv_Item18MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Inv_Item18MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Inv_Item18MouseEntered(evt);
            }
        });

        Inv_Item19.setMaximumSize(new java.awt.Dimension(72, 55));
        Inv_Item19.setMinimumSize(new java.awt.Dimension(72, 55));
        Inv_Item19.setPreferredSize(new java.awt.Dimension(72, 55));
        Inv_Item19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Inv_Item19MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Inv_Item19MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Inv_Item19MouseEntered(evt);
            }
        });

        Inv_Item20.setMaximumSize(new java.awt.Dimension(72, 55));
        Inv_Item20.setMinimumSize(new java.awt.Dimension(72, 55));
        Inv_Item20.setPreferredSize(new java.awt.Dimension(72, 55));
        Inv_Item20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Inv_Item20MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Inv_Item20MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Inv_Item20MouseEntered(evt);
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
                        .addComponent(Inv_Item8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(InventoryPanelLayout.createSequentialGroup()
                        .addComponent(Inv_Item9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(Inv_Item10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(Inv_Item11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Inv_Item12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(InventoryPanelLayout.createSequentialGroup()
                        .addComponent(Inv_Item13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(Inv_Item14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(Inv_Item15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Inv_Item16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(InventoryPanelLayout.createSequentialGroup()
                        .addComponent(Inv_Item17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(Inv_Item18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(Inv_Item19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Inv_Item20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(InventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Inv_Item10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Inv_Item11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Inv_Item12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Inv_Item9, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(InventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Inv_Item14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Inv_Item15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Inv_Item16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Inv_Item13, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(InventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Inv_Item18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Inv_Item19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Inv_Item20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Inv_Item17, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        inventoryLabel.setBackground(new java.awt.Color(213, 151, 90));
        inventoryLabel.setFont(new java.awt.Font("Ubuntu", 3, 36)); // NOI18N
        inventoryLabel.setForeground(new java.awt.Color(186, 163, 132));
        inventoryLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        inventoryLabel.setText("Inventory");

        equippedInventoryLabel.setBackground(new java.awt.Color(213, 151, 90));
        equippedInventoryLabel.setFont(new java.awt.Font("Ubuntu", 3, 36)); // NOI18N
        equippedInventoryLabel.setForeground(new java.awt.Color(186, 163, 132));
        equippedInventoryLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        equippedInventoryLabel.setText("Equipped Inventory");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(inventoryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(InventoryPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(IventoryCommandPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(equippedInventoryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EquippedInventoryPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inventoryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(equippedInventoryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(InventoryPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EquippedInventoryPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(IventoryCommandPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
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

    private void Inv_Item9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inv_Item9MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Inv_Item9MouseClicked

    private void Inv_Item9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inv_Item9MouseExited
        // TODO add your handling code here:
        decrementButtonDimension( (JButton)evt.getComponent(), false );
    }//GEN-LAST:event_Inv_Item9MouseExited

    private void Inv_Item9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inv_Item9MouseEntered
        // TODO add your handling code here:
        incrementButtonDimension( (JButton)evt.getComponent(), false );
    }//GEN-LAST:event_Inv_Item9MouseEntered

    private void Inv_Item10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inv_Item10MouseClicked
        // TODO add your handling code here:
        performActionOnButton((JButton)evt.getComponent() );
    }//GEN-LAST:event_Inv_Item10MouseClicked

    private void Inv_Item10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inv_Item10MouseExited
        // TODO add your handling code here:
        decrementButtonDimension( (JButton)evt.getComponent(), false );
        
    }//GEN-LAST:event_Inv_Item10MouseExited

    private void Inv_Item10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inv_Item10MouseEntered
        // TODO add your handling code here:
        incrementButtonDimension( (JButton)evt.getComponent(), false );
        
    }//GEN-LAST:event_Inv_Item10MouseEntered

    private void Inv_Item11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inv_Item11MouseClicked
        // TODO add your handling code here:
        performActionOnButton((JButton)evt.getComponent() );
        
    }//GEN-LAST:event_Inv_Item11MouseClicked

    private void Inv_Item11MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inv_Item11MouseExited
        // TODO add your handling code here:
        decrementButtonDimension( (JButton)evt.getComponent(), false );
    }//GEN-LAST:event_Inv_Item11MouseExited

    private void Inv_Item11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inv_Item11MouseEntered
        // TODO add your handling code here:
        incrementButtonDimension( (JButton)evt.getComponent(), false );
    }//GEN-LAST:event_Inv_Item11MouseEntered

    private void Inv_Item12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inv_Item12MouseClicked
        // TODO add your handling code here:
        performActionOnButton((JButton)evt.getComponent() );
        
    }//GEN-LAST:event_Inv_Item12MouseClicked

    private void Inv_Item12MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inv_Item12MouseExited
        // TODO add your handling code here:
        decrementButtonDimension( (JButton)evt.getComponent(), true );
    }//GEN-LAST:event_Inv_Item12MouseExited

    private void Inv_Item12MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inv_Item12MouseEntered
        // TODO add your handling code here:
        incrementButtonDimension( (JButton)evt.getComponent(), true );
    }//GEN-LAST:event_Inv_Item12MouseEntered

    private void Inv_Item13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inv_Item13MouseClicked
        // TODO add your handling code here:
        performActionOnButton((JButton)evt.getComponent() );
    }//GEN-LAST:event_Inv_Item13MouseClicked

    private void Inv_Item13MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inv_Item13MouseExited
        // TODO add your handling code here:
        decrementButtonDimension( (JButton)evt.getComponent(), false );
    }//GEN-LAST:event_Inv_Item13MouseExited

    private void Inv_Item13MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inv_Item13MouseEntered
        // TODO add your handling code here:
        incrementButtonDimension( (JButton)evt.getComponent(), false );
    }//GEN-LAST:event_Inv_Item13MouseEntered

    private void Inv_Item14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inv_Item14MouseClicked
        // TODO add your handling code here:
        performActionOnButton((JButton)evt.getComponent() );
        
    }//GEN-LAST:event_Inv_Item14MouseClicked

    private void Inv_Item14MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inv_Item14MouseExited
        // TODO add your handling code here:
        decrementButtonDimension( (JButton)evt.getComponent(), false );
    }//GEN-LAST:event_Inv_Item14MouseExited

    private void Inv_Item14MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inv_Item14MouseEntered
        // TODO add your handling code here:
        incrementButtonDimension( (JButton)evt.getComponent(), false );
    }//GEN-LAST:event_Inv_Item14MouseEntered

    private void Inv_Item15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inv_Item15MouseClicked
        // TODO add your handling code here:
        performActionOnButton((JButton)evt.getComponent() );
        
    }//GEN-LAST:event_Inv_Item15MouseClicked

    private void Inv_Item15MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inv_Item15MouseExited
        // TODO add your handling code here:
        decrementButtonDimension( (JButton)evt.getComponent(), false );
    }//GEN-LAST:event_Inv_Item15MouseExited

    private void Inv_Item15MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inv_Item15MouseEntered
        // TODO add your handling code here:
        incrementButtonDimension( (JButton)evt.getComponent(), false );
    }//GEN-LAST:event_Inv_Item15MouseEntered

    private void Inv_Item16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inv_Item16MouseClicked
        // TODO add your handling code here:
        performActionOnButton((JButton)evt.getComponent() );
    }//GEN-LAST:event_Inv_Item16MouseClicked

    private void Inv_Item16MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inv_Item16MouseExited
        // TODO add your handling code here:
        decrementButtonDimension( (JButton)evt.getComponent(), true );
    }//GEN-LAST:event_Inv_Item16MouseExited

    private void Inv_Item16MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inv_Item16MouseEntered
        // TODO add your handling code here:
        incrementButtonDimension( (JButton)evt.getComponent(), true );
    }//GEN-LAST:event_Inv_Item16MouseEntered

    private void Inv_Item17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inv_Item17MouseClicked
        // TODO add your handling code here:
        performActionOnButton((JButton)evt.getComponent() );
    }//GEN-LAST:event_Inv_Item17MouseClicked

    private void Inv_Item17MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inv_Item17MouseExited
        // TODO add your handling code here:
        decrementButtonDimension( (JButton)evt.getComponent(), false );
    }//GEN-LAST:event_Inv_Item17MouseExited

    private void Inv_Item17MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inv_Item17MouseEntered
        // TODO add your handling code here:
        incrementButtonDimension( (JButton)evt.getComponent(), false );
    }//GEN-LAST:event_Inv_Item17MouseEntered

    private void Inv_Item18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inv_Item18MouseClicked
        // TODO add your handling code here:
        performActionOnButton((JButton)evt.getComponent() );
        
    }//GEN-LAST:event_Inv_Item18MouseClicked

    private void Inv_Item18MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inv_Item18MouseExited
        // TODO add your handling code here:
        decrementButtonDimension( (JButton)evt.getComponent(), false );
    }//GEN-LAST:event_Inv_Item18MouseExited

    private void Inv_Item18MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inv_Item18MouseEntered
        // TODO add your handling code here:
        incrementButtonDimension( (JButton)evt.getComponent(), false );
    }//GEN-LAST:event_Inv_Item18MouseEntered

    private void Inv_Item19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inv_Item19MouseClicked
        // TODO add your handling code here:
        performActionOnButton((JButton)evt.getComponent() );
    }//GEN-LAST:event_Inv_Item19MouseClicked

    private void Inv_Item19MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inv_Item19MouseExited
        // TODO add your handling code here:
        decrementButtonDimension( (JButton)evt.getComponent(), false );
    }//GEN-LAST:event_Inv_Item19MouseExited

    private void Inv_Item19MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inv_Item19MouseEntered
        // TODO add your handling code here:
        incrementButtonDimension( (JButton)evt.getComponent(), false );
    }//GEN-LAST:event_Inv_Item19MouseEntered

    private void Inv_Item20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inv_Item20MouseClicked
        // TODO add your handling code here:
        performActionOnButton((JButton)evt.getComponent() );
    }//GEN-LAST:event_Inv_Item20MouseClicked

    private void Inv_Item20MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inv_Item20MouseExited
        // TODO add your handling code here:
        decrementButtonDimension( (JButton)evt.getComponent(), true );
    }//GEN-LAST:event_Inv_Item20MouseExited

    private void Inv_Item20MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Inv_Item20MouseEntered
        // TODO add your handling code here:
        incrementButtonDimension( (JButton)evt.getComponent(), true );
    }//GEN-LAST:event_Inv_Item20MouseEntered

    private void Equip_Inv_Item5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Equip_Inv_Item5MouseClicked
        // TODO add your handling code here:
        performActionOnButton((JButton)evt.getComponent() );
    }//GEN-LAST:event_Equip_Inv_Item5MouseClicked

    private void Equip_Inv_Item5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Equip_Inv_Item5MouseExited
        // TODO add your handling code here:
        decrementButtonDimension( (JButton)evt.getComponent(), false );
      
    }//GEN-LAST:event_Equip_Inv_Item5MouseExited

    private void Equip_Inv_Item5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Equip_Inv_Item5MouseEntered
        // TODO add your handling code here:
        incrementButtonDimension( (JButton)evt.getComponent(), false );
    }//GEN-LAST:event_Equip_Inv_Item5MouseEntered

    private void Equip_Inv_Item7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Equip_Inv_Item7MouseClicked
        // TODO add your handling code here:
        performActionOnButton((JButton)evt.getComponent() );
    }//GEN-LAST:event_Equip_Inv_Item7MouseClicked

    private void Equip_Inv_Item7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Equip_Inv_Item7MouseExited
        // TODO add your handling code here:
        decrementButtonDimension( (JButton)evt.getComponent(), false );
    }//GEN-LAST:event_Equip_Inv_Item7MouseExited

    private void Equip_Inv_Item7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Equip_Inv_Item7MouseEntered
        // TODO add your handling code here:
        incrementButtonDimension( (JButton)evt.getComponent(), false );
    }//GEN-LAST:event_Equip_Inv_Item7MouseEntered

    private void Equip_Inv_Item9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Equip_Inv_Item9MouseClicked
        // TODO add your handling code here:
        performActionOnButton((JButton)evt.getComponent() );
    }//GEN-LAST:event_Equip_Inv_Item9MouseClicked

    private void Equip_Inv_Item9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Equip_Inv_Item9MouseExited
        // TODO add your handling code here:
        decrementButtonDimension( (JButton)evt.getComponent(), false );
    }//GEN-LAST:event_Equip_Inv_Item9MouseExited

    private void Equip_Inv_Item9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Equip_Inv_Item9MouseEntered
        // TODO add your handling code here:
        incrementButtonDimension( (JButton)evt.getComponent(), false );
    }//GEN-LAST:event_Equip_Inv_Item9MouseEntered

    private void Equip_Inv_Item11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Equip_Inv_Item11MouseClicked
        // TODO add your handling code here:
        performActionOnButton((JButton)evt.getComponent() );
        
    }//GEN-LAST:event_Equip_Inv_Item11MouseClicked

    private void Equip_Inv_Item11MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Equip_Inv_Item11MouseExited
        // TODO add your handling code here:
        decrementButtonDimension( (JButton)evt.getComponent(), false );
    }//GEN-LAST:event_Equip_Inv_Item11MouseExited

    private void Equip_Inv_Item11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Equip_Inv_Item11MouseEntered
        // TODO add your handling code here:
        incrementButtonDimension( (JButton)evt.getComponent(), false );
    }//GEN-LAST:event_Equip_Inv_Item11MouseEntered

    private void Equip_Inv_Item6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Equip_Inv_Item6MouseClicked
        // TODO add your handling code here:
        performActionOnButton((JButton)evt.getComponent() );
        
        
    }//GEN-LAST:event_Equip_Inv_Item6MouseClicked

    private void Equip_Inv_Item6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Equip_Inv_Item6MouseExited
        // TODO add your handling code here:
        decrementButtonDimension( (JButton)evt.getComponent(), true );
    }//GEN-LAST:event_Equip_Inv_Item6MouseExited

    private void Equip_Inv_Item6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Equip_Inv_Item6MouseEntered
        // TODO add your handling code here:
        incrementButtonDimension( (JButton)evt.getComponent(), true );
    }//GEN-LAST:event_Equip_Inv_Item6MouseEntered

    private void Equip_Inv_Item8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Equip_Inv_Item8MouseClicked
        // TODO add your handling code here:
        performActionOnButton((JButton)evt.getComponent() );
    }//GEN-LAST:event_Equip_Inv_Item8MouseClicked

    private void Equip_Inv_Item8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Equip_Inv_Item8MouseExited
        // TODO add your handling code here:
        decrementButtonDimension( (JButton)evt.getComponent(), true );
    }//GEN-LAST:event_Equip_Inv_Item8MouseExited

    private void Equip_Inv_Item8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Equip_Inv_Item8MouseEntered
        // TODO add your handling code here:
        incrementButtonDimension( (JButton)evt.getComponent(), true );
    }//GEN-LAST:event_Equip_Inv_Item8MouseEntered

    private void Equip_Inv_Item10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Equip_Inv_Item10MouseClicked
        // TODO add your handling code here:
        performActionOnButton((JButton)evt.getComponent() );
    }//GEN-LAST:event_Equip_Inv_Item10MouseClicked

    private void Equip_Inv_Item10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Equip_Inv_Item10MouseExited
        // TODO add your handling code here:
        decrementButtonDimension( (JButton)evt.getComponent(), true );
        
    }//GEN-LAST:event_Equip_Inv_Item10MouseExited

    private void Equip_Inv_Item10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Equip_Inv_Item10MouseEntered
        // TODO add your handling code here:
        incrementButtonDimension( (JButton)evt.getComponent(), true );
    }//GEN-LAST:event_Equip_Inv_Item10MouseEntered

    private void Equip_Inv_Item14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Equip_Inv_Item14MouseClicked
        // TODO add your handling code here:
        performActionOnButton((JButton)evt.getComponent() );
    }//GEN-LAST:event_Equip_Inv_Item14MouseClicked

    private void Equip_Inv_Item14MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Equip_Inv_Item14MouseExited
        // TODO add your handling code here:
        decrementButtonDimension( (JButton)evt.getComponent(), true );
    }//GEN-LAST:event_Equip_Inv_Item14MouseExited

    private void Equip_Inv_Item14MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Equip_Inv_Item14MouseEntered
        // TODO add your handling code here:
        incrementButtonDimension( (JButton)evt.getComponent(), true );
    }//GEN-LAST:event_Equip_Inv_Item14MouseEntered

    private void Equip_Inv_Item12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Equip_Inv_Item12MouseClicked
        // TODO add your handling code here:
        performActionOnButton((JButton)evt.getComponent() );
    }//GEN-LAST:event_Equip_Inv_Item12MouseClicked

    private void Equip_Inv_Item12MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Equip_Inv_Item12MouseExited
        // TODO add your handling code here:
        decrementButtonDimension( (JButton)evt.getComponent(), false );
    }//GEN-LAST:event_Equip_Inv_Item12MouseExited

    private void Equip_Inv_Item12MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Equip_Inv_Item12MouseEntered
        // TODO add your handling code here:
        incrementButtonDimension( (JButton)evt.getComponent(), false );
        
    }//GEN-LAST:event_Equip_Inv_Item12MouseEntered

    private void Equip_Inv_Item13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Equip_Inv_Item13MouseClicked
        // TODO add your handling code here:
        performActionOnButton((JButton)evt.getComponent() );
    }//GEN-LAST:event_Equip_Inv_Item13MouseClicked

    private void Equip_Inv_Item13MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Equip_Inv_Item13MouseExited
        // TODO add your handling code here:
        decrementButtonDimension( (JButton)evt.getComponent(), false );
        
    }//GEN-LAST:event_Equip_Inv_Item13MouseExited

    private void Equip_Inv_Item13MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Equip_Inv_Item13MouseEntered
        // TODO add your handling code here:
        incrementButtonDimension( (JButton)evt.getComponent(), false );
    }//GEN-LAST:event_Equip_Inv_Item13MouseEntered


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Equip_Inv_Item1;
    private javax.swing.JButton Equip_Inv_Item10;
    private javax.swing.JButton Equip_Inv_Item11;
    private javax.swing.JButton Equip_Inv_Item12;
    private javax.swing.JButton Equip_Inv_Item13;
    private javax.swing.JButton Equip_Inv_Item14;
    private javax.swing.JButton Equip_Inv_Item2;
    private javax.swing.JButton Equip_Inv_Item3;
    private javax.swing.JButton Equip_Inv_Item4;
    private javax.swing.JButton Equip_Inv_Item5;
    private javax.swing.JButton Equip_Inv_Item6;
    private javax.swing.JButton Equip_Inv_Item7;
    private javax.swing.JButton Equip_Inv_Item8;
    private javax.swing.JButton Equip_Inv_Item9;
    private javax.swing.JPanel EquippedInventoryPanel;
    private javax.swing.JButton Inv_Item1;
    private javax.swing.JButton Inv_Item10;
    private javax.swing.JButton Inv_Item11;
    private javax.swing.JButton Inv_Item12;
    private javax.swing.JButton Inv_Item13;
    private javax.swing.JButton Inv_Item14;
    private javax.swing.JButton Inv_Item15;
    private javax.swing.JButton Inv_Item16;
    private javax.swing.JButton Inv_Item17;
    private javax.swing.JButton Inv_Item18;
    private javax.swing.JButton Inv_Item19;
    private javax.swing.JButton Inv_Item2;
    private javax.swing.JButton Inv_Item20;
    private javax.swing.JButton Inv_Item3;
    private javax.swing.JButton Inv_Item4;
    private javax.swing.JButton Inv_Item5;
    private javax.swing.JButton Inv_Item6;
    private javax.swing.JButton Inv_Item7;
    private javax.swing.JButton Inv_Item8;
    private javax.swing.JButton Inv_Item9;
    private javax.swing.JPanel InventoryPanel;
    private javax.swing.JPanel IventoryCommandPanel;
    private javax.swing.JButton dropButton;
    private javax.swing.JButton equipButton;
    private javax.swing.JLabel equippedInventoryLabel;
    private javax.swing.JLabel inventoryLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton unequipButton;
    // End of variables declaration//GEN-END:variables
}
