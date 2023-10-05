package project.WorkWithDB;



import project.DB_consts.DBConsts;
import project.Enteties.Employee;
import project.Enteties.Post;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class GetEmployeesFromDB {
    private static Connection connection = DBConsts.connection;

    public static ArrayList<Employee> getEmployees() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        ArrayList<Employee> employees = new ArrayList<>();
        Statement statement = connection.createStatement();

        String SQL_SELECT_EMPLOYEES = "SELECT * FROM employees";
        ResultSet result = statement.executeQuery(SQL_SELECT_EMPLOYEES);
        while(result.next()){
            int id = result.getInt("id");
            String name = result.getString("name");
            LocalDate date = result.getDate("birth_date").toLocalDate();
            String gender = result.getString("gender");
            String phone = result.getString("phone");
            Post post = Post.valueOf(result.getString("post"));
            String boss = getBossName(id);
            double salary = result.getDouble("salary");
            String login = result.getString("login");
            String password = result.getString("password");
            employees.add(new Employee(id, name, date, gender, phone, post, boss, salary));
        }
        return employees;
    }
    private static String getBossName(int employeeId){
        String name="";
        try {
            Statement statement = connection.createStatement();
            String temp = "SELECT name FROM employees WHERE id = (SELECT boss_id FROM employees WHERE id =" + employeeId + ")";
            ResultSet resultSet = statement.executeQuery(temp);
            while (resultSet.next()){
                name = resultSet.getString("name");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return name;
    }
}
