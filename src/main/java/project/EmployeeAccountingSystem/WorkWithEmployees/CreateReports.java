package project.EmployeeAccountingSystem.WorkWithEmployees;

import project.DB_consts.DBConsts;
import project.EmployeeAccountingSystem.EmployeeAccountingSystem;
import project.EmployeeAccountingSystem.FormEmloyeesTable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CreateReports {
    public void start(){
        try {
            showReportsMenu();
            getReports();
            EmployeeAccountingSystem.start();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void getReports() throws SQLException {
        int point = getPoint();
        Connection connection = DBConsts.connection;
        Statement statement = connection.createStatement();

        switch (point){
            case 1:
                System.out.println("Average salary of all employees - " + getAverageSalary(statement));
                break;
            case 2:
                showTopSalary(statement);
                break;
            case 3:
                showTopBirthDate(statement);
                break;
        }
    }
    private int getPoint(){
        int point = new Scanner(System.in).nextInt();
        if (point==0){
            return 0;
        }
        else if (point<1 || point > 3){
            System.out.print("Invalid point. Try again: ");
            point = getPoint();
        }
        return point;
    }
    private void showReportsMenu(){
        System.out.println("[1]. Average salary");
        System.out.println("[2]. TOP-5 highest paid employees");
        System.out.println("[3]. TOP-5 the oldest employees");
        System.out.println("[0]. Exit");
        System.out.print(">>> ");
    }
    private double getAverageSalary(Statement statement){
        double avgSalary = 0;
        double sum = 0;
        int counter = 0;

        try {
            ResultSet resultSet = statement.executeQuery("SELECT salary FROM employees");
            while (resultSet.next()){
                counter++;
                sum+=resultSet.getDouble("salary");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return sum/counter;
    }
    private void showTopSalary(Statement statement){
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employees ORDER BY salary DESC");
            FormEmloyeesTable.showEmployees(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void showTopBirthDate(Statement statement){
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employees ORDER BY birth_date ");
            FormEmloyeesTable.showEmployees(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
