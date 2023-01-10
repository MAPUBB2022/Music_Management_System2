package repository.jdbc;

import interfaces.ICrudRepository;
import interfaces.IUserFieldIdentifiers;
import model.concert.Ticket;
import model.song.Song;
import model.users.User;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcUserFavouritesRepository implements IUserFieldIdentifiers<Song>
{
	private final User user;
	private final Connection connection;
	
	public JdbcUserFavouritesRepository(User user)
	{
		this.user = user;
		this.connection = JdbcConnection.getConnectionInstance();
	}
	
	@Override
	public boolean add(Song entity)
	{
		try {
			PreparedStatement statement = this.connection.prepareStatement("insert into UserFavourites (userName, songName) values (?, ?);");
			statement.setString(1, this.user.getUsername());
			statement.setString(2, entity.getName());
			statement.executeUpdate();
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return false;
	}
	
	@Override
	public boolean remove(Song entity)
	{
		try {
			PreparedStatement statement = this.connection.prepareStatement("delete from UserFavourites where userName = ? and songName = ?;");
			statement.setString(1, this.user.getUsername());
			statement.setString(2, entity.getName());
			statement.executeUpdate();
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return false;
	}
	
	@Override
	public Song update(User user, Song entity)
	{
		//Unnecessary
		return null;
	}
	
	@Override
	public Song findByID(User user)
	{
		//Unnecessary
		return null;
	}
	
	@Override
	public List<Song> findAll()
	{
		List<Song> songs = new ArrayList<>();
		JdbcArtistsRepository artistsRepository = new JdbcArtistsRepository();
		JdbcBandsRepository bandsRepository = new JdbcBandsRepository();
		try {
			Statement statement = this.connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from Song inner join UserFavourites on Song.name = UserFavourites.songName;");
			while (resultSet.next()) {
				Song song = new Song();
				song.setName(resultSet.getString("name"));
				song.setRating(resultSet.getFloat("rating"));
				song.setReleaseDate(resultSet.getDate("releaseDate"));
				song.setSinger(artistsRepository.findByID(resultSet.getString("singer")));
				song.setBandSingers(bandsRepository.findByID(resultSet.getString("bandSingers")));
				song.setLyrics(resultSet.getString("lyrics"));
				song.setStreamCount(resultSet.getInt("streamCount"));
				song.setRelatedSongs(getRelatedSongs(song.getName()));
				songs.add(song);
			}
			return songs.size() == 0 ? null : songs;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public List<Song> findAllForUser()
	{
		List<Song> songs = new ArrayList<>();
		JdbcArtistsRepository artistsRepository = new JdbcArtistsRepository();
		JdbcBandsRepository bandsRepository = new JdbcBandsRepository();
		try {
			Statement statement = this.connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from Song inner join UserFavourites on Song.name = UserFavourites.songName where UserFavourites.userName = '" + this.user.getUsername() + "';");
			while (resultSet.next()) {
				Song song = new Song();
				song.setName(resultSet.getString("name"));
				song.setRating(resultSet.getFloat("rating"));
				song.setReleaseDate(resultSet.getDate("releaseDate"));
				song.setSinger(artistsRepository.findByID(resultSet.getString("singer")));
				song.setBandSingers(bandsRepository.findByID(resultSet.getString("bandSingers")));
				song.setLyrics(resultSet.getString("lyrics"));
				song.setStreamCount(resultSet.getInt("streamCount"));
				song.setRelatedSongs(getRelatedSongs(song.getName()));
				songs.add(song);
			}
			return songs.size() == 0 ? null : songs;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Song> getRelatedSongs(String songName)
	{
		List<Song> songList = new ArrayList<>();
		JdbcArtistsRepository artistsRepository = new JdbcArtistsRepository();
		JdbcBandsRepository bandsRepository = new JdbcBandsRepository();
		
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT s.name, s.rating,s.releaseDate,s.singer, s.bandSingers, s.lyrics, s.streamCount FROM Song s INNER JOIN Song_RelatedSong SRS on s.name = SRS.relatedSongName WHERE SRS.songName = ?");
			statement.setString(1, songName);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Song relatedSong = new Song();
				relatedSong.setName(resultSet.getString("name"));
				relatedSong.setRating(resultSet.getFloat("rating"));
				relatedSong.setReleaseDate(resultSet.getDate("releaseDate"));
				relatedSong.setSinger(artistsRepository.findByID(resultSet.getString("singer")));
				relatedSong.setBandSingers(bandsRepository.findByID(resultSet.getString("bandSingers")));
				relatedSong.setLyrics(resultSet.getString("lyrics"));
				relatedSong.setStreamCount(resultSet.getInt("streamCount"));
				songList.add(relatedSong);
			}
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return songList;
	}
	
	@Override
	public String toString()
	{
		StringBuilder endString = new StringBuilder("User: " + user.getUsername() + "\n");
		List<Song> songs = this.findAllForUser();
		if (songs == null) return endString.append("[Empty]\n").toString();
		for (Song song : songs)
			endString.append(song.getName()).append("\n");
		return endString.toString();
	}
}
