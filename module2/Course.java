package module2;
public class Course {
    private String courseCode;
    private String courseName;
    private int credits;
    private int capacity;
    private int enrolled;

    public Course(String courseCode, String courseName, int credits, int capacity) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.credits = credits;
        this.capacity = capacity;
        this.enrolled = 0;
    }

    public String getCourseCode() { return courseCode; }
    public String getCourseName() { return courseName; }
    public int getCredits() { return credits; }
    public int getCapacity() { return capacity; }
    public int getEnrolled() { return enrolled; }
    public void enroll() { enrolled++; }
    public void drop() { if (enrolled > 0) enrolled--; }
}

