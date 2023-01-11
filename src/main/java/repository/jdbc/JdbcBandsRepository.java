package repository.jdbc;

import interfaces.ICrudRepository;
import model.album.Artist;
import model.album.Band;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcBandsRepository implements ICrudRepository<String, Band>
{
	private final Connection connection;
	
	public JdbcBandsRepository()
	{
		this.connection = JdbcConnection.getConnectionInstance();
	}
	
	@Override
	public boolean add(Band entity)
	{
		if (findByID(entity.getName()) == null) {
			try {
				PreparedStatement statement = connection.prepareStatement("insert into Band (name, formationDate, origin) values (?, ?, ?);");
				statement.setString(1, entity.getName());
				statement.setDate(2, (Date) entity.getFormationDate());
				statement.setString(3, entity.getOrigin());
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
	public boolean remove(Band entity)
	{
		if (findByID(entity.getName()) != null) {
			try {
//				PreparedStatement statement = connection.prepareStatement("delete from Album_Song where songName = (select name from Song where bandSingers = ?) or albumName = (select title from Album where band = ?);");
//				statement.setString(1, entity.getName());
//				statement.setString(2, entity.getName());
//				statement.executeUpdate();
//
//				statement = connection.prepareStatement("delete from Song where bandSingers = ?;");
//				statement.setString(1, entity.getName());
//				statement.executeUpdate();
//
//				statement = connection.prepareStatement("delete from Album where band = ?;");
//				statement.setString(1, entity.getName());
//				statement.executeUpdate();
//
//				statement = connection.prepareStatement("update Artist set bandName = null where bandName = ?;");
//				statement.setString(1, entity.getName());
//				statement.executeUpdate();
				
				PreparedStatement statement = connection.prepareStatement("delete from Band where name = ?;");
				statement.setString(1, entity.getName());
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
	public Band update(String s, Band entity)
	{
		Band band = findByID(s);
		if (band != null) {
			try {
				PreparedStatement statement = connection.prepareStatement("update Band set name = ?, formationDate = ?, origin = ?  where name = ? ;");
				statement.setString(1, entity.getName());
				statement.setDate(2, (Date) entity.getFormationDate());
				statement.setString(3, entity.getOrigin());
				statement.setString(4, s);
				
//				statement = connection.prepareStatement("update Song set bandSingers = ? where bandSingers = ?;");
//				statement.setString(1, entity.getName());
//				statement.setString(2, s);
//				statement.executeUpdate();
//
//				statement = connection.prepareStatement("update Album set band = ? where band = ?;");
//				statement.setString(1, entity.getName());
//				statement.setString(2, s);
//				statement.executeUpdate();
//
//				statement = connection.prepareStatement("update Artist set bandName = ? where bandName = ?;");
//				statement.setString(1, entity.getName());
//				statement.setString(2, s);
//				statement.executeUpdate();
				
				return band;
			}
			catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return null;
	}
	
	@Override
	public Band findByID(String s)
	{
		try {
			PreparedStatement statement = connection.prepareStatement("select * from Band where name = ?");
			statement.setString(1, s);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				Band band = new Band();
				band.setName(resultSet.getString("name"));
				band.setFormationDate(resultSet.getDate("formationDate"));
				band.setOrigin(resultSet.getString("origin"));
				band.setArtistList(findArtistsForBand(s));
				return band;
			} else return null;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public List<Band> findAll()
	{
		List<Band> bands = new ArrayList<>();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Band");
			while (resultSet.next()) {
				Band band = new Band();
				band.setName(resultSet.getString("name"));
				band.setFormationDate(resultSet.getDate("formationDate"));
				band.setOrigin(resultSet.getString("origin"));
				band.setArtistList(findArtistsForBand(band.getName()));
				bands.add(band);
			}
			return bands.isEmpty() ? null : bands;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Artist> findArtistsForBand(String bandName)
	{
		List<Artist> artists = new ArrayList<>();
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Artist WHERE bandName = ?");
			statement.setString(1, bandName);
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				Artist artist = new Artist();
				artist.setStageName(resultSet.getString("stageName"));
				artist.setName(resultSet.getString("name"));
				artist.setSalary(resultSet.getFloat("salary"));
				artists.add(artist);
			}
			return artists.isEmpty() ? null : artists;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public String toString()
	{
		List<Band> bands = this.findAll();
		if (bands == null) return "";
		StringBuilder endString = new StringBuilder();
		for (Band band : bands)
			endString.append(band.toString()).append("\n");
		return endString.toString();
	}
}
