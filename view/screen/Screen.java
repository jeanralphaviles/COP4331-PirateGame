package view.screen;

import java.util.ArrayList;

import utility.IntentComponentMap;
import view.viewport.ViewPort;
import model.GameObject;
import model.Model;
import controller.Controller;
import controller.ScreenController;

public abstract class Screen {
	protected Controller controller;
	protected ViewPort viewPort;
	protected Model model;

	public Screen(Model model) {
		this.model = model;
		initialize();
	}

	public void initialize(){
		init();
		//From viewport, generate IntentComponentMaps
		ArrayList<IntentComponentMap> icm = viewPort.generateIntentComponentMapping();
		//Create controller from IntentComponentMaps
//		ScreenController s = new ScreenController(model, icm);
	}
	public abstract void init();

	public final void updateView(GameObject gameObject) {
		//viewPort.updateView(gameObject);
	}

}
