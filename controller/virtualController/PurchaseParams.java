/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.virtualController;

import model.item.Item;

/**
 *
 * @author comcc_000
 */
public class PurchaseParams {
    
    /*Properties*/
    
    public Item item;
    public int price;
    
    /*Constructor*/

    public PurchaseParams(Item item, int price) {
        this.item = item;
        this.price = price;
    }
    
}
