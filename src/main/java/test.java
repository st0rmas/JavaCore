import project.DB_consts.DBConsts;
import project.EmployeeAccountingSystem.FormEmloyeesTable;
import project.Enteties.Employee;
import project.Enteties.Post;

import javax.lang.model.element.NestingKind;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {
    public static void main(String[] args) throws SQLException {
        Connection connection = DBConsts.connection;

        Statement statement = connection.createStatement();
        String name = "Senin Artem Romanovich";
        String query = "SELECT * FROM employees WHERE name = " + "'" +name+"'";
        ResultSet result = statement.executeQuery(query);
//        System.out.println(result.wasNull());
//        System.out.println(result.next());
        FormEmloyeesTable.showEmployees(result);
    }
}
