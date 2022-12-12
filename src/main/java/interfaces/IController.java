package interfaces;

import model.users.User;

import java.sql.SQLException;

public interface IController
{
	String showArtists() throws SQLException;
	
	String showAlbums() throws SQLException;
	
	String showUpcomingConcerts() throws SQLException;
	
	void sortAlbumsByRevenue() throws SQLException;

	void sortSongsByRating() throws SQLException;

	void sortSongsByReleaseDate() throws SQLException;

	void sortArtistsByName() throws SQLException;

	void sortAlbumsByReleaseDate() throws SQLException;

	boolean addUser(User user) throws SQLException;
}
