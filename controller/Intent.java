package controller;

public enum Intent {
	LOAD(1),
	SAVE(2),
	EXIT(3);

	private int value;

	private Intent(int value) {
		this.setValue(value);
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
};