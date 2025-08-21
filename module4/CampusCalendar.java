package module4;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

class Event {
    private String title;
    private String description;
    private LocalDate date;

    public Event(String title, String description, LocalDate date) {
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return date.format(formatter) + " - " + title + ": " + description;
    }
}

class EventList {
    private Event[] events;
    private int size;

    public EventList() {
        events = new Event[10];
        size = 0;
    }

    public void add(Event e) {
        if (size == events.length) {
            Event[] newEvents = new Event[events.length * 2];
            for (int i = 0; i < events.length; i++) {
                newEvents[i] = events[i];
            }
            events = newEvents;
        }
        events[size++] = e;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public Event get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        return events[index];
    }
}

public class CampusCalendar {
    private static EventList events = new EventList();
    private static Scanner scanner = new Scanner(System.in);
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n=== Campus Event Calendar ===");
            System.out.println("1. Add Event");
            System.out.println("2. View All Events");
            System.out.println("3. Search Events by Date");
            System.out.println("0. Exit");
            System.out.print("Choose: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addEvent();
                case 2 -> viewEvents();
                case 3 -> searchEvents();
                case 0 -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    public static void addEvent() {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        System.out.print("Enter date (DD-MM-YYYY): ");
        String dateStr = scanner.nextLine();

        try {
            LocalDate date = LocalDate.parse(dateStr, formatter);
            events.add(new Event(title, description, date));
            System.out.println("Event added!");
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use DD-MM-YYYY.");
        }
    }

    public static void viewEvents() {
        if (events.isEmpty()) {
            System.out.println("No events yet.");
        } else {
            System.out.println("\nAll Events:");
            for (int i = 0; i < events.size(); i++) {
                System.out.println(events.get(i));
            }
        }
    }

    public static void searchEvents() {
        System.out.print("Enter date to search (DD-MM-YYYY): ");
        String dateStr = scanner.nextLine();

        try {
            LocalDate date = LocalDate.parse(dateStr, formatter);

            System.out.println("\nEvents on " + date.format(formatter) + ":");
            boolean found = false;
            for (int i = 0; i < events.size(); i++) {
                if (events.get(i).getDate().equals(date)) {
                    System.out.println(events.get(i));
                    found = true;
                }
            }
            if (!found) {
                System.out.println("No events found on this date.");
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use DD-MM-YYYY.");
        }
    }
}
