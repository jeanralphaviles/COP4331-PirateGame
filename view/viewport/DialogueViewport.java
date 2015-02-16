/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.viewport;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.GameObject;
import utility.IntentComponentMap;


public class DialogueViewport extends ViewPort {

	//private JPanel flavImg = new FlavorImageViewport();
	JPanel fvp;
	JPanel dvp;
	
	public DialogueViewport() {
		fvp = new FlavorImageViewport();
		dvp = new DialogueScreenViewport();
		add(fvp, BorderLayout.NORTH);
		add(dvp, BorderLayout.SOUTH);
	}

	

	@Override
	public void updateView(GameObject gameObject) {

	}



	@Override
	public ArrayList<IntentComponentMap> generateIntentComponentMapping() {
		ArrayList<IntentComponentMap> icms = new ArrayList<IntentComponentMap>(
				1);
		icms.add(new IntentComponentMap(((DialogueScreenViewport) dvp).getcontPastDialogueButton(),
				IntentComponentMap.Intent.GOTO_GAME));
		return icms;
	}


}