package interfaces;

import model.album.Album;
import model.album.Artist;
import model.album.Band;
import model.concert.Concert;
import model.label.MusicLabel;
import model.song.Song;
import model.users.User;

public interface IAdminController extends IController
{
	boolean addAlbum(Album album);
	
	boolean deleteAlbum(Album album);
	
	Album modifyAlbum(Album album);
	
	boolean addArtist(Artist artist);
	
	boolean deleteArtist(Artist artist);
	
	Artist modifyArtist(Artist artist);
	
	boolean addBand(Band band);
	
	boolean deleteBand(Band band);
	
	Band modifyBand(Band band);
	
	boolean addConcert(Concert concert);
	
	boolean deleteConcert(Concert concert);
	
	Concert modifyConcert(Concert concert);
	
	boolean addMusicLabel(MusicLabel musicLabel);
	
	boolean deleteMusicLabel(MusicLabel musicLabel);
	
	MusicLabel modifyMusicLabel(MusicLabel musicLabel);
	
	boolean addSong(Song song);
	
	boolean deleteSong(Song song);
	
	Song modifySong(Song song);
	
	boolean deleteUser(User user);
	
	User modifyUser(User user);
	
	String showConcerts();
	
	void sortAlbumsByProductionCost();
}
