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
	String addAlbum(Album album);
	
	String deleteAlbum(Album album);
	
	void modifyAlbum(Album album);
	
	String addArtist(Artist artist);
	
	String deleteArtist(Artist artist);
	
	void modifyArtist(Artist artist);
	
	String addBand(Band band);
	
	String deleteBand(Band band);
	
	void modifyBand(Band band);
	
	String addConcert(Concert concert);
	
	String deleteConcert(Concert concert);
	
	void modifyConcert(Concert concert);
	
	String addMusicLabel(MusicLabel musicLabel);
	
	String deleteMusicLabel(MusicLabel musicLabel);
	
	void modifyMusicLabel(MusicLabel musicLabel);
	
	String addSong(Song song);
	
	String deleteSong(Song song);
	
	void modifySong(Song song);
	
	String addUser(User user);
	
	String deleteUser(User user);
	
	void modifyUser(User user);
	
	void showConcerts();
	
	void sortAlbumsByProductionCost();
}
