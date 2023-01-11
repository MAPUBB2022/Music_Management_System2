package repository.jdbc;

import interfaces.ICrudRepository;
import model.album.Artist;
import model.concert.Concert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcConcertsRepository implements ICrudRepository<String, Concert>
{
	private final Connection connection;
	
	public JdbcConcertsRepository()
	{
		this.connection = JdbcConnection.getConnectionInstance();
	}
	
	@Override
	public boolean add(Concert entity)
	{
		if (findByID(entity.getName()) == null) {
			try {
				PreparedStatement statement = connection.prepareStatement("insert into Concert (name, location, date, capacity, ticketPrice, ticketsSold, rentCosts) values (?, ?, ?, ?, ?, ?, ?);");
				statement.setString(1, entity.getName());
				statement.setString(2, entity.getLocation());
				statement.setDate(3, (Date) entity.getDate());
				statement.setInt(4, entity.getCapacity());
				statement.setFloat(5, entity.getTicketPrice());
				statement.setInt(6, entity.getTicketsSold());
				statement.setFloat(7, entity.getRentCosts());
				statement.executeUpdate();
				
				for (Artist artist : entity.getArtistList()) {
					statement = connection.prepareStatement("insert into Concert_Artist (concertName, artistName) values (?, ?)");
					statement.setString(1, entity.getName());
					statement.setString(2, artist.getStageName());
					statement.executeUpdate();
				}
				
				return true;
			}
			catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return false;
	}
	
	@Override
	public boolean remove(Concert entity)
	{
		if (findByID(entity.getName()) != null) {
			try {
//				PreparedStatement statement = connection.prepareStatement("delete from Concert_Artist where concertName = ?;");
//				statement.setString(1, entity.getName());
//				statement.executeUpdate();
//
//				statement = connection.prepareStatement("delete from Ticket where concertName = ?;");
//				statement.setString(1, entity.getName());
//				statement.executeUpdate();
				
				PreparedStatement statement = connection.prepareStatement("delete from Concert where name = ?;");
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
	public Concert update(String s, Concert entity)
	{
		Concert concert = findByID(s);
		if (concert != null) {
			try {
				PreparedStatement statement = connection.prepareStatement("update Concert set name = ?, location = ?, date = ?, capacity = ?, ticketPrice = ?, ticketsSold = ?, rentCosts = ? where name = ?;");
				statement.setString(1, entity.getName());
				statement.setString(2, entity.getLocation());
				statement.setDate(3, (Date) entity.getDate());
				statement.setInt(4, entity.getCapacity());
				statement.setFloat(5, entity.getTicketPrice());
				statement.setInt(6, entity.getTicketsSold());
				statement.setFloat(7, entity.getRentCosts());
				statement.setString(8, s);
				statement.executeUpdate();
				
//				statement = connection.prepareStatement("update Ticket set concertName = ? where concertName = ?;");
//				statement.setString(1, entity.getName());
//				statement.setString(2, s);
//
//				statement = connection.prepareStatement("update Concert_Artist set concertName = ? where concertName = ?;");
//				statement.setString(1, entity.getName());
//				statement.setString(2, s);
//
				return concert;
			}
			catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return null;
	}
	
	@Override
	public Concert findByID(String s)
	{
		Concert concert = new Concert();
		try {
			PreparedStatement statement = connection.prepareStatement("select * from Concert where name = ?");
			statement.setString(1, s);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				concert.setName(resultSet.getString("name"));
				concert.setLocation(resultSet.getString("location"));
				concert.setDate(resultSet.getDate("date"));
				concert.setCapacity(resultSet.getInt("capacity"));
				concert.setTicketPrice(resultSet.getFloat("ticketPrice"));
				concert.setTicketsSold(resultSet.getInt("ticketsSold"));
				concert.setRentCosts(resultSet.getFloat("rentCosts"));
				concert.setArtistList(findArtistsForConcert(s));
				return concert;
			} else return null;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public List<Concert> findAll()
	{
		List<Concert> concerts = new ArrayList<>();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from Concert");
			
			while (resultSet.next()) {
				Concert concert = new Concert();
				concert.setName(resultSet.getString("name"));
				concert.setLocation(resultSet.getString("location"));
				concert.setDate(resultSet.getDate("date"));
				concert.setCapacity(resultSet.getInt("capacity"));
				concert.setTicketPrice(resultSet.getFloat("ticketPrice"));
				concert.setTicketsSold(resultSet.getInt("ticketsSold"));
				concert.setRentCosts(resultSet.getFloat("rentCosts"));
				concert.setArtistList(findArtistsForConcert(concert.getName()));
				concerts.add(concert);
			}
			
			return concerts.isEmpty() ? null : concerts;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Artist> findArtistsForConcert(String concertName)
	{
		List<Artist> artists = new ArrayList<>();
		try {
			PreparedStatement statement = connection.prepareStatement("select a.stageName, a.name, a.salary from Artist a inner join Concert_Artist CA on a.stageName = CA.artistName where CA.concertName = ?");
			statement.setString(1, concertName);
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
		List<Concert> concerts = this.findAll();
		if (concerts == null) return "";
		StringBuilder endString = new StringBuilder();
		for (Concert concert : concerts)
			endString.append(concert.toString()).append("\n");
		return endString.toString();
	}
}
