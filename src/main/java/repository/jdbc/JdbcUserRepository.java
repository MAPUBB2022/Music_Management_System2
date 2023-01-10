package repository.jdbc;

import interfaces.UserRepository;
import model.song.Song;
import model.users.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcUserRepository implements UserRepository
{
	private final Connection connection;
	
	public JdbcUserRepository()
	{
		this.connection = JdbcConnection.getConnectionInstance();
	}
	
	@Override
	public boolean add(User entity)
	{
		if (findByID(entity.getUsername()) == null) {
			try {
				PreparedStatement statement = connection.prepareStatement("insert into [User] (username, password, isAdmin) values (?, ?, ?);");
				statement.setString(1, entity.getUsername());
				statement.setString(2, entity.getPassword());
				statement.setBoolean(3, entity.isAdmin());
				statement.executeUpdate();
				return true;
			}
			catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return false;
	}
	
	@Override
	public boolean remove(User entity)
	{
		if (findByID(entity.getUsername()) != null) {
			try {
				PreparedStatement statement = connection.prepareStatement("delete from [User] where username = ?;");
				statement.setString(1, entity.getUsername());
				statement.executeUpdate();
				return true;
			}
			catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return false;
	}
	
	@Override
	public User update(String s, User entity)
	{
		User user = findByID(s);
		if (user != null) {
			try {
				PreparedStatement statement = connection.prepareStatement("update [User] set username = ?, password = ?, isAdmin = ? where username = ?;");
				statement.setString(1, entity.getUsername());
				statement.setString(2, entity.getUsername());
				statement.setBoolean(3, entity.isAdmin());
				statement.setString(4, s);
				statement.executeUpdate();
				
//				statement = connection.prepareStatement("update Ticket set username = ? where username = ?;");
//				statement.setString(1, entity.getUsername());
//				statement.setString(2, s);
				
				return user;
			}
			catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return null;
	}
	
	@Override
	public User findByID(String s)
	{
		try {
			PreparedStatement statement = connection.prepareStatement("select * from [User] where username = ?;");
			statement.setString(1, s);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				User user = new User();
				user.setUsername(resultSet.getString("username"));
				user.setPassword(resultSet.getString("password"));
				user.setAdminStatus(resultSet.getBoolean("isAdmin"));
				return user;
			} else return null;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public List<User> findAll()
	{
		List<User> users = new ArrayList<>();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from [User];");
			
			while (resultSet.next()) {
				User user = new User();
				user.setUsername(resultSet.getString("username"));
				user.setPassword(resultSet.getString("password"));
				user.setAdminStatus(resultSet.getBoolean("isAdmin"));
				users.add(user);
			}
			
			return users.isEmpty() ? null : users;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public User findByUsernameAndPassword(String username, String password)
	{
		try {
			PreparedStatement statement = connection.prepareStatement("select * from [User] where username = ? and password = ?");
			statement.setString(1, username);
			statement.setString(2, password);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				User user = new User();
				user.setUsername(resultSet.getString("username"));
				user.setPassword(resultSet.getString("password"));
				user.setAdminStatus(resultSet.getBoolean("isAdmin"));
				return user;
			} else return null;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public String toString()
	{
		StringBuilder endString = new StringBuilder();
		List<User> users = this.findAll();
		if (users == null) return "";
		for (User user : users)
			endString.append(user.toString()).append("\n");
		return endString.toString();
	}
}
