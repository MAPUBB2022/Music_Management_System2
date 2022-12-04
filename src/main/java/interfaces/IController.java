package interfaces;

import model.album.Artist;
import model.users.User;

public interface IController
{
	String showArtists();
	
	String showAlbums();
	
	String showAlbumsForArtist(Artist artist);
	
	String showUpcomingConcerts();
	
	void sortAlbumsByRevenue();
	
	void sortSongsByRating();
	
	void sortSongsByReleaseDate();
	
	void sortArtistsByName();
	
	void sortAlbumsByReleaseDate();
	
	boolean addUser(User user);
}