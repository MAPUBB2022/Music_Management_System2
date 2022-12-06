package controller;

import exceptions.*;
import interfaces.ICrudRepository;
import interfaces.IUserController;
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
	
	private List<Song> myFavourites;
	
	private List<Ticket> myTickets;
	
	public UserController(ICrudRepository<String, Album> albumList, ICrudRepository<String, Artist> artistList, ICrudRepository<String, Concert> concertList, ICrudRepository<String, Song> songList, ICrudRepository<String, User> userList)
	{
		this.albumList = albumList;
		this.artistList = artistList;
		this.concertList = concertList;
		this.songList = songList;
		this.userList = userList;
		this.myFavourites = new ArrayList<>();
		this.myTickets = new ArrayList<>();
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
		if (this.albumList == null) {
			throw new AlbumRepoException("[ERROR] Album List is NULL");
		}
		String endString = "";
		for (Album album : this.albumList.findAll())
			if (album.getArtist().getStageName().equals(artist.getStageName())) endString += album.toString();
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
//		return endString.equals("") ? "[WARNING] Artist List is Empty\n" : endString;
	}
	
	@Override
	public void sortSongsByRating()
	{
		if (this.songList == null) {
			throw new SongRepoException("[ERROR] Song List is NULL");
		}
		this.songList.findAll().sort(Comparator.comparing(Song::getRating));
//		return endString.equals("") ? "[WARNING] Song List is Empty\n" : endString;
	}
	
	@Override
	public void sortSongsByReleaseDate()
	{
		if (this.songList == null) {
			throw new SongRepoException("[ERROR] Song List is NULL");
		}
		this.songList.findAll().sort(Comparator.comparing(Song::getReleaseDate));
//		return endString.equals("") ? "[WARNING] Song List is Empty\n" : endString;
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
		if (this.myFavourites.contains(song)) return false; //"[ERROR] Song already contained in List\n";
		this.myFavourites.add(song);
		return true; //"[] Song added\n";
	}
	
	@Override
	public boolean removeFavourite(Song song)
	{
		if (!this.myFavourites.contains(song)) return false; //"[ERROR] Song not in List\n";
		this.myFavourites.remove(song);
		return true; //"[] Song deleted\n";
	}
	
	@Override
	public String showFavourites()
	{
		if (this.myFavourites == null) throw new FavouritesListException("[ERROR] Favourites List is NULL");
		String endString = "";
		for (Song song : this.myFavourites)
			endString += song.toString();
		return endString.equals("") ? "[WARNING] List of Favourites is Empty\n" : endString;
	}
	
	@Override
	public boolean buyTicket(Concert concert, Integer count)
	{
		Concert con = this.concertList.findByID(concert.getName());
		if (con == null) return false;
		if (con.getTicketsSold() < con.getCapacity()) {
			this.myTickets.add(new Ticket(con.getName(), con.getLocation(), con.getDate(), new Date(), con.getTicketPrice()));
			con.setTicketsSold(con.getTicketsSold() + 1);
			return true; //"[] Ticket Purchase Successful\n";
		}
		return false; //"[ERROR] Tickets unavailable\n";
	}
	
	@Override
	public String showTickets()
	{
		if (this.myTickets == null) throw new TicketListException("[ERROR] Ticket List is NULL");
		String endString = "";
		for (Ticket ticket : this.myTickets)
			endString += ticket.toString();
		return endString.equals("") ? "[WARNING] Ticket List is Empty\n" : endString;
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
		int max;
		String endString = "";
		Song s1, s2, s3;
		List<Integer> randoms;
		
		if (!this.myFavourites.isEmpty()) {
			max = this.myFavourites.size() - 1;
			randoms = generateUnequalRandom(max);
			s1 = this.myFavourites.get(randoms.get(0)).getRelatedSongs().get(0);
			s2 = this.myFavourites.get(randoms.get(1)).getRelatedSongs().get(0);
			s3 = this.myFavourites.get(randoms.get(2)).getRelatedSongs().get(0);
		} else {
			max = songList.findAll().size() - 1;
			List<Song> songs = songList.findAll();
			randoms = generateUnequalRandom(max);
			s1 = songs.get(randoms.get(0));
			s2 = songs.get(randoms.get(1));
			s3 = songs.get(randoms.get(2));
		}
		
		endString += s1.getName() + " by ";
		endString += s1.getSinger() != null ? s1.getSinger().getStageName() : s1.getBandSingers().getName();
		endString += "; ";
		endString += s2.getName() + " by ";
		endString += s2.getSinger() != null ? s2.getSinger().getStageName() : s2.getBandSingers().getName();
		endString += "; ";
		endString += s3.getName() + " by ";
		endString += s3.getSinger() != null ? s3.getSinger().getStageName() : s3.getBandSingers().getName();
		
		return endString;
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
