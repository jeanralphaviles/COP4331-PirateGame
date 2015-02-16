package view.viewport.InventoryViewPort;

/**
 * Created by Nikita and Carlos
 * on 2/15/15.
 */

public enum ViewInventoryCategory {

    INVENTORY_VIEW(1),
    EQUIPPED_INVENTORY_VIEW(2);

    private int value;

    // ----------- METHODS IMPLEMENTATION -------------------
    // -----------                        -------------------
    private ViewInventoryCategory(int value) {
        this.setValue(value);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}

