package view.screen;

import view.viewport.ViewPort;
import model.GameObject;
import model.Model;
import controller.Controller;

public abstract class Screen {
	protected Controller controller;
	protected ViewPort viewPort;
	protected Model model;

	public Screen(Model model) {
		this.model = model;
		init();
	}

	public abstract void init();

	public final void updateView(GameObject gameObject) {
		// TODO
	}

}
