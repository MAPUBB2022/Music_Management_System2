package controller;

import interfaces.IAdminController;
import interfaces.ICrudRepository;
import model.album.Album;
import model.album.Artist;
import model.album.Band;
import model.concert.Concert;
import model.label.MusicLabel;
import model.song.Song;
import model.users.User;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.Date;

public class AdminController implements IAdminController
{
	private final ICrudRepository<String, Album> albumList;
	private final ICrudRepository<String, Artist> artistList;
	private final ICrudRepository<String, Concert> concertList;
	private final ICrudRepository<String, Song> songList;
	private final ICrudRepository<String, Band> bandList;
	private final ICrudRepository<String, MusicLabel> labelList;
	private final ICrudRepository<String, User> userList;

	public AdminController(ICrudRepository<String, Album> albumList, ICrudRepository<String, Artist> artistList,
						   ICrudRepository<String, Concert> concertList, ICrudRepository<String, Song> songList,
						   ICrudRepository<String, Band> bandList, ICrudRepository<String, MusicLabel> labelList,
						   ICrudRepository<String, User> userList) {
		this.albumList = albumList;
		this.artistList = artistList;
		this.concertList = concertList;
		this.songList = songList;
		this.bandList = bandList;
		this.labelList = labelList;
		this.userList = userList;
	}

	public ICrudRepository<String, Album> getAlbumList()
	{
		return albumList;
	}

	public ICrudRepository<String, Artist> getArtistList()
	{
		return artistList;
	}

	public ICrudRepository<String, Concert> getConcertList()
	{
		return concertList;
	}

	public ICrudRepository<String, Song> getSongList()
	{
		return songList;
	}
	public ICrudRepository<String, Band> getBandList()
	{
		return bandList;
	}
	public ICrudRepository<String, MusicLabel> getLabelList()
	{
		return labelList;
	}
	public ICrudRepository<String, User> getUserList()
	{
		return userList;
	}


	@Override
	public boolean addAlbum(Album album) throws SQLException {
		if (this.albumList.findAll().contains(album))
			return false;
		this.albumList.add(album);
		return true;
	}

	@Override
	public boolean deleteAlbum(Album album) throws SQLException {
		if (!this.albumList.findAll().contains(album))
			return false;
		this.albumList.remove(album);
		return true;
	}

