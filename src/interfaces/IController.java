package interfaces;

import model.album.Artist;

public interface IController
{
	void showArtists();
	
	void showAlbums();
	
	void showAlbumsForArtist(Artist artist);
	
	void showUpcomingConcerts();
	
	void sortAlbumsByRevenue();
	
	void sortSongsByRating();
	
	void sortSongsByReleaseDate();
	
	void sortArtistsByName();
	
	void sortAlbumsByReleaseDate();
}
