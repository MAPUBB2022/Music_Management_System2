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
	
	boolean modifyAlbum(Album album);
	
	boolean addArtist(Artist artist);
	
	boolean deleteArtist(Artist artist);
	
	boolean modifyArtist(Artist artist);
	
	boolean addBand(Band band);

	boolean deleteBand(Band band);

	boolean modifyBand(Band band);

	boolean addConcert(Concert concert);

	boolean deleteConcert(Concert concert);

	boolean modifyConcert(Concert concert);

	boolean addMusicLabel(MusicLabel musicLabel);

	boolean deleteMusicLabel(MusicLabel musicLabel);

	boolean modifyMusicLabel(MusicLabel musicLabel);

	boolean addSong(Song song);

	boolean deleteSong(Song song);

	boolean modifySong(Song song);
	
	boolean addUser(User user);

	boolean deleteUser(User user);

	boolean modifyUser(User user);
	
	String showConcerts();
	
	void sortAlbumsByProductionCost();
}
