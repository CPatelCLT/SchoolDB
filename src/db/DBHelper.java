import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class DBHelper {
    public static Connection dbConn;

    public DBHelper() {
        try {
            dbConn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/schooldb","root","");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Student> getAllStudents() {
        ArrayList<Student> tempList = new ArrayList<Student>();
        try {
            Statement myStmt = dbConn.createStatement();
            ResultSet myRS = myStmt.executeQuery("select * from students");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            while (myRS.next()) {
                 Student sRecord = new Student(parseInt(myRS.getString("student_id")), myRS.getString("first_name"),myRS.getString("last_name"), myRS.getString("gender").charAt(0),formatter.parse(myRS.getString("birthdate")), parseInt(myRS.getString("class")));
                tempList.add(sRecord);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return tempList;
    }

    public static Student getStudentByID(int sID) {
        Student s;
        try {
            Statement myStmt = dbConn.createStatement();
            ResultSet myRS = myStmt.executeQuery("select * from students where student_id = " + sID);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            myRS.next();
            s = new Student(parseInt(myRS.getString("student_id")), myRS.getString("first_name"),myRS.getString("last_name"), myRS.getString("gender").charAt(0),formatter.parse(myRS.getString("birthdate")), parseInt(myRS.getString("class")));
            return s;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Transcript getTranscriptByID(int sID) {
        Transcript t;

        try {
            Statement myStmt = dbConn.createStatement();
            ResultSet myRS = myStmt.executeQuery("select course_id, course_name, courses.class as class, grade, first_name, last_name from registration inner join students on registration.students_student_id = students.student_id inner join courses on registration.courses_course_id = courses.course_id where student_id = "+sID);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            ArrayList<TranscriptItem> transcriptItems = new ArrayList<TranscriptItem>();
            while (myRS.next()) {

                Course tempC = getCourseBy(myRS.getInt("course_id"));
                TranscriptItem ti = new TranscriptItem(tempC, myRS.getDouble("grade"));
                transcriptItems.add(ti);
            }
            t = new Transcript(getStudentByID(sID), transcriptItems);
            return t;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Report getReportByID(int cID) {
        Report r;

        try {
            Statement myStmt = dbConn.createStatement();
            ResultSet myRS = myStmt.executeQuery("select student_id, first_name, last_name, grade, course_name from registration inner join students on registration.students_student_id = students.student_id inner join courses on registration.courses_course_id = courses.course_id where course_id = "+cID);
            ArrayList<ReportItem> reportItems = new ArrayList<ReportItem>();
            while (myRS.next()) {

                Student tempS = getStudentByID(myRS.getInt("student_id"));
                ReportItem ri = new ReportItem(tempS, myRS.getDouble("grade"));
                reportItems.add(ri);
            }
            r = new Report(getCourseBy(cID), reportItems);
            return r;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static Course getCourseBy(int cID) {
        Course c;
        try {
            Statement myStmt = dbConn.createStatement();
            ResultSet myRS = myStmt.executeQuery("select * from courses where course_id = " + cID);
            myRS.next();
            c = new Course(parseInt(myRS.getString("course_id")), myRS.getString("course_name"), parseInt(myRS.getString("class")));
            return c;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Course> getAllCourses() {
        ArrayList<Course> tempList = new ArrayList<Course>();
        try {
            Statement myStmt = dbConn.createStatement();
            ResultSet myRS = myStmt.executeQuery("select * from courses");
            while (myRS.next()) {
                Course cRecord = new Course(parseInt(myRS.getString("course_id")), myRS.getString("course_name"), parseInt(myRS.getString("class")));
                tempList.add(cRecord);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return tempList;
    }
}