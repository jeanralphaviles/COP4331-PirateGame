package utility.response;

import model.Model;

public class RudeResponse extends Response {

    /*Properties*/
    
    private String dialogue;
    
    /*Constructors*/
    
    public RudeResponse(String dialogue) {
        super();
        this.dialogue = dialogue;
    }
    
    /*Methods*/
    
    @Override
    public void address(Model model) {
        //do nothing
    }
    
    @Override
    public String getDialogue() {
        return dialogue;
    }
    
    /*Get-Sets*/
    
    /*Inner Classes*/
    
}
