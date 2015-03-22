package model;

import java.io.File;
import java.util.ArrayList;

import application.RunGame;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import model.entity.Avatar;
import model.entity.Entity;
import model.entity.Trader;
import model.entity.occupation.Occupation;
import model.entity.occupation.ability.Ability;
import model.inventory.Slot;
import model.item.EntitySpawner;
import model.item.InteractiveItem;
import model.map.GridLocation;
import model.map.Map;
import model.map.areaeffect.HealDamageAreaEffect;
import model.map.areaeffect.InstantDeathAreaEffect;
import model.map.areaeffect.LevelUpAreaEffect;
import model.map.areaeffect.TakeDamageAreaEffect;
import model.map.areaeffect.TeleportAreaEffect;
import utility.Course;
import utility.ItemGenerator;
import utility.MapGenerator;
import utility.UtilityData;
import view.screen.MainScreen;
import view.screen.Screen;
import view.viewport.AreaViewport;

/**
 * @author Jean-Ralph Aviles and Carlos Vizcaino
 */
public class Model extends Thread {

    /*Properties*/
    private GameObject gameObject;
    private UtilityData utilityData;
    private Screen currentScreen;
    private int viewUpdatesPerSecond;
    public static String loadSaveDirectory = "LoadSave/";
    //
    private static final int sleepTime = 10; //ms, polling time of thread
    private static final int second = 1000; //ms
    private static final int environmentsStepUpdatesPerSecond = 3; //stuff like projectiles
    private static final int gameStepUpdatesPerSecond = 3; //stuff like npc AI
    private static final int tolerance = 20; //window for checking if an update should occur
    //
    private Mode mode = new  RunMode(); //new PauseMode();

    /*Constructors*/
    public Model() {
        Avatar avatar = new Avatar();
        this.gameObject = new GameObject(avatar);
        this.setUtilityData(new UtilityData());
        loadLevels(1, avatar);
    }

