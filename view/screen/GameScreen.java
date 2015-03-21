package view.screen;

import application.RunGame;
import controller.Intent;
import controller.virtualController.GameVirtualController;
import java.util.ArrayList;
import model.Model;
import controller.IntentMap.IntentMap;
import controller.control.KeyboardControl;
import java.awt.event.KeyEvent;
import utility.Course;
import view.viewport.GameScreenViewport;

public class GameScreen extends Screen {

    public GameScreen(Model model) {
        super(model);
    }
    
    @Override
    public void createView() {
        viewPort = new GameScreenViewport();
        //viewPort = new AreaViewport();
        viewPort.updateView(model.getGameObject());
    }
    
    @Override
    public void createController() {
        //Virtual Controller
        ArrayList<IntentMap> virtualIMs = viewPort.generateIntentMapping();
        virtualController = new GameVirtualController(model, virtualIMs);
        
        //Physical Controller
        ArrayList<IntentMap> physicalIMs = this.generateIntentMapping();
        physicalController = RunGame.getAuxController();
        physicalController.adaptForScreen(physicalIMs, virtualController);
    }
    
    @Override
    protected ArrayList<IntentMap> generateIntentMapping() {
        ArrayList<IntentMap> physicalIMs = new ArrayList<IntentMap>(1);
        
        //Move
        physicalIMs.add(new IntentMap(new KeyboardControl(KeyEvent.VK_NUMPAD1), null, new Course(Course.left_down), Intent.MOVE, ""));
        physicalIMs.add(new IntentMap(new KeyboardControl(KeyEvent.VK_NUMPAD2), null, new Course(Course.down), Intent.MOVE, ""));
        physicalIMs.add(new IntentMap(new KeyboardControl(KeyEvent.VK_NUMPAD3), null, new Course(Course.right_down), Intent.MOVE, ""));
        physicalIMs.add(new IntentMap(new KeyboardControl(KeyEvent.VK_NUMPAD4), null, new Course(Course.left), Intent.MOVE, ""));
        physicalIMs.add(new IntentMap(new KeyboardControl(KeyEvent.VK_NUMPAD6), null, new Course(Course.right), Intent.MOVE, ""));
        physicalIMs.add(new IntentMap(new KeyboardControl(KeyEvent.VK_NUMPAD7), null, new Course(Course.left_up), Intent.MOVE, ""));
        physicalIMs.add(new IntentMap(new KeyboardControl(KeyEvent.VK_NUMPAD8), null, new Course(Course.up), Intent.MOVE, ""));
        physicalIMs.add(new IntentMap(new KeyboardControl(KeyEvent.VK_NUMPAD9), null, new Course(Course.right_up), Intent.MOVE, ""));
        
        //Load and Save
        physicalIMs.add(new IntentMap(new KeyboardControl(KeyEvent.VK_BACK_SLASH), null, null, Intent.SAVE, ""));
        physicalIMs.add(new IntentMap(new KeyboardControl(KeyEvent.VK_L), null, null, Intent.LOAD, ""));
        
        //Pause
        
        
        return physicalIMs;
    }
    
}
