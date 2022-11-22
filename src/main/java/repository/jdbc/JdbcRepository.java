package repository.jdbc;

import interfaces.UserRepository;
import model.users.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class JdbcRepository implements UserRepository
{

    private JDBCConnection connection;

    public JdbcRepository(JDBCConnection connection)
    {
        this.connection = connection;
    }

    public void populateUsers()
    {
        Connection con = JDBCConnection.getInstance();
        try {
            PreparedStatement statement = con.prepareStatement("insert into Users(username, password, status) values (?, ?, ?)");
            statement.setString(1, "ion");
            statement.setString(2, "1234");
            statement.setString(3, "false");
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



    @Override
    public void remove(User entity) {

    }


    @Override
    public void update(String s, User newEntity)
    {

    }

    @Override
    public void add(User entity) {

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