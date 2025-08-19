package module2;
public class Student {
    private String studentId;
    private String studentName;
    private Course[] registeredCourses;

    public Student(String studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.registeredCourses = new Course[0];
    }

    public String getStudentId() { return studentId; }
    public String getStudentName() { return studentName; }
    public Course[] getRegisteredCourses() { return registeredCourses; }
    // Add methods to add/remove courses if needed
}
