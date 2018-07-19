import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;


public class main {
    private JTable table1;
    public static ArrayList<Student> students;
    public static ArrayList<Course> courses;
    public static DBHelper db;

    public static void main(String[] args) {

        db = new DBHelper();
        students = DBHelper.getAllStudents();
        courses = DBHelper.getAllCourses();

//        for (Student s : students) {
//            System.out.println(s.getLastName() +", "+ s.getFirstName());
//        }

        String input;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter 's' for Student Transcripts, 'c' for Course Reports, or 'x' to exit:  ");
        input = sc.next();
        do {
            switch (input) {
                case "s":
                    showStudentList();
                    System.out.print("Enter the ID to open transcript, or 'x' to exit:  ");
                    input = sc.next();
                    if (input.equals('x')) {
                        break;
                    } else {
                        showTranscript(parseInt(input));
                    }
                    break;
                case "c":
                    showCourseList();
                    System.out.print("Enter the ID to open course report, or 'x' to exit:  ");
                    input = sc.next();
                    if (input.equals('x')) {
                        break;
                    } else {
                        showCourseReport(parseInt(input));
                    }
                    break;
                default: System.out.println("Not a valid input"); break;
            }
            System.out.print("Enter 's' for Student Transcripts, 'c' for Course Reports, or 'x' to exit:  ");
            input = sc.next();
        } while(!input.toLowerCase().equals("x"));

    }

    private static void showCourseReport(int input) {
        Report r = db.getReportByID(input);
        System.out.printf("%s\t%20s\t%s%n", "Row", "Student", "Grade");
        int c = 0;
        double avg = 0.00;
        for(ReportItem ri: r.getGrades()) {
            c++;
            avg+=ri.getGrade();
            System.out.printf("%d\t%20s\t%.2f%n", c, ri.getStudent().getFullName(), ri.getGrade());
        }
        System.out.println("The average grade is: " + String.format("%.2f",avg/c));
    }

    private static void showTranscript(int input) {
        Transcript t = db.getTranscriptByID(input);
        System.out.printf("%s\t%15s\t%s%n", "Row", "CourseName", "Grade");
        int c = 0;
        double avg = 0.00;
        for(TranscriptItem ti:t.getGrades()) {
            c++;
            avg+=ti.getGrade();
            System.out.printf("%d\t%15s\t%.2f%n", c, ti.getCourse().getCourseName(), ti.getGrade());
        }
        System.out.println("The average grade is: " + String.format("%.2f",avg/c));
    }

    private static void showStudentList() {
        System.out.printf("%s\t%15s\t%15s\t%s%n", "ID", "LastName", "FirstName", "Year");
        for (Student s : students) {
            System.out.printf("%d\t%15s\t%15s\t%s%n", s.getStudent_id(), s.getLastName(), s.getFirstName(), s.getYear());
        }
    }

    private static void showCourseList() {
        System.out.printf("%s\t%15s\t%s%n", "ID", "CourseName", "Year");
        for (Course c : courses) {
            System.out.printf("%d\t%15s\t%d%n", c.getCourseID(), c.getCourseName() , c.getCourseYear());
        }
    }
}