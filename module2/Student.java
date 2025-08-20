package module2;

public class Student {
    private String studentId;
    private String studentName;
    private Course[] registeredCourses;
    private int registeredCount;

    public Student(String studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.registeredCourses = new Course[10]; // Cho phép đăng ký tối đa 10 môn
        this.registeredCount = 0;
    }

    public String getStudentId() { return studentId; }
    public String getStudentName() { return studentName; }
    public Course[] getRegisteredCourses() {
        // Trả về mảng các môn đã đăng ký (không có null)
        Course[] result = new Course[registeredCount];
        for (int i = 0; i < registeredCount; i++) {
            result[i] = registeredCourses[i];
        }
        return result;
    }
    public boolean addCourse(Course course) {
        if (registeredCount < registeredCourses.length) {
            registeredCourses[registeredCount++] = course;
            return true;
        }
        return false;
    }
    public boolean removeCourse(String courseCode) {
        for (int i = 0; i < registeredCount; i++) {
            if (registeredCourses[i].getCourseCode().equalsIgnoreCase(courseCode)) {
                // Dịch trái các phần tử sau
                for (int j = i; j < registeredCount - 1; j++) {
                    registeredCourses[j] = registeredCourses[j + 1];
                }
                registeredCourses[--registeredCount] = null;
                return true;
            }
        }
        return false;
    }
}
