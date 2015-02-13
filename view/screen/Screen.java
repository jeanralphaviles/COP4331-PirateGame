package view.screen;

import model.Model;
import controller.Controller;

public abstract class Screen {
	protected Controller controller;
	protected Model model;
	//protected ViewPort viewPort;
	
	public Screen(Model model) {
		// TODO Auto-generated constructor stub
	}

	public abstract void init();
	
	public void updateView(GameObject gameObject){
		//forwards message from model to viewPort
	}
	
	
}
