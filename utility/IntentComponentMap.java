package utility;

import javax.swing.JComponent;

import controller.Intent;

/**
 * @author Team Tiger
 * 
 *maps view button with it's action-listener (control)
 *Allows view and controller to be decoupled
 */

public class IntentComponentMap {
	private JComponent jComponent;
	private Intent intent;
	
	public IntentComponentMap(JComponent jComponent, Intent intent) {
		this.setjComponent(jComponent);
		this.setIntent(intent);
	}

	public Intent getIntent() {
		return intent;
	}

	public void setIntent(Intent intent) {
		this.intent = intent;
	}

	public JComponent getjComponent() {
		return jComponent;
	}

	public void setjComponent(JComponent jComponent) {
		this.jComponent = jComponent;
	}
}
