import java.sql.*;
import java.util.ArrayList;

public class db {

    public static void main(String[] args) {
        try {
            Connection myConn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/schooldb","root","");
            Statement myStmt = myConn.createStatement();
            ResultSet myRS = myStmt.executeQuery("select * from students");
            while (myRS.next()) {
                System.out.println(myRS.getString("last_name") + ", " + myRS.getString("first_name"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Student> getAllStudents() {
        //TODO Create Method
        return null;
    }
}
