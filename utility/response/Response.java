package utility.response;

import model.Model;

public abstract class Response {
    
    /*Properties*/
    
    /*Constructors*/
    
    /*Methods*/
    
    public abstract void address(Model model);
    
    //reasonable default implementation
    public String getDialogue() {
        return "";
    }
    
    //reasonable default implementation
    public boolean advanceResponse() { //returns true if there's a next dialogue
        return false;
    }
    
    /*Get-Sets*/
    
    /*Inner Classes*/
    
}
