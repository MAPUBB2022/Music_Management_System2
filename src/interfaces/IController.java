package interfaces;

import model.album.Artist;
import model.users.User;

public interface IController
{
	String showArtists();
	
	String showAlbums();
	
	String showAlbumsForArtist(Artist artist);
	
	String showUpcomingConcerts();
	
	String sortAlbumsByRevenue();
	
	String sortSongsByRating();
	
	String sortSongsByReleaseDate();
	
	String sortArtistsByName();
	
	String sortAlbumsByReleaseDate();
	
	boolean addUser(User user);
}