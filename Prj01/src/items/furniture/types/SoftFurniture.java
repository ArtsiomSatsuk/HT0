package items.furniture.types;

import items.Furniture;

public abstract class SoftFurniture extends Furniture {

    public SoftFurniture(String name, double minSpaceInM2, double maxSpaceInM2) {
        super(name, minSpaceInM2, maxSpaceInM2);
    }
}