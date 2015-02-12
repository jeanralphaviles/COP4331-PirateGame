package model;

import inventory.EquippedInventory;
import inventory.Inventory;

import java.awt.*;

/*
* Created by Conor Doherty
* on 2/11/2015
* Last edited: 2/11/15 by Conor Doherty
*
* The Avatar class is a subclass of Entity. It will be the instance of Entity
* that the player controls. For all intents and purposes, Avatar "is" the player.
*/

/*
* Changelog:
* 2/11/15 Added file and started coding bby - Conor Doherty
*/

public class Avatar extends Entity {

    /* Attributes */

    private Color hatColor;
    private String name;

    /* Constructors */

    Avatar(Occupation occ, Statistics stats, Inventory inv,
           EquippedInventory eInv, Color hatColor, String name) {
        super(occ, stats, inv, eInv);
        this.hatColor = hatColor;
        this.name = name;
    }

    /* Get-Set methods (Auto-generated) */

    public Color getHatColor() {
        return hatColor;
    }

    public void changeHatColor(Color hatColor) {
        this.hatColor = hatColor;
    }

    public String getName() {
        return name;
    }

    public void changeName(String name) {
        this.name = name;
    }
}
