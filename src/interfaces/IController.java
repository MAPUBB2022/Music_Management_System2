package interfaces;

import model.album.Artist;

public interface IController
{
	String showArtists();
	
	String showAlbums();
	
	String showUpcomingConcerts();
	
	String sortAlbumsByRevenue();
	
	String sortSongsByRating();
	
	String sortSongsByReleaseDate();
	
	String sortArtistsByName();
	
	String sortAlbumsByReleaseDate();
}
