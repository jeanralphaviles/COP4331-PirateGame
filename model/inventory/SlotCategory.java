package model.inventory;

/**
 * @author Jean-Ralph Aviles AND Carlos Vizcaino
 */
public enum SlotCategory {
	ANY_SLOT(1),
    HEAD(2),
    EYEPATCH(3),
    HALFBEARD(4),
    NECK(5),
    CHEST(6),
    RING(7),
    HAND(8),
    PANT(9),
    FOOT(10);
	/* Others */
	
	private int value;

    // ----------- METHODS IMPLEMENTATION -------------------
    // -----------                        -------------------
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
