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
import org.w3c.dom.ls.LSOutput;

import java.util.Comparator;
import java.util.Date;

public class AdminController implements IAdminController
{
	private final ICrudRepository<Integer, Album> albumList;
	private final ICrudRepository<Integer, Artist> artistList;
	private final ICrudRepository<String, Concert> concertList;
	private final ICrudRepository<Integer, Song> songList;
	private final ICrudRepository<Integer, Band> bandList;
	private final ICrudRepository<Integer, MusicLabel> labelList;
	private final ICrudRepository<Integer, User> userList;

	public AdminController(ICrudRepository<Integer, Album> albumList, ICrudRepository<Integer, Artist> artistList,
						   ICrudRepository<String, Concert> concertList, ICrudRepository<Integer, Song> songList,
						   ICrudRepository<Integer, Band> bandList, ICrudRepository<Integer, MusicLabel> labelList,
						   ICrudRepository<Integer, User> userList) {
		this.albumList = albumList;
		this.artistList = artistList;
		this.concertList = concertList;
		this.songList = songList;
		this.bandList = bandList;
		this.labelList = labelList;
		this.userList = userList;
	}

	@Override
	public String addAlbum(Album album)
	{
		if(this.albumList.findAll().contains(album))
			return "[ERROR] Album already contained in List\n";
		this.albumList.add(album);
		return "[] Album added\n";
	}
	
	@Override
	public String deleteAlbum(Album album)
	{
		if (!this.albumList.findAll().contains(album))
			return "[ERROR] Song not in List\n";
		this.albumList.remove(album);
		return "[] Album deleted\n";
	}
	
	@Override
	public String modifyAlbum(Album album)
	{
		for(Album albums:albumList.findAll()) {
			if (albums.equals(album)) {
				albumList.update(albumList.findAll().indexOf(albums), album);
				return "Album modified successfully\n";
			}
		}
		return "[ERROR] Album not in List\n";
	}
	
	@Override
	public String addArtist(Artist artist)
	{
		if(this.artistList.findAll().contains(artist))
			return "[ERROR] Artist already contained in List\n";
		this.artistList.add(artist);
		return "[] Artist added\n";
	}
	
	@Override
	public String deleteArtist(Artist artist)
	{
		if (!this.artistList.findAll().contains(artist))
			return "[ERROR] Artist not in List\n";
		this.artistList.remove(artist);
		return "[] Artist deleted\n";
	}
	
	@Override
	public String modifyArtist(Artist artist)
	{
		for(Artist artists:artistList.findAll()) {
			if (artist.equals(artists)) {
				artistList.update(artistList.findAll().indexOf(artists), artist);
				return "Artist modified successfully\n";
			}
		}
		return "[ERROR] Artist not in List\n";
	}
	
	@Override
	public String addBand(Band band)
	{
		if(this.bandList.findAll().contains(band))
			return "[ERROR] Band already contained in List\n";
		this.bandList.add(band);
		return "[] Band added\n";
	}
	
	@Override
	public String deleteBand(Band band)
	{
		if (!this.bandList.findAll().contains(band))
			return "[ERROR] Band not in List\n";
		this.bandList.remove(band);
		return "[] Band deleted\n";
	}
	
	@Override
	public String modifyBand(Band band)
	{
		for(Band bands:bandList.findAll()) {
			if (bands.equals(band)) {
				bandList.update(bandList.findAll().indexOf(bands), band);
				return "Band modified successfully\n";
			}
		}
		return "[ERROR] Band not in List\n";
	}
	
	@Override
	public String addConcert(Concert concert)
	{
		if(this.concertList.findAll().contains(concert))
			return "[ERROR] Concert already contained in List\n";
		this.concertList.add(concert);
		return "[] Concert added\n";
	}
	
	@Override
	public String deleteConcert(Concert concert)
	{
		if (!this.concertList.findAll().contains(concert))
			return "[ERROR] Concert not in List\n";
		this.concertList.remove(concert);
		return "[] Concert deleted\n";
	}
	
	@Override
	public String modifyConcert(Concert concert)
	{
		for(Concert concerts:concertList.findAll()) {
			if (concerts.equals(concert)) {
				concertList.update(String.valueOf(concertList.findAll().indexOf(concerts)), concert);
				return "Concert modified successfully\n";
			}
		}
		return "[ERROR] Concert not in List\n";
	}
	
	@Override
	public String addMusicLabel(MusicLabel musicLabel)
	{
		if(this.labelList.findAll().contains(musicLabel))
			return "[ERROR] Label already contained in List\n";
		this.labelList.add(musicLabel);
		return "[] Label added\n";
	}
	
	@Override
	public String deleteMusicLabel(MusicLabel musicLabel)
	{
		if (!this.labelList.findAll().contains(musicLabel))
			return "[ERROR] Label not in List\n";
		this.labelList.remove(musicLabel);
		return "[] Label deleted\n";
	}
	
	@Override
	public String modifyMusicLabel(MusicLabel musicLabel)
	{
		for(MusicLabel labels:labelList.findAll()) {
			if (labels.equals(musicLabel)) {
				labelList.update(labelList.findAll().indexOf(labels), musicLabel);
				return "Label modified successfully\n";
			}
		}
		return "[ERROR] Label not in List\n";
	}
	
	@Override
	public String addSong(Song song)
	{
		if(this.songList.findAll().contains(song))
			return "[ERROR] Song already contained in List\n";
		this.songList.add(song);
		return "[] Song added\n";
	}
	
