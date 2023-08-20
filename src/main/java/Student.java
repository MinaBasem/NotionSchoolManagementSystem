import java.time.LocalDate;

public class Student extends Person{
    private int id;
    private int grade;
    private int enrollementYear;
    private int warnings;
    private float fees;
    private LocalDate registrationDate;

    public Student(int id, String name, String nationality, int age, int grade, int enrollementYear, int warnings, float fees) {
        super(name, nationality, age);
        this.id = id;
        this.grade = grade;
        this.enrollementYear = enrollementYear;
        this.warnings = warnings;
        this.fees = fees;
        registrationDate = LocalDate.now();
    }
    public Student(int id, String name, String nationality, int age, int grade, int enrollementYear, int warnings, String date, float fees) {
        super(name, nationality, age);
        this.id = id;
        this.grade = grade;
        this.enrollementYear = enrollementYear;
        this.warnings = warnings;
        this.fees = fees;
        registrationDate = LocalDate.parse(date);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getEnrollementYear() {
        return enrollementYear;
    }

    public void setEnrollementYear(int enrollementYear) {
        this.enrollementYear = enrollementYear;
    }

    public int getWarnings() {
        return warnings;
    }

    public void setWarnings(int warnings) {
        this.warnings = warnings;
    }

    public float getFees() {
        return fees;
    }

    public void setFees(float fees) {
        this.fees = fees;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    @Override
    public String toString() {
        return id + ";" + super.toString()+ ";" + grade + ";" + enrollementYear + ";" + warnings + ";" + registrationDate + ";" + fees;
    }
}
