package view;

import controller.UserController;
import model.album.Artist;
import model.concert.Concert;
import model.song.Song;
import model.users.User;
import repository.inmemory.*;

import java.text.ParseException;
import java.util.Scanner;

public class UserUI
{
	UserController controller;
	boolean isAdmin;
	
	public UserUI(Integer saveType, boolean isAdmin) throws ParseException, RuntimeException
	{
		this.isAdmin = isAdmin;
		if (saveType == 1) {
			AlbumsInMemoryRepository albumsInMemoryRepository = new AlbumsInMemoryRepository();
			ArtistsInMemoryRepository artistsInMemoryRepository = new ArtistsInMemoryRepository();
			ConcertsInMemoryRepository concertsInMemoryRepository = new ConcertsInMemoryRepository();
			SongsInMemoryRepository songsInMemoryRepository = new SongsInMemoryRepository();
			UserInMemoryRepository userInMemoryRepository = new UserInMemoryRepository();
			if (isAdmin) System.out.println("Not yet implemented!");
			else
				this.controller = new UserController(albumsInMemoryRepository, artistsInMemoryRepository, concertsInMemoryRepository, songsInMemoryRepository, userInMemoryRepository);
		} else
			//TODO add file and db controllers
			throw new RuntimeException("Not implemented yet!");
	}
	
	public void switchMenu()
	{
		if (isAdmin)
			adminMenu();
		else
			userMenu();
	}
	
	private void adminMenu() {}
	
	private void userMenu()
	{
		boolean exit = false;
		while (!exit) {
			System.out.println("""
					\t--- Music Song Management ---
					
					1. Show List of Artists
					2. Show available Albums
					3. Show Albums for Artist
					4. Show Upcoming Events
					5. Sort Albums by Revenue
					6. Sort Songs by Rating
					7. Sort Songs by Release Date
					8. Sort Artists by Name (Stage Name)
					9. Sort Albums by Release Date
					10. Add Song to Favourites
					11. Remove Song from Favourites
					12. Show Favourites
					13. Buy Ticket for Concert
					14. Show my Tickets
					15. Show Recommended
					16. Add New User
					17. Exit
					""");
			System.out.println("#Menu: ");
			Scanner input = new Scanner(System.in);
			int choice = input.nextInt();
			switch (choice) {
				case 1 -> {
					System.out.println(this.controller.showArtists());
				}
				case 2 -> {
					System.out.println(this.controller.showAlbums());
				}
				case 3 -> {
					System.out.println("\nEnter Artist Stage Name: ");
					Scanner artistIn = new Scanner(System.in);
					String stageName = artistIn.nextLine();
					Artist artist = this.controller.getArtistList().findByID(stageName);
					try {
						System.out.println(this.controller.showAlbumsForArtist(artist));
					}
					catch (NullPointerException e) {
						System.out.println(e.toString());
					}
				}
				case 4 -> {
					System.out.println(this.controller.showUpcomingConcerts());
				}
				case 5 -> {
					this.controller.sortAlbumsByRevenue();
					System.out.println(this.controller.getAlbumList().toString());
				}
				case 6 -> {
					this.controller.sortSongsByRating();
					System.out.println(this.controller.getSongList().toString());
				}
				case 7 -> {
					this.controller.sortSongsByReleaseDate();
					System.out.println(this.controller.getSongList().toString());
				}
				case 8 -> {
					this.controller.sortArtistsByName();
					System.out.println(this.controller.getArtistList().toString());
				}
				case 9 -> {
					this.controller.sortAlbumsByReleaseDate();
					System.out.println(this.controller.getAlbumList().toString());
				}
				case 10 -> {
					System.out.println("\nEnter Song Name to be added: ");
					Scanner songIn = new Scanner(System.in);
					String songName = songIn.nextLine();
					try {
						Song song = this.controller.getSongList().findByID(songName);
						this.controller.addFavourite(song);
					}
					catch (NullPointerException exception) {
						System.out.println("Unavailable Song Name\n");
					}
				}
				case 11 -> {
					System.out.println("\nEnter Song Name to be removed: ");
					Scanner songIn = new Scanner(System.in);
					String songName = songIn.nextLine();
					try {
						Song song = this.controller.getSongList().findByID(songName);
						this.controller.removeFavourite(song);
					}
					catch (NullPointerException e) {
						System.out.println("Unavailable Song Name\n");
					}
				}
				case 12 -> {
					System.out.println(this.controller.showFavourites());
				}
				case 13 -> {
					System.out.println(this.controller.getConcertList().toString());
					System.out.println("\nEnter Concert Name: ");
					Scanner concertIn = new Scanner(System.in);
					String concertName = concertIn.nextLine();
					Concert concert = this.controller.getConcertList().findByID(concertName);
					try {
						this.controller.buyTicket(concert, 1);
					}
					catch (NullPointerException e) {
						System.out.println(e.toString());
					}
				}
				case 14 -> {
					System.out.println(this.controller.showTickets());
				}
				case 15 -> {
					System.out.println(this.controller.showRecommended());
				}
				case 16 -> {
					Scanner scanner = new Scanner(System.in);
					System.out.println("\nEnter Username: ");
					String username = scanner.nextLine();
					System.out.println("\nEnter Password: ");
					String password = scanner.nextLine();
					this.controller.addUser(new User(username, password));
				}
				case 17 -> {
					exit = true;
				}
				default -> System.out.println("Invalid Input!\n");
			}
		}
	}
}
