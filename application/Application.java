package application;

import controller.Controller;
import model.LoadSave;
import model.Model;

public class Application {
	private static Model model;
	private static String modelFileName;
	private static LoadSave loadSave;
	private static Controller auxController;
	private static int updatesPerSecond;

	public static void main(String[] args) {
		System.out.println("Game exit");
	}

	public static Model initModel(String modelFilename, LoadSave loadSave) {
		// init Model
		return null;
	}

	public static void initAuxillaryController() {
		
	}

	public static void launchModel(int updatesPerSecond) {
		
	}

	public static Model getModel() {
		return model;
	}

	public static void setModel(Model model) {
		Application.model = model;
	}

	public static String getModelFileName() {
		return modelFileName;
	}

	public static void setModelFileName(String modelFileName) {
		Application.modelFileName = modelFileName;
	}

	public static LoadSave getLoadSave() {
		return loadSave;
	}

	public static void setLoadSave(LoadSave loadSave) {
		Application.loadSave = loadSave;
	}

	public static Controller getAuxController() {
		return auxController;
	}

	public static void setAuxController(Controller auxController) {
		Application.auxController = auxController;
	}

	public static int getUpdatesPerSecond() {
		return updatesPerSecond;
	}

	public static void setUpdatesPerSecond(int updatesPerSecond) {
		Application.updatesPerSecond = updatesPerSecond;
	}
}
