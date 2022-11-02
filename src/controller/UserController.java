package controller;

import interfaces.ICrudRepository;
import interfaces.IUserController;
import model.album.Album;
import model.album.Artist;
import model.album.Band;
import model.concert.Concert;
import model.label.MusicLabel;
import model.song.Song;


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
	public void showArtists()
	{
	
	}
	
	@Override
	public void showAlbums()
	{
	
	}
	
	@Override
	public void showAlbumsForArtist(Artist artist)
	{
	
	}
	
	@Override
	public void showUpcomingConcerts()
	{
	
	}
	
	@Override
	public void sortAlbumsByRevenue()
	{
	
	}
	
	@Override
	public void sortSongsByRating()
	{
	
	}
	
	@Override
	public void sortSongsByReleaseDate()
	{
	
	}
	
	@Override
	public void sortArtistsByName()
	{
	
	}
	
	@Override
	public void sortAlbumsByReleaseDate()
	{
	
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
