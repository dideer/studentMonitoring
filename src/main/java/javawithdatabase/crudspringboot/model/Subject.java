package javawithdatabase.crudspringboot.model;


import jakarta.persistence.*;

@Entity
@Table(name = "subject")
public class Subject {
    @Id
    private String SubjectCode;

    private String SubjectName;


    private String SubjectDescription;

    @ManyToOne
    @JoinColumn(name = "level_id")
    private Level level;

    public Subject(){
        super();
    }


    public Subject(String subjectName, String subjectCode, String subjectDescription, Level level) {
        SubjectName = subjectName;
        SubjectCode = subjectCode;
        SubjectDescription = subjectDescription;
        this.level = level;
    }

    public String getSubjectCode() {
        return SubjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        SubjectCode = subjectCode;
    }

    public String getSubjectName() {
        return SubjectName;
    }

    public void setSubjectName(String subjectName) {
        SubjectName = subjectName;
    }

    public String getSubjectDescription() {
        return SubjectDescription;
    }

    public void setSubjectDescription(String subjectDescription) {
        SubjectDescription = subjectDescription;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}
