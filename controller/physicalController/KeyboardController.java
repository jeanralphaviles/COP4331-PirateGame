package controller.physicalController;

import controller.control.KeyboardControl;
import controller.IntentMap.IntentMap;
import java.awt.KeyEventPostProcessor;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import model.Model;

public class KeyboardController extends PhysicalController {

    /*Properties*/
    private KeyboardFocusManager keyboardManager;

    /*Constructors*/
    public KeyboardController(Model model) {
        super(model);

        initKeyboardManager();
    }

    /*Methods*/
    private void initKeyboardManager() {
        keyboardManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        keyboardManager.addKeyEventPostProcessor(new EnterKeyListener(model));
    }

    /*Get-Sets*/
    
    
    /*Inner Classes*/
    class EnterKeyListener implements KeyEventPostProcessor {

        private Model model;
        private boolean captureMode = false;

        public EnterKeyListener(Model model) {
            this.model = model;
        }

        @Override
        public boolean postProcessKeyEvent(KeyEvent e) {
            int keyCode = e.getKeyCode();
            if (e.getID() == KeyEvent.KEY_PRESSED) {
                IntentMap im = getIM(keyCode);
                virtualController.executeAction(im);
            }
            return true;
        }
        
        private IntentMap getIM(int keyCode) {
            KeyboardControl control;
            int currentKey;
            IntentMap im;
            for (int i=0; i<intentMaps.size(); i++) {
                im = intentMaps.get(i);
                control = (KeyboardControl)im.getControl();
                if (control.representsKey(keyCode)) {
                    return im;
                }
            }
            return null;
        }
        
//        @Override
//        public boolean postProcessKeyEvent(KeyEvent e) {
//            int keyCode = e.getKeyCode();
//            if (e.getID() == KeyEvent.KEY_PRESSED) {
//                if (keyCode == KeyEvent.VK_NUMPAD1) {
//                    System.out.println("you pressed 1");
//                    model.moveAvatar(new Course(-1, -1));
//                } else if (keyCode == KeyEvent.VK_NUMPAD2 || keyCode == KeyEvent.VK_DOWN) {
//                    System.out.println("pressed 2 move down");
//                    model.moveAvatar(new Course(0, 1));
//                } else if (keyCode == KeyEvent.VK_NUMPAD3) {
//                    System.out.println("pressed 3 move left down");
//                    model.moveAvatar(new Course(1, -1));
//                } else if (keyCode == KeyEvent.VK_NUMPAD4 || keyCode == KeyEvent.VK_LEFT) {
//                    System.out.println("you pressed 4 move left");
//                    model.moveAvatar(new Course(-1, 0));
//                } else if (keyCode == KeyEvent.VK_NUMPAD6 || keyCode == KeyEvent.VK_RIGHT) {
//                    System.out.println("you pressed 6 move right");
//                    model.moveAvatar(new Course(1, 0));
//                } else if (keyCode == KeyEvent.VK_NUMPAD7) {
//                    System.out.println("you pressed 7 move top left");
//                    model.moveAvatar(new Course(-1, 1));
//                } else if (keyCode == KeyEvent.VK_NUMPAD8 || keyCode == KeyEvent.VK_UP) {
//                    System.out.println("you pressed 8 move top");
//                    model.moveAvatar(new Course(0, -1));
//                } else if (keyCode == KeyEvent.VK_NUMPAD9) {
//                    System.out.println("you pressed 9 move top right");
//                    model.moveAvatar(new Course(1, 1));
//                } else if (keyCode == KeyEvent.VK_P) {
//                    JOptionPane.showMessageDialog(null, "You've Paused the Game!");
//                } else if (keyCode == KeyEvent.VK_BACK_SLASH) {
//                    System.out.println("Saving Model");
//                    model.save();
//                } else if (keyCode == KeyEvent.VK_ENTER) {
//                    System.out.println("Loading Model");
//                    model.load("This is obviously wrong");
//                } else if (keyCode == KeyEvent.VK_L) {
//
//                    Screen screen = new LoadSavePopup(model);
//                    model.launchScreen(screen);
//
//                } else {
//                    System.out.println("You pressed an invalid control");
//                }
//            }
//            return true;
//        }
        
    }
}
