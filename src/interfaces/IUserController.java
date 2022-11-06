package interfaces;

import model.concert.Concert;
import model.song.Song;

public interface IUserController extends IController
{
	String addFavourite(Song song);
	
	String  removeFavourite(Song song);
	
	String showFavourites();
	
	String buyTicket(Concert concert);
	
	String showTickets();
	
	String showRecommended();
}