	@Override
	public boolean modifyAlbum(Album album) throws SQLException {
		for (Album albums : albumList.findAll()) {
			if (albums.equals(album)) {
				albumList.update(String.valueOf(albumList.findAll().indexOf(albums)), album);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean addArtist(Artist artist) throws SQLException {
		if (this.artistList.findAll().contains(artist))
			return false;
		this.artistList.add(artist);
		return true;
	}

	@Override
	public boolean deleteArtist(Artist artist) throws SQLException {
		if (!this.artistList.findAll().contains(artist))
			return false;
		this.artistList.remove(artist);
		return true;
	}

	@Override
	public boolean modifyArtist(Artist artist) throws SQLException {
		for (Artist artists : artistList.findAll()) {
			if (artist.equals(artists)) {
				artistList.update(String.valueOf(artistList.findAll().indexOf(artists)), artist);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean addBand(Band band) throws SQLException {
		if (this.bandList.findAll().contains(band))
			return false;
		this.bandList.add(band);
		return true;
	}

	@Override
	public boolean deleteBand(Band band) throws SQLException {
		if (!this.bandList.findAll().contains(band))
			return false;
		this.bandList.remove(band);
		return true;
	}

	@Override
	public boolean modifyBand(Band band) throws SQLException {
		for (Band bands : bandList.findAll()) {
			if (bands.equals(band)) {
				bandList.update(String.valueOf(bandList.findAll().indexOf(bands)), band);
			}
		}
		return false;
	}

	@Override
	public boolean addConcert(Concert concert) throws SQLException {
		if (this.concertList.findAll().contains(concert))
			return false;
		this.concertList.add(concert);
		return true;
	}

	@Override
	public boolean deleteConcert(Concert concert) throws SQLException {
		if (!this.concertList.findAll().contains(concert))
			return false;
		this.concertList.remove(concert);
		return true;
	}

	@Override
	public boolean modifyConcert(Concert concert) throws SQLException {
		for (Concert concerts : concertList.findAll()) {
			if (concerts.equals(concert)) {
				concertList.update(String.valueOf(concertList.findAll().indexOf(concerts)), concert);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean addMusicLabel(MusicLabel musicLabel) throws SQLException {
		if (this.labelList.findAll().contains(musicLabel))
			return false;
		this.labelList.add(musicLabel);
		return true;
	}

	@Override
	public boolean deleteMusicLabel(MusicLabel musicLabel) throws SQLException {
		if (!this.labelList.findAll().contains(musicLabel))
			return false;
		this.labelList.remove(musicLabel);
		return true;
	}

	@Override
	public boolean modifyMusicLabel(MusicLabel musicLabel) throws SQLException {
		for (MusicLabel labels : labelList.findAll()) {
			if (labels.equals(musicLabel)) {
				labelList.update(String.valueOf(labelList.findAll().indexOf(labels)), musicLabel);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean addSong(Song song) throws SQLException {
		//TODO implementation -> related songs - empty
		if (this.songList.findAll().contains(song))
			return false;
		this.songList.add(song);
		return true;
	}

	@Override
	public boolean deleteSong(Song song) throws SQLException {
		if (!this.songList.findAll().contains(song))
			return false;
		this.songList.remove(song);
		return true;
	}

	@Override
	public boolean modifySong(Song song) throws SQLException {
		for (Song songs : songList.findAll()) {
			if (song.equals(songs)) {
				songList.update(String.valueOf(songList.findAll().indexOf(songs)), song);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean addUser(User user) throws SQLException {
		if(this.userList.findAll().contains(user))
			return false;
		this.userList.add(user);
		return true;
	}

	@Override
	public boolean deleteUser(User user) throws SQLException {
		if (!this.userList.findAll().contains(user))
			return false;
		this.userList.remove(user);
		return true;
	}

	@Override
	public boolean modifyUser(User user) throws SQLException {
		for (User users : userList.findAll()) {
			if (users.equals(user)) {
				userList.update(String.valueOf(userList.findAll().indexOf(users)), user);
				return true;
			}
		}
		return false;
	}

	@Override
	public void sortAlbumsByRevenue() throws NullPointerException, SQLException {
		if (this.albumList.findAll() == null) {
			throw new NullPointerException();
		}
		this.albumList.findAll().sort((album1, album2) -> (int) (album1.calculateProfit() - album2.calculateProfit()));
	}

	@SuppressWarnings("DuplicatedCode")
	@Override
	public void sortSongsByRating() throws NullPointerException, SQLException {
		if (this.songList.findAll() == null) {
			throw new NullPointerException();
		}
		this.songList.findAll().sort(Comparator.comparing(Song::getRating));
	}

	@SuppressWarnings("DuplicatedCode")
	@Override
	public void sortSongsByReleaseDate() throws NullPointerException, SQLException {
		if (this.songList.findAll() == null) {
			throw new NullPointerException();
		}
		this.songList.findAll().sort(Comparator.comparing(Song::getReleaseDate));
	}

	@Override
	public void sortArtistsByName() throws NullPointerException, SQLException {
		if (this.artistList.findAll() == null) {
			throw new NullPointerException();
		}
		this.artistList.findAll().sort(Comparator.comparing(Artist::getStage_name));
	}

	@Override
	public void sortAlbumsByReleaseDate() throws NullPointerException, SQLException {
		if (this.albumList.findAll() == null) {
			throw new NullPointerException();
		}
		this.albumList.findAll().sort(Comparator.comparing(Album::getReleaseDate));
	}

	@Override
	public void sortAlbumsByProductionCost() throws NullPointerException, SQLException {
		if (this.albumList.findAll() == null) {
			throw new NullPointerException();
		}
		this.albumList.findAll().sort(Comparator.comparing(Album::getProductionCost));
	}

	@Override
	public String showConcerts() throws NullPointerException, SQLException {
		if (this.concertList == null) throw new NullPointerException();
		StringBuilder endString = new StringBuilder();
		for (Concert concert : this.concertList.findAll())
			endString.append(concert.toString());
		return endString.toString().equals("") ? "[WARNING] Concert List is Empty\n" : endString.toString();
	}

	@Override
	public String showArtists() throws NullPointerException, SQLException {
		if (this.artistList == null) throw new NullPointerException();
		StringBuilder endString = new StringBuilder();
		for (Artist artist : this.artistList.findAll())
			endString.append(artist.toString());
		return endString.toString().equals("") ? "[WARNING] Artist List is Empty\n" : endString.toString();
	}

	@Override
	public String showAlbums() throws NullPointerException, SQLException {
		if (this.albumList == null) throw new NullPointerException();
		StringBuilder endString = new StringBuilder();
		for (Album album : this.albumList.findAll())
			endString.append(album.toString());
		return endString.toString().equals("") ? "[WARNING] Album List is Empty\n" : endString.toString();
	}

	@Override
	public String showUpcomingConcerts() throws NullPointerException, SQLException {
		if (this.concertList.findAll() == null) {
			throw new NullPointerException();
		}
		StringBuilder endString = new StringBuilder();
		Date today = new Date();
		for (Concert concert : this.concertList.findAll()) {
			if (concert.getDate().after(today)) endString.append(concert.toString());
		}
		return endString.toString().equals("") ? "[WARNING] No Upcoming Concert exist\n" : endString.toString();
	}


}
