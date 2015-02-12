package item;

public enum Category {
	MAPTILE_SLOT(1);

	private int value;

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