package repository.jdbc;

import interfaces.UserRepository;
import model.users.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
    public boolean remove(User entity) {
        return false;
    }

    @Override
    public User update(String s, User newEntity)
    {
        return newEntity;
    }

    @Override
    public boolean add(User entity) {
        return false;
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