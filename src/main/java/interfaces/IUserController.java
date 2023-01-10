package interfaces;

import model.concert.Concert;
import model.song.Song;
import model.users.User;

/**
 * Interface implements User-specific Functionality
 */
public interface IUserController extends IController
{
	
	/**
	 * Adds a Song to the Favourites Song List.
	 *
	 * @param song Song to be added.
	 * @return True, if the Song was successfully added, False otherwise.
	 */
	boolean addFavourite(Song song);
	
	/**
	 * Deletes a Song to the Favourites Song List.
	 *
	 * @param song Song to be deleted.
	 * @return True, if the Song was successfully removed, False otherwise.
	 */
	boolean removeFavourite(Song song);
	
	/**
	 * Creates a String in human-readable Format of Songs in the Favourites Song List.
	 *
	 * @return True, if the Song was successfully added, False otherwise.
	 */
	String showFavourites();
	
	
	/**
	 * Provides Functionality for User to buy a Ticket for a specified Concert.
	 *
	 * @param concert Concert to be attended.
	 * @param count   Number of Tickets to be purchased.
	 * @param user    User which buys the Tickets.
	 * @return True, if Tickets were purchased successfully, False otherwise.
	 */
	boolean buyTicket(Concert concert, Integer count, User user);
	
	
	/**
	 * Creates a String in human-readable Format of all the Tickets in the List.
	 *
	 * @return String of Tickets.
	 */
	String showTickets();
	
	
	/**
	 * Function provides Functionality for the User to access some recommended Songs based on his Favourites
	 * and the already existing Songs.
	 *
	 * @return String of recommended Songs.
	 */
	String showRecommended();
}