	@Override
	public String deleteSong(Song song)
	{
		if (!this.songList.findAll().contains(song))
			return "[ERROR] Song not in List\n";
		this.songList.remove(song);
		return "[] Song deleted\n";
	}
	
	@Override
	public String modifySong(Song song)
	{
		for(Song songs:songList.findAll()) {
			if (song.equals(songs)) {
				songList.update(songList.findAll().indexOf(songs), song);
				return "Song modified successfully\n";
			}
		}
		return "[ERROR] Song not in List\n";
	}
	
	@Override
	public String addUser(User user)
	{
		if(this.userList.findAll().contains(user))
			return "[ERROR] User already contained in List\n";
		this.userList.add(user);
		return "[] User added\n";
	}
	
	@Override
	public String deleteUser(User user)
	{
		if (!this.userList.findAll().contains(user))
			return "[ERROR] User not in List\n";
		this.userList.remove(user);
		return "[] User deleted\n";
	}
	
	@Override
	public String modifyUser(User user)
	{
		for(User users:userList.findAll()) {
			if (users.equals(user)) {
				userList.update(userList.findAll().indexOf(users), user);
				return "User modified successfully\n";
			}
		}
		return "[ERROR] User not in List\n";
	}

	@Override
	public String sortAlbumsByRevenue() throws NullPointerException
	{
		if (this.albumList.findAll() == null) {
			throw new NullPointerException();
		}
		StringBuilder endString = new StringBuilder();
		this.albumList.findAll().sort((album1, album2) -> (int) (album1.calculateProfit() - album2.calculateProfit()));
		for (Album album : this.albumList.findAll())
			endString.append(album.toString());
		return endString.toString().equals("") ? "[WARNING] Artist List is Empty\n" : endString.toString();
	}

	@SuppressWarnings("DuplicatedCode")
	@Override
	public String sortSongsByRating() throws NullPointerException
	{
		if (this.songList.findAll() == null) {
			throw new NullPointerException();
		}
		StringBuilder endString = new StringBuilder();
		this.songList.findAll().sort(Comparator.comparing(Song::getRating));
		for (Song song : this.songList.findAll())
			endString.append(song.toString());
		return endString.toString().equals("") ? "[WARNING] Song List is Empty\n" : endString.toString();
	}

	@SuppressWarnings("DuplicatedCode")
	@Override
	public String sortSongsByReleaseDate() throws NullPointerException
	{
		if (this.songList.findAll() == null) {
			throw new NullPointerException();
		}
		StringBuilder endString = new StringBuilder();
		this.songList.findAll().sort(Comparator.comparing(Song::getReleaseDate));
		for (Song song : this.songList.findAll())
			endString.append(song.toString());
		return endString.toString().equals("") ? "[WARNING] Song List is Empty\n" : endString.toString();
	}

	@Override
	public String sortArtistsByName() throws NullPointerException
	{
		if (this.artistList.findAll() == null) {
			throw new NullPointerException();
		}
		StringBuilder endString = new StringBuilder();
		this.artistList.findAll().sort(Comparator.comparing(Artist::getStage_name));
		for (Artist artist : this.artistList.findAll())
			endString.append(artist.toString());
		return endString.toString().equals("") ? "[WARNING] Artist List is Empty\n" : endString.toString();
	}

	@Override
	public String sortAlbumsByReleaseDate() throws NullPointerException
	{
		if (this.albumList.findAll() == null) {
			throw new NullPointerException();
		}
		StringBuilder endString = new StringBuilder();
		this.albumList.findAll().sort(Comparator.comparing(Album::getReleaseDate));
		for (Album album : this.albumList.findAll())
			endString.append(album.toString());
		return endString.toString().equals("") ? "[WARNING] Album List is Empty\n" : endString.toString();
	}

	@Override
	public String sortAlbumsByProductionCost() throws NullPointerException
	{
		if (this.albumList.findAll() == null) {
			throw new NullPointerException();
		}
		StringBuilder endString = new StringBuilder();
		this.albumList.findAll().sort(Comparator.comparing(Album::getProductionCost));
		for (Album album : this.albumList.findAll())
			endString.append(album.toString());
		return endString.toString().equals("") ? "[WARNING] Album List is Empty\n" : endString.toString();
	}
	
	@Override
	public String showConcerts() throws NullPointerException
	{
		if (this.concertList == null) throw new NullPointerException();
		StringBuilder endString = new StringBuilder();
		for (Concert concert : this.concertList.findAll())
			endString.append(concert.toString());
		return endString.toString().equals("") ? "[WARNING] Concert List is Empty\n" : endString.toString();
	}

	@Override
	public String showArtists() throws NullPointerException
	{
		if (this.artistList == null) throw new NullPointerException();
		StringBuilder endString = new StringBuilder();
		for (Artist artist : this.artistList.findAll())
			endString.append(artist.toString());
		return endString.toString().equals("") ? "[WARNING] Artist List is Empty\n" : endString.toString();
	}
	
	@Override
	public String showAlbums() throws NullPointerException
	{
		if (this.albumList == null) throw new NullPointerException();
		StringBuilder endString = new StringBuilder();
		for (Album album : this.albumList.findAll())
			endString.append(album.toString());
		return endString.toString().equals("") ? "[WARNING] Album List is Empty\n" : endString.toString();
	}
	
	@Override
	public String showUpcomingConcerts() throws NullPointerException
	{
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
