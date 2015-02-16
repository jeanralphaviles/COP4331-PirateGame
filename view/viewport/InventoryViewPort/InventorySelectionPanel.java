package view.viewport.InventoryViewPort;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by darien on 2/15/15.
 */
public class InventorySelectionPanel extends JPanel {

    // Attributes
    private JButton dropButton;
    private JButton equipButton;
    private JButton unquipButton;
    private ViewInventoryCategory category;
    private InventoryPanel inventoryPanel;

    // Default Constructor
    public InventorySelectionPanel() {

        inventoryPanel = new InventoryPanel();
        this.category = ViewInventoryCategory.INVENTORY_VIEW;
        createButtons();
    }

    // Constructor I
    public InventorySelectionPanel(InventoryPanel inventoryPanel, ViewInventoryCategory category) {

        this.inventoryPanel = inventoryPanel;
        this.category = category;
        createButtons();
    }

    // ----------- METHODS IMPLEMENTATION -------------------
    // -----------                        -------------------

    // ------------------------------------------------------
    private void createButtons() {

        System.out.println("Inside create buttons");
        // Setup Panel
        this.setLayout(new FlowLayout(5, 5, 5));

        if (category == ViewInventoryCategory.INVENTORY_VIEW) {

            dropButton = new JButton("Drop");
            dropButton.addActionListener( new Action(dropButton) );
            equipButton = new JButton("Equip");
            equipButton.addActionListener( new Action(equipButton));
            this.add(dropButton);
            this.add(equipButton);

        } else {

            unquipButton = new JButton(("Unequip"));
            unquipButton.addActionListener(new Action(unquipButton));
            this.add(unquipButton);
        }
        this.setVisible(true);
    }

    // ------- ACTION LISTENER ------------
    // -------                 ------------
    private class Action implements ActionListener {

        JButton buttonHolder;

        public Action(JButton button) {

            buttonHolder = button;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            if (buttonHolder == dropButton) {

                inventoryPanel.dropItem();

            }
            else if ( buttonHolder == equipButton){

                System.out.println("Inside here");
                inventoryPanel.equipItem();
            }
            else if ( buttonHolder == unquipButton ){

                inventoryPanel.unequipItem();
            }
            inventoryPanel.updateItemsView();
        }
    }
} // End of class

