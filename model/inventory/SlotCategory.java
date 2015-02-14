package model.inventory;

/**
 * @author Jean-Ralph Aviles
 */
public enum SlotCategory {
	ANY_SLOT(1);
	/* Others */
	
	private int value;
	
	private SlotCategory(int value) {
		this.setValue(value);
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
