import java.util.Scanner;

public class dlsFunction implements idls {
    Scanner scanner = new Scanner(System.in);

    public static int MAX = 1000;
    String[] titles   = new String[MAX];
    String[] authors  = new String[MAX];
    int[] total    = new int[MAX];
    int[] available= new int[MAX];
    boolean[] active   = new boolean[MAX];
    int size = 0;


    String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = scanner.nextLine().trim();
            try { return Integer.parseInt(s); }
            catch (NumberFormatException e) { System.out.println("Please enter a valid integer."); }
        }
    }

    boolean exists(int id) {
        return id >= 0 && id < size && active[id];
    }

    int findFreeSlot() {
        for (int i = 0; i < size; i++) if (!active[i]) return i;
        return (size < MAX) ? size : -1;
    }

    void shrinkTail() {
        while (size > 0 && !active[size - 1]) size--;
    }

    void printRow(int id) {
        String status = (available[id] > 0) ? "Available" : "Unavailable";
        System.out.println("#" + id + " | '" + titles[id] + "' - " + authors[id]
                + " | copies: " + available[id] + "/" + total[id] + " | status: " + status);
    }


    @Override
    public void addBook() {
        int slot = findFreeSlot();
        if (slot == -1) {
            System.out.println("Storage is full. Cannot add more books.");
            return;
        }
        String title  = readLine("Title: ");
        String author = readLine("Author: ");
        int c = readInt("Number of copies (>=1): ");
        if (c < 1) c = 1;

        titles[slot]    = title;
        authors[slot]   = author;
        total[slot]     = c;
        available[slot] = c;
        active[slot]    = true;

        if (slot == size) size++;
        System.out.println("Added with ID: " + slot);
    }

    @Override
    public void searchBookTitle() {
        String q = readLine("Enter exact title: ");
        int found = 0;
        for (int i = 0; i < size; i++) {
            if (active[i] && titles[i].equals(q)) {
                printRow(i);
                found++;
            }
        }
        if (found == 0) System.out.println("No book found with that title.");
    }

    @Override
    public void searchBookAuthor() {
        String q = readLine("Enter exact author name: ");
        int found = 0;
        for (int i = 0; i < size; i++) {
            if (active[i] && authors[i].equals(q)) {
                printRow(i);
                found++;
            }
        }
        if (found == 0) System.out.println("No book found with that author.");
    }

    @Override
    public void borrowBook() {
        int id = readInt("Enter book ID to borrow: ");
        if (!exists(id)) { System.out.println("Invalid ID."); return; }

        if (available[id] <= 0) {
            System.out.println("Book unavailable.");
            return;
        }

        available[id] -= 1;
        System.out.println("Borrowed successfully.");
        printRow(id);
    }

    @Override
    public void returnBook() {
        int id = readInt("Enter book ID to return: ");
        if (!exists(id)) { System.out.println("Invalid ID."); return; }

        if (available[id] >= total[id]) {
            System.out.println("No outstanding borrowed copy to return.");
            return;
        }

        available[id] += 1; // trả = đưa 1 bản về kho
        System.out.println("Returned. Thank you!");
        printRow(id);
    }

    @Override
    public void displayBook() {
        boolean any = false;
        for (int i = 0; i < size; i++) if (active[i]) { any = true; break; }
        if (!any) { System.out.println("(empty)"); return; }

        System.out.println("-- All Books --");
        for (int i = 0; i < size; i++) if (active[i]) printRow(i);
    }

    @Override
    public void deleteBook() {
        int id = readInt("Enter book ID to delete: ");
        if (!exists(id)) {
            System.out.println("Invalid ID.");
            return;
        }

        if (available[id] < total[id]) {
            System.out.println("Cannot delete. Some copies are still borrowed.");
            return;
        }

        active[id]    = false;
        titles[id]    = null;
        authors[id]   = null;
        total[id]     = 0;
        available[id] = 0;

        if (id == size - 1) shrinkTail();
        System.out.println("Deleted book ID: " + id);
    }
}
