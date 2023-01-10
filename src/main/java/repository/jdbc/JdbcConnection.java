package repository.jdbc;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection
{
    private static Connection connectionInstance;
    
    private static Connection getConnection()
    {
        Connection connection = null;
        try {
            DriverManager.registerDriver(new SQLServerDriver());
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost\\SQLEXPRESS;database=MAP_Labor;encrypt=true;trustServerCertificate=true", "guest", "1234");
        }
        catch (SQLException e) {
            System.out.println("[ERROR] Database Connection unestablished\n");
        }
        return connection;
    }
    
    public static Connection getConnectionInstance()
    {
        try {
            if (connectionInstance == null || connectionInstance.isClosed()) connectionInstance = getConnection();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connectionInstance;
    }
}
