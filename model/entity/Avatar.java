package model.entity;

import utility.decal.Decal;
import model.entity.occupation.Occupation;
import model.inventory.EquippedInventory;
import model.inventory.Inventory;

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

    private String name;

    /* Constructors */

    Avatar(Occupation occ, Statistics stats, Inventory inv,
           EquippedInventory eInv, Decal decal, String name) {
        super(occ, stats, inv, eInv, decal);
        this.name = name;
    }

    /* Get-Set methods (Auto-generated) */

    public String getName() {
        return name;
    }

    public void changeName(String name) {
        this.name = name;
    }
}
