package module5;

public class Room {
    private String roomNumber;
    private String roomType; // e.g., "Standard", "Deluxe", "Suite"
    private double pricePerNight;
    private RoomStatus status;

    public Room(String roomNumber, String roomType, double pricePerNight) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.status = RoomStatus.AVAILABLE; // A new room is always available
    }

    // Getters
    public String getRoomNumber() { return roomNumber; }
    public String getRoomType() { return roomType; }
    public double getPricePerNight() { return pricePerNight; }
    public RoomStatus getStatus() { return status; }

    // Setter
    public void setStatus(RoomStatus status) { this.status = status; }

    @Override
    public String toString() {
        return "Room [Number='" + roomNumber + "', Type='" + roomType + 
               "', Price/Night=$" + pricePerNight + ", Status=" + status + ']';
    }
}