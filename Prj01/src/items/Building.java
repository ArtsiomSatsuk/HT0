package items;

import customException.*;

import java.util.ArrayList;

import static items.Room.MAX_ROOM_ILLUMINANCE_VALUE;
import static items.Room.MIN_ROOM_ILLUMINANCE_VALUE;

public class Building {

    private String buildingName;

    private String getBuildingName() {
        return buildingName;
    }

    public Building(String buildingName) {
        this.buildingName = buildingName;
    }

    private ArrayList<Room> listOfRooms = new ArrayList<>();

    public void addRoom(String nameOfRoom, double roomSpace, int numberOfWindows) {
        for (Room room : listOfRooms) {
            if (room.getNameOfRoom().equals(nameOfRoom)) {
                throw new RoomNameMatchException("This room already exists!");
            }
        }
        listOfRooms.add(new Room(nameOfRoom, roomSpace, numberOfWindows));

    }

    public void removeRoom(String name) {
        int n = 0;
        for (int i = 0; i < listOfRooms.size(); i++) {
            Room room = listOfRooms.get(i);
            if (room.getNameOfRoom().equals(name)) {
                listOfRooms.remove(room);
                n++;
            }
        }
        if (n == 0) {
            throw new RoomNotFoundException("This room doesn't exist!");
        }
    }

    public Room getRoom(String nameOfRoom) {
        Room currentRoom = null;
        int n = 0;
        for (Room room : listOfRooms) {
            if (room.getNameOfRoom().equals(nameOfRoom)) {
                currentRoom = room;
                n++;
            }
        }
        if (n != 1) {
            throw new RoomNotFoundException("This room doesn't exist!");
        }
        return currentRoom;
    }

    /**
     * Method 'validate' returns true, if building meets the requirements
     **/

    public boolean validate() {
        for (Room room : listOfRooms) {
            if (room.getTotalRoomIlluminance() < MIN_ROOM_ILLUMINANCE_VALUE) {
                return false;
            }
            if (room.getTotalRoomIlluminance() > MAX_ROOM_ILLUMINANCE_VALUE) {
                return false;
            }
            if (room.getRoomSpace() <= 0) {
                return false;
            }
            if (room.getNumberOfWindows() < 0) {
                return false;
            }
            if (room.validateLampAndFurniture() != true) {
                return false;
            }
        }
        return true;
    }

    public void checkRoomExceptions() {
        for (Room room : listOfRooms) {
            if (room.getTotalRoomIlluminance() < MIN_ROOM_ILLUMINANCE_VALUE) {
                throw new NotEnoughIlluminanceException("Not enough illuminance! - " + room.getNameOfRoom());
            }
            if (room.getTotalRoomIlluminance() > MAX_ROOM_ILLUMINANCE_VALUE) {
                throw new IlluminanceTooMuchException("Too much illuminance! - " + room.getNameOfRoom());
            }
            if (room.getRoomSpace() <= 0) {
                throw new ImproperRoomSpaceValueException("Incorrect room space value! - " + room.getNameOfRoom());
            }
            if (room.getNumberOfWindows() < 0) {
                throw new IncorrectAmountOfWindowsException("Incorrect number of windows! - " + room.getNameOfRoom());
            }
            room.checkExceptions();
        }
    }


    public void describe() {
        checkRoomExceptions();
        System.out.println(getBuildingName());
        for (int i = 0; i < listOfRooms.size(); i++) {
            Room room = listOfRooms.get(i);
            System.out.println("\f" + (i + 1) + " room - " + room.getNameOfRoom());
            room.describeTotalIlluminance();
            room.describeRoomSpaceAndFurniture();
        }
    }
}