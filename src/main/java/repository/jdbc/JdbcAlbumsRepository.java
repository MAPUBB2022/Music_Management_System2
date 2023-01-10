package repository.jdbc;

import interfaces.ICrudRepository;
import model.album.Album;
import model.song.Song;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcAlbumsRepository implements ICrudRepository<String, Album>
{
	private final Connection connection;
	
	public JdbcAlbumsRepository()
	{
		this.connection = JdbcConnection.getConnectionInstance();
	}
	
	@Override
	public boolean add(Album entity)
	{
		if (findByID(entity.getTitle()) == null) {
			try {
				PreparedStatement statement = connection.prepareStatement("INSERT INTO Album(title, artist, band, language, productionCost, releaseDate, copiesSold, discPrice) VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
				statement.setString(1, entity.getTitle());
				statement.setString(2, entity.getArtist().getStageName());
				statement.setString(3, entity.getBand().getName());
				statement.setString(4, entity.getLanguage());
				statement.setFloat(5, entity.getProductionCost());
				statement.setDate(6, (Date) entity.getReleaseDate());
				statement.setInt(7, entity.getCopiesSold());
				statement.setFloat(8, entity.getDiscPrice());
				statement.executeUpdate();
				
				for (Song song : entity.getSongList()) {
					statement = connection.prepareStatement("INSERT INTO Album_Song VALUES (?, ?);");
					statement.setString(1, entity.getTitle());
					statement.setString(2, song.getName());
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
	public boolean remove(Album entity)
	{
		if (findByID(entity.getTitle()) != null) {
			try {
//				PreparedStatement statement = connection.prepareStatement("DELETE FROM Album_Song WHERE albumName = ?;");
//				statement.setString(1, entity.getTitle());
//				statement.executeUpdate();
				
				PreparedStatement statement = connection.prepareStatement("DELETE FROM Album WHERE title = ?;");
				statement.setString(1, entity.getTitle());
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
	public Album update(String s, Album entity)
	{
		Album album = findByID(s);
		if (album != null) {
			try {
				PreparedStatement statement = connection.prepareStatement("UPDATE Album SET title = ?, artist = ?, band = ?, language = ?, productionCost = ?, releaseDate = ?, copiesSold = ?, discPrice = ? WHERE title = ?;");
				statement.setString(1, entity.getTitle());
				statement.setString(2, entity.getArtist().getStageName());
				statement.setString(3, entity.getBand().getName());
				statement.setString(4, entity.getLanguage());
				statement.setFloat(5, entity.getProductionCost());
				statement.setDate(6, (Date) entity.getReleaseDate());
				statement.setInt(7, entity.getCopiesSold());
				statement.setFloat(8, entity.getDiscPrice());
				statement.setString(9, s);
				statement.executeUpdate();
				
//				statement = connection.prepareStatement("UPDATE Album_Song SET albumName = ? WHERE albumName = ?;");
//				statement.setString(1, entity.getTitle());
//				statement.setString(2, s);
				
				return album;
			}
			catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return null;
	}
	
	@Override
	public Album findByID(String s)
	{
		JdbcArtistsRepository artistsRepository = new JdbcArtistsRepository();
		JdbcBandsRepository bandsRepository = new JdbcBandsRepository();
		
		try {
			Album album = new Album();
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM Album WHERE title = ?;");
			statement.setString(1, s);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				album.setTitle(resultSet.getString("title"));
				album.setArtist(artistsRepository.findByID(resultSet.getString("artist")));//
				album.setBand(bandsRepository.findByID(resultSet.getString("band")));
				album.setLanguage(resultSet.getString("language"));
				album.setProductionCost(resultSet.getFloat("productionCost"));
				album.setReleaseDate(resultSet.getDate("releaseDate"));
				album.setCopiesSold(resultSet.getInt("copiesSold"));
				album.setDiscPrice(resultSet.getFloat("discPrice"));
				album.setSongList(findSongsForAlbum(album.getTitle()));
				
				return album;
			} else return null;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public List<Album> findAll()
	{
		List<Album> albums = new ArrayList<>();
		JdbcArtistsRepository artistsRepository = new JdbcArtistsRepository();
		JdbcBandsRepository bandsRepository = new JdbcBandsRepository();
		
		try {
			Statement statement = this.connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Album;");
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
				album.setSongList(findSongsForAlbum(album.getTitle()));
				albums.add(album);
			}
			return albums.isEmpty() ? null : albums;
		}
		catch (SQLException exception) {
			throw new RuntimeException(exception);
		}
	}
	
	public List<Song> findSongsForAlbum(String albumTitle)
	{
		List<Song> songs = new ArrayList<>();
		JdbcArtistsRepository artistsRepository = new JdbcArtistsRepository();
		JdbcBandsRepository bandsRepository = new JdbcBandsRepository();
		JdbcSongsRepository songsRepository = new JdbcSongsRepository();
		
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT s.name, s.rating,s.releaseDate,s.singer, s.bandSingers, s.lyrics, s.streamCount FROM Song s INNER JOIN Album_Song [AS] on s.name = [AS].songName WHERE [AS].albumName = ?;");
			statement.setString(1, albumTitle);
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				Song song = new Song();
				song.setName(resultSet.getString("name"));
				song.setRating(resultSet.getFloat("rating"));
				song.setReleaseDate(resultSet.getDate("releaseDate"));
				song.setSinger(artistsRepository.findByID(resultSet.getString("singer")));
				song.setBandSingers(bandsRepository.findByID(resultSet.getString("bandSingers")));
				song.setLyrics(resultSet.getString("lyrics"));
				song.setStreamCount(resultSet.getInt("streamCount"));
				song.setRelatedSongs(songsRepository.getRelatedSongs(song.getName()));
				songs.add(song);
			}
			return songs.isEmpty() ? null : songs;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public String toString()
	{
		List<Album> albums = this.findAll();
		if (albums == null) return "";
		StringBuilder endString = new StringBuilder();
		for (Album album : albums)
			endString.append(album.toString()).append("\n");
		return endString.toString();
	}
}
