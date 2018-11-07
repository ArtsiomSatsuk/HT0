package items.furniture.types;

import items.Furniture;

public abstract class SolidFurniture extends Furniture {

    public SolidFurniture(String name, double maxSpaceInM2) {
        super(name, maxSpaceInM2);
    }
}