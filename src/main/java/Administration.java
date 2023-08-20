public class Administration extends Person implements Staff{
    private String role;
    private int salary;

    public Administration(String name, String nationality, int age, String role, int salary) {
        super(name, nationality, age);
        this.role = role;
        this.salary = salary;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
