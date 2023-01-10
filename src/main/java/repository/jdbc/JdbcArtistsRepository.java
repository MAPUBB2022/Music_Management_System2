package repository.jdbc;

import interfaces.ICrudRepository;
import model.album.Artist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcArtistsRepository implements ICrudRepository<String, Artist>
{
	private final Connection connection;
	
	public JdbcArtistsRepository()
	{
		this.connection = JdbcConnection.getConnectionInstance();
	}
	
	@Override
	public boolean add(Artist entity)
	{
		if (findByID(entity.getStageName()) == null) {
			try {
				PreparedStatement statement = this.connection.prepareStatement("INSERT INTO Artist(stageName, name, salary) VALUES(?, ?, ?);");
				statement.setString(1, entity.getStageName());
				statement.setString(2, entity.getName());
				statement.setFloat(3, entity.getSalary());
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
	public boolean remove(Artist entity)
	{
		if (findByID(entity.getStageName()) != null) {
			try {
//				PreparedStatement statement = this.connection.prepareStatement("DELETE FROM Concert_Artist WHERE artistName = ?;");
//				statement.setString(1, entity.getStageName());
//				statement.executeUpdate();
//
//				statement = this.connection.prepareStatement("DELETE FROM MusicLabel_Artist WHERE artistName = ?;");
//				statement.setString(1, entity.getStageName());
//				statement.executeUpdate();
//
//				statement = this.connection.prepareStatement("DELETE FROM Album_Song WHERE songName = (select name from Song where singer = ?);");
//				statement.setString(1, entity.getStageName());
//				statement.executeUpdate();
//
//				statement = this.connection.prepareStatement("DELETE FROM Song WHERE singer = ?;");
//				statement.setString(1, entity.getStageName());
//				statement.executeUpdate();
				
				PreparedStatement statement = this.connection.prepareStatement("DELETE FROM Artist WHERE stageName = ?;");
				statement.setString(1, entity.getStageName());
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
	public Artist update(String s, Artist entity)
	{
		Artist artist = findByID(s);
		if (artist != null) {
			try {
				PreparedStatement statement = this.connection.prepareStatement("UPDATE Artist SET stageName = ?, name = ?, salary = ? WHERE stageName = ?");
				statement.setString(1, entity.getStageName());
				statement.setString(2, entity.getName());
				statement.setFloat(3, entity.getSalary());
				statement.setString(4, s);
				statement.executeUpdate();
				
//				statement = this.connection.prepareStatement("UPDATE Concert_Artist SET artistName = ? WHERE artistName = ?;");
//				statement.setString(1, entity.getStageName());
//				statement.setString(2, s);
//				statement.executeUpdate();
//
//				statement = this.connection.prepareStatement("UPDATE MusicLabel_Artist SET artistName = ? WHERE artistName = ?;");
//				statement.setString(1, entity.getStageName());
//				statement.setString(2, s);
//				statement.executeUpdate();
//
//				statement = this.connection.prepareStatement("UPDATE Song SET singer = ? WHERE singer = ?;");
//				statement.setString(1, entity.getStageName());
//				statement.setString(2, s);
//				statement.executeUpdate();
//
//				statement = this.connection.prepareStatement("UPDATE Album SET artist = ? WHERE artist = ?;");
//				statement.setString(1, entity.getStageName());
//				statement.setString(2, s);
//				statement.executeUpdate();
				
				return artist;
			}
			catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return null;
	}
	
	@Override
	public Artist findByID(String s)
	{
		try {
			PreparedStatement statement = this.connection.prepareStatement("SELECT stageName, name, salary FROM Artist WHERE stageName = ?;");
			statement.setString(1, s);
			ResultSet result = statement.executeQuery();
			
			if (result.next()) {
				Artist artist = new Artist(result.getString("stageName"));
				artist.setName(result.getString("name"));
				artist.setSalary(result.getFloat("salary"));
				return artist;
			} else return null;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public List<Artist> findAll()
	{
		List<Artist> artists = new ArrayList<>();
		try {
			Statement getAll = this.connection.createStatement();
			ResultSet resultSet = getAll.executeQuery("SELECT stageName, name, salary FROM Artist;");
			while (resultSet.next()) {
				Artist artist = new Artist();
				artist.setStageName(resultSet.getString("stageName"));
				artist.setName(resultSet.getString("name"));
				artist.setSalary(resultSet.getFloat("salary"));
				artists.add(artist);
			}
			return artists.isEmpty() ? null : artists;
		}
		catch (SQLException exception) {
			System.out.println(exception.toString());
		}
		return null;
	}
	
	@Override
	public String toString()
	{
		List<Artist> artists = this.findAll();
		if (artists == null) return "";
		StringBuilder endString = new StringBuilder();
		for (Artist artist : artists)
			endString.append(artist.toString()).append("\n");
		return endString.toString();
	}
}
