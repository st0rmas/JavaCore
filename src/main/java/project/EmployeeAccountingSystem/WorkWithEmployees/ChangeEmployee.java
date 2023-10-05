package project.EmployeeAccountingSystem.WorkWithEmployees;

import project.DB_consts.DBConsts;
import project.EmployeeAccountingSystem.EmployeeAccountingSystem;
import project.EmployeeAccountingSystem.FormEmloyeesTable;
import project.EmployeeAccountingSystem.PatternsForInput.PatternsForInput;
import project.Enteties.Employee;
import project.Enteties.Post;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Scanner;

public class ChangeEmployee {

    public void start(){
        try {
            Connection connection = DBConsts.connection;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");
            FormEmloyeesTable.showEmployees(resultSet);
            change();
            EmployeeAccountingSystem.start();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    private Employee getEmployee(int id){
        Employee employee = new Employee();
        Connection connection = DBConsts.connection;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employees WHERE id = " + id);

            while (resultSet.next()){
                employee.setId(Integer.parseInt(resultSet.getString("id")));
                employee.setFullName(resultSet.getString("name"));
                employee.setDate(LocalDate.parse(resultSet.getString("birth_date")));
                employee.setGender(resultSet.getString("gender"));
                employee.setPhone(resultSet.getString("phone"));
                employee.setPost(Post.valueOf(resultSet.getString("post")));
                employee.setBoss(resultSet.getString("boss_id"));
                employee.setSalary(Double.parseDouble(resultSet.getString("salary")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return employee;
    }
    private int getEmployeeId(){
        System.out.print("Enter employee's id: ");
        return new Scanner(System.in).nextInt();
    }
    private int getCharacteristic(){

        int point = 0;
        System.out.println("[1]. Name\n" +
                           "[2]. Birth date\n" +
                           "[3]. Phone number\n" +
                           "[4]. Post\n" +
                           "[5]. Boss\n" +
                           "[6]. Salary\n" +
                           "[0]. Exit");
        System.out.print("Enter characteristic: ");
        point = new Scanner(System.in).nextInt();
        return point;
    }

    private void change(){
        int id = getEmployeeId();
        Employee employee = getEmployee(id);
        int point = getCharacteristic();
        switch (point){
            case 1:
                System.out.print("Enter new name: ");
                employee.setFullName(PatternsForInput.correctFullName());
                break;
            case 2:
                System.out.print("Enter new date: ");
                employee.setDate(PatternsForInput.correctBirthDate());
                break;
            case 3:
                System.out.print("Enter new phone: ");
                employee.setPhone(PatternsForInput.correctPhone());
                break;
            case 4:
                System.out.print("Enter new post: ");
                employee.setPost(PatternsForInput.correctPost());
                break;
            case 5:
                System.out.print("Enter new boss id: ");
                employee.setBoss(new Scanner(System.in).nextLine());
                break;
            case 6:
                System.out.print("Enter new salary: ");
                employee.setSalary(PatternsForInput.correctSalary());
                break;
            case 0:
                break;
            default:
                System.out.println("Incorrect input");
                break;
        }
        if (point!=0) updateInfo(employee);

    }
    private void updateInfo(Employee employee){
        int id = employee.getId();
        String name = "'" + employee.getFullName() +"'";
        String birth = "'" + employee.getDate() +"'";
        String gender = "'" + employee.getGender() +"'";
        String phone = "'" + employee.getPhone() +"'";
        String post = "'" + employee.getPost() +"'";
        int boss_id = Integer.parseInt(employee.getBoss());
        double salary = employee.getSalary();

        Connection connection = DBConsts.connection;
        try {
            Statement statement = connection.createStatement();
            int temp1 = statement.executeUpdate("UPDATE employees SET name = " + name + " WHERE id = " + id);
            int temp2 = statement.executeUpdate("UPDATE employees SET birth_date = " + birth + " WHERE id = " + id);
            int temp3 = statement.executeUpdate("UPDATE employees SET gender = " + gender + " WHERE id = " + id);
            int temp4 = statement.executeUpdate("UPDATE employees SET phone = " + phone + " WHERE id = " + id);
            int temp5 = statement.executeUpdate("UPDATE employees SET post = " + post + " WHERE id = " + id);
            int temp6 = statement.executeUpdate("UPDATE employees SET boss_id = " + boss_id + " WHERE id = " + id);
            int temp7 = statement.executeUpdate("UPDATE employees SET salary = " + salary + " WHERE id = " + id);
            System.out.println("Success");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
