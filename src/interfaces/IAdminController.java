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
	void addAlbum(Album album);
	
	void deleteAlbum(Album album);
	
	void modifyAlbum(Album album);
	
	void addArtist(Artist artist);
	
	void deleteArtist(Artist artist);
	
	void modifyArtist(Artist artist);
	
	void addBand(Band band);
	
	void deleteBand(Band band);
	
	void modifyBand(Band band);
	
	void addConcert(Concert concert);
	
	void deleteConcert(Concert concert);
	
	void modifyConcert(Concert concert);
	
	void addMusicLabel(MusicLabel musicLabel);
	
	void deleteMusicLabel(MusicLabel musicLabel);
	
	void modifyMusicLabel(MusicLabel musicLabel);
	
	void addSong(Song song);
	
	void deleteSong(Song song);
	
	void modifySong(Song song);
	
	void addUser(User user);
	
	void deleteUser(User user);
	
	void modifyUser(User user);
	
	void showConcerts();
	
	void sortAlbumsByProductionCost();
}
