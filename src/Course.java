public class Course {
    public int courseID;
    public String courseName;
    public int courseYear;

    public Course() {
        courseID = 0;
        courseName = "";
        courseYear = 1;
    }

    public Course(int cID, String cName, int cYear) {
        this.courseID=cID;
        this.courseName=cName;
        this.courseYear=cYear;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCourseYear() {
        return courseYear;
    }

    public void setCourseYear(int courseYear) {
        this.courseYear = courseYear;
    }
}