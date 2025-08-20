package module2;

import java.util.*;

public class CourseManager {
    private Course[] courses = new Course[100];
    private int courseCount = 0;
    private Student[] students = new Student[100];
    private int studentCount = 0;
    private Student currentStudent;

    public CourseManager() {
        addCourse("COMP1843", "Principles of Security", 3, 30);
        addCourse("1649A", "Data Structure & Algorithms", 3, 30);
        addCourse("COMP1752", "Object Oriented Programming", 3, 30);
    }

    public void addCourse(String courseCode, String courseName, int credits, int capacity) {
        if (courseCount < courses.length) {
            courses[courseCount++] = new Course(courseCode, courseName, credits, capacity);
        }
    }

    public Course[] getCourses() {
        return Arrays.copyOf(courses, courseCount);
    }

    public Student[] getStudents() {
        return Arrays.copyOf(students, studentCount);
    }

    public Student getCurrentStudent() {
        return currentStudent;
    }

    public void setCurrentStudent(Student student) {
        currentStudent = student;
    }

    public void addStudent(String studentId, String studentName) {
        if (studentCount < students.length) {
            Student newStudent = new Student(studentId, studentName);
            students[studentCount++] = newStudent;
            sortStudentsByName();
            currentStudent = newStudent;
        }
    }

    public void sortStudentsByName() {
        for (int i = 0; i < studentCount - 1; i++) {
            for (int j = i + 1; j < studentCount; j++) {
                if (students[i].getStudentName().compareToIgnoreCase(students[j].getStudentName()) > 0) {
                    Student temp = students[i];
                    students[i] = students[j];
                    students[j] = temp;
                }
            }
        }
    }

    public boolean registerCourseForStudent(String studentId, String courseCode) {
        for (int i = 0; i < studentCount; i++) {
            Student student = students[i];
            if (student.getStudentId().equalsIgnoreCase(studentId.trim())) {
                for (int j = 0; j < courseCount; j++) {
                    Course course = courses[j];
                    if (course.getCourseCode().equalsIgnoreCase(courseCode.trim())) {
                        if (course.getEnrolled() < course.getCapacity()) {
                            // Kiểm tra trùng môn
                            for (Course regCourse : student.getRegisteredCourses()) {
                                if (regCourse.getCourseCode().equalsIgnoreCase(courseCode.trim())) {
                                    return false;
                                }
                            }
                            if (student.addCourse(course)) {
                                course.enroll();
                                return true;
                            } else {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    }
                }
                return false;
            }
        }
        return false;
    }

    public boolean dropCourseForStudent(String studentId, String courseCode) {
        for (int i = 0; i < studentCount; i++) {
            Student student = students[i];
            if (student.getStudentId().equalsIgnoreCase(studentId.trim())) {
                for (int j = 0; j < courseCount; j++) {
                    Course course = courses[j];
                    if (course.getCourseCode().equalsIgnoreCase(courseCode.trim())) {
                        if (course.getEnrolled() > 0) {
                            Course[] regCourses = student.getRegisteredCourses();
                            int toRemoveIdx = -1;
                            for (int k = 0; k < regCourses.length; k++) {
                                Course regCourse = regCourses[k];
                                if (regCourse != null && regCourse.getCourseCode().equalsIgnoreCase(courseCode.trim())) {
                                    toRemoveIdx = k;
                                    break;
                                }
                            }
                            if (toRemoveIdx != -1) {
                                for (int k = toRemoveIdx; k < regCourses.length - 1; k++) {
                                    regCourses[k] = regCourses[k + 1];
                                }
                                regCourses[regCourses.length - 1] = null;
                                course.drop();
                                return true;
                            } else {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    }
                }
                return false;
            }
        }
        return false;
    }

    public Course findCourseByCode(String code) {
        for (int i = 0; i < courseCount; i++) {
            if (courses[i].getCourseCode().equalsIgnoreCase(code.trim())) {
                return courses[i];
            }
        }
        return null;
    }

    public Student findStudentById(String studentId) {
        for (int i = 0; i < studentCount; i++) {
            if (students[i].getStudentId().equalsIgnoreCase(studentId.trim())) {
                return students[i];
            }
        }
        return null;
    }

    public int getStudentCount() {
        return studentCount;
    }
}
