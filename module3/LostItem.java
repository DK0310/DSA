package module3;

public class LostItem {
    private String description;
    private String date;

    public LostItem(String description, String date) {
        this.description = description;
        this.date = date;
    }

    public String getDescription() { return description; }
    public String getDate() { return date; }
}