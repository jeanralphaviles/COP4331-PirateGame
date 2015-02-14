package view.screen;

import controller.Controller;
import model.GameObject;
import model.Model;
import utility.IntentComponentMap;
import view.viewport.ViewPort;

import java.util.ArrayList;

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
        //screenController s = new ScreenController(model, icm);

	}
	public abstract void init();

	public final void updateView(GameObject gameObject) {
		//viewPort.updateView(gameObject);
	}

}
