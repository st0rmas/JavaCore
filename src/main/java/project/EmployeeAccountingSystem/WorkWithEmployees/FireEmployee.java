package project.EmployeeAccountingSystem.WorkWithEmployees;

import project.DB_consts.DBConsts;
import project.EmployeeAccountingSystem.EmployeeAccountingSystem;
import project.Enteties.Employee;

import javax.swing.*;
import javax.xml.transform.Result;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import static project.EmployeeAccountingSystem.FormEmloyeesTable.*;

public class FireEmployee {

    public  void start(){
        try {
            Connection connection = DBConsts.connection;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");
            showEmployees(resultSet);
            deleteEmployee();
            EmployeeAccountingSystem.start();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private void formTable() throws SQLException{
        System.out.println("------------------------------------------------------------------------------------------------------------------------------");

        System.out.printf("%-8s %-27s %s %-12s %-10s %s %s %s %s %n", "| id     |", "Full name","|", "Birth date", "| Gender |", "Phone        |", "Post           |"
                            , "Boss id    |", "Salary     |");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------");

    }
    private void showEmployeeInTable(ResultSet resultSet) throws SQLException {
        System.out.printf("%s %-6s %s %-27s %s %-12s %s %-6s %s %-12s %s %-14s %s %-10s %s %-10s %s %n",
                "|",
                resultSet.getString("id"),"|",
                resultSet.getString("name"),"|",
                resultSet.getString("birth_date"),"|",
                resultSet.getString("gender"),"|",
                resultSet.getString("phone"),"|",
                resultSet.getString("post"),"|",
                resultSet.getString("boss_id"),"|",
                resultSet.getString("salary"),"|");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------");

    }
    private void deleteEmployee(){
        System.out.println();
        System.out.print("Enter employee's id to delete: ");
        int id = getDeletedId();
        Connection connection = DBConsts.connection;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            int result = statement.executeUpdate("UPDATE employees SET boss_id = null WHERE boss_id = "+ id);
            int resultSet = statement.executeUpdate("DELETE FROM employees WHERE id = " + id);
            System.out.println("Success");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private int getDeletedId(){
        int id = new Scanner(System.in).nextInt();
        ArrayList<Integer> ids = new ArrayList<>();
        Connection connection = DBConsts.connection;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id FROM employees");
            while(resultSet.next()){
                ids.add(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (ids.contains(id)){
            return id;
        }
        else{
            System.out.println("Employee with that id wasn't find. Enter id: ");
            id = getDeletedId();
        }

        return id;
    }
}
