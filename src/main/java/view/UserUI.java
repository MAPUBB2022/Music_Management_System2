package view;

import controller.AdminController;
import controller.UserController;
import model.album.Artist;
import model.concert.Concert;
import model.song.Song;
import model.users.User;
import repository.inmemory.*;
import repository.jdbc.*;

import java.text.ParseException;
import java.util.Scanner;

public class UserUI
{
	AdminController adminController;
	UserController userController;
	User user;
	
	public UserUI(Integer saveType, User user) throws ParseException, RuntimeException
	{
		this.user = user;
		switch (saveType) {
			case 1 -> {
				AlbumsInMemoryRepository albumsInMemoryRepository = new AlbumsInMemoryRepository();
				ArtistsInMemoryRepository artistsInMemoryRepository = new ArtistsInMemoryRepository();
				ConcertsInMemoryRepository concertsInMemoryRepository = new ConcertsInMemoryRepository();
				SongsInMemoryRepository songsInMemoryRepository = new SongsInMemoryRepository();
				UserInMemoryRepository userInMemoryRepository = new UserInMemoryRepository();
				TicketsInMemoryRepository ticketsInMemoryRepository = new TicketsInMemoryRepository(this.user);
				UserFavouritesInMemoryRepository userFavouritesInMemoryRepository = new UserFavouritesInMemoryRepository(this.user);
				if (user.isAdmin()) {
					System.out.println("Not yet implemented!");
					BandsInMemoryRepository bandsInMemoryRepository = new BandsInMemoryRepository();
					MusicLabelsInMemoryRepository musicLabelsInMemoryRepository = new MusicLabelsInMemoryRepository();
				} else
					this.userController = new UserController(albumsInMemoryRepository, artistsInMemoryRepository, concertsInMemoryRepository, songsInMemoryRepository, userInMemoryRepository, ticketsInMemoryRepository, userFavouritesInMemoryRepository);
			}
			case 2 -> {//TODO add file and db controllers
				throw new RuntimeException("Not implemented yet!");
			}
			case 3 -> {
				JdbcAlbumsRepository albumsRepository = new JdbcAlbumsRepository();
				JdbcArtistsRepository artistsRepository = new JdbcArtistsRepository();
				JdbcConcertsRepository concertsRepository = new JdbcConcertsRepository();
				JdbcSongsRepository songsRepository = new JdbcSongsRepository();
				JdbcUserRepository userRepository = new JdbcUserRepository();
				JdbcTicketsRepository ticketsRepository = new JdbcTicketsRepository(this.user);
				JdbcUserFavouritesRepository userFavouritesRepository = new JdbcUserFavouritesRepository(this.user);
				if (user.isAdmin()) {
					System.out.println("Not yet implemented!");
					JdbcBandsRepository bandsRepository = new JdbcBandsRepository();
					JdbcMusicLabelsRepository musicLabelsRepository = new JdbcMusicLabelsRepository();
				} else
					this.userController = new UserController(albumsRepository, artistsRepository, concertsRepository, songsRepository, userRepository, ticketsRepository, userFavouritesRepository);
			}
		}
	}
	
	public void switchMenu()
	{
		if (user.isAdmin()) adminMenu();
		else userMenu();
	}
	
	private void adminMenu() {}
	
	private void userMenu()
	{
		boolean exit = false;
		while (!exit) {
			System.out.println("""
					\t--- Music Song Management ---
					
					1. Show List of Artists
					2. Show Available Albums
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
					System.out.println(this.userController.showArtists());
				}
				case 2 -> {
					System.out.println(this.userController.showAlbums());
				}
				case 3 -> {
					System.out.println("\nEnter Artist Stage Name: ");
					Scanner artistIn = new Scanner(System.in);
					String stageName = artistIn.nextLine();
					Artist artist = this.userController.getArtistList().findByID(stageName);
					try {
						System.out.println(this.userController.showAlbumsForArtist(artist));
					}
					catch (NullPointerException e) {
						System.out.println(e.getMessage());
					}
				}
				case 4 -> {
					System.out.println(this.userController.showUpcomingConcerts());
				}
				case 5 -> {
					this.userController.sortAlbumsByRevenue();
					System.out.println(this.userController.getAlbumList().toString());
				}
				case 6 -> {
					this.userController.sortSongsByRating();
					System.out.println(this.userController.getSongList().toString());
				}
				case 7 -> {
					this.userController.sortSongsByReleaseDate();
					System.out.println(this.userController.getSongList().toString());
				}
				case 8 -> {
					this.userController.sortArtistsByName();
					System.out.println(this.userController.getArtistList().toString());
				}
				case 9 -> {
					this.userController.sortAlbumsByReleaseDate();
					System.out.println(this.userController.getAlbumList().toString());
				}
				case 10 -> {
					System.out.println("\nEnter Song Name to be added: ");
					Scanner songIn = new Scanner(System.in);
					String songName = songIn.nextLine();
					try {
						Song song = this.userController.getSongList().findByID(songName);
						this.userController.addFavourite(song);
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
						Song song = this.userController.getSongList().findByID(songName);
						this.userController.removeFavourite(song);
					}
					catch (NullPointerException e) {
						System.out.println("Unavailable Song Name\n");
					}
				}
				case 12 -> {
					System.out.println(this.userController.showFavourites());
				}
				case 13 -> {
					System.out.println(this.userController.getConcertList().toString());
					System.out.println("\nEnter Concert Name: ");
					Scanner scannerIn = new Scanner(System.in);
					String concertName = scannerIn.nextLine();
					System.out.println("\nEnter Number of Tickets to be Purchased: ");
					int ticketCount = Integer.parseInt(scannerIn.next());
					Concert concert = this.userController.getConcertList().findByID(concertName);
					try {
						this.userController.buyTicket(concert, ticketCount, user);
					}
					catch (NullPointerException e) {
						System.out.println(e.getMessage());
					}
				}
				case 14 -> {
					System.out.println(this.userController.showTickets());
				}
				case 15 -> {
					System.out.println(this.userController.showRecommended());
				}
				case 16 -> {
					Scanner scanner = new Scanner(System.in);
					System.out.println("\nEnter Username: ");
					String username = scanner.nextLine();
					System.out.println("\nEnter Password: ");
					String password = scanner.nextLine();
					this.userController.addUser(new User(username, password));
				}
				case 17 -> {
					exit = true;
				}
				default -> System.out.println("Invalid Input!\n");
			}
		}
	}
}
