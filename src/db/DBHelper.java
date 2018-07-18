import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class DBHelper {
    public static Connection dbConn;

    public DBHelper() {
        try {
            dbConn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/schooldb","root","");
            Statement myStmt = dbConn.createStatement();
            ResultSet myRS = myStmt.executeQuery("select * from students");
            while (myRS.next()) {
                //System.out.println(myRS.getString("last_name") + ", " + myRS.getString("first_name"));
            }
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
                 //System.out.println(myRS.getString("last_name") + ", " + myRS.getString("first_name"));
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

                Course tempC = new Course(myRS.getInt("course_id"), myRS.getString("course_name"), myRS.getInt("class"));
                TranscriptItem ti = new TranscriptItem(tempC, myRS.getDouble("grade"));
                transcriptItems.add(ti);
                //Student sRecord = new Student(parseInt(myRS.getString("student_id")), myRS.getString("first_name"),myRS.getString("last_name"), myRS.getString("gender").charAt(0),formatter.parse(myRS.getString("birthdate")), parseInt(myRS.getString("class")));
                //tempList.add(sRecord);
                //System.out.println(myRS.getString("last_name") + ", " + myRS.getString("first_name"));
            }
            t = new Transcript(getStudentByID(sID), transcriptItems);
            return t;
            //s = new Student(parseInt(myRS.getString("student_id")), myRS.getString("first_name"),myRS.getString("last_name"), myRS.getString("gender").charAt(0),formatter.parse(myRS.getString("birthdate")), parseInt(myRS.getString("class")));
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ArrayList<Course> getAllCourses() {
        //TODO Create Method
        return null;
    }

    public static ArrayList<Transcript> getAllSchedules() {
        //TODO Create Method
        return null;
    }
}
