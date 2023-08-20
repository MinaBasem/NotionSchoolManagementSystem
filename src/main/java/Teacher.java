public class Teacher extends Person implements Staff{
    private String subjectTaught;
    private int salary;

    public Teacher(String name, String nationality, int age, String subjectTaught, int salary) {
        super(name, nationality, age);
        this.subjectTaught = subjectTaught;
        this.salary = salary;
    }

    public String getSubjectTaught() {
        return subjectTaught;
    }

    public void setSubjectTaught(String subjectTaught) {
        this.subjectTaught = subjectTaught;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public void quitJob() {

    }
}
