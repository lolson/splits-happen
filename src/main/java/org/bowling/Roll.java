package org.bowling;

public class Roll {
    private String value; // [X0-9]
    private int index;

    public Roll(String value, int index) {
        this.value = value;
        this.index = index;
    }

    public String getKey() {
        return index + "";
    }

    public String getValue() {
        return value;
    }
}

