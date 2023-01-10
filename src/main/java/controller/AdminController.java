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

import java.util.Comparator;
import java.util.Date;

// modify return type of methods: String to bool
public class AdminController implements IAdminController
{
	private final ICrudRepository<String, Album> albumList;
	private final ICrudRepository<String, Artist> artistList;
	private final ICrudRepository<String, Concert> concertList;
	private final ICrudRepository<String, Song> songList;
	private final ICrudRepository<String, Band> bandList;
	private final ICrudRepository<String, MusicLabel> labelList;
	private final ICrudRepository<String, User> userList;
	
	public AdminController(ICrudRepository<String, Album> albumList, ICrudRepository<String, Artist> artistList, ICrudRepository<String, Concert> concertList, ICrudRepository<String, Song> songList, ICrudRepository<String, Band> bandList, ICrudRepository<String, MusicLabel> labelList, ICrudRepository<String, User> userList)
	{
		this.albumList = albumList;
		this.artistList = artistList;
		this.concertList = concertList;
		this.songList = songList;
		this.bandList = bandList;
		this.labelList = labelList;
		this.userList = userList;
	}
	
	@Override
	public boolean addAlbum(Album album)
	{
		try {
			if (this.albumList.findAll().contains(album)) {
				this.albumList.add(album);
				return true;
			}
		}
		catch (Exception error) {
			System.out.println(error.getMessage());
		}
		return false;
	}
	
	@Override
	public boolean deleteAlbum(Album album)
	{
		try {
			if (!this.albumList.findAll().contains(album)) {
				this.albumList.remove(album);
				return true;
			}
		}
		catch (Exception error) {
			System.out.println(error.getMessage());
		}
		return false;
	}
	
	@Override
	public Album modifyAlbum(Album album)
	{
		try {
			for (Album albums : albumList.findAll()) {
				if (albums.equals(album)) {
					albumList.update(String.valueOf(albumList.findAll().indexOf(albums)), album);
					return albums;
				}
			}
		}
		catch (Exception error) {
			System.out.println(error.getMessage());
		}
		return null;
	}
	
	@Override
	public boolean addArtist(Artist artist)
	{
		try {
			if (this.artistList.findAll().contains(artist)) {
				this.artistList.add(artist);
				return true;
			}
		}
		catch (Exception error) {
			System.out.println(error.getMessage());
		}
		return false;
	}
	
	@Override
	public boolean deleteArtist(Artist artist)
	{
		try {
			if (!this.artistList.findAll().contains(artist)) {
				this.artistList.remove(artist);
				return true;
			}
		}
		catch (Exception error) {
			System.out.println(error.getMessage());
		}
		return false;
	}
	
	@Override
	public Artist modifyArtist(Artist artist)
	{
		try {
			for (Artist artists : artistList.findAll()) {
				if (artist.equals(artists)) {
					artistList.update(String.valueOf(artistList.findAll().indexOf(artists)), artist);
					return artists;
				}
			}
		}
		catch (Exception error) {
			System.out.println(error.getMessage());
		}
		return null;
	}
	
	@Override
	public boolean addBand(Band band)
	{
		try {
			if (this.bandList.findAll().contains(band)) {
				this.bandList.add(band);
				return true;
			}
		}
		catch (Exception error) {
			System.out.println(error.getMessage());
		}
		return false;
	}
	
	@Override
	public boolean deleteBand(Band band)
	{
		try {
			if (!this.bandList.findAll().contains(band)) {
				this.bandList.remove(band);
				return true;
			}
		}
		catch (Exception error) {
			System.out.println(error.getMessage());
		}
		return false;
	}
	
	@Override
	public Band modifyBand(Band band)
	{
		try {
			for (Band bands : bandList.findAll()) {
				if (bands.equals(band)) {
					bandList.update(String.valueOf(bandList.findAll().indexOf(bands)), band);
					return bands;
				}
			}
		}
		catch (Exception error) {
			System.out.println(error.getMessage());
		}
		return null;
	}
	
	@Override
	public boolean addConcert(Concert concert)
	{
		try {
			if (this.concertList.findAll().contains(concert)) {
				this.concertList.add(concert);
				return true;
			}
		}
		catch (Exception error) {
			System.out.println(error.getMessage());
		}
		return false;
	}
	
	@Override
	public boolean deleteConcert(Concert concert)
	{
		try {
			if (!this.concertList.findAll().contains(concert)) {
				this.concertList.remove(concert);
				return true;
			}
		}
		catch (Exception error) {
			System.out.println(error.getMessage());
		}
		return false;
	}
	
	@Override
	public Concert modifyConcert(Concert concert)
	{
		try {
			for (Concert concerts : concertList.findAll()) {
				if (concerts.equals(concert)) {
					concertList.update(String.valueOf(concertList.findAll().indexOf(concerts)), concert);
					return concerts;
				}
			}
		}
		catch (Exception error) {
			System.out.println(error.getMessage());
		}
		return null;
	}
	
