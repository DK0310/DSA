import java.util.Scanner;



public class Main {
    // Use Course and Student classes from their own files
    private CourseManager courseManager;
    private final Scanner scanner;

    public Main() {
        scanner = new Scanner(System.in);
        courseManager = new CourseManager();
        // Remove addCourse calls, handled in CourseManager constructor
    }

    public String getModuleName() {
        return "Course Registration Assistant";
    }

    public void initialize() {
        System.out.println("Welcome to " + getModuleName() + "!");
        selectOrRegisterStudent();
    }

    private void selectOrRegisterStudent() {
        while (true) {
            System.out.println("\nStudent Menu:");
            if (courseManager.getStudentCount() == 0) {
                System.out.println("No students found. Please register a new student.");
                registerStudent();
                return;
            }
            for (int i = 0; i < courseManager.getStudentCount(); i++) {
                Student student = courseManager.getStudents()[i];
                System.out.println((i + 1) + ". " + student.getStudentName() + " (ID: " + student.getStudentId() + ")");
            }
            System.out.println((courseManager.getStudentCount() + 1) + ". Register new student");
            System.out.print("Select a student or register new (1-" + (courseManager.getStudentCount() + 1) + "): ");
            String input = scanner.nextLine().trim();
            try {
                int choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= courseManager.getStudentCount()) {
                    courseManager.setCurrentStudent(courseManager.getStudents()[choice - 1]);
                    return;
                } else if (choice == courseManager.getStudentCount() + 1) {
                    registerStudent();
                    return;
                } else {
                    System.out.println("Invalid choice. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Try again.");
            }
        }
    }

    // Make these methods public for MainSystem
    public void displayAllCourses() {
        Course[] courses = courseManager.getCourses();
        if (courses.length == 0) {
            System.out.println("No courses available.");
            return;
        }
        System.out.println("\nAvailable Courses:");
        System.out.println("--------------------------------------------------");
        System.out.printf("%-10s %-30s %-10s %-15s\n", "Code", "Name", "Credits", "Availability");
        System.out.println("--------------------------------------------------");
        for (Course course : courses) {
            System.out.printf("%-10s %-30s %-10d %d/%d\n", course.getCourseCode(), course.getCourseName(), course.getCredits(), course.getEnrolled(), course.getCapacity());
        }
        System.out.println("--------------------------------------------------");
    }

    public void displayStudentCourses(String studentId) {
        Student student = courseManager.findStudentById(studentId);
        if (student == null) {
            System.out.println("Student with ID " + studentId + " not found.");
            return;
        }
        Course[] regCourses = student.getRegisteredCourses();
        boolean hasCourse = false;
        for (Course course : regCourses) {
            if (course != null) {
                hasCourse = true;
                break;
            }
        }
        if (!hasCourse) {
            System.out.println(student.getStudentName() + " (ID: " + studentId + ") has no registered courses.");
            return;
        }
        System.out.println("\nRegistered Courses for " + student.getStudentName() + " (ID: " + studentId + "):");
        System.out.println("--------------------------------------------------");
        System.out.printf("%-10s %-30s %-10s\n", "Code", "Name", "Credits");
        System.out.println("--------------------------------------------------");
        for (Course course : regCourses) {
            if (course != null)
                System.out.printf("%-10s %-30s %-10d\n", course.getCourseCode(), course.getCourseName(), course.getCredits());
        }
        System.out.println("--------------------------------------------------");
    }

