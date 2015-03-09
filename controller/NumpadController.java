package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import model.Model;

public class NumpadController extends Controller implements KeyListener {

    public NumpadController(Model model) {
        super(model);
    }

    @Override
	public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_1) {
            print("you pressed 1");
        } else if (keyCode == KeyEvent.VK_2) {
            print("pressed 2 move down");
        } else if (keyCode == KeyEvent.VK_3) {
            print("pressed 3 move left down");
        } else if (keyCode == KeyEvent.VK_4) {
            print("you pressed 4 move left");
        } else if (keyCode == KeyEvent.VK_5) {
            print("you pressed 5");
        } else if (keyCode == KeyEvent.VK_6) {
            print("you pressed 6 move right");
        } else if (keyCode == KeyEvent.VK_7) {
            print("you pressed 7 move top left");
        } else if (keyCode == KeyEvent.VK_8) {
            print("you pressed 8 move top");
        } else if (keyCode == KeyEvent.VK_9) {
            print("you pressed 9 move top right");
        } else if (keyCode == KeyEvent.VK_0) {
            print("you pressed 0 do nothing!");
        } else if (keyCode == KeyEvent.VK_P) {
            JOptionPane.showMessageDialog(null, "You've Paused the Game!");
        } else {
            System.out.println("You pressed an invalid control");

        }

    }

    @Override
    public void keyReleased(KeyEvent txt) {

    }

    public static void main(String[] args) {
        NumpadController go = new NumpadController(new Model());
    }
}
