import java.util.ArrayList;

public class Transcript {
    public Student student;
    public ArrayList<TranscriptItem> grades;

    public Transcript(Student student, ArrayList<TranscriptItem> grades) {
        this.student = student;
        this.grades = grades;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public ArrayList<TranscriptItem> getGrades() {
        return grades;
    }

    public void setGrades(ArrayList<TranscriptItem> grades) {
        this.grades = grades;
    }
}
