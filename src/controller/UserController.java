package controller;

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
	private final ICrudRepository<Integer, Album> albumList;
	private final ICrudRepository<Integer, Artist> artistList;
	private final ICrudRepository<String, Concert> concertList;
	private final ICrudRepository<Integer, Song> songList;
	
	private final ICrudRepository<String, User> userList;
	
	private List<Song> myFavourites;
	
	private List<Ticket> myTickets;
	
	public UserController(ICrudRepository<Integer, Album> albumList, ICrudRepository<Integer, Artist> artistList, ICrudRepository<String, Concert> concertList, ICrudRepository<Integer, Song> songList, ICrudRepository<String, User> userList)
	{
		this.albumList = albumList;
		this.artistList = artistList;
		this.concertList = concertList;
		this.songList = songList;
		this.userList = userList;
		this.myFavourites = new ArrayList<>();
		this.myTickets = new ArrayList<>();
	}
	
	public ICrudRepository<Integer, Album> getAlbumList()
	{
		return albumList;
	}
	
	public ICrudRepository<Integer, Artist> getArtistList()
	{
		return artistList;
	}
	
	public ICrudRepository<String, Concert> getConcertList()
	{
		return concertList;
	}
	
	public ICrudRepository<Integer, Song> getSongList()
	{
		return songList;
	}
	
	@Override
	public String showArtists() throws NullPointerException
	{
		if (this.artistList.findAll() == null) {
			throw new NullPointerException("[ERROR] Artist List is NULL");
		}
		String endString = "";
		for (Artist artist : this.artistList.findAll())
			endString += artist.toString();
		return endString.equals("") ? "[WARNING] Artist List is Empty\n" : endString;
	}
	
	@Override
	public String showAlbums() throws NullPointerException
	{
		if (this.albumList.findAll() == null) {
			throw new NullPointerException("[ERROR] Album List is NULL");
		}
		String endString = "";
		for (Album album : this.albumList.findAll())
			endString += album.toString();
		return endString.equals("") ? "[WARNING] Album List is Empty\n" : endString;
	}
	
	@Override
	public String showAlbumsForArtist(Artist artist) throws NullPointerException
	{
		if (this.albumList.findAll() == null) {
			throw new NullPointerException("[ERROR] Album List is NULL");
		}
		String endString = "";
		for (Album album : this.albumList.findAll()) {
			if (album.getArtist().equals(artist)) endString += album.toString();
		}
		return endString.equals("") ? "[WARNING] No Albums found for specified Artist\n" : endString;
	}
	
	@Override
	public String showUpcomingConcerts() throws NullPointerException
	{
		if (this.concertList.findAll() == null) {
			throw new NullPointerException("[ERROR] Concert List is NULL");
		}
		String endString = "";
		Date today = new Date();
		for (Concert concert : this.concertList.findAll()) {
			if (concert.getDate().after(today)) endString += concert.toString();
		}
		return endString.equals("") ? "[WARNING] No Upcoming Concert exist\n" : endString;
	}
	
	@Override
	public String sortAlbumsByRevenue() throws NullPointerException
	{
		if (this.albumList.findAll() == null) {
			throw new NullPointerException("[ERROR] Album List is NULL");
		}
		String endString = "";
		this.albumList.findAll().sort((album1, album2) -> (int) (album1.calculateProfit() - album2.calculateProfit()));
		for (Album album : this.albumList.findAll())
			endString += album.toString();
		return endString.equals("") ? "[WARNING] Artist List is Empty\n" : endString;
	}
	
	@SuppressWarnings("DuplicatedCode")
	@Override
	public String sortSongsByRating() throws NullPointerException
	{
		if (this.songList.findAll() == null) {
			throw new NullPointerException("[ERROR] Song List is NULL");
		}
		String endString = "";
		this.songList.findAll().sort(Comparator.comparing(Song::getRating));
		for (Song song : this.songList.findAll())
			endString += song.toString();
		return endString.equals("") ? "[WARNING] Song List is Empty\n" : endString;
	}
	
	@SuppressWarnings("DuplicatedCode")
	@Override
	public String sortSongsByReleaseDate() throws NullPointerException
	{
		if (this.songList.findAll() == null) {
			throw new NullPointerException("[ERROR] Song List is NULL");
		}
		String endString = "";
		this.songList.findAll().sort(Comparator.comparing(Song::getReleaseDate));
		for (Song song : this.songList.findAll())
			endString += song.toString();
		return endString.equals("") ? "[WARNING] Song List is Empty\n" : endString;
	}
	
	@Override
	public String sortArtistsByName() throws NullPointerException
	{
		if (this.artistList.findAll() == null) {
			throw new NullPointerException("[ERROR] Artist List is NULL");
		}
		String endString = "";
		this.artistList.findAll().sort(Comparator.comparing(Artist::getStage_name));
		for (Artist artist : this.artistList.findAll())
			endString += artist.toString();
		return endString.equals("") ? "[WARNING] Artist List is Empty\n" : endString;
	}
	
	@Override
	public String sortAlbumsByReleaseDate() throws NullPointerException
	{
		if (this.albumList.findAll() == null) {
			throw new NullPointerException("[ERROR] Album List is NULL");
		}
		String endString = "";
		this.albumList.findAll().sort(Comparator.comparing(Album::getReleaseDate));
		for (Album album : this.albumList.findAll())
			endString += album.toString();
		return endString.equals("") ? "[WARNING] Album List is Empty\n" : endString;
	}
	
	@Override
	public String addFavourite(Song song)
	{
		if (this.myFavourites.contains(song)) return "[ERROR] Song already contained in List\n";
		this.myFavourites.add(song);
		return "[] Song added\n";
	}
	
	@Override
	public String removeFavourite(Song song)
	{
		if (!this.myFavourites.contains(song)) return "[ERROR] Song not in List\n";
		this.myFavourites.remove(song);
		return "[] Song deleted\n";
	}
	
	@Override
	public String showFavourites()
	{
		String endString = "";
		for (Song song : this.myFavourites)
			endString += song.toString();
		return endString.equals("") ? "[WARNING] List of Favourites is Empty\n" : endString;
	}
	
	@Override
	public String buyTicket(Concert concert)
	{
		Concert con = this.concertList.findByID(concert.getName());
		if (con.getTicketsSold() < con.getCapacity()) {
			this.myTickets.add(new Ticket(con.getName(), con.getLocation(), con.getDate(), new Date(), con.getTicketPrice()));
			con.setTicketsSold(con.getTicketsSold() + 1);
			return "[] Ticket Purchase Successful\n";
		}
		return "[ERROR] Tickets unavailable\n";
	}
	
	@Override
	public String showTickets() throws NullPointerException
	{
		if (this.myTickets == null) throw new NullPointerException("[ERROR] Ticket List is NULL");
		String endString = "";
		for (Ticket ticket : this.myTickets)
			endString += ticket.toString();
		return endString.equals("") ? "[WARNING] Ticket List is Empty\n" : endString;
	}
	
	@Override
	public String showRecommended()
	{
		String endString = "";
		if (!myFavourites.isEmpty()) {
			int min = 0, max = myFavourites.size() - 1;
			int random1 = min + (int) (Math.random() * ((max - min) + 1));
			int random2 = min + (int) (Math.random() * ((max - min) + 1));
			int random3 = min + (int) (Math.random() * ((max - min) + 1));
			if (myFavourites.get(random1).getRelatedSongs().get(random2).getSinger() != null)
				endString += myFavourites.get(random1).getRelatedSongs().get(random2).getName() + " by " + myFavourites.get(random1).getRelatedSongs().get(random2).getSinger().getStage_name();
			else
				endString += myFavourites.get(random1).getRelatedSongs().get(random2).getName() + " by " + myFavourites.get(random1).getRelatedSongs().get(random2).getBand_singers().getName();
			if (myFavourites.get(random2).getRelatedSongs().get(random3).getSinger() != null)
				endString += myFavourites.get(random2).getRelatedSongs().get(random3).getName() + " by " + myFavourites.get(random2).getRelatedSongs().get(random3).getSinger().getStage_name();
			else
				endString += myFavourites.get(random2).getRelatedSongs().get(random3).getName() + " by " + myFavourites.get(random2).getRelatedSongs().get(random3).getBand_singers().getName();
			if (myFavourites.get(random3).getRelatedSongs().get(random1).getSinger() != null)
				endString += myFavourites.get(random3).getRelatedSongs().get(random1).getName() + " by " + myFavourites.get(random3).getRelatedSongs().get(random1).getSinger().getStage_name();
			else
				endString += myFavourites.get(random3).getRelatedSongs().get(random1).getName() + " by " + myFavourites.get(random3).getRelatedSongs().get(random1).getBand_singers().getName();
			
		} else {
			int min = 0, max = songList.findAll().size() - 1;
			int random1 = min + (int) (Math.random() * ((max - min) + 1));
			int random2 = min + (int) (Math.random() * ((max - min) + 1));
			int random3 = min + (int) (Math.random() * ((max - min) + 1));
			List<Song> songs = songList.findAll();
			endString += songs.get(random1).getName() + " by " + songs.get(random1).getSinger().getStage_name();
			endString += songs.get(random2).getName() + " by " + songs.get(random2).getSinger().getStage_name();
			endString += songs.get(random3).getName() + " by " + songs.get(random3).getSinger().getStage_name();
		}
		return endString;
	}
	
	@Override
	public boolean addUser(User user)
	{
		if (user == null) throw new NullPointerException("[ERROR] User List is NULL");
		if (this.userList.findAll().contains(user)) return false;
		this.userList.add(user);
		return true;
	}
}
