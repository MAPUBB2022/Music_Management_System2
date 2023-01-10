package interfaces;

import model.album.Album;
import model.album.Artist;
import model.album.Band;
import model.concert.Concert;
import model.label.MusicLabel;
import model.song.Song;
import model.users.User;

import java.sql.SQLException;

public interface IAdminController extends IController
{
	boolean addAlbum(Album album) throws SQLException;
	
	boolean deleteAlbum(Album album) throws SQLException;
	
	boolean modifyAlbum(Album album) throws SQLException;
	
	boolean addArtist(Artist artist) throws SQLException;
	
	boolean deleteArtist(Artist artist) throws SQLException;
	
	boolean modifyArtist(Artist artist) throws SQLException;
	
	boolean addBand(Band band) throws SQLException;

	boolean deleteBand(Band band) throws SQLException;

	boolean modifyBand(Band band) throws SQLException;

	boolean addConcert(Concert concert) throws SQLException;

	boolean deleteConcert(Concert concert) throws SQLException;

	boolean modifyConcert(Concert concert) throws SQLException;

	boolean addMusicLabel(MusicLabel musicLabel) throws SQLException;

	boolean deleteMusicLabel(MusicLabel musicLabel) throws SQLException;

	boolean modifyMusicLabel(MusicLabel musicLabel) throws SQLException;

	boolean addSong(Song song) throws SQLException;

	boolean deleteSong(Song song) throws SQLException;

	boolean modifySong(Song song) throws SQLException;
	
	boolean addUser(User user);

	boolean deleteUser(User user) throws SQLException;

	boolean modifyUser(User user) throws SQLException;
	
	String showConcerts() throws SQLException;
	
	void sortAlbumsByProductionCost() throws SQLException;
}
