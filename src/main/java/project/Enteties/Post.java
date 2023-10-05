package project.Enteties;

public enum Post {
    ADMINISTRATOR(70000),
    MANAGER(50000),
    CLEANER(30000),
    OFFICE_EMPLOYEE(40000),
    BOSS(100000);

    private double salary;

    Post(double salary) {
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
