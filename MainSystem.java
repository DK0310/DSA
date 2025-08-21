import module2.Main;
import module1.dlsFunction;
import module1.idls;
import module3.LostAndFoundTracker;
import module4.CampusCalendar;

import java.util.Scanner;

public class MainSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        idls dlsService = new dlsFunction();  // Module 1: Digital Library System
        Main module2 = new Main();            // Module 2: Course Registration Assistant
        LostAndFoundTracker module3 = new LostAndFoundTracker(); // Module 3: Lost And Found Tracker
        CampusCalendar module4 = new CampusCalendar(); // Module 4: Campus Event Calendar

        while (running) {
            System.out.println("=== MAIN MENU ===");
            System.out.println("0. Exit");
            System.out.println("1. Digital Library System");
            System.out.println("2. " + module2.getModuleName());
            System.out.println("3. LostAndFoundTracker");
            System.out.println("4. Campus Event Calendar");
            System.out.println("5. Module 5 (placeholder)");
            System.out.print("Select an option: ");

            String raw = sc.nextLine().trim();
            int choice;
            try {
                choice = Integer.parseInt(raw);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.\n");
                continue;
            }

            switch (choice) {
                case 0:
                    running = false;
                    System.out.println("Goodbye!");
                    break;

                case 1: { // === MODULE 1: DLS ===
                    boolean inDLS = true;
                    while (inDLS) {
                        System.out.println("=== Digital Library System ===");
                        System.out.println("0. Back to Main Menu");
                        System.out.println("1. Add new book");
                        System.out.println("2. Search book by title");
                        System.out.println("3. Search book by author");
                        System.out.println("4. Borrow book");
                        System.out.println("5. Return book");
                        System.out.println("6. Display all books");
                        System.out.println("7. Delete book");
                        System.out.print("Select an option: ");

                        String sel = sc.nextLine().trim();
                        int c;
                        try {
                            c = Integer.parseInt(sel);
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a valid number.\n");
                            continue;
                        }

                        switch (c) {
                            case 0:
                                inDLS = false;
                                break;
                            case 1:
                                dlsService.addBook();
                                break;
                            case 2:
                                dlsService.searchBookTitle();
                                break;
                            case 3:
                                dlsService.searchBookAuthor();
                                break;
                            case 4:
                                dlsService.borrowBook();
                                break;
                            case 5:
                                dlsService.returnBook();
                                break;
                            case 6:
                                dlsService.displayBook();
                                break;
                            case 7:
                                dlsService.deleteBook();
                                break;
                            default:
                                System.out.println("Please enter a valid choice");
                        }
                        System.out.println();
                    }
                    break;
                }

                case 2: {

                    module2.run();
                    break;
                }

                case 3: {
                    boolean inLF = true;
                    while (inLF) {
                        System.out.println("=== Lost & Found Tracker ===");
                        System.out.println("0. Back to Main Menu");
                        System.out.println("1. Add Item");
                        System.out.println("2. View Items");
                        System.out.println("3. Search Item");
                        System.out.println("4. Claim Item");
                        System.out.print("Select an option: ");

                        String sel = sc.nextLine().trim();
                        int c;
                        try {
                            c = Integer.parseInt(sel);
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a valid number.\n");
                            continue;
                        }

                        switch (c) {
                            case 0:
                                inLF = false;
                                break;
                            case 1:
                                System.out.print("Enter description: ");
                                String desc = sc.nextLine();
                                System.out.print("Enter date: ");
                                String date = sc.nextLine();
                                module3.addItem(desc, date);
                                break;
                            case 2:
                                module3.displayItems();
                                break;
                            case 3:
                                System.out.print("Enter keyword: ");
                                String keyword = sc.nextLine();
                                module3.searchItem(keyword);
                                break;
                            case 4:
                                System.out.print("Enter description to claim: ");
                                String claim = sc.nextLine();
                                module3.claimItem(claim);
                                break;
                            default:
                                System.out.println("Please enter a valid choice");
                        }
                        System.out.println();
                    }
                    break;
                }


                case 4: {
                    boolean inCC = true;
                    while (inCC) {
                        System.out.println("\n=== Campus Event Calendar ===");
                        System.out.println("0. Back to Main Menu");
                        System.out.println("1. Add Event");
                        System.out.println("2. View All Events");
                        System.out.println("3. Search Events by Date");
                        System.out.print("Choose: ");

                        String sel = sc.nextLine().trim();
                        int c;
                        try {
                            c = Integer.parseInt(sel);
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a valid number.\n");
                            continue;
                        }

                        switch (c) {
                            case 0:
                                inCC = false;
                                break;
                            case 1:
                                module4.addEvent();
                                break;
                            case 2:
                                module4.viewEvents();
                                break;
                            case 3:
                                module4.searchEvents();
                                break;
                            default:
                                System.out.println("Please enter a valid choice\n");
                        }
                    }
                    break;
                }


                case 5:
                    System.out.println("[Module 5] Hook here.\n");
                    break;

                    default:
                        System.out.println("Please enter a valid choice\n");
            }
        }
    }
}

