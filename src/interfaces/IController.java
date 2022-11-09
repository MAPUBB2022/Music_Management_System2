package interfaces;

import model.album.Artist;

public interface IController
{
	void showArtists();
	
	void showAlbums();
	
	void showAlbumsForArtist(Artist artist);
	
	void showUpcomingConcerts();
	
	String sortAlbumsByRevenue();
	
	String sortSongsByRating();
	
	String sortSongsByReleaseDate();
	
	String sortArtistsByName();
	
	String sortAlbumsByReleaseDate();
}
