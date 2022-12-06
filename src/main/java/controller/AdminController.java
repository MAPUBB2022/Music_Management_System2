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
public class
AdminController implements IAdminController
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
	public void addAlbum(Album album) throws NullPointerException
	{
		if(album==null)
			throw new NullPointerException();

		if (this.albumList.findAll().contains(album))
			this.albumList.add(album);
	}
	
	@Override
	public void deleteAlbum(Album album)
	{
		try {
			if (!this.albumList.findAll().contains(album))
				this.albumList.remove(album);

		}
		catch(Exception error) {
			System.out.println(error.getMessage());
		}
	}
	
	@Override
	public void modifyAlbum(Album album)
	{
			try {
				for (Album albums : albumList.findAll()) {
					if (albums.equals(album)) {
						albumList.update(albumList.findAll().indexOf(albums), album);
						break;
					}
				}
			}
			catch(Exception error){
				System.out.println(error.getMessage());
			}

	}
	
	@Override
	public void addArtist(Artist artist)
	{
		try {
			if(this.artistList.findAll().contains(artist))
				this.artistList.add(artist);
		}
		catch(Exception error){
			System.out.println(error.getMessage());
		}
	}
	
	@Override
	public void deleteArtist(Artist artist)
	{
		try {
			if (!this.artistList.findAll().contains(artist))
				this.artistList.remove(artist);
		}
		catch(Exception error){
			System.out.println(error.getMessage());
		}
	}
	
	@Override
	public void modifyArtist(Artist artist)
	{
		try {
			for (Artist artists : artistList.findAll()) {
				if (artist.equals(artists)) {
					artistList.update(artistList.findAll().indexOf(artists), artist);
					break;
				}
			}
		}
		catch(Exception error){
			System.out.println(error.getMessage());
		}

	}
	
	@Override
	public void addBand(Band band)
	{
		try {
			if(this.bandList.findAll().contains(band))
				this.bandList.add(band);
		}
		catch(Exception error){
			System.out.println(error.getMessage());
		}
	}
	
	@Override
	public void deleteBand(Band band)
	{
		try {
			if (!this.bandList.findAll().contains(band))
				this.bandList.remove(band);
		}
		catch(Exception error){
			System.out.println(error.getMessage());
		}
	}
	
	@Override
	public void modifyBand(Band band)
	{
		try {
			for (Band bands : bandList.findAll()) {
				if (bands.equals(band)) {
					bandList.update(bandList.findAll().indexOf(bands), band);
				}
			}
		}
		catch(Exception error){
			System.out.println(error.getMessage());
		}
	}
	
	@Override
	public void addConcert(Concert concert)
	{
		try {
			if(this.concertList.findAll().contains(concert))
				this.concertList.add(concert);
		}
		catch(Exception error){
			System.out.println(error.getMessage());
		}
	}
	
	@Override
	public void deleteConcert(Concert concert)
	{
		try {
			if (!this.concertList.findAll().contains(concert))
				this.concertList.remove(concert);
		}
		catch(Exception error){
			System.out.println(error.getMessage());
		}
	}
	
	@Override
	public void modifyConcert(Concert concert)
	{
		try {
			for (Concert concerts : concertList.findAll()) {
				if (concerts.equals(concert)) {
					concertList.update(String.valueOf(concertList.findAll().indexOf(concerts)), concert);
				}
			}
		}
		catch(Exception error){
			System.out.println(error.getMessage());
		}
	}
	
	@Override
	public void addMusicLabel(MusicLabel musicLabel)
	{
		try {
			if(this.labelList.findAll().contains(musicLabel))
				this.labelList.add(musicLabel);
		}
		catch(Exception error){
			System.out.println(error.getMessage());
		}
	}
	
	@Override
	public void deleteMusicLabel(MusicLabel musicLabel)
	{
		try {
			if(!this.labelList.findAll().contains(musicLabel))
				this.labelList.remove(musicLabel);
		}
		catch(Exception error){
			System.out.println(error.getMessage());
		}
	}
	
	@Override
	public void modifyMusicLabel(MusicLabel musicLabel)
	{
		try {
			for (MusicLabel labels : labelList.findAll()) {
				if (labels.equals(musicLabel)) {
					labelList.update(labelList.findAll().indexOf(labels), musicLabel);
				}
			}
		}
		catch(Exception error){
			System.out.println(error.getMessage());
		}
	}
	
	@Override
	public void addSong(Song song)
	{
		try {
			if(this.songList.findAll().contains(song))
				this.songList.add(song);
		}
		catch(Exception error){
			System.out.println(error.getMessage());
		}
	}
	
	@Override
	public void deleteSong(Song song)
	{
		try {
			if (!this.songList.findAll().contains(song))
				this.songList.remove(song);
		}
		catch(Exception error){
			System.out.println(error.getMessage());
		}
	}
	
	@Override
	public void modifySong(Song song)
	{
		try {
			for (Song songs : songList.findAll()) {
				if (song.equals(songs)) {
					songList.update(songList.findAll().indexOf(songs), song);
				}
			}
		}
		catch(Exception error){
			System.out.println(error.getMessage());
		}
	}
	
	@Override
	public boolean addUser(User user)
	{
		if(this.userList.findAll().contains(user))
			return false;
		this.userList.add(user);
		return true;
	}
	
	@Override
	public void deleteUser(User user)
	{
		try {
			if (!this.userList.findAll().contains(user))
				this.userList.remove(user);
		}
		catch(Exception error){
			System.out.println(error.getMessage());
		}
	}
	
	@Override
	public void modifyUser(User user)
	{
		try {
			for (User users : userList.findAll()) {
				if (users.equals(user)) {
					userList.update(userList.findAll().indexOf(users), user);
				}
			}
		}
		catch(Exception error){
			System.out.println(error.getMessage());
		}
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
		this.artistList.findAll().sort(Comparator.comparing(Artist::getStage_name));
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
