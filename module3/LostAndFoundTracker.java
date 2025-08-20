package module3;

import java.util.Scanner;

public class LostAndFoundTracker {
    private final LostItemList list = new LostItemList();

    public void addItem(String description, String date) {
        list.addItem(description, date);
    }

    public void displayItems() {
        list.displayItems();
    }

    public void searchItem(String keyword) {
        list.searchItem(keyword);
    }

    public void claimItem(String description) {
        list.claimItem(description);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LostAndFoundTracker tracker = new LostAndFoundTracker();
        int choice;

        do {
            System.out.println("\n--- Lost and Found Tracker ---");
            System.out.println("1. Add Item");
            System.out.println("2. View Items");
            System.out.println("3. Search Item");
            System.out.println("4. Claim Item");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            while (!sc.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                System.out.print("Enter your choice: ");
                sc.nextLine();
            }
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: {
                    System.out.print("Enter description: ");
                    String desc = sc.nextLine();
                    System.out.print("Enter date: ");
                    String date = sc.nextLine();
                    tracker.addItem(desc, date);
                    break;
                }
                case 2:
                    tracker.displayItems();
                    break;
                case 3: {
                    System.out.print("Enter keyword: ");
                    String keyword = sc.nextLine();
                    tracker.searchItem(keyword);
                    break;
                }
                case 4: {
                    System.out.print("Enter description to claim: ");
                    String claim = sc.nextLine();
                    tracker.claimItem(claim);
                    break;
                }
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 5);

        sc.close();
    }
}


class Node {
    LostItem data;
    Node next;

    Node(LostItem data) {
        this.data = data;
        this.next = null;
    }
}

class LostItemList {
    private Node head;

    public LostItemList() {
        head = null;
    }


    public void addItem(String description, String date) {
        LostItem item = new LostItem(description, date);
        Node newNode = new Node(item);

        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    // Display all items
    public void displayItems() {
        if (head == null) {
            System.out.println("No items in the list.");
            return;
        }
        Node current = head;
        while (current != null) {
            System.out.println("Description: " + current.data.getDescription() +
                    " | Date: " + current.data.getDate());
            current = current.next;
        }
    }

    // Search by keyword
    public void searchItem(String keyword) {
        Node current = head;
        boolean found = false;
        while (current != null) {
            if (current.data.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println("Found: " + current.data.getDescription() +
                        " | Date: " + current.data.getDate());
                found = true;
            }
            current = current.next;
        }
        if (!found) {
            System.out.println("No item matched your search.");
        }
    }

    // Claim (remove) an item
    public void claimItem(String description) {
        if (head == null) return;

        if (head.data.getDescription().equalsIgnoreCase(description)) {
            head = head.next; // remove first item
            System.out.println("Item claimed successfully.");
            return;
        }

        Node current = head;
        while (current.next != null) {
            if (current.next.data.getDescription().equalsIgnoreCase(description)) {
                current.next = current.next.next; // skip over the node
                System.out.println("Item claimed successfully.");
                return;
            }
            current = current.next;
        }
        System.out.println("Item not found to claim.");
    }
}
