package repository.jdbc;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection
{
    private static Connection instance;

    private static Connection getNewConnection()
    {
        Connection con = null;
        try {
            DriverManager.registerDriver(new SQLServerDriver());
            con = DriverManager.getConnection("jdbc:sqlserver://localhost\\SQLEXPRESS;database=MAP", "MAP_project", "1234");
        }
        catch (SQLException e) {
            System.out.println("Error getting connection " + e);
        }
        return con;
    }

    public static Connection getInstance()
    {
        try {
            if (instance == null || instance.isClosed()) instance = getNewConnection();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return instance;
    }
}
