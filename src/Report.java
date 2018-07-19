import java.util.ArrayList;

public class Report {
    public Course course;
    public ArrayList<ReportItem> grades;

    public Report(Course course, ArrayList<ReportItem> grades) {
        this.course = course;
        this.grades = grades;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public ArrayList<ReportItem> getGrades() {
        return grades;
    }

    public void setGrades(ArrayList<ReportItem> grades) {
        this.grades = grades;
    }
}
