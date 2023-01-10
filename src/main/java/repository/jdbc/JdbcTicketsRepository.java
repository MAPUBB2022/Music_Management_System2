package repository.jdbc;

import interfaces.ICrudRepository;
import interfaces.IUserFieldIdentifiers;
import model.album.Album;
import model.concert.Concert;
import model.concert.Ticket;
import model.users.User;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class JdbcTicketsRepository implements IUserFieldIdentifiers<Ticket>
{
	private final Connection connection;
	private final User user;
	
	public JdbcTicketsRepository(User user)
	{
		this.connection = JdbcConnection.getConnectionInstance();
		this.user = user;
	}
	
	@Override
	public boolean add(Ticket entity)
	{
		try {
			PreparedStatement statement = this.connection.prepareStatement("insert into Ticket (userName, concertName, concertLocation, concertDate, purchaseDate, ticketPrice, ticketCount) values (?, ?, ?, ?, ?, ?, ?);");
			Date now = entity.getPurchaseDate();
			String pattern = "dd.MM.yyyy";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			statement.setString(1, user.getUsername());
			statement.setString(2, entity.getConcertName());
			statement.setString(3, entity.getConcertLocation());
			statement.setDate(4, (java.sql.Date) entity.getConcertDate());
			statement.setString(5, simpleDateFormat.format(now));
			statement.setFloat(6, entity.getTicketPrice());
			statement.setInt(7, entity.getTicketCount());
			statement.executeUpdate();
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return false;
	}
	
	@Override
	public boolean remove(Ticket entity)
	{
		JdbcConcertsRepository concertsRepository = new JdbcConcertsRepository();
		try {
			PreparedStatement statement = this.connection.prepareStatement("delete from Ticket where userName = ? and concertName = ? and concertLocation = ? and concertDate = ? and purchaseDate = ? and ticketPrice = ? and ticketCount = ?;");
			Date now = entity.getPurchaseDate();
			String pattern = "dd.MM.yyyy";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			statement.setString(1, user.getUsername());
			statement.setString(2, entity.getConcertName());
			statement.setString(3, entity.getConcertLocation());
			statement.setDate(4, (java.sql.Date) entity.getConcertDate());
			statement.setString(5, simpleDateFormat.format(now));
			statement.setFloat(6, entity.getTicketPrice());
			statement.setInt(7, entity.getTicketCount());
			statement.executeUpdate();
			
			Concert concert = concertsRepository.findByID(entity.getConcertName());
			concert.setTicketsSold(concert.getTicketsSold() - entity.getTicketCount());
			concertsRepository.update(concert.getName(), concert);
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return false;
	}
	
	@Override
	public Ticket update(User user, Ticket entity)
	{
		return null;
	}
	
	@Override
	public Ticket findByID(User user)
	{
		return null;
	}
	
	@Override
	public List<Ticket> findAll()
	{
		List<Ticket> tickets = new ArrayList<>();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from Ticket;");
			while (resultSet.next()) {
				Ticket ticket = new Ticket();
				ticket.setConcertName(resultSet.getString("concertName"));
				ticket.setConcertLocation(resultSet.getString("concertLocation"));
				ticket.setConcertDate(resultSet.getDate("concertDate"));
				ticket.setPurchaseDate(resultSet.getDate("purchaseDate"));
				ticket.setTicketPrice(resultSet.getFloat("ticketPrice"));
				ticket.setTicketCount(resultSet.getInt("ticketCount"));
				tickets.add(ticket);
			}
			return tickets.isEmpty() ? null : tickets;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public List<Ticket> findAllForUser()
	{
		List<Ticket> tickets = new ArrayList<>();
		try {
			Statement statement = connection.createStatement();
			String queryString = "select * from Ticket where Ticket.userName = '" + this.user.getUsername() + "';";
			ResultSet resultSet = statement.executeQuery(queryString);
			while (resultSet.next()) {
				Ticket ticket = new Ticket();
				ticket.setConcertName(resultSet.getString("concertName"));
				ticket.setConcertLocation(resultSet.getString("concertLocation"));
				ticket.setConcertDate(resultSet.getDate("concertDate"));
				ticket.setPurchaseDate(resultSet.getDate("purchaseDate"));
				ticket.setTicketPrice(resultSet.getFloat("ticketPrice"));
				ticket.setTicketCount(resultSet.getInt("ticketCount"));
				tickets.add(ticket);
			}
			return tickets.isEmpty() ? null : tickets;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public String toString()
	{
		StringBuilder endString = new StringBuilder();
		endString.append("User: ").append(this.user.getUsername()).append("\n");
		List<Ticket> tickets = this.findAllForUser();
		if (tickets == null) return endString.append("[Empty]\n").toString();
		for (Ticket ticket : tickets)
			endString.append(ticket.toString()).append("\n");
		return endString.toString();
	}
}
