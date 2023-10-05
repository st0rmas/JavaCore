package project.EmployeeAccountingSystem.WorkWithEmployees;

import project.DB_consts.DBConsts;
import project.EmployeeAccountingSystem.EmployeeAccountingSystem;
import project.EmployeeAccountingSystem.PatternsForInput.PatternsForInput;
import project.Enteties.Employee;
import project.Enteties.Post;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

public class AddEmployee {

    public void start(){
        addEmployeeToDB();
        EmployeeAccountingSystem.start();
    }
    private  Employee getEmployee(){
        Employee employee = new Employee();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter an info about employee: ");

        System.out.print("Enter full name (Format: SecondName FirstName MiddleName): ");
        String name = PatternsForInput.correctFullName();

        System.out.print("Enter birthday date (Format: dd.MM.yyyy): ");
        LocalDate date = PatternsForInput.correctBirthDate();

        System.out.print("Enter gender(M/W): ");
        String gender = PatternsForInput.correctGender();

        System.out.print("Enter phone( Format: 89036351133): ");
        String phone = PatternsForInput.correctPhone();

        System.out.print("Enter post " + Arrays.toString(Post.values()) + ": ");
        Post post = PatternsForInput.correctPost();

        System.out.print("Enter boss surname (If employee has no boss, enter null): ");
        String boss = PatternsForInput.correctBossName();


        System.out.print("Enter salary: ");
        double salary = PatternsForInput.correctSalary();

        employee.setFullName(name);
        employee.setDate(date);
        employee.setGender(gender);
        employee.setPhone(phone);
        employee.setPost(post);
        employee.setBoss(boss);
        employee.setSalary(salary);
        return employee;
    }

    public void addEmployeeToDB(){
        Employee employee = getEmployee();
        System.out.println(employee);
        Connection connection = DBConsts.connection;
        try {
            Statement statement = connection.createStatement();
            int resultSet = statement.executeUpdate("INSERT INTO employees(name, birth_date, gender, phone, post, boss_id, salary) VALUES ('" +
                     employee.getFullName()+ "', '" + employee.getDate() + "', '"+ employee.getGender().toUpperCase() + "', '"+ employee.getPhone() + "', '"
                    + employee.getPost() + "', "+ employee.getBoss() + ", " + employee.getSalary() + ")");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
