package view;

import controller.AdminController;
import controller.UserController;
import model.album.Album;
import model.album.Artist;
import model.album.Band;
import model.concert.Concert;
import model.label.MusicLabel;
import model.song.Song;
import model.users.User;
import repository.inmemory.*;

import java.awt.*;
import java.text.ParseException;
import java.util.Scanner;

public class UserUI
{
    UserController controller;
    AdminController controllerAdmin;
    boolean isAdmin;

    public UserUI(Integer saveType, boolean isAdmin) throws ParseException, RuntimeException
    {
//        this.isAdmin = isAdmin;
//        if (saveType == 1) {
//            AlbumsInMemoryRepository albumsInMemoryRepository = new AlbumsInMemoryRepository();
//            ArtistsInMemoryRepository artistsInMemoryRepository = new ArtistsInMemoryRepository();
//            ConcertsInMemoryRepository concertsInMemoryRepository = new ConcertsInMemoryRepository();
//            SongsInMemoryRepository songsInMemoryRepository = new SongsInMemoryRepository();
//            UserInMemoryRepository userInMemoryRepository = new UserInMemoryRepository();
//            if (isAdmin) System.out.println("Not yet implemented!");
//            else
//                this.controller = new UserController(albumsInMemoryRepository, artistsInMemoryRepository, concertsInMemoryRepository, songsInMemoryRepository, userInMemoryRepository);
//        } else
//            //TODO add file and db controllers
//            throw new RuntimeException("Not implemented yet!");
    }

    public void switchMenu()
    {
        if (isAdmin)
            adminMenu();
        else
            userMenu();
    }

    private void adminMenu() {
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
            Scanner input = new Scanner(System.in);
            int choice = input.nextInt();
            switch (choice) {
                /*
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
                */
                case 1 -> {
                    System.out.println("\nEnter Album Name to be added: ");
                    Scanner albumIn = new Scanner(System.in);
                    String albumName = albumIn.nextLine();
                    try {
                        Album album = this.controllerAdmin.getAlbumList().findByID(albumName);
                        this.controllerAdmin.addAlbum(album);
                    }
                    catch (NullPointerException exception) {
                        System.out.println("Unavailable Album Name\n");
                    }                }
                case 2 -> {

                }
                case 3 -> {

                }
                case 4 -> {
                    System.out.println("\nEnter Artist Name to be added: ");
                    Scanner artistIn = new Scanner(System.in);
                    String artistName = artistIn.nextLine();
                    try {
                        Artist artist = this.controllerAdmin.getArtistList().findByID(artistName);
                        this.controllerAdmin.addArtist(artist);
                    }
                    catch (NullPointerException exception) {
                        System.out.println("Unavailable Artist Name\n");
                    }
                }
                case 5 -> {
                    //todo
                }

                case 7 -> {
                    System.out.println("\nEnter Band Name to be added: ");
                    Scanner bandIn = new Scanner(System.in);
                    String bandName = bandIn.nextLine();
                    try {
                        Band band = this.controllerAdmin.getBandList().findByID(bandName);
                        this.controllerAdmin.addBand(band);
                    }
                    catch (NullPointerException exception) {
                        System.out.println("Unavailable Band Name\n");
                    }
                }
                case 8 -> {
                    //todo
                }
                case 9 -> {
                    //todo
                }
                case 10 -> {
                    System.out.println("\nEnter Concert Name to be added: ");
                    Scanner concertIn = new Scanner(System.in);
                    String concertName = concertIn.nextLine();
                    try {
                        Concert concert = this.controllerAdmin.getConcertList().findByID(concertName);
                        this.controllerAdmin.addConcert(concert);
                    }
                    catch (NullPointerException exception) {
                        System.out.println("Unavailable Concert Name\n");
                    }
                }
                case 11 -> {
                    //todo
                }
                case 12 -> {
                    //todo
                }
                case 13 -> {
                    System.out.println("\nEnter Label Name to be added: ");
                    Scanner labelIn = new Scanner(System.in);
                    String labelName = labelIn.nextLine();
                    try {
                        MusicLabel label = this.controllerAdmin.getLabelList().findByID(labelName);
                        this.controllerAdmin.addMusicLabel(label);
                    }
                    catch (NullPointerException exception) {
                        System.out.println("Unavailable Label Name\n");
                    }
                }
                case 14 -> {
                    //todo
                }
                case 15 -> {
                    //todo
                }
                case 16 -> {
                    System.out.println("\nEnter Song Name to be added: ");
                    Scanner songIn = new Scanner(System.in);
                    String songName = songIn.nextLine();
                    try {
                        Song song = this.controllerAdmin.getSongList().findByID(songName);
                        this.controllerAdmin.addSong(song);
                    }
                    catch (NullPointerException exception) {
                        System.out.println("Unavailable Song Name\n");
                    }
                }
                case 17 -> {
                    //todo

                }
                case 18 -> {
                    //todo

                }
                case 19 -> {
                    System.out.println("\nEnter User Name to be added: ");
                    Scanner userIn = new Scanner(System.in);
                    String userName = userIn.nextLine();
                    try {
                        User user = this.controllerAdmin.getUserList().findByID(userName);
                        this.controllerAdmin.addUser(user);
                    }
                    catch (NullPointerException exception) {
                        System.out.println("Unavailable User Name\n");
                    }
                }
                case 20 -> {
                    //todo

                }

                case 21 -> {
                    //todo

                }

                //all done below
                case 22 -> {
                    this.controllerAdmin.sortAlbumsByRevenue();
                    System.out.println(this.controllerAdmin.getAlbumList().toString());
                }
                case 23 -> {
                    this.controllerAdmin.sortSongsByRating();
                    System.out.println(this.controllerAdmin.getSongList().toString());
                }
                case 24 -> {
                    this.controllerAdmin.sortSongsByReleaseDate();
                    System.out.println(this.controllerAdmin.getSongList().toString());
                }
                case 25 -> {
                    this.controllerAdmin.sortArtistsByName();
                    System.out.println(this.controllerAdmin.getArtistList().toString());
                }
                case 26 -> {
                    this.controllerAdmin.sortAlbumsByReleaseDate();
                    System.out.println(this.controllerAdmin.getAlbumList().toString());
                }
                case 27 -> {
                    this.controllerAdmin.sortAlbumsByProductionCost();
                    System.out.println(this.controllerAdmin.getAlbumList().toString());
                }
                case 28 -> {
                    System.out.println(this.controllerAdmin.showConcerts());
                }
                case 29 -> {
                    System.out.println(this.controllerAdmin.showArtists());
                }
                case 30 -> {
                    System.out.println(this.controllerAdmin.showAlbums());
                }
                case 31 -> {
                    System.out.println(this.controllerAdmin.showUpcomingConcerts());
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
