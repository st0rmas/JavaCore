package project.EmployeeAccountingSystem.WorkWithEmployees;

import project.DB_consts.DBConsts;
import project.EmployeeAccountingSystem.EmployeeAccountingSystem;
import project.EmployeeAccountingSystem.FormEmloyeesTable;
import project.EmployeeAccountingSystem.PatternsForInput.PatternsForInput;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class FindEmployee {

    public void start(){
        menu();
        try {
            findEmployees();
            EmployeeAccountingSystem.start();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private int getCharacteristic(){
        int point = new Scanner(System.in).nextInt();
        if (point==0){
            return 0;
        }
        else if (point<1 || point > 7){
            System.out.print("Invalid point. Try again: ");
            point = getCharacteristic();
        }
        return point;
    }

    private void findEmployees() throws SQLException {
        Connection connection = DBConsts.connection;
        Statement statement = connection.createStatement();
        ResultSet result = null;
        int answer = getCharacteristic();
        switch (answer){
            case 1:
                System.out.print("Enter name: ");
                String name = PatternsForInput.correctFullName();
                System.out.println();
                result = statement.executeQuery("SELECT * FROM employees WHERE name = " + "'" +name+"'");
                break;
            case 2:
                System.out.print("Enter birth date: ");
                String birth = String.valueOf(PatternsForInput.correctBirthDate());
                System.out.println();
                result = statement.executeQuery("SELECT * FROM employees WHERE birth_date = " + "'" +birth+"'");
                break;
            case 3:
                System.out.print("Enter gender: ");
                String gender = PatternsForInput.correctGender();
                System.out.println();
                result = statement.executeQuery("SELECT * FROM employees WHERE gender = " + "'" +gender+"'");
                break;
            case 4:
                System.out.print("Enter phone: ");
                String phone = PatternsForInput.correctPhone();
                System.out.println();
                result = statement.executeQuery("SELECT * FROM employees WHERE phone = " + "'" +phone+"'");
                break;
            case 5:
                System.out.print("Enter post: ");
                String post = String.valueOf(PatternsForInput.correctPost());
                System.out.println();
                result = statement.executeQuery("SELECT * FROM employees WHERE post = " + "'" +post+"'");
                break;
            case 6:
                System.out.print("Enter boss id: ");
                String boss_id = new Scanner(System.in).next();
                System.out.println();
                result = statement.executeQuery("SELECT * FROM employees WHERE boss_id = " + boss_id);
                break;
            case 7:
                System.out.print("Enter salary: ");
                double salary = PatternsForInput.correctSalary();
                System.out.println();
                result = statement.executeQuery("SELECT * FROM employees WHERE salary = " + "'" +salary+"'");
                break;
            case 0:
                break;
            default:
                System.out.println("Invalid point");
                break;
        }
        if (answer==0){
            return;
        }
        FormEmloyeesTable.showEmployees(result);


    }
    private void menu(){
        System.out.println("[1]. Name\n" +
                "[2]. Birth date\n" +
                "[3]. Gender\n" +
                "[4]. Phone number\n" +
                "[5]. Post\n" +
                "[6]. Boss\n" +
                "[7]. Salary\n" +
                "[0]. Exit");
        System.out.print("Enter characteristic: ");
    }
}
