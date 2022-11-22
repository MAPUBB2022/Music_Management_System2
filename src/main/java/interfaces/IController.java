package interfaces;

import model.users.User;

public interface IController
{
	String showArtists();
	
	String showAlbums();
	
	String showUpcomingConcerts();
	
	void sortAlbumsByRevenue();

	void sortSongsByRating();

	void sortSongsByReleaseDate();

	void sortArtistsByName();

	void sortAlbumsByReleaseDate();

	boolean addUser(User user);
}
