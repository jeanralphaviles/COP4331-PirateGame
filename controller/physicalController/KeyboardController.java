package controller.physicalController;

import controller.control.KeyboardControl;
import controller.IntentMap.IntentMap;
import controller.control.Control;
import controller.virtualController.OptionsVirtualController;
import java.awt.KeyEventPostProcessor;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import model.Model;
import utility.Course;
import view.viewport.OptionsViewport;

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
    
    public void activateRebindMode() {
        setMode(PhysicalControllerMode.REBIND);
    }
    
    @Override
    public void rebind(Control control) {
        int keyCode = ((KeyboardControl) control).getKeyCode();
        OptionsVirtualController ovc = ((OptionsVirtualController) virtualController);
        RebindInfo ri = ovc.getRebindInfo();
        reassignControlWithIntent(new KeyboardControl(keyCode), ri.object, ri.intent);
        //label key appropriately
        JButton button = (JButton) ri.component;
        button.setText(KeyEvent.getKeyText(keyCode));
        setMode(PhysicalControllerMode.DISABLED); //rebindMode = false; //remember to do this /*Derpy face*/
        JButton backButton = (JButton) ri.backButton;
        backButton.setEnabled(true); //re-enable back button 
    }

    @Override
    public void performAction(Control control) {
        int keyCode = ((KeyboardControl) control).getKeyCode();
        IntentMap im = getIM(keyCode);
        virtualController.executeAction(im);
    }
    
    private IntentMap getIM(int keyCode) {
        if (intentMaps == null) {
            return null;
        }
        KeyboardControl control;
        int currentKey;
        IntentMap im;
        for (int i = 0; i < intentMaps.size(); i++) {
            im = intentMaps.get(i);
            control = (KeyboardControl) im.getControl();
            if (control.representsKey(keyCode)) {
                return im;
            }
        }
        return null;
    }

    /*Get-Sets*/
    
    
    /*Inner Classes*/
    class EnterKeyListener implements KeyEventPostProcessor {

        private Model model;

        public EnterKeyListener(Model model) {
            this.model = model;
        }

        @Override
        public boolean postProcessKeyEvent(KeyEvent e) {
            int keyCode = e.getKeyCode();
            if (e.getID() == KeyEvent.KEY_PRESSED) {
                KeyboardControl control = new KeyboardControl(keyCode);
                mode.actionPerformed(control);
            }
            return true;
        }

        
    }
}
