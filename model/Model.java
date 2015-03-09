package model;

import java.io.File;
import java.util.ArrayList;

import application.RunGame;
import model.entity.Avatar;
import model.entity.occupation.Occupation;
import model.inventory.Slot;
import model.map.GridLocation;
import model.map.Map;
import utility.Course;
import utility.ItemGenerator;
import utility.MapGenerator;
import utility.UtilityData;
import view.screen.MainScreen;
import view.screen.Screen;

/**
 * @author Jean-Ralph Aviles
 */
public class Model extends Thread {
	private GameObject gameObject;
	private UtilityData utilityData;
	private Screen currentScreen;
	private int updatesPerSecond;
	
	public Model() {
		Avatar avatar = new Avatar();
		this.gameObject = new GameObject(avatar);
		this.setUtilityData(new UtilityData());
		loadLevels(1, avatar);
	}
	
	public void launch(int updatesPerSecond, String saveName) {
		this.updatesPerSecond = updatesPerSecond;
		launchFirstScreen();
		this.start();
	}
	
	public void launchFirstScreen() {
		launchScreen(new MainScreen(this));
	}

	public void launchScreen(Screen screen) {
		RunGame.mainWindow.displayScreen(screen);
		this.currentScreen = screen;
	}
	
	public void mainGameLoop() {
		gameObject.gameStep();
		updateView();
		refreshController();
	}
	
	public void updateView() {
		currentScreen.updateView(this.gameObject);
	}
	
	public void refreshController() {
		currentScreen.refreshController();
	}
	
	public boolean moveAvatar(Course course) {
		return this.gameObject.moveAvatar(course);
	}
	
	public void loadLevels(int numLevels, Avatar avatar) {
		if (numLevels< 1) {
			return;
		}
		ArrayList<Level> levels = new ArrayList<Level>(numLevels);
		Map map;
		Level level;
		for (int i = 1; i <= numLevels; ++i) {
			map = MapGenerator.generateMap(new File("Levels/Map" + i + ".csv"));
			Slot[][] slots = ItemGenerator.generateItems(new File("Levels/Items" + i + ".csv"), map);
			level = new Level(map, slots);
			levels.add(level);
		}
		GridLocation avatarLocation = new GridLocation(levels.get(0).getWidth() / 2, levels.get(0).getHeight() / 2);
		levels.get(0).addEntity(avatar, avatarLocation);
		setGameObject(new GameObject(levels));
	}
	
	@Override
	public void run() {
		System.out.println("Model Started");
		int sleepTime = 1000 / updatesPerSecond;
		while(true) {
			mainGameLoop();
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public String toString() {
		return "[" + gameObject.toString() + "," + utilityData.toString() + "," + updatesPerSecond + "]";
	}
	
	public static Model fromString(String string) {
		Model model = new Model();
		if (string.isEmpty()) {
			return model;
		}
		string = string.trim();
		String stripped = string.substring(1, string.length() - 1);
		int bracketCount = 0;
		int start = 0;
		int itemCount = 0;
		for (int i = 0; i < stripped.length(); ++i) {
			if (bracketCount == 0 && stripped.charAt(i) == ',') {
				if (itemCount == 0) {
					GameObject gameObject = GameObject.fromString(stripped.substring(start, i));
					model.gameObject = gameObject;
				} else if (itemCount == 1) {
					UtilityData utilityData = UtilityData.fromString(stripped.substring(start, i));
					model.utilityData = utilityData;
					start = i + 1;
					break;
				}
				start = i + 1;
				++itemCount;
			} else if (stripped.charAt(i) == '[') {
				++bracketCount;
			} else if (stripped.charAt(i) == ']') {
				--bracketCount;
			}
		}
		//model.updatesPerSecond = Integer.parseInt(stripped.substring(start));
		return model;
	}

	public Screen getCurrentScreen() {
		return currentScreen;
	}
	
	public void setCurrentScreen(Screen currentScreen) {
		this.currentScreen = currentScreen;
	}
	
	public GameObject getGameObject() {
		return gameObject;
	}

	public void setGameObject(GameObject gameObject) {
		this.gameObject = gameObject;
	}

	public UtilityData getUtilityData() {
		return utilityData;
	}

	public void setUtilityData(UtilityData utilityData) {
		this.utilityData = utilityData;
	}

	// TODO(jraviles) figure out what to do with these legacy functions
	public String getNextDialogue() {
		return gameObject.getLevel().getNextDialogue();
	}
	
	public void setDialogue(String dialogue) {
		gameObject.getLevel().setCurrentDialogue(dialogue);
	}
	
	public void setAvatarOccupation(Occupation occupation) {
		this.gameObject.getAvatar().setOccupation(occupation);
	}
	
	public void setAvatarNickname(String nickname) {
		this.gameObject.getAvatar().setNickname(nickname);
	}
	
	public static void main(String[] args) {
		Model orig = new Model();
		orig.loadLevels(1, new Avatar());
		orig.updatesPerSecond = 15;
		Model restored = Model.fromString(orig.toString());
		
		if (orig.toString().equals(restored.toString()) == false) {
			System.out.println("Serialized Strings are different");
		}
		if (orig.gameObject.toString().equals(restored.gameObject.toString()) == false) {
			System.out.println("Game Objects Differ");
		}
		if (orig.utilityData.toString().equals(restored.utilityData.toString()) == false) {
			System.out.println("Utility Data differs");
		}
		if (orig.updatesPerSecond != restored.updatesPerSecond) {
			System.out.println("UpdatesPerSecond differs");
		}
	}
}
