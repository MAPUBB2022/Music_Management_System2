package controller;

import exceptions.NonExistentUserException;
import model.album.Album;
import model.album.Artist;
import model.concert.Concert;
import model.song.Song;
import model.users.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import repository.inmemory.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest
{
	private UserController userController;
	private AlbumsInMemoryRepository albums;
	private ArtistsInMemoryRepository artists;
	private ConcertsInMemoryRepository concerts;
	private SongsInMemoryRepository songs;
	private UserInMemoryRepository users;
	
	@BeforeEach
	void setUp() throws ParseException
	{
		User user = new User("test", "test");
		albums = new AlbumsInMemoryRepository();
		artists = new ArtistsInMemoryRepository();
		concerts = new ConcertsInMemoryRepository();
		songs = new SongsInMemoryRepository();
		users = new UserInMemoryRepository();
		TicketsInMemoryRepository myTickets = new TicketsInMemoryRepository(user);
		UserFavouritesInMemoryRepository userFavourites = new UserFavouritesInMemoryRepository(user);
		userController = new UserController(albums, artists, concerts, songs, users, myTickets, userFavourites);
	}
	
	@Test
	@DisplayName("Test - Get Album InMemory Repository")
	void getAlbumList()
	{
		AlbumsInMemoryRepository albums1 = (AlbumsInMemoryRepository) userController.getAlbumList();
		assert albums1 != null;
		assertEquals(albums1.findAll().size(), albums.findAll().size());
		assertNotEquals(albums1.toString(), "");
	}
	
	@Test
	@DisplayName("Test - Get Artist InMemory Repository")
	void getArtistList()
	{
		ArtistsInMemoryRepository artists1 = (ArtistsInMemoryRepository) userController.getArtistList();
		assert artists1 != null;
		assertEquals(artists1.findAll().size(), artists.findAll().size());
		assertNotEquals(artists1.toString(), "");
	}
	
	@Test
	@DisplayName("Test - Get Concert InMemory Repository")
	void getConcertList()
	{
		ConcertsInMemoryRepository concerts1 = (ConcertsInMemoryRepository) userController.getConcertList();
		assert concerts1 != null;
		assertEquals(concerts1.findAll().size(), albums.findAll().size());
		assertNotEquals(concerts1.toString(), "");
	}
	
	@Test
	@DisplayName("Test - Get Song InMemory Repository")
	void getSongList()
	{
		SongsInMemoryRepository songs1 = (SongsInMemoryRepository) userController.getSongList();
		assert songs1 != null;
		assertEquals(songs1.findAll().size(), songs.findAll().size());
//		assertNotEquals(songs1.toString(), "");
	}
	
	@Test
	@DisplayName("Test - Show All Artists")
	void showArtists()
	{
		assertNotEquals(userController.showArtists(), "");
	}
	
	@Test
	@DisplayName("Test - Show All Albums")
	void showAlbums()
	{
		assertNotEquals(userController.showAlbums(), "");
	}
	
	@Test
	@DisplayName("Test - Show Albums For Specified Artist")
	void showAlbumsForArtist()
	{
		Artist artist = artists.findByID("Eminem");
		Artist artist2 = artists.findByID("Non-Existing Artist");
		Artist artist3 = artists.findByID("50 Cent");
		assertNotEquals(artist, null);
		assertNotEquals(artist3, null);
		assertNull(artist2);
		assertNotEquals(userController.showAlbumsForArtist(artist), "");
		try {
			userController.showAlbumsForArtist(null);
			assert false;
		}
		catch (NullPointerException exception) {
			assert true;
		}
		assertEquals(userController.showAlbumsForArtist(artist3), "[WARNING] No Albums found for specified Artist\n");
	}
	
	@Test
	@DisplayName("Test - Show Upcoming Concerts")
	void showUpcomingConcerts()
	{
		Date today = new Date();
		List<Concert> concertList = new ArrayList<>();
		for (Concert concert : concerts.findAll())
			if (concert.getDate().after(today)) concertList.add(concert);
		if (concertList.size() == 0)
			assertEquals(userController.showUpcomingConcerts(), "[WARNING] No Upcoming Concert exist\n");
		else assertNotEquals(userController.showUpcomingConcerts(), "");
	}
	
	@Test
	@DisplayName("Test - Sort Albums By Revenue")
	void sortAlbumsByRevenue()
	{
		userController.sortAlbumsByRevenue();
		List<Album> albumList = userController.getAlbumList().findAll();
		float current = albumList.get(0).calculateProfit();
		for (Album album : albumList) {
			float newCurrent = album.calculateProfit();
			assert current <= newCurrent;
			current = newCurrent;
		}
	}
	
	@Test
	@DisplayName("Test - Sort Songs By Rating")
	void sortSongsByRating()
	{
		userController.sortSongsByRating();
		List<Song> songList = userController.getSongList().findAll();
		Float rating = songList.get(0).getRating();
		for (Song song : songList) {
			Float newRating = song.getRating();
			assert rating >= newRating;
			rating = newRating;
		}
	}
	
	@Test
	@DisplayName("Test - Sort Songs By Release Date")
	void sortSongsByReleaseDate()
	{
		userController.sortSongsByReleaseDate();
		List<Song> songList = userController.getSongList().findAll();
		Date releaseDate = songList.get(0).getReleaseDate();
		for (Song song : songList) {
			Date newReleaseDate = song.getReleaseDate();
			assert releaseDate.compareTo(newReleaseDate) <= 0;
			releaseDate = newReleaseDate;
		}
	}
	
	@Test
	@DisplayName("Test - Sort Artists By Stage Name")
	void sortArtistsByName()
	{
		userController.sortArtistsByName();
		List<Artist> artistList = userController.getArtistList().findAll();
		String stageName = artistList.get(0).getStageName();
		for (Artist artist : artistList) {
			String newStageName = artist.getStageName();
			assert stageName.compareTo(newStageName) <= 0;
			stageName = newStageName;
		}
	}
	
	@Test
	@DisplayName("Test - Sort Albums By Release Date")
	void sortAlbumsByReleaseDate()
	{
		userController.sortAlbumsByReleaseDate();
		List<Album> albumList = userController.getAlbumList().findAll();
		Date releaseDate = albumList.get(0).getReleaseDate();
		for (Album album : albumList) {
			Date newReleaseDate = album.getReleaseDate();
			assert releaseDate.compareTo(newReleaseDate) <= 0;
			releaseDate = newReleaseDate;
		}
	}
	
	@Test
	@DisplayName("Test - Add To Favourites")
	void addFavourite()
	{
		Song s1 = songs.findByID("Mercy");
		Song s2 = songs.findByID("Non-Existing Song");
		
		assertEquals(userController.getMyFavourites().findAll().size(), 0);
		assertTrue(userController.addFavourite(s1));
		assertEquals(userController.getMyFavourites().findAll().size(), 1);
		assert userController.getMyFavourites().findAll().contains(s1);
		try {
			userController.addFavourite(s2);
			assert false;
		}
		catch (NullPointerException e) {
			assert true;
		}
		assertFalse(userController.addFavourite(s1));
	}
	
	@Test
	@DisplayName("Test - Remove From Favourites")
	void removeFavourite()
	{
		Song s1 = songs.findByID("Mercy");
		Song s2 = songs.findByID("Non-Existing Song");
		Song s3 = songs.findByID("Not Afraid");
		userController.addFavourite(s1);
		
		assertEquals(userController.getMyFavourites().findAll().size(), 1);
		assert userController.getMyFavourites().findAll().contains(s1);
		userController.removeFavourite(s1);
		assertEquals(userController.getMyFavourites().findAll().size(), 0);
		try {
			userController.removeFavourite(s2);
			assert false;
		}
		catch (NullPointerException e) {
			assert true;
		}
		assertFalse(userController.removeFavourite(s3));
	}
	
	@Test
	@DisplayName("Test - Show Favourites")
	void showFavourites()
	{
		assertEquals(userController.showFavourites(), "[WARNING] List of Favourites is Empty\n");
		Song s1 = songs.findByID("Mercy");
		Song s2 = songs.findByID("Not Afraid");
		assertTrue(userController.addFavourite(s1));
		assertTrue(userController.addFavourite(s2));
		assertNotEquals(userController.showFavourites(), "");
	}
	
	@Test
	@DisplayName("Test - Buy Ticket")
	void buyTicket()
	{
		User user = users.findByID("admin");
		try {
			userController.buyTicket(null, 0, user);
			assert false;
		}
		catch (NullPointerException e) {
			assert true;
		}
		int initialRepoSize = userController.getMyTickets().findAll().size();
		Concert concert = concerts.findByID("Heaven 17");
		int tickets = concert.getTicketsSold();
		assertFalse(userController.buyTicket(concert, 10000000, user));
		assertTrue(userController.buyTicket(concert, 1, user));
		assertEquals(concert.getTicketsSold(), tickets + 1);
		assertEquals(userController.getMyTickets().findAll().size(), initialRepoSize + 1);
	}
	
	@Test
	@DisplayName("Test - Show Tickets")
	void showTickets()
	{
		User user = users.findByID("admin");
		assertNotEquals(userController.showTickets(), "[WARNING] Ticket List is Empty\n");
		Concert concert = concerts.findByID("Heaven 17");
		try {
			userController.buyTicket(null, 0, user);
			assert false;
		}
		catch (NullPointerException e) {
			assert true;
		}
		try {
			userController.buyTicket(concert, 0, null);
			assert false;
		}
		catch (NullPointerException e) {
			assert true;
		}
		assertFalse(userController.buyTicket(concert, 0, user));
		userController.buyTicket(concert, 1, user);
		userController.buyTicket(concert, 2, user);
		assertNotEquals(userController.showTickets(), "[WARNING] Ticket List is Empty\n");
	}
	
	@Test
	@DisplayName("Test - Show Recommended")
	void showRecommended()
	{
		assertNotEquals(userController.showRecommended(), "");
	}
	
	@Test
	@DisplayName("Test - Add User")
	void addUser()
	{
		try {
			userController.addUser(null);
			assert false;
		}
		catch (NonExistentUserException e) {
			assert true;
		}
		User u1 = users.findByID("admin");
		User u2 = new User("test", "valid");
		assertFalse(userController.addUser(u1));
		assertTrue(userController.addUser(u2));
	}
}