package model.entity;

import utility.decal.Decal;
import utility.response.TradeResponse;

public final class Trader extends Entity {

    /*Properties*/
    
    /*Constructors*/
    
    public Trader() {
        super(new Decal(Decal.trader));
        response = new TradeResponse();
    }
    
    

}
