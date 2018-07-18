import javax.swing.*;
import java.util.ArrayList;

public class main {
    private JTable table1;
    public static ArrayList<Student> students;
    public static ArrayList<Course> courses;
    public static ArrayList<Schedule> schedules;
    public static DBHelper db;

    public static void main(String[] args) {
        db = new DBHelper();
        students = DBHelper.getAllStudents();
        courses = DBHelper.getAllCourses();
        schedules = DBHelper.getAllSchedules();

        for (Student s : students) {
            System.out.println(s.getLastName() +", "+ s.getFirstName());
        }

    }
}