	@Override
	public boolean addMusicLabel(MusicLabel musicLabel)
	{
		try {
			if (this.labelList.findAll().contains(musicLabel)) {
				this.labelList.add(musicLabel);
				return true;
			}
		}
		catch (Exception error) {
			System.out.println(error.getMessage());
		}
		return false;
	}
	
	@Override
	public boolean deleteMusicLabel(MusicLabel musicLabel)
	{
		try {
			if (!this.labelList.findAll().contains(musicLabel)) {
				this.labelList.remove(musicLabel);
				return true;
			}
		}
		catch (Exception error) {
			System.out.println(error.getMessage());
		}
		return false;
	}
	
	@Override
	public MusicLabel modifyMusicLabel(MusicLabel musicLabel)
	{
		try {
			for (MusicLabel labels : labelList.findAll()) {
				if (labels.equals(musicLabel)) {
					labelList.update(String.valueOf(labelList.findAll().indexOf(labels)), musicLabel);
					return labels;
				}
			}
		}
		catch (Exception error) {
			System.out.println(error.getMessage());
		}
		return null;
	}
	
	@Override
	public boolean addSong(Song song)
	{
		try {
			if (this.songList.findAll().contains(song)) {
				this.songList.add(song);
				return true;
			}
		}
		catch (Exception error) {
			System.out.println(error.getMessage());
		}
		return false;
	}
	
	@Override
	public boolean deleteSong(Song song)
	{
		try {
			if (!this.songList.findAll().contains(song)) {
				this.songList.remove(song);
				return true;
			}
		}
		catch (Exception error) {
			System.out.println(error.getMessage());
		}
		return false;
	}
	
	@Override
	public Song modifySong(Song song)
	{
		try {
			for (Song songs : songList.findAll()) {
				if (song.equals(songs)) {
					songList.update(String.valueOf(songList.findAll().indexOf(songs)), song);
					return songs;
				}
			}
		}
		catch (Exception error) {
			System.out.println(error.getMessage());
		}
		return null;
	}
	
	@Override
	public boolean addUser(User user)
	{
		if (this.userList.findAll().contains(user)) return false;
		this.userList.add(user);
		return true;
	}
	
	@Override
	public boolean deleteUser(User user)
	{
		try {
			if (!this.userList.findAll().contains(user)) {
				this.userList.remove(user);
				return true;
			}
		}
		catch (Exception error) {
			System.out.println(error.getMessage());
		}
		return false;
	}
	
	@Override
	public User modifyUser(User user)
	{
		try {
			for (User users : userList.findAll()) {
				if (users.equals(user)) {
					userList.update(String.valueOf(userList.findAll().indexOf(users)), user);
					return users;
				}
			}
		}
		catch (Exception error) {
			System.out.println(error.getMessage());
		}
		return null;
	}
	
	@Override
	public void sortAlbumsByRevenue() throws NullPointerException
	{
		if (this.albumList.findAll() == null) {
			throw new NullPointerException();
		}
		this.albumList.findAll().sort((album1, album2) -> (int) (album1.calculateProfit() - album2.calculateProfit()));
	}
	
	@SuppressWarnings("DuplicatedCode")
	@Override
	public void sortSongsByRating() throws NullPointerException
	{
		if (this.songList.findAll() == null) {
			throw new NullPointerException();
		}
		this.songList.findAll().sort(Comparator.comparing(Song::getRating));
	}
	
	@SuppressWarnings("DuplicatedCode")
	@Override
	public void sortSongsByReleaseDate() throws NullPointerException
	{
		if (this.songList.findAll() == null) {
			throw new NullPointerException();
		}
		this.songList.findAll().sort(Comparator.comparing(Song::getReleaseDate));
	}
	
	@Override
	public void sortArtistsByName() throws NullPointerException
	{
		if (this.artistList.findAll() == null) {
			throw new NullPointerException();
		}
		this.artistList.findAll().sort(Comparator.comparing(Artist::getStageName));
	}
	
	@Override
	public void sortAlbumsByReleaseDate() throws NullPointerException
	{
		if (this.albumList.findAll() == null) {
			throw new NullPointerException();
		}
		this.albumList.findAll().sort(Comparator.comparing(Album::getReleaseDate));
	}
	
	@Override
	public void sortAlbumsByProductionCost() throws NullPointerException
	{
		if (this.albumList.findAll() == null) {
			throw new NullPointerException();
		}
		this.albumList.findAll().sort(Comparator.comparing(Album::getProductionCost));
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
	public String showAlbumsForArtist(Artist artist)
	{
		return null;
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
			if (concert.getDate().after(today)) endString.append(concert);
		}
		return endString.toString().equals("") ? "[WARNING] No Upcoming Concert exist\n" : endString.toString();
	}
	
	
}
