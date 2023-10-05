package project.EmployeeAccountingSystem;

import project.DB_consts.DBConsts;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FormEmloyeesTable {
    public static void showEmployees(ResultSet resultSet) throws SQLException {

        Connection connection = DBConsts.connection;
        Statement statement = connection.createStatement();

        if (resultSet.next()){
            formTable();
            do {
                showEmployeeInTable(resultSet);
            }
            while (resultSet.next());
        }
        else{
            System.out.println("No employees found");
        }


    }
    private static void showEmployeeInTable(ResultSet resultSet) throws SQLException {

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
    private static void formTable() throws SQLException {
        System.out.println("--------------------------------------------------------------------------------------------------------------------------");

        System.out.printf("%-8s %-27s %s %-12s %-10s %s %s %s %s %n", "| id     |", "Full name","|", "Birth date", "| Gender |", "Phone        |", "Post           |"
                , "Boss id    |", "Salary     |");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------");

    }
}
