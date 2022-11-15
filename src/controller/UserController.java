package controller;

import interfaces.ICrudRepository;
import interfaces.IUserController;
import model.album.Album;
import model.album.Artist;
import model.album.Band;
import model.concert.Concert;
import model.label.MusicLabel;
import model.song.Song;
import model.users.User;


public class UserController implements IUserController
{
	private ICrudRepository<Integer, Album> albumList;
	private ICrudRepository<Integer, Artist> artistList;
	private ICrudRepository<Integer, Band> bandList;
	private ICrudRepository<Integer, Concert> concertList;
	private ICrudRepository<Integer, MusicLabel> musicLabelList;
	private ICrudRepository<Integer, Song> songList;
	
	public UserController(ICrudRepository<Integer, Album> albumList, ICrudRepository<Integer, Artist> artistList, ICrudRepository<Integer, Band> bandList, ICrudRepository<Integer, Concert> concertList, ICrudRepository<Integer, MusicLabel> musicLabelList, ICrudRepository<Integer, Song> songList)
	{
		this.albumList = albumList;
		this.artistList = artistList;
		this.bandList = bandList;
		this.concertList = concertList;
		this.musicLabelList = musicLabelList;
		this.songList = songList;
	}
	
	@Override
	public String showArtists()
	{

		return null;
	}
	
	@Override
	public String showAlbums()
	{

		return null;
	}
	
	@Override
	public String showUpcomingConcerts()
	{

		return null;
	}
	
	@Override
	public String sortAlbumsByRevenue()
	{
		return null;
	}
	
	@Override
	public String sortSongsByRating()
	{
		return null;
	}
	
	@Override
	public String sortSongsByReleaseDate()
	{
		return null;
	}
	
	@Override
	public String sortArtistsByName()
	{
		return null;
	}
	
	@Override
	public String sortAlbumsByReleaseDate()
	{
		return null;
	}

	@Override
	public boolean addUser(User user) {
		return false;
	}

	@Override
	public void addFavourite(Song song)
	{
	
	}
	
	@Override
	public void removeFavourite(Song song)
	{
	
	}
	
	@Override
	public void showFavourites()
	{
	
	}
	
	@Override
	public void buyTicket(Concert concert)
	{
	
	}
	
	@Override
	public void showRecommended()
	{
	
	}
}
