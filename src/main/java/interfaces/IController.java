package interfaces;

import model.album.Artist;
import model.users.User;

/**
 * Interface implements Common Program Functionality available for both User and Administrator.
 */
public interface IController
{
	/**
	 * Transforms Artists from Repository into a human-readable Format.
	 *
	 * @return String containing All Artists.
	 */
	String showArtists();
	
	/**
	 * Transforms Albums from Repository into a human-readable Format.
	 *
	 * @return String containing All Albums.
	 */
	String showAlbums();
	
	/**
	 * Transforms Albums of a specified Artist from Repository into a human-readable Format.
	 *
	 * @return String containing Artist-specific Albums.
	 */
	String showAlbumsForArtist(Artist artist);
	
	/**
	 * Shows all Upcoming Concerts from Repository into a human-readable Format.
	 * The reference Date is initialized at the Function's Execution.
	 *
	 * @return String containing All Upcoming Concerts.
	 */
	String showUpcomingConcerts();
	
	/**
	 * Sorts all Albums from Repository by Revenue.
	 */
	void sortAlbumsByRevenue();
	
	/**
	 * Sorts all Songs from Repository by Rating.
	 */
	void sortSongsByRating();
	
	/**
	 * Sorts all Songs from Repository by Release Date.
	 */
	void sortSongsByReleaseDate();
	
	/**
	 * Sorts all Artists from Repository by Stage Name.
	 */
	void sortArtistsByName();
	
	/**
	 * Sorts all Albums from Repository by Release Date.
	 */
	void sortAlbumsByReleaseDate();
	
	/**
	 * Function adds a new User to its specific Repository.
	 *
	 * @param user User to be added.
	 * @return True, if User was successfully added, False otherwise.
	 */
	boolean addUser(User user);
}