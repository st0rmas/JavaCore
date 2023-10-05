package project.DB_consts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConsts {
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "Temik_Senin123";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/EmployeesAccountingSystem";

    public static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
