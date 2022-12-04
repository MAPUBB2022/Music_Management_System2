package controller;

import interfaces.IAdminController;
import model.album.Album;
import model.album.Artist;
import model.album.Band;
import model.concert.Concert;
import model.label.MusicLabel;
import model.song.Song;
import model.users.User;

public class AdminController implements IAdminController
{
	
	@Override
	public boolean addAlbum(Album album)
	{
		return false;
	}
	
	@Override
	public boolean deleteAlbum(Album album)
	{
		return false;
	}
	
	@Override
	public Album modifyAlbum(Album album)
	{
		return null;
	}
	
	@Override
	public boolean addArtist(Artist artist)
	{
		return false;
	}
	
	@Override
	public boolean deleteArtist(Artist artist)
	{
		return false;
	}
	
	@Override
	public Artist modifyArtist(Artist artist)
	{
		return null;
	}
	
	@Override
	public boolean addBand(Band band)
	{
		return false;
	}
	
	@Override
	public boolean deleteBand(Band band)
	{
		return false;
	}
	
	@Override
	public Band modifyBand(Band band)
	{
		return null;
	}
	
	@Override
	public boolean addConcert(Concert concert)
	{
		return false;
	}
	
	@Override
	public boolean deleteConcert(Concert concert)
	{
		return false;
	}
	
	@Override
	public Concert modifyConcert(Concert concert)
	{
		return null;
	}
	
	@Override
	public boolean addMusicLabel(MusicLabel musicLabel)
	{
		return false;
	}
	
	@Override
	public boolean deleteMusicLabel(MusicLabel musicLabel)
	{
		return false;
	}
	
	@Override
	public MusicLabel modifyMusicLabel(MusicLabel musicLabel)
	{
		return null;
	}
	
	@Override
	public boolean addSong(Song song)
	{
		return false;
	}
	
	@Override
	public boolean deleteSong(Song song)
	{
		return false;
	}
	
	@Override
	public Song modifySong(Song song)
	{
		return null;
	}
	
	@Override
	public boolean deleteUser(User user)
	{
		return false;
	}
	
	@Override
	public User modifyUser(User user)
	{
		return null;
	}
	
	@Override
	public String showConcerts()
	{
		return null;
	}
	
	@Override
	public void sortAlbumsByProductionCost()
	{
	
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
	public String showAlbumsForArtist(Artist artist)
	{
		return null;
	}
	
	@Override
	public String showUpcomingConcerts()
	{
		return null;
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
	public boolean addUser(User user)
	{
		return false;
	}
}
