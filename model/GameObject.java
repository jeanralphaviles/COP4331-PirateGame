package model;

import java.util.ArrayList;

import utility.Course;
import utility.decal.Decal;
import model.entity.Avatar;
import model.entity.occupation.ability.Ability;
import model.map.GridLocation;

/**
 * @author Jean-Ralph Aviles
 */
public class GameObject {
	// TODO(jraviles) AVATARLEVEL IS SUPER HACKY RIGHT NOW WARNING!!!!!!!
	private int avatarLevel; // TODO(jraviles) Figure out where to put the avatar in each level, currently placing it on the center of the map
	private ArrayList<Level> levels;
	
	public GameObject(Avatar avatar) {
		levels = new ArrayList<Level>(1);
		levels.add(new Level());
		avatarLevel = 0;
		levels.get(0).addEntity(avatar, avatarStartLocation());
		setAvatarLevel(levels.get(0).getAvatar().getStatistics().getLevel());
	}
	
	public GameObject(ArrayList<Level> levels) {
		avatarLevel = 0;
		this.levels = levels;
		// setAvatarLevel(levels.get(0).getAvatar().getStatistics().getLevel());
	}
	
	public GridLocation getAvatarLocation() {
		return levels.get(0).getAvatarLocation();
	}
	
	public boolean moveAvatar(Course course) {
		return levels.get(0).moveAvatar(course);
	}
	
	public ArrayList<Decal> getDecals(GridLocation gridLocation) {
		return levels.get(0).getDecals(gridLocation);
	}
	
	public void nextLevel() {
		// Stops at the last level (when levels run out)
		/*
		if (avatarLevel < currentLevel()) {
			Avatar avatar = levels.get(avatarLevel).getAvatar();
			levels.get(avatarLevel).removeEntity(avatar);
			levels.get(currentLevel()).addEntity(avatar, avatarStartLocation());
			avatarLevel = currentLevel();
		}
		*/
	}
	
	public void gameStep() {
		levels.get(currentLevel()).gameStep();
	}
	
	public void environmentGameStep() {
		levels.get(currentLevel()).environmentGameStep();
	}

	public GridLocation avatarStartLocation() {
		// TODO(jraviles) Figure this out
		int startX = levels.get(currentLevel()).getWidth() / 2;
		int startY = levels.get(currentLevel()).getHeight() / 2;
		return new GridLocation(startX, startY);
	}
	
	public int currentLevel() {
		return 0; // Math.min(avatarLevel, levels.size()) - 1;
	}

	public void activateAvatarAbility(Ability ability) {
		this.getLevel().activateAvatarAbility(ability);
	}
        
        public ArrayList<String> getInspectionDetails(GridLocation gridLocation) {
            return this.getLevel().getInspectionDetails(gridLocation);
        }

	@Override
	public String toString() {
		return "[" + levels.toString() + "," + avatarLevel + "]";
	}

	public static GameObject fromString(String string) {
		String stripped = string.substring(1, string.length() - 1);
		ArrayList<Level> levels = new ArrayList<Level>();
		int bracketCount = 0;
		int start = 0;
		for (int i = 0; i < stripped.length(); ++i) {
			if (bracketCount == 0 && stripped.charAt(i) == ',') {
				int start2 = 0;
				String arrayListStripped = stripped.substring(start + 1, i - 1);
				for (int j = 0; j < arrayListStripped.length(); ++j) {
					if ((bracketCount == 0 && arrayListStripped.charAt(j) == ',') || j == arrayListStripped.length() - 1) {
						Level level;
						if (arrayListStripped.charAt(j) == ',') {
							level = Level.fromString(arrayListStripped.substring(start2, j));
						} else {
							level = Level.fromString(arrayListStripped.substring(start2, j + 1));
						}
						levels.add(level);
						start2 = j + 1;
					} else if (arrayListStripped.charAt(j) == '[') {
						++bracketCount;
					} else if (arrayListStripped.charAt(j) == ']') {
						--bracketCount;
					}
				}
				start = i + 1;
				break;
			} else if (stripped.charAt(i) == '[') {
				++bracketCount;
			} else if (stripped.charAt(i) == ']') {
				--bracketCount;
			}
		}
		GameObject gameObject = new GameObject(levels);
		gameObject.avatarLevel = Integer.parseInt(stripped.substring(start, stripped.length()));
		return gameObject;
	}

	public int getAvatarLevel() {
		return avatarLevel;
	}

	public void setAvatarLevel(int avatarLevel) {
		this.avatarLevel = avatarLevel;
	}
	
	// TODO(jraviles) Lets take this out or figure out what to do with it, These functions are here for legacy reasons
	public Level getLevel() {
		return levels.get(currentLevel());
	}
	
	public ArrayList<Level> getLevels() {
		return levels;
	}
	
	public Avatar getAvatar() {
		return levels.get(currentLevel()).getAvatar();
	}
	
	public static void main(String[] args) {
		ArrayList<Level> levels = new ArrayList<Level>(2);
		levels.add(new Level());
		levels.add(new Level());
		GameObject original = new GameObject(new Avatar());
		GameObject restored = GameObject.fromString(original.toString());
		
		if (original.toString().equals(restored.toString()) == false) {
			System.out.println("Serialized Strings differ");
		}
		if (original.avatarLevel != restored.avatarLevel) {
			System.out.println("Avatar Levels differ");
		}
		if (original.levels.size() != restored.levels.size()) {
			System.out.println("Number of Levels don't match");
		}
		for (int i = 0; i < original.levels.size(); ++i) {
			if (original.levels.get(i).toString().equals(restored.levels.get(i).toString()) == false) {
				System.out.println("Levels don't match");
			}
		}
		if (original.getAvatar().toString().equals(restored.getAvatar().toString()) == false) {
			System.out.println("Avatars differ");
		}
	}
}
