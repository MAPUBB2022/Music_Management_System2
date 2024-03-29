package view;

import controller.AdminController;
import controller.UserController;
import model.album.*;
import model.concert.*;
import model.label.*;
import model.song.*;
import model.users.*;
import repository.inmemory.*;
import repository.jdbc.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
					BandsInMemoryRepository bandsInMemoryRepository = new BandsInMemoryRepository();
					MusicLabelsInMemoryRepository musicLabelsInMemoryRepository = new MusicLabelsInMemoryRepository();
					this.adminController = new AdminController(albumsInMemoryRepository, artistsInMemoryRepository, concertsInMemoryRepository, songsInMemoryRepository, bandsInMemoryRepository, musicLabelsInMemoryRepository, userInMemoryRepository);
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
					JdbcBandsRepository bandsRepository = new JdbcBandsRepository();
					JdbcMusicLabelsRepository musicLabelsRepository = new JdbcMusicLabelsRepository();
					this.adminController = new AdminController(albumsRepository, artistsRepository, concertsRepository, songsRepository, bandsRepository, musicLabelsRepository, userRepository);
				} else
					this.userController = new UserController(albumsRepository, artistsRepository, concertsRepository, songsRepository, userRepository, ticketsRepository, userFavouritesRepository);
			}
		}
	}
	
	public void switchMenu() throws SQLException, ParseException
	{
		if (user.isAdmin()) adminMenu();
		else userMenu();
	}
	
	private void adminMenu() throws SQLException, ParseException
	{
		// create instances
		Artist artist = new Artist();
		Album album = new Album();
		Band band = new Band();
		Concert concert = new Concert();
		User user = new User();
		MusicLabel label = new MusicLabel();
		
		boolean exit = false;
		while (!exit) {
			System.out.println("""
					\t--- Music Song Management ---
					
					1. Add Album
					2. Delete Album
					3. Modify Album
					4. Add Artist
					5. Delete Artist
					6. Modify Artist
					7. Add Band
					8. Delete Band
					9. Modify Band
					10. Add Concert
					11. Delete Concert
					12. Modify Concert
					13. Add Music Label
					14. Delete Music Label
					15. Modify Music Label
					16. Add Song
					17. Delete Song
					18. Modify Song
					19. Add User
					20. Delete User
					21. Modify User
					22. Sort Albums by Revenue
					23. Sort Songs by Rating
					24. Sort Songs by Release Date
					25. Sort Artists by Name
					26. Sort Albums by Release Date
					27. Sort Albums by Production Cost
					28. Show Concerts
					29. Show Artists
					30. Show Albums
					31. Show Upcoming Concerts
					""");
			System.out.println("#Menu: ");
			Scanner inp = new Scanner(System.in);
			int choice = inp.nextInt();
			int choice2;
			
			switch (choice) {
				case 1 -> {
					boolean valid = false;
					System.out.println("\nAdd Album:");
					do {
						System.out.println("1. Album for Artist\n2. Album for Band");
						choice2 = inp.nextInt();
						if (choice2 == 1 || choice2 == 2) valid = true;
						else System.out.println("Invalid input! Choose 1 or 2!");
						
					} while (!valid);
					
					if (choice2 == 1) {
						String albumTitle;
						System.out.println("Enter album information:");
						
						//name
						System.out.println("name: ");
						albumTitle = inp.nextLine();
						Album album1 = this.adminController.getAlbumList().findByID(albumTitle);
						if (album1 != null) // album already exists
							adminController.addAlbum(album1);
						else album.setTitle(albumTitle);
						
						
						System.out.println("Enter artist information:");
						
						//stage name
						System.out.println("stage name: ");
						String stageName = inp.nextLine();
						Artist artist1 = this.adminController.getArtistList().findByID(stageName);
						
						if (artist1 != null)  //set artist to album if it already exists in the artist list
							album.setArtist(artist1);
						else { // input for new artist
							//stage name
							artist.setStageName(stageName);
							
							//name
							System.out.println("name: ");
							String name = inp.nextLine();
							artist.setName(name);
							
							//salary
							System.out.println("salary: ");
							float salary = inp.nextFloat();
							artist.setSalary(salary);
						}
						
						
						//Song list
						System.out.println("Enter song list:\nThe number of songs you want to add: ");
						int nr = inp.nextInt();
						String songName = null;
						String type;
						
						do {
							//name
							System.out.println("name: ");
							songName = inp.nextLine();
							
							// set song
							
							Song song = this.adminController.getSongList().findByID(songName);
							if (song != null)  //set song to album if it already exists in the song list
								album.addSong(song); // add input song to album
							else {
								song.setName(songName);
								
								//rating
								System.out.println("rating: ");
								float rating = inp.nextFloat();
								
								//release date
								System.out.println("release date: ");
								String date = inp.nextLine();
								Date release = new SimpleDateFormat("dd/MM/yyyy").parse(date);
								
								
								//language of album
								System.out.println("\nlanguage of album: ");
								String language = inp.nextLine();
								album.setLanguage(language);
								
								//production cost
								System.out.println("production cost: ");
								float productionCost = inp.nextFloat();
								album.setProductionCost(productionCost);
								
								// copies sold
								System.out.println("copies sold: ");
								int copies = inp.nextInt();
								album.setCopiesSold(copies);
								
								// disc price
								System.out.println("copies sold: ");
								float price = inp.nextFloat();
								album.setDiscPrice(price);
								
								song.setRating(rating);
								song.setReleaseDate(release);
								song.setSinger(artist);
								
								album.addSong(song); // add input song to album
							}
							nr--;
							
						} while (nr > 0);
						
						// add new album to the list of albums (at this point, album must be valid)
						adminController.addAlbum(album);
						
					} else {   // choice2 = 2
						String albumTitle;
						System.out.println("Enter album information:");
						
						//name
						System.out.println("name: ");
						albumTitle = inp.nextLine();
						Album album1 = this.adminController.getAlbumList().findByID(albumTitle);
						if (album1 != null) // album already exists
							adminController.addAlbum(album1);
						else album.setTitle(albumTitle);
						
						
						System.out.println("Enter band information:");
						
						//name
						System.out.println("name: ");
						String name = inp.nextLine();
						Band band1 = this.adminController.getBandList().findByID(name);
						
						if (band1 != null)  //set band to album if it already exists in the band list
							album.setBand(band1);
						else { // input for new artist
							
							//name
							band.setName(name);
							
							//formation date
							System.out.println("formation date: ");
							String date = inp.nextLine();
							Date formation = new SimpleDateFormat("dd/MM/yyyy").parse(date);
							band.setFormationDate(formation);
							
							//origin
							System.out.println("origin: ");
							String origin = inp.nextLine();
							band.setOrigin(origin);
							
							
							//artist list
							List<Artist> artists = null;
							System.out.println("Enter artist list:\nThe number of artists you want to add: ");
							int nr = inp.nextInt();
							do {
								//name
								System.out.println("stage name: ");
								String stageName = inp.nextLine();
								
								Artist artist2 = this.adminController.getArtistList().findByID(stageName);
								
								if (artist2 != null)  //set artist to album if it already exists in the artist list
									album.setArtist(artist2);
								else { // input for new artist
									//name
									artist.setStageName(stageName);
									
									//stage name
									System.out.println("name: ");
									name = inp.nextLine();
									artist.setName(name);
									
									//salary
									System.out.println("salary: ");
									float salary = inp.nextFloat();
									artist.setSalary(salary);
									
									artists.add(artist); // add arists to new list
								}
								nr--;
							} while (nr > 0);
							
							band.setArtistList(artists);
						}
						
						
						//Song list
						System.out.println("Enter song list:\nThe number of songs you want to add: ");
						int nr = inp.nextInt();
						String songName = null;
						String type;
						
						do {
							//name
							System.out.println("name: ");
							songName = inp.nextLine();
							
							// set song
							
							Song song = this.adminController.getSongList().findByID(songName);
							if (song != null)  //set song to album if it already exists in the song list
								album.addSong(song); // add input song to album
							else {
								song.setName(songName);
								
								//rating
								System.out.println("rating: ");
								float rating = inp.nextFloat();
								
								//release date
								System.out.println("release date: ");
								String date = inp.nextLine();
								Date release = new SimpleDateFormat("dd/MM/yyyy").parse(date);
								
								
								//language of album
								System.out.println("\nlanguage of album: ");
								String language = inp.nextLine();
								album.setLanguage(language);
								
								//production cost
								System.out.println("production cost: ");
								float productionCost = inp.nextFloat();
								album.setProductionCost(productionCost);
								
								// copies sold
								System.out.println("copies sold: ");
								int copies = inp.nextInt();
								album.setCopiesSold(copies);
								
								// disc price
								System.out.println("copies sold: ");
								float price = inp.nextFloat();
								album.setDiscPrice(price);
								
								song.setRating(rating);
								song.setReleaseDate(release);
								song.setSinger(artist);
								
								album.addSong(song); // add input song to album
							}
							nr--;
							
						} while (nr > 0);
						
						// add new album to the list of albums (at this point, album must be valid)
						adminController.addAlbum(album);
					}
				}
				
				case 2 -> {
					System.out.println("\nEnter Album Name to be deleted: ");
					Scanner albumIn = new Scanner(System.in);
					String albumName = albumIn.nextLine();
					album = this.adminController.getAlbumList().findByID(albumName);
					if (album != null) {
						this.adminController.deleteAlbum(album);
						System.out.println("Album deleted successfully!");
					} else System.out.println("Unavailable Album Name\n");
				}
				
				case 3 -> {
					System.out.println("\nEnter Album Name to be modified: ");
					Scanner albumIn = new Scanner(System.in);
					String albumName = albumIn.nextLine();
					
					if (this.adminController.getAlbumList().findByID(albumName) != null) {
						album.setTitle(albumName);
						
						System.out.println("The number of attributes you want to modify: ");
						Scanner nrModif = new Scanner(System.in);
						int counter = nrModif.nextInt();
						
						do {
							System.out.println("Enter what you want to modify (1.production cost," + " 2.copies sold, 3.disc price ): ");
							Scanner choice3 = new Scanner(System.in);
							int inp1 = choice3.nextInt();
							
							
							if (inp1 == 1) {
								System.out.println("Enter the new production cost: ");
								Scanner inpCost = new Scanner(System.in);
								float newCost = inpCost.nextFloat();
								album.setProductionCost(newCost);
							} else if (inp1 == 2) {
								System.out.println("Enter the new amount of copies sold: ");
								Scanner inpCopiesSold = new Scanner(System.in);
								int newCopiesSold = inpCopiesSold.nextInt();
								album.setCopiesSold(newCopiesSold);
							} else if (inp1 == 3) {
								System.out.println("Enter the new disc price: ");
								Scanner inpDiscPrice = new Scanner(System.in);
								float newDiscPrice = inpDiscPrice.nextFloat();
								album.setDiscPrice(newDiscPrice);
							} else {
								System.out.println("Invalid input! Choose between 1-3. You still have " + counter + " modifications to do");
								counter++;
							}
							
							counter--;
						} while (counter > 0);
						
						this.adminController.modifyAlbum(album);
						System.out.println("Album modified successfully!");
					} else System.out.println("Album Name not available!");
					
				}
				
				case 4 -> {
					System.out.println("\nEnter Artist Name to be added: ");
					Scanner artistIn = new Scanner(System.in);
					String artistName = artistIn.nextLine();
					
					if (this.adminController.getArtistList().findByID(artistName) == null) {
						
						artist.setName(artistName);
						
						System.out.println("Enter Artist information:\nstage name: ");
						Scanner input = new Scanner(System.in);
						String stageName = input.nextLine();
						artist.setStageName(stageName);
						
						System.out.println("salary: ");
						Scanner input3 = new Scanner(System.in);
						float salary = input3.nextFloat();
						artist.setSalary(salary);
						
						this.adminController.addArtist(artist);
						System.out.println("Artist added successfully!");
					} else System.out.println("Artist Name already exists!\n");
					
				}
				
				case 5 -> {
					System.out.println("\nEnter Artist Name to be deleted: ");
					Scanner artistIn = new Scanner(System.in);
					String artistName = artistIn.nextLine();
					artist = this.adminController.getArtistList().findByID(artistName);
					if (artist != null) {
						this.adminController.deleteArtist(artist);
						System.out.println("Artist deleted successfully");
					} else System.out.println("Unavailable Artist Name\n");
				}
				
				case 6 -> {
					System.out.println("\nEnter Artist Name to be modified: ");
					Scanner artistIn = new Scanner(System.in);
					String artistStageName = artistIn.nextLine();
					
					if (this.adminController.getArtistList().findByID(artistStageName) != null) {
						artist.setStageName(artistStageName);
						
						System.out.println("The number of attributes you want to modify: ");
						Scanner nrModif = new Scanner(System.in);
						int counter = nrModif.nextInt();
						
						do {
							System.out.println("Enter what you want to modify (1.name," + " 2.salary): ");
							Scanner choice3 = new Scanner(System.in);
							int inp1 = choice3.nextInt();
							
							if (inp1 == 1) {
								System.out.println("Enter the new name: ");
								Scanner name = new Scanner(System.in);
								String newName = name.nextLine();
								artist.setName(newName);
							} else if (inp1 == 2) {
								System.out.println("Enter the new salary: ");
								Scanner inpSalary = new Scanner(System.in);
								float newSalary = inpSalary.nextFloat();
								artist.setSalary(newSalary);
							} else {
								System.out.println("Invalid input! Choose between 1-3. You still have " + counter + " modifications to do");
								counter++;
							}
							
							counter--;
						} while (counter > 0);
						
						this.adminController.modifyArtist(artist);
						System.out.println("Artist modified successfully!");
					} else System.out.println("Artist Name not available!");
					
				}
				
				case 7 -> {
					//name
					System.out.println("Enter band name to be added:");
					String name = inp.nextLine();
					band = this.adminController.getBandList().findByID(name);
					
					if (band == null) {
						
						System.out.println("formation date: ");
						String date = inp.nextLine();
						Date formation = new SimpleDateFormat("dd/MM/yyyy").parse(date);
						band.setFormationDate(formation);
						
						System.out.println("origin: ");
						String origin = inp.nextLine();
						band.setOrigin(origin);
						
						//artist list
						System.out.println("Enter artist list:\nThe number of artists you want to add: ");
						int nr = inp.nextInt();
						List<Artist> artists = null;
						do {
							//stage name
							System.out.println("stage name: ");
							String stageName = inp.nextLine();
							
							if (this.adminController.getArtistList().findByID(stageName) == null) {
								//stage name
								artist.setStageName(stageName);
								
								//name
								System.out.println("name: ");
								name = inp.nextLine();
								artist.setName(name);
								
								//salary
								System.out.println("salary: ");
								float salary = inp.nextFloat();
								artist.setSalary(salary);
								
								//add artist to new list
								artists.add(artist);
							} else System.out.println("Artist already exists!");
							
							nr--;
						} while (nr > 0);
						
						band.setArtistList(artists); // set artist list
						this.adminController.addBand(band); // add band
					} else System.out.println("Band already exists!");
				}
				
				case 8 -> {
					System.out.println("\nEnter Band Name to be deleted: ");
					Scanner bandIn = new Scanner(System.in);
					String bandName = bandIn.nextLine();
					
					band = this.adminController.getBandList().findByID(bandName);
					if (band != null) {
						this.adminController.deleteBand(band);
						System.out.println("Band deleted successfully");
					} else System.out.println("Unavailable Band Name\n");
				}
				
				case 9 -> {
					System.out.println("\nEnter Band Name to be modified: ");
					Scanner bandIn = new Scanner(System.in);
					String bandName = bandIn.nextLine();
					
					band = this.adminController.getBandList().findByID(bandName);
					if (band != null) {
						
						System.out.println("The number of artists you want to modify: ");
						Scanner nrModif = new Scanner(System.in);
						int counter = nrModif.nextInt();
						do {
							System.out.println("\nEnter Artist Name to be modified: ");
							Scanner artistIn = new Scanner(System.in);
							String artistStageName = artistIn.nextLine();
							
							if (this.adminController.getArtistList().findByID(artistStageName) != null) {
								artist.setStageName(artistStageName);
								
								System.out.println("The number of attributes you want to modify: ");
								Scanner nrModifA = new Scanner(System.in);
								int counter1 = nrModifA.nextInt();
								
								do {
									System.out.println("Enter what you want to modify (1.name," + " 2.salary): ");
									Scanner choice3 = new Scanner(System.in);
									int inp1 = choice3.nextInt();
									
									if (inp1 == 1) {
										System.out.println("Enter the new name: ");
										Scanner name = new Scanner(System.in);
										String newName = name.nextLine();
										artist.setName(newName);
									} else if (inp1 == 2) {
										System.out.println("Enter the new salary: ");
										Scanner inpSalary = new Scanner(System.in);
										float newSalary = inpSalary.nextFloat();
										artist.setSalary(newSalary);
									} else {
										System.out.println("Invalid input! Choose between 1-2. You still have " + counter1 + " modifications to do");
										counter1++;
									}
									
									counter1--;
								} while (counter1 > 0);
								
								this.adminController.modifyArtist(artist);
								System.out.println("Artist modified successfully!");
							} else {
								System.out.println("Artist Name not available! You still have " + counter + " modifications to do!");
								counter++;
							}
							counter--;
						} while (counter > 0);
						
						this.adminController.modifyBand(band);
						System.out.println("Band modified successfully!");
					} else System.out.println("Band Name not available!");
				}
				
				case 10 -> {
					System.out.println("\nEnter Concert Name to be added: ");
					Scanner concertIn = new Scanner(System.in);
					String concertName = concertIn.nextLine();
					
					if (this.adminController.getConcertList().findByID(concertName) == null) {
						System.out.println("Enter concert information:");
						concert.setName(concertName);
						
						
						//location
						System.out.println("location: ");
						String l = inp.nextLine();
						concert.setLocation(l);
						
						//date
						System.out.println("date: ");
						String date = inp.nextLine();
						Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(date);
						concert.setDate(date1);
						
						//capacity
						System.out.println("capacity: ");
						int cap = inp.nextInt();
						concert.setCapacity(cap);
						
						//ticket price
						System.out.println("ticket price: ");
						float price = inp.nextFloat();
						concert.setTicketPrice(price);
						
						//tickets sold
						System.out.println("number of tickets sold: ");
						int nrT = inp.nextInt();
						concert.setTicketsSold(nrT);
						
						//rent costs
						System.out.println("rent costs: ");
						float rent = inp.nextFloat();
						concert.setRentCosts(rent);
						
						
						//artist list
						System.out.println("Enter artist list:\nThe number of artists you want to add: ");
						int nr = inp.nextInt();
						List<Artist> artists = null;
						do {
							//stage name
							System.out.println("stage name: ");
							String stageName = inp.nextLine();
							
							if (this.adminController.getArtistList().findByID(stageName) == null) {
								//stage name
								artist.setStageName(stageName);
								
								//name
								System.out.println("name: ");
								String name = inp.nextLine();
								artist.setName(name);
								
								//salary
								System.out.println("salary: ");
								float salary = inp.nextFloat();
								artist.setSalary(salary);
								
								//add artist to new list
								artists.add(artist);
							} else System.out.println("Artist already exists!");
							
							nr--;
						} while (nr > 0);
						
						concert.setArtistList(artists); // set artist list
						this.adminController.addConcert(concert); // add concert
						
					} else System.out.println("Unavailable Concert Name\n");
				}
				
				case 11 -> {
					System.out.println("\nEnter Concert Name to be deleted: ");
					Scanner concertIn = new Scanner(System.in);
					String concertName = concertIn.nextLine();
					
					concert = this.adminController.getConcertList().findByID(concertName);
					if (concert != null) {
						this.adminController.deleteConcert(concert);
						System.out.println("Concert deleted successfully");
					} else System.out.println("Unavailable Concert Name\n");
				}
				
				case 12 -> {
					System.out.println("\nEnter Concert Name to be modified: ");
					Scanner concertIn = new Scanner(System.in);
					String concertName = concertIn.nextLine();
					
					concert = this.adminController.getConcertList().findByID(concertName);
					if (concert != null) {
						
						System.out.println("The number of attributes you want to modify: ");
						Scanner nrModif = new Scanner(System.in);
						int counter = nrModif.nextInt();
						
						do {
							System.out.println("Enter what you want to modify (1.artist list (delete or add artist," + " 2.location, 3.date, 4.capacity, 5.ticketPrice, 6.ticketsSold, 7.rentCosts: ");
							Scanner choice3 = new Scanner(System.in);
							int inp1 = choice3.nextInt();
							
							if (inp1 == 1) {
								boolean valid = false;
								do {
									System.out.println("Modify the list of artists:\n1.delete or 2.add artist: ");
									Scanner inp4 = new Scanner(System.in);
									int choice4 = inp4.nextInt();
									
									if (choice4 == 1) {
										//delete existing
										valid = true;
										System.out.println("The number of artists you want to delete:");
										
										Scanner i = new Scanner(System.in);
										int nr = i.nextInt();
										
										do {
											System.out.println("\nEnter Artist Name to be deleted: ");
											Scanner artistIn = new Scanner(System.in);
											String artistName = artistIn.nextLine();
											artist = this.adminController.getArtistList().findByID(artistName);
											
											if (concert.getArtistList().contains(artist)) {
												this.adminController.deleteArtist(artist); // delete artist if exists in the list
												System.out.println("Artist deleted successfully!");
											} else {
												System.out.println("Unavailable Artist Name! You still have " + nr + " artist(s) to delete");
												nr++;
											}
											
											nr--;
										} while (nr > 0);
										
									} else if (choice4 == 2) {
										//add non-existing
										valid = true;
										System.out.println("The number of artists you want to add:");
										Scanner i = new Scanner(System.in);
										int nr = i.nextInt();
										
										do {
											System.out.println("\nEnter Artist Name to be added: ");
											Scanner artistIn = new Scanner(System.in);
											String artistName = artistIn.nextLine();
											artist = this.adminController.getArtistList().findByID(artistName);
											
											if (!concert.getArtistList().contains(artist)) { // if artist does not appear yet
												concert.getArtistList().add(artist); // add artist to the artist list of the concert
												System.out.println("Artist added successfully!");
											} else {
												System.out.println("Artist Name already in list! You still have " + nr + " artist(s) to add");
												nr++;
											}
											
											nr--;
										} while (nr > 0);
										
									} else {
										System.out.println("Invalid input! Choose from 1-2!");
									}
								} while (!valid);
							} else if (inp1 == 2) {
								//location
								System.out.println("Enter the new location: ");
								String l = inp.nextLine();
								concert.setLocation(l);
							} else if (inp1 == 3) {
								//date
								System.out.println("Enter the new date: ");
								String date = inp.nextLine();
								Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(date);
								concert.setDate(date1);
							} else if (inp1 == 4) {
								//capacity
								System.out.println("Enter the new capacity: ");
								int cap = inp.nextInt();
								concert.setCapacity(cap);
							} else if (inp1 == 5) {
								//ticket price
								System.out.println("Enter the new ticketPrice: ");
								float price = inp.nextFloat();
								concert.setTicketPrice(price);
							} else if (inp1 == 6) {
								//tickets sold
								System.out.println("Enter the new ticketsSold: ");
								int nrT = inp.nextInt();
								concert.setTicketsSold(nrT);
							} else if (inp1 == 7) {
								//rent costs
								System.out.println("Enter the new rent costs: ");
								float rent = inp.nextFloat();
								concert.setRentCosts(rent);
							} else {
								System.out.println("Invalid input! Choose between 1-7. You still have " + counter + " modifications to do");
								counter++;
							}
							
							counter--;
						} while (counter > 0);
						
						this.adminController.modifyConcert(concert);
						System.out.println("Concert modified successfully!");
					} else System.out.println("Concert Name not available!");
					
					
				}
				case 13 -> {
					System.out.println("\nEnter Label Name to be added: ");
					Scanner labelIn = new Scanner(System.in);
					String labelName = labelIn.nextLine();
					
					if (this.adminController.getLabelList().findByID(labelName) == null) {
						label.setName(labelName);
						
						//address
						System.out.println("address: ");
						String addr = inp.nextLine();
						label.setAddress(addr);
						
						//revenue
						System.out.println("revenue: ");
						float rev = inp.nextFloat();
						label.setRevenue(rev);
						
						//artist list
						System.out.println("Enter artist list:\nThe number of artists you want to add: ");
						int nr = inp.nextInt();
						List<Artist> artists = null;
						do {
							//stage name
							System.out.println("stage name: ");
							String stageName = inp.nextLine();
							
							if (this.adminController.getArtistList().findByID(stageName) == null) {
								//stage name
								artist.setStageName(stageName);
								
								//name
								System.out.println("name: ");
								String name = inp.nextLine();
								artist.setName(name);
								
								//salary
								System.out.println("salary: ");
								float salary = inp.nextFloat();
								artist.setSalary(salary);
								
								//add artist to new list
								artists.add(artist);
							} else System.out.println("Artist already exists!");
							
							nr--;
						} while (nr > 0);
						
						label.setArtistList(artists); // set artist list
						
						//album list - can add existing albums only
						System.out.println("Enter album list (existing albums only):\nThe number of albums you want to add: ");
						int nrA = inp.nextInt();
						List<Album> albums = null;
						do {
							//title
							System.out.println("title: ");
							String title = inp.nextLine();
							
							album = this.adminController.getAlbumList().findByID(title);
							
							if (album != null) { // add existing album (can't create new album here)
								albums.add(album);
							} else System.out.println("This album is not available!");
							
							nrA--;
						} while (nrA > 0);
						
						label.setAlbumList(albums); // set album list
						
						//upcoming events
						System.out.println("Enter concert list (existing concerts):\nThe number of concerts you want to add: ");
						int nrC = inp.nextInt();
						List<Concert> concerts = null;
						do {
							//name
							System.out.println("name: ");
							String name = inp.nextLine();
							
							concert = this.adminController.getConcertList().findByID(name);
							
							if (concert != null) { // add existing concert (can't create new concert here)
								concerts.add(concert);
							} else System.out.println("This concert is not available!");
							
							nrC--;
						} while (nrC > 0);
						label.setUpcomingEvents(concerts); // set upcoming concerts
						
						this.adminController.addMusicLabel(label);
						System.out.println("Label added successfully!");
					} else System.out.println("Unavailable Label Name\n");
				}
				
				case 14 -> {
					System.out.println("\nEnter Label Name to be deleted: ");
					Scanner labelIn = new Scanner(System.in);
					String labelName = labelIn.nextLine();
					label = this.adminController.getLabelList().findByID(labelName);
					
					if (label != null) {
						this.adminController.deleteMusicLabel(label);
						System.out.println("Music Label deleted successfully!");
					} else System.out.println("Unavailable Label Name\n");
				}
				
				case 15 -> {
					System.out.println("\nEnter Label Name to be modified: ");
					Scanner labelIn = new Scanner(System.in);
					String labelName = labelIn.nextLine();
					
					label = this.adminController.getLabelList().findByID(labelName);
					if (label != null) {
						
						System.out.println("The number of attributes you want to modify: ");
						Scanner nrModifA = new Scanner(System.in);
						int counter1 = nrModifA.nextInt();
						
						do {
							System.out.println("Enter what you want to modify (1.address," + " 2.revenue): ");
							Scanner choice3 = new Scanner(System.in);
							int inp1 = choice3.nextInt();
							
							if (inp1 == 1) {
								System.out.println("Enter the new address: ");
								Scanner address = new Scanner(System.in);
								String newAddress = address.nextLine();
								label.setAddress(newAddress);
							} else if (inp1 == 2) {
								System.out.println("Enter the new revenue: ");
								Scanner revenue = new Scanner(System.in);
								float newRevenue = revenue.nextFloat();
								label.setRevenue(newRevenue);
							} else {
								System.out.println("Invalid input! Choose between 1-2. You still have " + counter1 + " modifications to do");
								counter1++;
							}
							counter1--;
						} while (counter1 > 0);
						
						this.adminController.modifyMusicLabel(label);
						System.out.println("Artist modified successfully!");
					} else System.out.println("Unavailable Label Name");
				}
				
				case 16 -> {
					//Song list
					System.out.println("Enter song list:\nThe number of songs you want to add: ");
					int nr = inp.nextInt();
					String songName = null;
					String type;
					
					do {
						//name
						System.out.println("name: ");
						songName = inp.nextLine();
						
						// set song
						
						Song song = this.adminController.getSongList().findByID(songName);
						if (song != null)  //set song to album if it already exists in the song list
							album.addSong(song); // add input song to album
						else {
							song.setName(songName);
							
							//rating
							System.out.println("rating: ");
							float rating = inp.nextFloat();
							
							//release date
							System.out.println("release date: ");
							String date = inp.nextLine();
							Date release = new SimpleDateFormat("dd/MM/yyyy").parse(date);
							
							
							//language of album
							System.out.println("\nlanguage of album: ");
							String language = inp.nextLine();
							album.setLanguage(language);
							
							//production cost
							System.out.println("production cost: ");
							float productionCost = inp.nextFloat();
							album.setProductionCost(productionCost);
							
							// copies sold
							System.out.println("copies sold: ");
							int copies = inp.nextInt();
							album.setCopiesSold(copies);
							
							// disc price
							System.out.println("copies sold: ");
							float price = inp.nextFloat();
							album.setDiscPrice(price);
							
							song.setRating(rating);
							song.setReleaseDate(release);
							song.setSinger(artist);
							
							album.addSong(song); // add input song to album
						}
						nr--;
						
					} while (nr > 0);
					
					// add new album to the list of albums (at this point, album must be valid)
					adminController.addAlbum(album);
					
					
				}
				
				case 17 -> {
					System.out.println("\nEnter Song Name to be removed: ");
					Scanner songIn = new Scanner(System.in);
					String songName = songIn.nextLine();
					Song song = this.adminController.getSongList().findByID(songName);
					
					if (song != null) {
						this.adminController.deleteSong(song);
						System.out.println("Song deleted successfully!");
					} else System.out.println("Unavailable Song Name\n");
				}
				
				case 18 -> {
					//Song list
					System.out.println("Enter song list:\nThe number of songs you want to add: ");
					int nr = inp.nextInt();
					String songName = null;
					String type;
					
					do {
						//name
						System.out.println("name: ");
						songName = inp.nextLine();
						
						// set song
						
						Song song = this.adminController.getSongList().findByID(songName);
						if (song != null)  //set song to album if it already exists in the song list
							album.addSong(song); // add input song to album
						else {
							song.setName(songName);
							
							//rating
							System.out.println("rating: ");
							float rating = inp.nextFloat();
							
							//release date
							System.out.println("release date: ");
							String date = inp.nextLine();
							Date release = new SimpleDateFormat("dd/MM/yyyy").parse(date);
							
							
							//language of album
							System.out.println("\nlanguage of album: ");
							String language = inp.nextLine();
							album.setLanguage(language);
							
							//production cost
							System.out.println("production cost: ");
							float productionCost = inp.nextFloat();
							album.setProductionCost(productionCost);
							
							// copies sold
							System.out.println("copies sold: ");
							int copies = inp.nextInt();
							album.setCopiesSold(copies);
							
							// disc price
							System.out.println("copies sold: ");
							float price = inp.nextFloat();
							album.setDiscPrice(price);
							
							song.setRating(rating);
							song.setReleaseDate(release);
							song.setSinger(artist);
							
							album.addSong(song); // add input song to album
						}
						nr--;
						
					} while (nr > 0);
					
					// add new album to the list of albums (at this point, album must be valid)
					adminController.addAlbum(album);
				}
				
				case 19 -> {
					System.out.println("\nEnter information about the new user: ");
					Scanner scanner = new Scanner(System.in);
					System.out.println("\nEnter Username to be added: ");
					String userName = scanner.nextLine();
					if (this.adminController.getUserList().findByID(userName) == null) {
						System.out.println("\nEnter Password: ");
						String password = scanner.nextLine();
						this.adminController.addUser(new User(userName, password));
						System.out.println("\nUser added successfully!");
					} else System.out.println("This username already exists!\n");
				}
				
				case 20 -> {
					System.out.println("\nEnter Username to be deleted: ");
					Scanner userIn = new Scanner(System.in);
					String userName = userIn.nextLine();
					user = this.adminController.getUserList().findByID(userName);
					if (user != null) {
						this.adminController.deleteUser(user);
						System.out.println("User deleted successfully!");
					} else System.out.println("Unavailable Username\n");
				}
				
				case 21 -> {
					//only password can be modified
					System.out.println("\nEnter Username to be modified: ");
					Scanner userIn = new Scanner(System.in);
					String userName = userIn.nextLine();
					
					if (this.adminController.getUserList().findByID(userName) != null) {
						user.setUsername(userName);
						
						System.out.println("\nEnter new password: ");
						Scanner userInNew = new Scanner(System.in);
						String userPasswdNew = userInNew.nextLine();
						user.setUsername(userPasswdNew);
						
						this.adminController.modifyUser(user);
						System.out.println("User modified successfully!");
					} else System.out.println("This username doesn't exist!\n");
				}
				
				case 22 -> {
					this.adminController.sortAlbumsByRevenue();
					System.out.println(this.adminController.getAlbumList().toString());
				}
				case 23 -> {
					this.adminController.sortSongsByRating();
					System.out.println(this.adminController.getSongList().toString());
				}
				case 24 -> {
					this.adminController.sortSongsByReleaseDate();
					System.out.println(this.adminController.getSongList().toString());
				}
				case 25 -> {
					this.adminController.sortArtistsByName();
					System.out.println(this.adminController.getArtistList().toString());
				}
				case 26 -> {
					this.adminController.sortAlbumsByReleaseDate();
					System.out.println(this.adminController.getAlbumList().toString());
				}
				case 27 -> {
					this.adminController.sortAlbumsByProductionCost();
					System.out.println(this.adminController.getAlbumList().toString());
				}
				case 28 -> {
					System.out.println(this.adminController.showConcerts());
				}
				case 29 -> {
					System.out.println(this.adminController.showArtists());
				}
				case 30 -> {
					System.out.println(this.adminController.showAlbums());
				}
				case 31 -> {
					System.out.println(this.adminController.showUpcomingConcerts());
				}
				case 32 -> {
					exit = true;
				}
				default -> System.out.println("Invalid Input!\n");
			}
		}
		
	}
	
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
