import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

    public static ArrayList<Course> getAllCourses() {
        //TODO Create Method
        return null;
    }

    public static ArrayList<Schedule> getAllSchedules() {
        //TODO Create Method
        return null;
    }
}
