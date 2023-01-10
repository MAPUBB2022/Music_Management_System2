package repository.jdbc;

import interfaces.ICrudRepository;
import model.album.Album;
import model.song.Song;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// TODO: 09-Jan-23 Song-RelatedSong Table! test->test2; test2->test
public class JdbcSongsRepository implements ICrudRepository<String, Song>
{
	private final Connection connection;
	
	public JdbcSongsRepository()
	{
		this.connection = JdbcConnection.getConnectionInstance();
	}
	
	@Override
	public boolean add(Song entity)
	{
		if (findByID(entity.getName()) == null) {
			try {
				PreparedStatement statement = connection.prepareStatement("insert into Song (name, rating, releaseDate, singer, bandSingers, lyrics, streamCount) values (?, ?, ?, ?, ?, ?, ?);");
				statement.setString(1, entity.getName());
				statement.setFloat(2, entity.getRating());
				statement.setDate(3, (Date) entity.getReleaseDate());
				statement.setString(4, entity.getSinger().getName());
				statement.setString(5, entity.getBandSingers().getName());
				statement.setString(6, entity.getLyrics());
				statement.setInt(7, entity.getStreamCount());
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
	public boolean remove(Song entity)
	{
		if (findByID(entity.getName()) != null) {
			try {
//				PreparedStatement statement = connection.prepareStatement("delete from Album_Song where songName = ?;");
//				statement.setString(1, entity.getName());
//				statement.executeUpdate();
//
//				statement = connection.prepareStatement("delete from Song_RelatedSong where songName = ?;");
//				statement.setString(1, entity.getName());
//				statement.executeUpdate();
				
				PreparedStatement statement = connection.prepareStatement("delete from Song where name = ?;");
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
	public Song update(String s, Song entity)
	{
		Song song = findByID(s);
		if (song != null) {
			try {
				PreparedStatement statement = connection.prepareStatement("update Song set name = ?, rating = ?, releaseDate = ?, singer = ?, bandSingers = ?, lyrics = ?, streamCount = ? where name = ?;");
				statement.setString(1, entity.getName());
				statement.setFloat(2, entity.getRating());
				statement.setDate(3, (Date) entity.getReleaseDate());
				statement.setString(4, entity.getSinger().getName());
				statement.setString(5, entity.getBandSingers().getName());
				statement.setString(6, entity.getLyrics());
				statement.setInt(7, entity.getStreamCount());
				statement.setString(8, s);
				statement.executeUpdate();
				return song;
			}
			catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return null;
	}
	
	@Override
	public Song findByID(String s)
	{
		try {
			PreparedStatement statement = connection.prepareStatement("select * from Song where name = ?");
			statement.setString(1, s);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				JdbcArtistsRepository artistsRepository = new JdbcArtistsRepository();
				JdbcBandsRepository bandsRepository = new JdbcBandsRepository();
				Song song = new Song();
				song.setName(resultSet.getString("name"));
				song.setRating(resultSet.getFloat("rating"));
				song.setReleaseDate(resultSet.getDate("releaseDate"));
				song.setSinger(artistsRepository.findByID(resultSet.getString("singer")));
				song.setBandSingers(bandsRepository.findByID(resultSet.getString("bandSingers")));
				song.setLyrics(resultSet.getString("lyrics"));
				song.setStreamCount(resultSet.getInt("streamCount"));
				song.setRelatedSongs(getRelatedSongs(song.getName()));
				return song;
			} else return null;
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public List<Song> findAll()
	{
		List<Song> songs = new ArrayList<>();
		JdbcArtistsRepository artistsRepository = new JdbcArtistsRepository();
		JdbcBandsRepository bandsRepository = new JdbcBandsRepository();
		
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from Song");
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
			return songs.isEmpty() ? null : songs;
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
		List<Song> songs = this.findAll();
		if (songs == null) return "";
		StringBuilder endString = new StringBuilder();
		for (Song song : songs)
			endString.append(song.toString()).append("\n");
		return endString.toString();
	}
}
