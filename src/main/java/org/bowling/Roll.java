package org.bowling;

public class Roll {
    private String value; // [X/0-9]
    private int frameNumber;
    private String firstBonus;
    private String secondBonus;
    private int index;

    public Roll(String value, int frameNumber, int index) {
       this.value = value;
       this.frameNumber = frameNumber;
       this.index = index;
    }

    public String getKey() { return index+""; }

    public String getValue() { return value; }

    public int getFrameNumber() { return frameNumber; }

    public String getFirstBonus() {
        return firstBonus;
    }

    public void setFirstBonus(String firstBonus) {
        this.firstBonus = firstBonus;
    }

    public String getSecondBonus() {
        return secondBonus;
    }

    public void setSecondBonus(String secondBonus) {
        this.secondBonus = secondBonus;
    }
}