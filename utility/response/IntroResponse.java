package utility.response;

import java.util.ArrayList;
import model.Model;

public class IntroResponse extends Response {

    /*Properties*/
    
    private ArrayList<String> dialogueLines = new ArrayList<String>(1);
    private int currentIndex = 0;
    
    /*Constructors*/
    
    public IntroResponse(ArrayList<String> dialogueLines) {
        this.dialogueLines = dialogueLines;
    }
    
    /*Methods*/
    
    @Override
    public void address(Model model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /*Get-Sets*/
    
    @Override
    public String getDialogue() {
        if (currentIndex < dialogueLines.size()) {
            String dialogue = dialogueLines.get(currentIndex);
            return dialogue;
        }
        return null;
    }
    
    @Override
    public boolean advanceResponse() {
        currentIndex++;
        if (currentIndex < dialogueLines.size()) {
            return true;
        }
        return false;
    }
    
    /*Inner Classes*/
    
}
