package view.viewport.InventoryViewPort;

import model.entity.Avatar;
import model.item.Weapon;

import javax.swing.*;
import utility.decal.Decal;

/**
 * Created by Carlos and Nikita
 * on 2/15/15.
 */


public class InventoryViewPort {

    public static void main(String[] args){
        
        Avatar avatar  = new Avatar();
        Weapon pistol = new Weapon( new Decal(Decal.item_default), "Pistol",  5, 5);
        Weapon spade = new Weapon( new Decal(Decal.item_default), "Spade", 5, 5);
        Weapon stick = new Weapon( new Decal(Decal.item_default), "Stick", 5, 5);
        Weapon arch = new Weapon( new Decal(Decal.item_default), "Arch", 5, 5);

        avatar.storeItem( pistol );
        avatar.storeItem( spade );
        avatar.storeItem( stick );
        avatar.storeItem( arch );
        avatar.equipItem( pistol );

        // Create JFrame

        JFrame inventory = new JFrame("Inventory");
        inventory.setSize(500,500);
        inventory.add( new MainInventoryPanel(avatar)  );
        //inventory.add( new InventoryItemsPanel(avatar));

        inventory.setVisible(true);
        inventory.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
