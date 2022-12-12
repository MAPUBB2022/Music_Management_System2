package repository.jdbc;

import interfaces.UserRepository;
import model.users.User;

import java.sql.*;
import java.util.List;

public class UserJdbcRepository implements UserRepository
{

    private JDBCConnection connection;

    public UserJdbcRepository(JDBCConnection connection)
    {
        this.connection = connection;
    }

    public void populateUsers()
    {
        Connection con = JDBCConnection.getInstance();
        try {
            PreparedStatement statement = con.prepareStatement("insert into Users(username, password) values (?, ?)");
            statement.setString(1, "ion");
            statement.setString(2, "1234");
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean add(User entity) throws SQLException {
        if(findByID(entity.getUsername()) == null) {
            Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost\\SQLEXPRESS;database=MAP",
                    "MAP_project", "1234");

            Statement insert = connection.createStatement();

            String insert_string_fancy = "insert into Users(username, password) values (?, ?)";

            PreparedStatement insert_fancy = connection.prepareStatement(insert_string_fancy);
            insert_fancy.setString(1, entity.getUsername());
            insert_fancy.setString(2, entity.getPassword());

            return true;
        }
        return false;
    }

    @Override
    public boolean remove(User entity) throws SQLException {
        if(findByID(entity.getUsername()) != null){
            Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost\\SQLEXPRESS;database=MAP",
                    "MAP_project", "1234");

            Statement delete = connection.createStatement();

            String delete_string_fancy = "delete * from Users where Users.username = "+entity.getUsername();

            PreparedStatement delete_fancy = connection.prepareStatement(delete_string_fancy);

            return true;
        }
        return false;
    }

    @Override
    public User update(String s, User newEntity)
    {
        return newEntity;
    }

    @Override
    public User findByID(String s)
    {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findByUsernameAndPassword(String username, String password)
    {
        return null;
    }
}