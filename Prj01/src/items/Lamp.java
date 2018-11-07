package items;

public class Lamp {

    private int lampIlluminance;

    public Lamp(int lampIlluminance) {
        this.lampIlluminance = lampIlluminance;
    }

    protected int getLampIlluminance() {
        return lampIlluminance;
    }
}