    /*Methods*/
    public void launch(int updatesPerSecond, String saveName) {
        this.viewUpdatesPerSecond = updatesPerSecond;
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

    public void updateView() {
        currentScreen.updateView(this.gameObject);
    }

    public void refreshController() {
        currentScreen.refreshController();
    }

    public boolean moveAvatar(Course course) {
        return this.gameObject.moveAvatar(course);
    }

    // TODO(jraviles) figure out what to do with these legacy functions
    public String getNextDialogue() {
        return gameObject.getLevel().getNextDialogue();
    }

    public void setDialogue(String dialogue) {
        gameObject.getLevel().setCurrentDialogue(dialogue);
    }

    @Override
    public void run() {
        System.out.println("Model Started");
        int ms = 0; //between 0-1000, 
        while (true) {
            //mainGameLoop(ms);
            try {
                Thread.sleep(sleepTime);

                //adjust ms accordingly
                if (ms < 1000) {
                    ms += sleepTime; //increment
                } else {
                    ms = 0; //reset
                }

                mode.executeMainGameLoop(ms);
                

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void activateAvatarAbility(Ability ability) {
        this.gameObject.activateAvatarAbility(ability);
    }

    public ArrayList<Ability> getAvatarAbilities() {
        return this.gameObject.getAvatar().getAbilities();
    }

    public void setAvatarOccupation(Occupation occupation) {
        this.gameObject.getAvatar().setOccupation(occupation);
    }

    public void setAvatarNickname(String nickname) {
        this.gameObject.getAvatar().setNickname(nickname);
    }

    //***********************************LOAD AND SAVE STUFF*********************************************//
    public void loadLevels(int numLevels, Avatar avatar) {
        if (numLevels < 1) {
            return;
        }
        ArrayList<Level> levels = new ArrayList<Level>(numLevels);
        Map map;
        Level level;
        for (int i = 1; i <= numLevels; ++i) {
            map = MapGenerator.generateMap(new File("Levels/Map" + i + ".csv"));
            map.getMaptile(new GridLocation(10, 5)).setAreaEffect(new TeleportAreaEffect(new GridLocation(25, 5)));
            map.getMaptile(new GridLocation(27, 5)).setAreaEffect(new TeleportAreaEffect(new GridLocation(17, 8)));
            map.getMaptile(new GridLocation(14, 5)).setAreaEffect(new InstantDeathAreaEffect());
            map.getMaptile(new GridLocation(10, 12)).setAreaEffect(new LevelUpAreaEffect());
            map.getMaptile(new GridLocation(11, 13)).setAreaEffect(new HealDamageAreaEffect());
            map.getMaptile(new GridLocation(14, 6)).setAreaEffect(new TakeDamageAreaEffect());
            Slot[][] slots = ItemGenerator.generateItems(new File("Levels/Items" + i + ".csv"), map);
            level = new Level(map, slots);
            Entity newEntity = new Entity();
            newEntity.setFriendly(false);
            Trader newTrader = new Trader();
            InteractiveItem entitySpawner = new EntitySpawner();
            level.addItem(entitySpawner, new GridLocation(19, 8));
            level.addEntity(newEntity, new GridLocation(28, 5));
            level.addEntity(newTrader, new GridLocation(19, 13));
            levels.add(level);
        }
        GridLocation avatarLocation = new GridLocation(levels.get(0).getWidth() / 2, levels.get(0).getHeight() / 2);
        levels.get(0).addEntity(avatar, avatarLocation);
        setGameObject(new GameObject(levels));
    }

    @Override
    public String toString() {
        return "[" + gameObject.toString() + "," + utilityData.toString() + "," + viewUpdatesPerSecond + "]";
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
        model.viewUpdatesPerSecond = Integer.parseInt(stripped.substring(start));
        return model;
    }

    public void save() {

        try {

            Avatar avatar = this.getGameObject().getAvatar();

            if (avatar.getNickname() != null && !avatar.getNickname().isEmpty()) {

                File file = new File(loadSaveDirectory + avatar.getNickname() + "/");
                if (file.exists() == false) {

                    file.mkdir();
                }

                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
                Date date = new Date();
                String fileName = avatar.getOccupation().toString() + "_" + this.getGameObject().getAvatarLevel() + "_" + dateFormat.format(date);
                File newFile = new File(loadSaveDirectory + avatar.getNickname() + "/" + fileName + "_*$%");

                File[] listOfFiles = file.listFiles();
                for (File i : listOfFiles) {

                    if (i.getName().contains("*$%")) {

                        i.delete();
                        break;
                    }
                }

                if (newFile.exists() == false) {

                    file.createNewFile();
                }

                String savedModel = this.toString();
                FileOutputStream fstream = new FileOutputStream(newFile, false);
                fstream.write(savedModel.getBytes());
                fstream.flush();
                fstream.close();

            } // End of if-statement

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save(String fileName) {

        try {

            Avatar avatar = this.getGameObject().getAvatar();

            if (avatar.getNickname() != null && !avatar.getNickname().isEmpty()) {

                File file = new File(loadSaveDirectory + avatar.getNickname() + "/");
                if (file.exists() == false) {

                    file.mkdir();
                }

                file = new File(loadSaveDirectory + avatar.getNickname() + "/" + fileName);

                if (file.exists() == false) {

                    file.createNewFile();
                }

                String savedModel = this.toString();
                FileOutputStream fstream = new FileOutputStream(file, false);
                fstream.write(savedModel.getBytes());
                fstream.flush();
                fstream.close();

            } // End of if-statement

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean load(String fileName) {
        try {
            Avatar avatar = this.getGameObject().getAvatar();

            if (avatar.getNickname() != null && !avatar.getNickname().isEmpty()) {

                File file = new File(loadSaveDirectory + avatar.getNickname() + "/" + fileName);

                if (file != null && file.exists()) {
                    StringBuilder fileContents = new StringBuilder((int) file.length());
                    Scanner scanner = new Scanner(file);
                    String lineSeparator = System.getProperty("line.separator");
                    while (scanner.hasNextLine()) {
                        fileContents.append(scanner.nextLine() + lineSeparator);
                    }
                    scanner.close();
                    Model newModel;

                    if (fileContents.toString().trim().length() != 0) {
                        System.out.println("Loading Model from DISK");
                        newModel = Model.fromString(fileContents.toString());
                    } else {
                        newModel = new Model();
                        newModel.loadLevels(1, new Avatar());
                    }
                    this.setGameObject(newModel.getGameObject());
                    this.setUtilityData(newModel.getUtilityData());
                }

            }

            return true;
        } catch (FileNotFoundException | NullPointerException e) {
            e.printStackTrace();
        }

        return false;
    }

    //***********************************END LOAD AND SAVE STUFF*********************************************//
    /*Get-Sets*/ //for just this class
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

    public void setMode(String mode) {
        switch (mode) {
            case Mode.PAUSE:
                this.mode = new PauseMode();
                break;
            case Mode.RUN:
                this.mode = new RunMode();
                break;
            default:
                RunGame.showErrorMessage("Invalid mode: " + mode);
                break;
        }
    }
    
    /*Inner Classes*//////////////////////////////////////////////////////////////////////////////
    public abstract class Mode {
        
        /*Properties*/
        
        public static final String PAUSE = "PAUSE";
        public static final String RUN = "RUN";
        
        /*Constructors*/
        
        /*Methods*/

        //Perform tasks on different timings
        public abstract void executeMainGameLoop(int ms);

        protected final void updateViewOnTimer(int ms) {
            int viewRemainder = ms % (second / viewUpdatesPerSecond);

            if (viewRemainder > 0 && viewRemainder < tolerance) { // View Timer
                updateView();
                refreshController();
            }
        }

        protected final void updateEnviornmentOnTimer(int ms) {
            int environmentRemainder = ms % (second / environmentsStepUpdatesPerSecond);
            
            if (environmentRemainder > 0 && environmentRemainder < tolerance) { // Environment Timer
                gameObject.environmentGameStep();
                AreaViewport.registerGameStep();
            }
        }
        
        protected final void updateGameStepOnTimer(int ms) {
            int gameStepRemainder = ms%(second/gameStepUpdatesPerSecond);
            
            if (gameStepRemainder > 0 && gameStepRemainder < tolerance) { // Game step timer
            gameObject.gameStep();
            AreaViewport.registerGameStep();
            if (gameObject.getAvatar().isDead()) {
                // Launch Popup
                RunGame.showErrorMessage("You have died. Insert coin to continue!");
                // Give avatar some more lives
                gameObject.getAvatar().getStatistics().changeCurrentHealth(1000);
                gameObject.getAvatar().getStatistics().changeLivesLeft(1);
            }
        }
        }

    }

    private class PauseMode extends Mode {

        public PauseMode() {
            //
        }
        
        @Override
        public void executeMainGameLoop(int ms) {
            updateViewOnTimer(ms); //just keep updating the view
        }

    }

    private class RunMode extends Mode {

        public RunMode() {
            //
        }
        
        @Override
        public void executeMainGameLoop(int ms) {
            //update the view
            updateViewOnTimer(ms); 
            //and execute game steps
            updateEnviornmentOnTimer(ms); //for projectiles and stuff
            updateGameStepOnTimer(ms); //for entities
        }

    }
    
    /*END Inner Classes*//////////////////////////////////////////////////////////////////////////////

    /*Main method for testing*/
    public static void main(String[] args) {
        Model orig = new Model();
        orig.loadLevels(1, new Avatar());
        orig.viewUpdatesPerSecond = 15;
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
        if (orig.viewUpdatesPerSecond != restored.viewUpdatesPerSecond) {
            System.out.println("UpdatesPerSecond differs");
        }
    }

}
