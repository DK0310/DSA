package module5;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        Scanner scanner = new Scanner(System.in);

        // Add some sample rooms
        hotel.addRoom(new Room("101", "Standard Single", 80.0));
        hotel.addRoom(new Room("102", "Standard Double", 120.0));
        hotel.addRoom(new Room("201", "Deluxe Suite", 250.0));

        int choice = 0;
        while (choice != 7) {
            System.out.println("\n===== HOTEL MANAGEMENT SYSTEM =====");
            System.out.println("1. Check-In Guest");
            System.out.println("2. Check-Out Guest");
            System.out.println("3. Find Available Rooms");
            System.out.println("4. View All Rooms");
            System.out.println("5. View All Bookings");
            System.out.println("6. Add a New Room");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("Enter Room Number to Check-In: ");
                        String roomIn = scanner.nextLine();
                        System.out.print("Enter Guest Name: ");
                        String guestName = scanner.nextLine();
                        System.out.print("Enter Check-Out Date (yyyy-mm-dd): ");
                        LocalDate checkOutDate = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ISO_LOCAL_DATE);
                        hotel.checkIn(roomIn, guestName, checkOutDate);
                        break;
                    case 2:
                        System.out.print("Enter Room Number to Check-Out: ");
                        String roomOut = scanner.nextLine();
                        hotel.checkOut(roomOut);
                        break;
                    case 3:
                        hotel.findAvailableRooms();
                        break;
                    case 4:
                        hotel.displayAllRooms();
                        break;
                    case 5:
                        hotel.viewBookings();
                        break;
                    case 6:
                        System.out.print("Enter new Room Number: ");
                        String newNum = scanner.nextLine();
                        System.out.print("Enter Room Type (e.g., Standard): ");
                        String newType = scanner.nextLine();
                        System.out.print("Enter Price per Night: ");
                        double newPrice = Double.parseDouble(scanner.nextLine());
                        hotel.addRoom(new Room(newNum, newType, newPrice));
                        break;
                    case 7:
                        System.out.println("Exiting the system. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please check your data and try again. " + e.getMessage());
            }
        }
        scanner.close();
    }
}