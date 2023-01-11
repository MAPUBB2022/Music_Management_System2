package repository.jdbc;

import interfaces.ICrudRepository;
import model.album.Album;
import model.album.Artist;
import model.concert.Concert;
import model.label.MusicLabel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcMusicLabelsRepository implements ICrudRepository<String, MusicLabel>
{
	private final Connection connection;
	
	public JdbcMusicLabelsRepository()
	{
		this.connection = JdbcConnection.getConnectionInstance();
	}
	
	@Override
	public boolean add(MusicLabel entity)
	{
		if (findByID(entity.getName()) == null) {
			try {
				PreparedStatement statement = connection.prepareStatement("insert into MusicLabel (name, address, revenue) values (?, ?, ?);");
				statement.setString(1, entity.getName());
				statement.setString(2, entity.getAddress());
				statement.setFloat(3, entity.getRevenue());
				statement.executeUpdate();
				
				for (Artist artist : entity.getArtistList()) {
					statement = connection.prepareStatement("insert into Artist (stageName, name, salary) values (?, ?, ?);");
					statement.setString(1, artist.getStageName());
					statement.setString(2, artist.getName());
					statement.setFloat(3, artist.getSalary());
					statement.executeUpdate();
					
					statement = connection.prepareStatement("insert into MusicLabel_Artist (musicLabelName, artistName) values (?, ?);");
					statement.setString(1, entity.getName());
					statement.setString(2, artist.getStageName());
					statement.executeUpdate();
				}
				
				for (Album album : entity.getAlbumList()) {
					statement = connection.prepareStatement("insert into Album (title, artist, band, language, productionCost, releaseDate, copiesSold, discPrice, MusicLabelName) values (?, ?, ?, ?, ?, ?, ?, ?, ?);");
					statement.setString(1, album.getTitle());
					statement.setString(2, album.getArtist().getStageName());
					statement.setString(3, album.getBand().getName());
					statement.setString(4, album.getLanguage());
					statement.setFloat(5, album.getProductionCost());
					statement.setDate(6, (Date) album.getReleaseDate());
					statement.setInt(7, album.getCopiesSold());
					statement.setFloat(8, album.getDiscPrice());
					statement.setString(9, entity.getName());
					statement.executeUpdate();
				}
				
				for (Concert concert : entity.getUpcomingEvents()) {
					statement = connection.prepareStatement("insert into Concert (name, location, date, capacity, ticketPrice, ticketsSold, rentCosts, MusicLabelName) values (?, ?, ?, ?, ?, ?, ?, ?);");
					statement.setString(1, concert.getName());
					statement.setString(2, concert.getLocation());
					statement.setDate(3, (Date) concert.getDate());
					statement.setInt(4, concert.getCapacity());
					statement.setFloat(5, concert.getTicketPrice());
					statement.setInt(6, concert.getTicketsSold());
					statement.setFloat(7, concert.getRentCosts());
					statement.setString(8, entity.getName());
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
	public boolean remove(MusicLabel entity)
	{
		if (findByID(entity.getName()) != null) {
			try {
//				PreparedStatement statement = connection.prepareStatement("delete from MusicLabel_Artist where musicLabelName = ?;");
//				statement.setString(1, entity.getName());
//				statement.executeUpdate();
//
//				statement = connection.prepareStatement("delete from Concert where musicLabelName = ?;");
//				statement.setString(1, entity.getName());
//				statement.executeUpdate();
//
//				statement = connection.prepareStatement("delete from Album where musicLabelName = ?;");
//				statement.setString(1, entity.getName());
//				statement.executeUpdate();
				
				PreparedStatement statement = connection.prepareStatement("delete from MusicLabel where name = ?;");
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
	public MusicLabel update(String s, MusicLabel entity)
	{
		MusicLabel musicLabel = findByID(s);
		if (musicLabel != null) {
			try {
				PreparedStatement statement = connection.prepareStatement("update MusicLabel set name = ?, address = ?, revenue = ? where name = ?;");
				statement.setString(1, entity.getName());
				statement.setString(2, entity.getAddress());
				statement.setFloat(3, entity.getRevenue());
				statement.setString(4, s);
				statement.executeUpdate();
				
				return musicLabel;
			}
			catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return null;
	}
	
	@Override
	public MusicLabel findByID(String s)
	{
		try {
			MusicLabel musicLabel = new MusicLabel();
			PreparedStatement statement = connection.prepareStatement("select * from MusicLabel where name = ?;");
			statement.setString(1, s);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				musicLabel.setName(resultSet.getString("name"));
				musicLabel.setAddress(resultSet.getString("address"));
				musicLabel.setRevenue(resultSet.getFloat("revenue"));
				musicLabel.setArtistList(findArtistsForMusicLabel(s));
				musicLabel.setAlbumList(findAlbumsForMusicLabel(s));
				musicLabel.setUpcomingEvents(findConcertsForMusicLabel(s));
				return musicLabel;
			} else return null;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public List<MusicLabel> findAll()
	{
		List<MusicLabel> musicLabels = new ArrayList<>();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from MusicLabel;");
			while (resultSet.next()) {
				MusicLabel musicLabel = new MusicLabel();
				musicLabel.setName(resultSet.getString("name"));
				musicLabel.setAddress(resultSet.getString("address"));
				musicLabel.setRevenue(resultSet.getFloat("revenue"));
				musicLabel.setArtistList(findArtistsForMusicLabel(musicLabel.getName()));
				musicLabel.setAlbumList(findAlbumsForMusicLabel(musicLabel.getName()));
				musicLabel.setUpcomingEvents(findConcertsForMusicLabel(musicLabel.getName()));
				musicLabels.add(musicLabel);
			}
			return musicLabels.isEmpty() ? null : musicLabels;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private List<Artist> findArtistsForMusicLabel(String labelName)
	{
		List<Artist> artists = new ArrayList<>();
		try {
			PreparedStatement statement = connection.prepareStatement("select a.stageName, a.name, a.salary from Artist a inner join MusicLabel_Artist MLA on a.stageName = MLA.artistName where MLA.musicLabelName = ?;");
			statement.setString(1, labelName);
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
	
	private List<Album> findAlbumsForMusicLabel(String labelName)
	{
		List<Album> albums = new ArrayList<>();
		JdbcArtistsRepository artistsRepository = new JdbcArtistsRepository();
		JdbcBandsRepository bandsRepository = new JdbcBandsRepository();
		JdbcAlbumsRepository albumsRepository = new JdbcAlbumsRepository();
		
		try {
			PreparedStatement statement = connection.prepareStatement("select * from Album where MusicLabelName = ?;");
			statement.setString(1, labelName);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Album album = new Album();
				album.setTitle(resultSet.getString("title"));
				album.setArtist(artistsRepository.findByID(resultSet.getString("artist")));
				album.setBand(bandsRepository.findByID(resultSet.getString("band")));
				album.setLanguage(resultSet.getString("language"));
				album.setProductionCost(resultSet.getFloat("productionCost"));
				album.setReleaseDate(resultSet.getDate("releaseDate"));
				album.setCopiesSold(resultSet.getInt("copiesSold"));
				album.setDiscPrice(resultSet.getFloat("discPrice"));
				album.setSongList(albumsRepository.findSongsForAlbum(album.getTitle()));
				albums.add(album);
			}
			return albums.isEmpty() ? null : albums;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private List<Concert> findConcertsForMusicLabel(String labelName)
	{
		List<Concert> concerts = new ArrayList<>();
		JdbcConcertsRepository concertsRepository = new JdbcConcertsRepository();
		
		try {
			PreparedStatement statement = connection.prepareStatement("select * from Concert where MusicLabelName = ?;");
			statement.setString(1, labelName);
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				Concert concert = new Concert();
				concert.setName(resultSet.getString("name"));
				concert.setLocation(resultSet.getString("location"));
				concert.setDate(resultSet.getDate("date"));
				concert.setCapacity(resultSet.getInt("capacity"));
				concert.setTicketPrice(resultSet.getFloat("ticketPrice"));
				concert.setTicketsSold(resultSet.getInt("ticketsSold"));
				concert.setRentCosts(resultSet.getFloat("rentCosts"));
				concert.setArtistList(concertsRepository.findArtistsForConcert(concert.getName()));
				concerts.add(concert);
			}
			return concerts.isEmpty() ? null : concerts;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public String toString()
	{
		List<MusicLabel> musicLabels = this.findAll();
		if (musicLabels == null) return "";
		StringBuilder endString = new StringBuilder();
		for (MusicLabel musicLabel : musicLabels)
			endString.append(musicLabel.toString()).append("\n");
		return endString.toString();
	}
}
