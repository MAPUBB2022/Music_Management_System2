package repository.jdbc;

import interfaces.ICrudRepository;
import model.album.Album;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class JdbcAlbumsRepository implements ICrudRepository<String, Album>
{
	private JdbcConnection connection;
	
	public JdbcAlbumsRepository(JdbcConnection connection)
	{
		this.connection = connection;
//		try {
//			PreparedStatement statement = con.prepareStatement("insert into Users(username, password, status) values (?, ?, ?)");
//			statement.setString(1, "ion");
//			statement.setString(2, "1234");
//			statement.setString(3, "false");
//			statement.executeUpdate();
//		}
//		catch (SQLException e) {
//			throw new RuntimeException(e);
//		}
	}
	
	@Override
	public boolean add(Album entity)
	{
		return false;
	}
	
	@Override
	public boolean remove(Album entity)
	{
		return false;
	}
	
	@Override
	public Album update(String s, Album entity)
	{
		return null;
	}
	
	@Override
	public Album findByID(String s)
	{
		return null;
	}
	
	@Override
	public List<Album> findAll()
	{
		return null;
	}
}
