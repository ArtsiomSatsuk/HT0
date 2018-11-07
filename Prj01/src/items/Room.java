package items;

import customException.*;

import java.util.ArrayList;
import java.util.Iterator;

public class Room {

    private String nameOfRoom;
    private double roomSpace;
    private double maxOccupiedRoomSpace;
    private double minOccupiedRoomSpace;
    private int numberOfWindows;
    private int totalRoomIlluminance;
    public static final int WINDOW_ILLUMINANCE = 700;
    public static final int MIN_ROOM_ILLUMINANCE_VALUE = 300;
    public static final int MAX_ROOM_ILLUMINANCE_VALUE = 4000;


    protected int getNumberOfWindows() {
        return numberOfWindows;
    }

    protected int getTotalRoomIlluminance() {
        return totalRoomIlluminance;
    }

    protected double getRoomSpace() {
        return roomSpace;
    }

    private double getMinOccupiedRoomSpace() {
        return minOccupiedRoomSpace;
    }

    private double getMaxOccupiedRoomSpace() {
        return maxOccupiedRoomSpace;
    }


    protected Room(String nameOfRoom, double roomSpace, int numberOfWindows) {
        this.roomSpace = roomSpace;
        this.numberOfWindows = numberOfWindows;
        this.nameOfRoom = nameOfRoom;
        this.totalRoomIlluminance = numberOfWindows * WINDOW_ILLUMINANCE;
    }

    protected String getNameOfRoom() {
        return nameOfRoom;
    }

    private ArrayList<Lamp> listOfLamps = new ArrayList<>();

    public void add(Lamp lamp) {
        listOfLamps.add(lamp);
        this.totalRoomIlluminance += lamp.getLampIlluminance();
    }

    /**
     * Method 'removeLamp' removes only one lamp with specified illuminance
     **/

