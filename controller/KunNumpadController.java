package controller;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class KunNumpadController extends JFrame implements KeyListener{

JTextField keyText = new JTextField(80);
JLabel keyLabel = new JLabel("Press Numbers");

KunNumpadController() {
		keyText.addKeyListener(this);
		setSize(400,400);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		BorderLayout layout = new BorderLayout();
		setLayout(layout);
		add(keyLabel, BorderLayout.NORTH);
		add(keyText, BorderLayout.CENTER);
	}

	

	@Override
	public void keyTyped (KeyEvent e) {

	}

	/**
	 * depending on key pressed the entity(our avatar) will be sent a message to move appropriately
	 */

	//http://docs.oracle.com/javase/7/docs/api/java/awt/event/KeyEvent.html replace VK_codes with 
	//VK_NUMPADX where x denotes the number pressed on the number pad VK_X denotes number pressed any
	//where on the keyboard
	@Override
	public void keyPressed (KeyEvent e){
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_1)
			keyLabel.setText("you pressed 1");
		else if(keyCode == KeyEvent.VK_2)
			keyLabel.setText("pressed 2 move down");
		else if(keyCode == KeyEvent.VK_3)
			keyLabel.setText("pressed 3 move left down");
		else if(keyCode == KeyEvent.VK_4)
			keyLabel.setText("you pressed 4 move left");
		else if(keyCode == KeyEvent.VK_5)
			keyLabel.setText("you pressed 5");
		else if(keyCode == KeyEvent.VK_6)
			keyLabel.setText("you pressed 6 move right");
		else if(keyCode == KeyEvent.VK_7)
			keyLabel.setText("you pressed 7 move top left");
		else if(keyCode == KeyEvent.VK_8)
			keyLabel.setText("you pressed 8 move top");
		else if(keyCode == KeyEvent.VK_9)	
			keyLabel.setText("you pressed 9 move top right");
		else if(keyCode == KeyEvent.VK_0)
			keyLabel.setText("you pressed 0 do nothing!");
		else if(keyCode == KeyEvent.VK_P)
			JOptionPane.showMessageDialog(null, "You've Paused the Game!");

		else{
			keyLabel.setText("You pressed an invalid control");

		}


	}
	@Override
	public void keyReleased (KeyEvent txt){


	}


	public static void main(String[] args){
		KunNumpadController go = new KunNumpadController();

	}
}
