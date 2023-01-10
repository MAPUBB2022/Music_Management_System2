package controller;

import exceptions.*;
import interfaces.ICrudRepository;
import interfaces.IUserController;
import interfaces.IUserFieldIdentifiers;
import model.album.Album;
import model.album.Artist;
import model.concert.Concert;
import model.concert.Ticket;
import model.song.Song;
import model.users.User;

import java.util.*;


@SuppressWarnings("StringConcatenationInLoop")
public class UserController implements IUserController
{
	private final ICrudRepository<String, Album> albumList;
	private final ICrudRepository<String, Artist> artistList;
	private final ICrudRepository<String, Concert> concertList;
	private final ICrudRepository<String, Song> songList;
	private final ICrudRepository<String, User> userList;
	private final IUserFieldIdentifiers<Song> myFavourites;
	private final IUserFieldIdentifiers<Ticket> myTickets;
	
	public UserController(ICrudRepository<String, Album> albumList, ICrudRepository<String, Artist> artistList, ICrudRepository<String, Concert> concertList, ICrudRepository<String, Song> songList, ICrudRepository<String, User> userList, IUserFieldIdentifiers<Ticket> tickets, IUserFieldIdentifiers<Song> userFavourites)
	{
		this.albumList = albumList;
		this.artistList = artistList;
		this.concertList = concertList;
		this.songList = songList;
		this.userList = userList;
		this.myFavourites = userFavourites;
		this.myTickets = tickets;
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
	
	public ICrudRepository<String, User> getUserList()
	{
		return userList;
	}
	
	public IUserFieldIdentifiers<Song> getMyFavourites()
	{
		return myFavourites;
	}
	
	public IUserFieldIdentifiers<Ticket> getMyTickets()
	{
		return myTickets;
	}
	
	@Override
	public String showArtists()
	{
		if (this.artistList == null) {
			throw new ArtistRepoException("[ERROR] Artist List is NULL");
		}
		String endString = this.artistList.toString();
		return endString.equals("") ? "[WARNING] Artist List is Empty\n" : endString;
	}
	
	@Override
	public String showAlbums()
	{
		if (this.albumList == null) {
			throw new AlbumRepoException("[ERROR] Album List is NULL");
		}
		String endString = this.albumList.toString();
		return endString.equals("") ? "[WARNING] Album List is Empty\n" : endString;
	}
	
	@Override
	public String showAlbumsForArtist(Artist artist)
	{
		if (artist == null) throw new NullPointerException("[ERROR] NULL-Pointer provided as Function Argument");
		if (this.albumList == null) {
			throw new AlbumRepoException("[ERROR] Album List is NULL");
		}
		String endString = "";
		for (Album album : this.albumList.findAll())
			if (album.getArtist() != null && album.getArtist().getStageName().equals(artist.getStageName()))
				endString += album.toString();
		return endString.equals("") ? "[WARNING] No Albums found for specified Artist\n" : endString;
	}
	
	@Override
	public String showUpcomingConcerts()
	{
		if (this.concertList == null) {
			throw new ConcertRepoException("[ERROR] Concert List is NULL");
		}
		String endString = "";
		Date today = new Date();
		for (Concert concert : this.concertList.findAll()) {
			if (concert.getDate().after(today)) endString += concert.toString();
		}
		return endString.equals("") ? "[WARNING] No Upcoming Concert exist\n" : endString;
	}
	
	@Override
	public void sortAlbumsByRevenue()
	{
		if (this.albumList == null) {
			throw new AlbumRepoException("[ERROR] Album List is NULL");
		}
		this.albumList.findAll().sort((album1, album2) -> (int) (album1.calculateProfit() - album2.calculateProfit()));
	}
	
	@Override
	public void sortSongsByRating()
	{
		if (this.songList == null) {
			throw new SongRepoException("[ERROR] Song List is NULL");
		}
		this.songList.findAll().sort(Comparator.comparing(Song::getRating));
		Collections.reverse(this.songList.findAll());
	}
	
	@Override
	public void sortSongsByReleaseDate()
	{
		if (this.songList == null) {
			throw new SongRepoException("[ERROR] Song List is NULL");
		}
//		this.songList.findAll().sort((song1, song2) -> song1.getReleaseDate().compareTo(song2.getReleaseDate()));
		this.songList.findAll().sort(Comparator.comparing(Song::getReleaseDate));
	}
	
	@Override
	public void sortArtistsByName()
	{
		if (this.artistList == null) {
			throw new ArtistRepoException("[ERROR] Artist List is NULL");
		}
		this.artistList.findAll().sort(Comparator.comparing(Artist::getStageName));
//		return endString.equals("") ? "[WARNING] Artist List is Empty\n" : endString;
	}
	
	@Override
	public void sortAlbumsByReleaseDate()
	{
		if (this.albumList == null) {
			throw new AlbumRepoException("[ERROR] Album List is NULL");
		}
		this.albumList.findAll().sort(Comparator.comparing(Album::getReleaseDate));
//		return endString.equals("") ? "[WARNING] Album List is Empty\n" : endString;
	}
	
	@Override
	public boolean addFavourite(Song song)
	{
		if (song == null) throw new NullPointerException("[ERROR] NULL-Pointer provided as Function Argument");
		if (this.myFavourites == null) throw new FavouritesListException("[ERROR] List of Favourites is NULL");
		if (this.myFavourites.findAllForUser() != null && this.myFavourites.findAllForUser().contains(song)) return false; //"[ERROR] Song already contained in List\n";
		this.myFavourites.add(song);
		return true; //"[] Song added\n";
	}
	
	@Override
	public boolean removeFavourite(Song song)
	{
		if (song == null) throw new NullPointerException("[ERROR] NULL-Pointer provided as Function Argument");
		if (this.myFavourites == null) throw new FavouritesListException("[ERROR] List of Favourites is NULL");
		boolean exists = false;
		for (Song s : this.myFavourites.findAllForUser())
			if (s.getName().equals(song.getName())) {
				exists = true;
				break;
			}
		if (!exists) return false; //"[ERROR] Song not in List\n";
		this.myFavourites.remove(song);
		return true; //"[] Song deleted\n";
	}
	
	@Override
	public String showFavourites()
	{
		if (this.myFavourites == null) throw new FavouritesListException("[ERROR] Favourites List is NULL");
		String endString = "";
		List<Song> songs = this.myFavourites.findAllForUser();
		if (songs == null || songs.isEmpty()) return "[WARNING] List of Favourites is Empty\n";
		else {
			for (Song song : this.myFavourites.findAllForUser())
				endString += song.toString();
		}
		return endString;
	}
	
	@Override
	public boolean buyTicket(Concert concert, Integer count, User user)
	{
		if (concert == null || user == null)
			throw new NullPointerException("[ERROR] NULL-Pointer provided as Function Argument");
		if (count < concert.getCapacity() - concert.getTicketsSold() && count > 0) {
			Ticket ticket = new Ticket(user, concert.getName(), concert.getLocation(), concert.getDate(), new Date(), concert.getTicketPrice());
			ticket.setTicketCount(count);
			this.myTickets.add(ticket);
			concert.setTicketsSold(concert.getTicketsSold() + count);
			concertList.update(concert.getName(), concert);
			return true;
		}
		return false;
	}
	
	@Override
	public String showTickets()
	{
		if (this.myTickets == null) throw new TicketListException("[ERROR] Ticket List is NULL");
		String endString = "";
		List<Ticket> tickets = this.myTickets.findAllForUser();
		if (tickets == null) return "[WARNING] Ticket List is Empty\n";
		else {
			for (Ticket ticket : tickets)
				endString += ticket.toString();
		}
		return endString;
	}
	
	
	private int generateRandom(int end)
	{
		return (int) (Math.random() * ((end) + 1));
	}
	
	private List<Integer> generateUnequalRandom(int end)
	{
		Integer r1 = generateRandom(end);
		Integer r2 = generateRandom(end);
		Integer r3 = generateRandom(end);
		while (r1.equals(r2) || r1.equals(r3) || r2.equals(r3)) {
			r1 = generateRandom(end);
			r2 = generateRandom(end);
			r3 = generateRandom(end);
		}
		return new ArrayList<>(Arrays.asList(r1, r2, r3));
	}
	
	@Override
	public String showRecommended()
	{
		//todo myFavourites can have less than 3 songs.
		int max, count = 3;
		String endString = "";
		Song s1, s2, s3;
		List<Integer> randoms;
		List<Song> songs = this.myFavourites.findAllForUser();
		
//		if (!songs.isEmpty()) {
//			max = songs.size() - 1;
//			randoms = generateUnequalRandom(max);
//			s1 = songs.get(randoms.get(0)).getRelatedSongs().get(0);
//			s2 = songs.get(randoms.get(1)).getRelatedSongs().get(0);
//			s3 = songs.get(randoms.get(2)).getRelatedSongs().get(0);
		
		if (songs == null){
			List<Song> songs2 = songList.findAll();
			max = songs2.size() - 1;
			randoms = generateUnequalRandom(max);
			s1 = songs2.get(randoms.get(0));
			s2 = songs2.get(randoms.get(1));
			s3 = songs2.get(randoms.get(2));
			
			endString += "1. " + s1.getName() + " by ";
			endString += s1.getSinger() != null ? s1.getSinger().getStageName() : s1.getBandSingers().getName();
			endString += "\n";
			endString += "2. " + s2.getName() + " by ";
			endString += s2.getSinger() != null ? s2.getSinger().getStageName() : s2.getBandSingers().getName();
			endString += "\n";
			endString += "3. " + s3.getName() + " by ";
			endString += s3.getSinger() != null ? s3.getSinger().getStageName() : s3.getBandSingers().getName();
			endString += "\n";
			
			return endString;
		}
		return "";
	}
	
	@Override
	public boolean addUser(User user)
	{
		if (user == null) throw new NonExistentUserException("[ERROR] User does not exist");
		if (this.userList.findAll().contains(user)) return false;
		this.userList.add(user);
		return true;
	}
}
