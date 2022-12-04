package interfaces;

import model.concert.Concert;
import model.song.Song;

public interface IUserController extends IController
{
	boolean addFavourite(Song song);
	
	boolean  removeFavourite(Song song);
	
	String showFavourites();
	
	boolean buyTicket(Concert concert, Integer count);
	
	String showTickets();
	
	String showRecommended();
}
