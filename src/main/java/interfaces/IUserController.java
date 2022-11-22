package interfaces;

import model.concert.Concert;
import model.song.Song;

public interface IUserController extends IController
{
	void addFavourite(Song song);
	
	void removeFavourite(Song song);
	
	void showFavourites();
	
	void buyTicket(Concert concert);
	
	void showRecommended();
}
