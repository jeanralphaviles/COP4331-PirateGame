package model.item;

public enum Category {
	ANY_ITEM(1),
	TAKEABLE_ITEM(2),
	ONE_SHOT_ITEM(3),
	INTERACTIVE_ITEM(4),
	OBSTACLE_ITEM(5);

	private int value;
    // ----------- METHODS IMPLEMENTATION -------------------
    // -----------                        -------------------
	private Category(int value) {
		this.setValue(value);
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
};