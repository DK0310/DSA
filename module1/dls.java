package module1;

import java.util.Scanner;

public class dls {
    static boolean ordering = true;

    public static void menu() {
        System.out.println("=== Digital Library System ==="
                + "\n0. Exit"
                + "\n1. Add new book"
                + "\n2. Search book by title"
                + "\n3. Search book by author"
                + "\n4. Borrow book"
                + "\n5. Return book"
                + "\n6. Display all books"
                + "\n7. Delete book");
        System.out.print("Select an option: ");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        idls service = new dlsFunction();

        do {
            menu();
            String raw = sc.nextLine().trim();
            int choice;
            try {
                choice = Integer.parseInt(raw);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 0:
                    ordering = false;
                    System.out.println("Goodbye!");
                    break;
                case 1:
                    service.addBook();
                    break;
                case 2:
                    service.searchBookTitle();
                    break;
                case 3:
                    service.searchBookAuthor();
                    break;
                case 4:
                    service.borrowBook();
                    break;
                case 5:
                    service.returnBook();
                    break;
                case 6:
                    service.displayBook();
                    break;
                case 7:
                    service.deleteBook();
                    break;
                default:
                    System.out.println("Please enter a valid choice");
                    break;
            }
        } while (ordering);
    }
}