    public void displayStudentsInCourse() {
        System.out.print("Enter course code to view students: ");
        String courseCode = scanner.nextLine().trim();
        Course course = courseManager.findCourseByCode(courseCode);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }
        Student[] students = courseManager.getStudents();
        Student[] enrolledStudents = new Student[students.length];
        int enrolledCount = 0;
        for (Student student : students) {
            for (Course regCourse : student.getRegisteredCourses()) {
                if (regCourse != null && regCourse.getCourseCode().equalsIgnoreCase(courseCode)) {
                    enrolledStudents[enrolledCount++] = student;
                    break;
                }
            }
        }
        if (enrolledCount == 0) {
            System.out.println("No students registered in this course.");
            return;
        }
        // Sort enrolledStudents by name
        for (int i = 0; i < enrolledCount - 1; i++) {
            for (int j = i + 1; j < enrolledCount; j++) {
                if (enrolledStudents[i].getStudentName().compareToIgnoreCase(enrolledStudents[j].getStudentName()) > 0) {
                    Student temp = enrolledStudents[i];
                    enrolledStudents[i] = enrolledStudents[j];
                    enrolledStudents[j] = temp;
                }
            }
        }
        System.out.println("\nStudents registered in " + course.getCourseName() + " (" + course.getCourseCode() + "):");
        System.out.println("---------------------------------------------");
        System.out.printf("%-30s %-10s\n", "Name", "ID");
        System.out.println("---------------------------------------------");
        for (int i = 0; i < enrolledCount; i++) {
            System.out.printf("%-30s %-10s\n", enrolledStudents[i].getStudentName(), enrolledStudents[i].getStudentId());
        }
        System.out.println("---------------------------------------------");
    }

    public void displayLibrary() {
        Student[] students = courseManager.getStudents();
        if (students.length == 0) {
            System.out.println("No students registered.");
            return;
        }
        courseManager.sortStudentsByName();
        System.out.println("\nLibrary of Registered Students (Sorted by Name):");
        System.out.println("--------------------------------------------------");
        for (Student student : students) {
            System.out.println(student.getStudentName() + " (ID: " + student.getStudentId() + ")");
        }
        System.out.println("--------------------------------------------------");
    }

    public String getCurrentStudentId() {
        Student current = courseManager.getCurrentStudent();
        return current != null ? current.getStudentId() : null;
    }

    // Implement required interface methods as empty (for compatibility)
    public void displayMenu() {
        System.out.println("\n" + getModuleName() + " Menu:");
        System.out.println("1. Display all available courses");
        System.out.println("2. Register for a course");
        System.out.println("3. Drop a course");
        System.out.println("4. Search for courses");
        System.out.println("5. Display my registered courses");
        System.out.println("6. Return to main menu");
        System.out.println("7. Display students in a course");
        System.out.println("8. Display library of registered students (sorted)");
    }

    public void processChoice(String choice) {
        switch (choice) {
            case "1":
                displayAllCourses();
                break;
            case "2":
                System.out.print("Enter course code to register: ");
                String regCode = scanner.nextLine().trim();
                updateData(regCode, "register");
                break;
            case "3":
                System.out.print("Enter course code to drop: ");
                String dropCode = scanner.nextLine().trim();
                updateData(dropCode, "drop");
                break;
            case "4":
                System.out.print("Enter search query (course code or name): ");
                String query = scanner.nextLine().trim();
                searchData(query);
                break;
            case "5":
                displayStudentCourses(getCurrentStudentId());
                break;
            case "6":
                break;
            case "7":
                displayStudentsInCourse();
                break;
            case "8":
                displayLibrary();
                break;
            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 8.");
        }
    }

    public void run() {
        initialize();
        boolean running = true;
        while (running) {
            displayMenu();
            System.out.print("Enter your choice (1-8): ");
            String choice = scanner.nextLine().trim();
            if ("6".equals(choice)) {
                running = false;
            } else {
                processChoice(choice);
            }
        }
    }

    public boolean updateData(String code, String action) {
        Student current = courseManager.getCurrentStudent();
        if (current == null) return false;
        if ("register".equalsIgnoreCase(action)) {
            boolean result = courseManager.registerCourseForStudent(current.getStudentId(), code);
            if (result) {
                System.out.println("Successfully registered for course.");
            } else {
                System.out.println("Registration failed.");
            }
            return result;
        } else if ("drop".equalsIgnoreCase(action)) {
            boolean result = courseManager.dropCourseForStudent(current.getStudentId(), code);
            if (result) {
                System.out.println("Successfully dropped course.");
            } else {
                System.out.println("Drop failed.");
            }
            return result;
        }
        System.out.println("Invalid action. Use 'register' or 'drop'.");
        return false;
    }

    public void searchData(String query) {
        Course[] courses = courseManager.getCourses();
        if (courses.length == 0) {
            System.out.println("No courses available.");
            return;
        }
        boolean found = false;
        System.out.println("\nSearch Results for '" + query + "':");
        System.out.println("--------------------------------------------------");
        System.out.printf("%-10s %-30s %-10s %-15s\n", "Code", "Name", "Credits", "Availability");
        System.out.println("--------------------------------------------------");
        for (Course course : courses) {
            if (course.getCourseCode().toLowerCase().contains(query.trim().toLowerCase()) ||
                    course.getCourseName().toLowerCase().contains(query.trim().toLowerCase())) {
                System.out.printf("%-10s %-30s %-10d %d/%d\n", course.getCourseCode(), course.getCourseName(), course.getCredits(), course.getEnrolled(), course.getCapacity());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No courses found matching '" + query + "'.");
        }
        System.out.println("--------------------------------------------------");
    }

    public boolean hasData(String code) {
        return courseManager.findCourseByCode(code) != null;
    }

    public String getDataDetails(String code) {
        Course course = courseManager.findCourseByCode(code);
        if (course != null) {
            return String.format("Code: %s, Name: %s, Credits: %d, Availability: %d/%d",
                    course.getCourseCode(), course.getCourseName(), course.getCredits(), course.getEnrolled(), course.getCapacity());
        }
        return "Course not found.";
    }

    private void registerStudent() {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine().trim();
        System.out.print("Enter student name: ");
        String studentName = scanner.nextLine().trim();
        courseManager.addStudent(studentId, studentName);
        System.out.println("Student " + studentName + " (ID: " + studentId + ") registered successfully.");
    }

    public static void main(String[] args) {
        Main mainSystem = new Main();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nSmart Campus Services System Menu:");
            System.out.println("1. Course Registration Assistant");
            System.out.println("2. Exit");
            System.out.print("Enter your choice (1-2): ");
            String choiceStr = scanner.nextLine().trim();
            try {
                int choice = Integer.parseInt(choiceStr);
                if (choice == 2) {
                    System.out.println("Exiting Smart Campus Services System...");
                    scanner.close();
                    break;
                } else if (choice == 1) {
                    mainSystem.run();
                } else {
                    System.out.println("Invalid choice. Please enter 1 or 2.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
}