    public void removeLamp(int lampIlluminance) {
        int n = 0;
        Iterator<Lamp> iterator = listOfLamps.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getLampIlluminance() == lampIlluminance) {
                this.totalRoomIlluminance = this.totalRoomIlluminance - lampIlluminance;
                iterator.remove();
                n++;
                break;
            }
        }
        if (n == 0) {
            throw new LampNotFoundException("There is no lamp with such illuminance!");
        }
    }

    public void removeAllLamps() {
        for (Lamp lamp : listOfLamps) {
            this.totalRoomIlluminance = this.totalRoomIlluminance - lamp.getLampIlluminance();
        }
        listOfLamps.clear();
    }

    /**
     * Method 'removeAllSpecifiedLamps' removes all lamps with specified illuminance in current room
     **/

    public void removeAllSpecifiedLamps(int lampIlluminance) {
        int n = 0;
        Iterator<Lamp> iterator = listOfLamps.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getLampIlluminance() == lampIlluminance) {
                this.totalRoomIlluminance = this.totalRoomIlluminance - lampIlluminance;
                iterator.remove();
                n++;
            }
        }
        if (n == 0) {
            throw new LampNotFoundException("There are no lamps with such illuminance!");
        }
    }

    private ArrayList<Furniture> listOfFurniture = new ArrayList<>();

    public void add(Furniture furniture) {
        listOfFurniture.add(furniture);
        this.maxOccupiedRoomSpace += furniture.getMaxSpaceInM2();
        if (furniture.getMinSpaceInM2() == furniture.getMaxSpaceInM2()) {
            this.minOccupiedRoomSpace += furniture.getMaxSpaceInM2();
        }
        if (furniture.getMinSpaceInM2() != furniture.getMaxSpaceInM2()) {
            this.minOccupiedRoomSpace += furniture.getMinSpaceInM2();
        }
    }

    /**
     * Method 'removeFurniture' removes only one item of furniture with a specified name
     **/

    public void removeFurniture(String furnitureName) {
        int n = 0;
        for (Furniture furniture : listOfFurniture) {
            if (furniture.getName().equals(furnitureName)) {
                this.maxOccupiedRoomSpace = this.maxOccupiedRoomSpace - furniture.getMaxSpaceInM2();
                if (furniture.getMinSpaceInM2() == furniture.getMaxSpaceInM2()) {
                    this.minOccupiedRoomSpace = this.minOccupiedRoomSpace - furniture.getMaxSpaceInM2();
                }
                if (furniture.getMinSpaceInM2() != furniture.getMaxSpaceInM2()) {
                    this.minOccupiedRoomSpace = this.minOccupiedRoomSpace - furniture.getMinSpaceInM2();
                }
                listOfFurniture.remove(furniture);
                n++;
                break;
            }
        }
        if (n == 0) {
            throw new FurnitureNotFoundException("Such furniture doesn't exist!");
        }
    }

    public void removeAllFurniture() {
        for (Furniture furniture : listOfFurniture) {
            this.maxOccupiedRoomSpace = this.maxOccupiedRoomSpace - furniture.getMaxSpaceInM2();
            if (furniture.getMinSpaceInM2() == furniture.getMaxSpaceInM2()) {
                this.minOccupiedRoomSpace = this.minOccupiedRoomSpace - furniture.getMaxSpaceInM2();
            }
            if (furniture.getMinSpaceInM2() != furniture.getMaxSpaceInM2()) {
                this.minOccupiedRoomSpace = this.minOccupiedRoomSpace - furniture.getMinSpaceInM2();
            }
        }
        listOfFurniture.clear();
    }

    /**
     * Method 'removeAllSpecifiedFurniture' removes all items of furniture with a specified name in current room
     **/

    public void removeAllSpecifiedFurniture(String furnitureName) {
        int n = 0;
        for (Furniture furniture : listOfFurniture) {
            if (furniture.getName().equals(furnitureName)) {
                this.maxOccupiedRoomSpace = this.maxOccupiedRoomSpace - furniture.getMaxSpaceInM2();
                if (furniture.getMinSpaceInM2() == furniture.getMaxSpaceInM2()) {
                    this.minOccupiedRoomSpace = this.minOccupiedRoomSpace - furniture.getMaxSpaceInM2();
                }
                if (furniture.getMinSpaceInM2() != furniture.getMaxSpaceInM2()) {
                    this.minOccupiedRoomSpace = this.minOccupiedRoomSpace - furniture.getMinSpaceInM2();
                }
                n++;
            }
        }
        if (n == 0) {
            throw new FurnitureNotFoundException("Such furniture doesn't exist!");
        }
    }

    protected void checkExceptions() throws ImproperLampIlluminanceValue, IncorrectFurnitureSpaceValueException, SpaceUsageTooMuchException {
        for (Lamp lamp : listOfLamps) {
            if (lamp.getLampIlluminance() <= 0) {
                throw new ImproperLampIlluminanceValue("Improper lamp illuminance value!");
            }
        }
        for (Furniture furniture : listOfFurniture) {
            if (furniture.getMinSpaceInM2() > furniture.getMaxSpaceInM2() || furniture.getMinSpaceInM2() <= 0 || furniture.getMaxSpaceInM2() <= 0) {
                throw new IncorrectFurnitureSpaceValueException("Incorrect furniture space value!");
            }
            if (this.maxOccupiedRoomSpace > 0.7 * roomSpace) {
                throw new SpaceUsageTooMuchException("Furniture takes up too much space!");
            }
        }
    }

    /**
     * Method 'validateLampAndFurniture' returns true if lamps and furniture in current room meet the requirements
     **/

    protected boolean validateLampAndFurniture() {
        for (Lamp lamp : listOfLamps) {
            if (lamp.getLampIlluminance() <= 0) {
                return false;
            }
        }
        for (Furniture furniture : listOfFurniture) {
            if (furniture.getMinSpaceInM2() > furniture.getMaxSpaceInM2() || (furniture.getMinSpaceInM2() <= 0) || (furniture.getMaxSpaceInM2() <= 0)) {
                return false;
            }
            if (this.maxOccupiedRoomSpace > 0.7 * roomSpace) {
                return false;
            }
        }
        return true;
    }


    protected void describeTotalIlluminance() {
        System.out.print("\f\fIlluminance = " + getTotalRoomIlluminance() + " lux");
        if (getNumberOfWindows() == 0) {
            System.out.print("(there are no windows");
        }
        if (getNumberOfWindows() == 1) {
            System.out.print("(1 window with 700 lux");
        }
        if (getNumberOfWindows() > 1) {
            System.out.print("(" + getNumberOfWindows() + " windows 700 lux each");
        }
        if (listOfLamps.size() == 0) {
            System.out.println(" and zero lamps)");
        }
        if (listOfLamps.size() == 1) {
            System.out.println(", only one lamp with " + listOfLamps.get(0).getLampIlluminance() + " lux)");
        }
        if (listOfLamps.size() > 1) {
            System.out.print(", lamps ");
            for (int i = 0; i < listOfLamps.size(); i++) {
                if (i != listOfLamps.size() - 1) {
                    System.out.print(listOfLamps.get(i).getLampIlluminance() + " lux, ");
                }
                if (i == listOfLamps.size() - 1) {
                    System.out.println(listOfLamps.get(i).getLampIlluminance() + " lux)");
                }
            }
        }
    }

    protected void describeRoomSpaceAndFurniture() {
        System.out.print("\f\fSpace = " + getRoomSpace() + " m^2");
        if (listOfFurniture.isEmpty()) {
            System.out.print(" (100% free)");
        }
        if (getMaxOccupiedRoomSpace() != getMinOccupiedRoomSpace()) {
            System.out.print(" (occupied " + getMinOccupiedRoomSpace() + "-" + getMaxOccupiedRoomSpace() + " m^2, guaranteed free "
                    + (getRoomSpace() - getMaxOccupiedRoomSpace()) + " m^2 or " + (1 - (getMaxOccupiedRoomSpace() / getRoomSpace())) * 100 + "% of space)");
        }
        if (getMinOccupiedRoomSpace() == getMaxOccupiedRoomSpace() && !listOfFurniture.isEmpty()) {
            System.out.print("(occupied " + getMaxOccupiedRoomSpace() + " m^2, " + "guaranteed free " + (getRoomSpace() - getMaxOccupiedRoomSpace())
                    + " m^2 or " + (1 - (getMaxOccupiedRoomSpace() / getRoomSpace())) * 100 + "% of space)");
        }
        if (listOfFurniture.isEmpty()) {
            System.out.println("\n\f\fThere is no furniture in this room");
        }
        if (!listOfFurniture.isEmpty()) {
            System.out.println("\n\f\fFurniture: ");
            for (Furniture furniture : listOfFurniture) {
                if (furniture.getMinSpaceInM2() == furniture.getMaxSpaceInM2()) {
                    System.out.println("\t" + furniture.getName() + " (space " + furniture.getMaxSpaceInM2() + " m^2)");
                } else {
                    System.out.println("\t" + furniture.getName() + " (space from " + furniture.getMinSpaceInM2() +
                            " to " + furniture.getMaxSpaceInM2() + " m^2)");
                }
            }
        }
    }
}