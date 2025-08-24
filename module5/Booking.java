package module5;
import java.time.LocalDate;
import java.util.UUID;

public class Booking {
    private String bookingId;
    private String roomNumber;
    private String guestName;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    public Booking(String roomNumber, String guestName, LocalDate checkInDate, LocalDate checkOutDate) {
        this.bookingId = UUID.randomUUID().toString(); // Generate a unique ID
        this.roomNumber = roomNumber;
        this.guestName = guestName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    // Getters
    public String getBookingId() { return bookingId; }
    public String getRoomNumber() { return roomNumber; }
    public String getGuestName() { return guestName; }

    @Override
    public String toString() {
        return "Booking [ID='" + bookingId + "', Room='" + roomNumber + 
               "', Guest='" + guestName + "', Check-in=" + checkInDate + 
               ", Check-out=" + checkOutDate + ']';
    }
}