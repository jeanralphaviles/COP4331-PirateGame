package utility.response;

import model.Model;
import view.screen.TradeScreen;

public class TradeResponse extends Response {

    /*Properties*/
    
    /*Constructors*/
    
    /*Methods*/
    
    @Override
    public void address(Model model) {
        //GOTO trade screen
        model.launchScreen(new TradeScreen(model));
    }
    /*Get-Sets*/
    
    /*Inner Classes*/
    
}
