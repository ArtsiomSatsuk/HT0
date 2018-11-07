package items;

public abstract class Furniture {

    private String name;
    private double minSpaceInM2;
    private double maxSpaceInM2;

    protected Furniture(String name, double maxSpaceInM2) {
        this.name = name;
        this.maxSpaceInM2 = maxSpaceInM2;
        this.minSpaceInM2 = maxSpaceInM2;
    }

    protected Furniture(String name, double minSpaceInM2, double maxSpaceInM2) {
        this.name = name;
        this.minSpaceInM2 = minSpaceInM2;
        this.maxSpaceInM2 = maxSpaceInM2;
    }

    protected String getName() {
        return name;
    }

    protected double getMinSpaceInM2() {
        return minSpaceInM2;
    }

    protected double getMaxSpaceInM2() {
        return maxSpaceInM2;
    }
}