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
	
	String modifyAlbum(Album album);
	
	String addArtist(Artist artist);
	
	String deleteArtist(Artist artist);
	
	String modifyArtist(Artist artist);
	
	String addBand(Band band);
	
	String deleteBand(Band band);
	
	String modifyBand(Band band);
	
	String addConcert(Concert concert);
	
	String deleteConcert(Concert concert);
	
	String modifyConcert(Concert concert);
	
	String addMusicLabel(MusicLabel musicLabel);
	
	String deleteMusicLabel(MusicLabel musicLabel);
	
	String modifyMusicLabel(MusicLabel musicLabel);
	
	String addSong(Song song);
	
	String deleteSong(Song song);
	
	String modifySong(Song song);
	
	String addUser(User user);
	
	String deleteUser(User user);
	
	String modifyUser(User user);
	
	void showConcerts();
	
	void sortAlbumsByProductionCost();
}
