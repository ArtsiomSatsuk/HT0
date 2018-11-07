import items.Building;
import items.Lamp;
import items.furniture.soft.SoftArmchair;
import items.furniture.solid.Cupboard;
import items.furniture.solid.Table;

public class Main {
    public static void main(String[] args) {
        Building building = new Building("Shop");
        building.addRoom("kitchen", 100, 0);
        building.getRoom("kitchen").add(new Lamp(250));
        building.getRoom("kitchen").add(new Lamp(300));
        building.getRoom("kitchen").add(new Lamp(350));
        building.getRoom("kitchen").add(new Lamp(200));
        building.getRoom("kitchen").add(new Table("Kitchen table", 54));
        building.getRoom("kitchen").add(new SoftArmchair("Soft armchair", 4, 5));
        building.getRoom("kitchen").add(new SoftArmchair("Soft armchair", 5, 7));
        building.getRoom("kitchen").removeAllSpecifiedFurniture("Soft armchair");
        building.addRoom("bathroom", 100, 1);
        building.getRoom("bathroom").add(new Lamp(175));
        building.getRoom("bathroom").add(new Lamp(150));
        building.getRoom("bathroom").add(new Lamp(450));
        building.getRoom("bathroom").add(new Table("Bedside table", 5));
        building.addRoom("Hall", 145, 5);
        building.addRoom("Entrance hall", 145, 2);
        building.getRoom("Entrance hall").add(new Lamp(200));
        building.getRoom("Entrance hall").add(new Table("Kitchen table", 55));
        building.getRoom("Entrance hall").add(new SoftArmchair("Soft armchair", 5, 5));
        building.getRoom("Entrance hall").add(new Cupboard("Cupboard", 10));
        System.out.println(building.validate());
        building.describe();
    }
}