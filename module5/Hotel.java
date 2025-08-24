package module5;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Hotel {
    private Map<String, Room> rooms;
    private List<Booking> bookings;

    public Hotel() {
        this.rooms = new HashMap<>();
        this.bookings = new ArrayList<>();
    }

    // Add a new room to the hotel
    public void addRoom(Room room) {
        rooms.put(room.getRoomNumber(), room);
        System.out.println("Room " + room.getRoomNumber() + " added successfully.");
    }

    // Handle guest check-in
    public void checkIn(String roomNumber, String guestName, LocalDate checkOutDate) {
        Room room = rooms.get(roomNumber);
        if (room == null) {
            System.out.println("Error: Room " + roomNumber + " does not exist.");
            return;
        }

        if (room.getStatus() == RoomStatus.AVAILABLE) {
            room.setStatus(RoomStatus.OCCUPIED);
            Booking newBooking = new Booking(roomNumber, guestName, LocalDate.now(), checkOutDate);
            bookings.add(newBooking);
            System.out.println("Guest " + guestName + " has checked into room " + roomNumber + ".");
        } else {
            System.out.println("Check-in failed. Room is currently: " + room.getStatus());
        }
    }

    // Handle guest check-out
    public void checkOut(String roomNumber) {
        Room room = rooms.get(roomNumber);
        if (room == null) {
            System.out.println("Error: Room " + roomNumber + " does not exist.");
            return;
        }

        if (room.getStatus() == RoomStatus.OCCUPIED) {
            room.setStatus(RoomStatus.AVAILABLE);
            // Remove the corresponding booking record
            bookings.removeIf(booking -> booking.getRoomNumber().equals(roomNumber));
            System.out.println("Room " + roomNumber + " has been checked out.");
        } else {
            System.out.println("Check-out failed. Room is not occupied. Current status: " + room.getStatus());
        }
    }

    // Find all rooms that are currently available
    public void findAvailableRooms() {
        System.out.println("\n--- Available Rooms ---");
        List<Room> availableRooms = rooms.values().stream()
                .filter(room -> room.getStatus() == RoomStatus.AVAILABLE)
                .collect(Collectors.toList());

        if (availableRooms.isEmpty()) {
            System.out.println("No rooms are currently available.");
        } else {
            availableRooms.forEach(System.out::println);
        }
    }

    // Display all rooms and their status
    public void displayAllRooms() {
        System.out.println("\n--- All Hotel Rooms ---");
        if (rooms.isEmpty()) {
            System.out.println("No rooms have been added to the hotel yet.");
            return;
        }
        rooms.values().forEach(System.out::println);
    }

    // View all current bookings
    public void viewBookings() {
        System.out.println("\n--- Current Bookings ---");
        if (bookings.isEmpty()) {
            System.out.println("There are no active bookings.");
            return;
        }
        bookings.forEach(System.out::println);
    }
}