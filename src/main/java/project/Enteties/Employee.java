package project.Enteties;

import java.time.LocalDate;

public class Employee {
    private int id;
    private String fullName;
    private LocalDate date;
    private String gender;
    private String phone;
    private Post post;
    private String boss;
    private double salary;

    public Employee(){}
    public Employee(int id, String fullName){
        this.id = id;
        this.fullName = fullName;
    }
    public Employee(int id, String fullName, LocalDate date, String gender, String phone, Post post, String boss, double salary) {
        this.id = id;
        this.fullName = fullName;
        this.date = date;
        this.gender = gender;
        this.phone = phone;
        this.post = post;
        this.boss = boss;
        this.salary = salary;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getBoss() {
        return boss;
    }

    public void setBoss(String boss) {
        this.boss = boss;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", date=" + date +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", post=" + post +
                ", boss='" + boss + '\'' +
                ", salary=" + salary +
                '}';
    }
}
