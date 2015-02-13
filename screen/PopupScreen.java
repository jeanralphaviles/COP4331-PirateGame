package screen;

import model.Model;
import controller.Controller;

public abstract class PopupScreen {
	private Screen parentScreen;
	
	public PopupScreen(Model model) {
		super(model);
	}

	public void init(){
		//Does something
	}
	
	
}
