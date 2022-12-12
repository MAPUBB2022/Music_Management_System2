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

import java.sql.SQLException;
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

    public void switchMenu() throws SQLException {
        if (isAdmin)
            adminMenu();
        else
            userMenu();
    }

    private void adminMenu() throws SQLException {
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
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }

                case 2 -> {
                    System.out.println("\nEnter Album Name to be added: ");
                    Scanner albumIn = new Scanner(System.in);
                    String albumName = albumIn.nextLine();
                    try {
                        Album album = this.controllerAdmin.getAlbumList().findByID(albumName);
                        this.controllerAdmin.deleteAlbum(album);
                    }
                    catch (NullPointerException | SQLException exception) {
                        System.out.println("Unavailable Album Name\n");
                    }
                }

                case 3 -> {
                    System.out.println("\nEnter Album Name to be modified: ");
                    Scanner albumIn = new Scanner(System.in);
                    String albumName = albumIn.nextLine();
                    try {
                        Album album = this.controllerAdmin.getAlbumList().findByID(albumName);
                        this.controllerAdmin.modifyAlbum(album);
                    }
                    catch (NullPointerException exception) {
                        System.out.println("Unavailable Album Name\n");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }

                case 4 -> {
                    System.out.println("\nEnter Artist Name to be added: ");
                    Scanner artistIn = new Scanner(System.in);
                    String artistName = artistIn.nextLine();
                    try {
                        Artist artist = this.controllerAdmin.getArtistList().findByID(artistName);
                        this.controllerAdmin.addArtist(artist);
                    }
                    catch (NullPointerException | SQLException exception) {
                        System.out.println("Unavailable Artist Name\n");
                    }
                }

                case 5 -> {
                    System.out.println("\nEnter Artist Name to be removed: ");
                    Scanner artistIn = new Scanner(System.in);
                    String artistName = artistIn.nextLine();
                    try {
                        Artist artist = this.controllerAdmin.getArtistList().findByID(artistName);
                        this.controllerAdmin.deleteArtist(artist);
                    }
                    catch (NullPointerException | SQLException exception) {
                        System.out.println("Unavailable Artist Name\n");
                    }
                }

                case 6 -> {
                    System.out.println("\nEnter Artist Name to be modified: ");
                    Scanner artistIn = new Scanner(System.in);
                    String artistName = artistIn.nextLine();
                    try {
                        Artist artist = this.controllerAdmin.getArtistList().findByID(artistName);
                        this.controllerAdmin.modifyArtist(artist);
                    }
                    catch (NullPointerException | SQLException exception) {
                        System.out.println("Unavailable Artist Name\n");
                    }
                }

                case 7 -> {
                    System.out.println("\nEnter Band Name to be added: ");
                    Scanner bandIn = new Scanner(System.in);
                    String bandName = bandIn.nextLine();
                    try {
                        Band band = this.controllerAdmin.getBandList().findByID(bandName);
                        this.controllerAdmin.addBand(band);
                    }
                    catch (NullPointerException | SQLException exception) {
                        System.out.println("Unavailable Band Name\n");
                    }
                }

                case 8 -> {
                    System.out.println("\nEnter Band Name to be removed: ");
                    Scanner bandIn = new Scanner(System.in);
                    String bandName = bandIn.nextLine();
                    try {
                        Band band = this.controllerAdmin.getBandList().findByID(bandName);
                        this.controllerAdmin.deleteBand(band);
                    }
                    catch (NullPointerException | SQLException exception) {
                        System.out.println("Unavailable Band Name\n");
                    }
                }

                case 9 -> {
                    System.out.println("\nEnter Band Name to be modified: ");
                    Scanner bandIn = new Scanner(System.in);
                    String bandName = bandIn.nextLine();
                    try {
                        Band band = this.controllerAdmin.getBandList().findByID(bandName);
                        this.controllerAdmin.modifyBand(band);
                    }
                    catch (NullPointerException | SQLException exception) {
                        System.out.println("Unavailable Band Name\n");
                    }
                }

                case 10 -> {
                    System.out.println("\nEnter Concert Name to be added: ");
                    Scanner concertIn = new Scanner(System.in);
                    String concertName = concertIn.nextLine();
                    try {
                        Concert concert = this.controllerAdmin.getConcertList().findByID(concertName);
                        this.controllerAdmin.addConcert(concert);
                    }
                    catch (NullPointerException | SQLException exception) {
                        System.out.println("Unavailable Concert Name\n");
                    }
                }

                case 11 -> {
                    System.out.println("\nEnter Concert Name to be removed: ");
                    Scanner concertIn = new Scanner(System.in);
                    String concertName = concertIn.nextLine();
                    try {
                        Concert concert = this.controllerAdmin.getConcertList().findByID(concertName);
                        this.controllerAdmin.deleteConcert(concert);
                    }
                    catch (NullPointerException | SQLException exception) {
                        System.out.println("Unavailable Concert Name\n");
                    }
                }

                case 12 -> {
                    System.out.println("\nEnter Concert Name to be modified: ");
                    Scanner concertIn = new Scanner(System.in);
                    String concertName = concertIn.nextLine();
                    try {
                        Concert concert = this.controllerAdmin.getConcertList().findByID(concertName);
                        this.controllerAdmin.modifyConcert(concert);
                    }
                    catch (NullPointerException exception) {
                        System.out.println("Unavailable Concert Name\n");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }

                case 13 -> {
                    System.out.println("\nEnter Label Name to be added: ");
                    Scanner labelIn = new Scanner(System.in);
                    String labelName = labelIn.nextLine();
                    try {
                        MusicLabel label = this.controllerAdmin.getLabelList().findByID(labelName);
                        this.controllerAdmin.addMusicLabel(label);
                    }
                    catch (NullPointerException | SQLException exception) {
                        System.out.println("Unavailable Label Name\n");
                    }
                }

                case 14 -> {
                    System.out.println("\nEnter Label Name to be removed: ");
                    Scanner labelIn = new Scanner(System.in);
                    String labelName = labelIn.nextLine();
                    try {
                        MusicLabel label = this.controllerAdmin.getLabelList().findByID(labelName);
                        this.controllerAdmin.deleteMusicLabel(label);
                    }
                    catch (NullPointerException | SQLException exception) {
                        System.out.println("Unavailable Label Name\n");
                    }
                }

                case 15 -> {
                    System.out.println("\nEnter Label Name to be modified: ");
                    Scanner labelIn = new Scanner(System.in);
                    String labelName = labelIn.nextLine();
                    try {
                        MusicLabel label = this.controllerAdmin.getLabelList().findByID(labelName);
                        this.controllerAdmin.modifyMusicLabel(label);
                    }
                    catch (NullPointerException | SQLException exception) {
                        System.out.println("Unavailable Label Name\n");
                    }
                }

                case 16 -> {
                    System.out.println("\nEnter Song Name to be added: ");
                    Scanner songIn = new Scanner(System.in);
                    String songName = songIn.nextLine();
                    try {
                        Song song = this.controllerAdmin.getSongList().findByID(songName);
                        this.controllerAdmin.addSong(song);
                    }
                    catch (NullPointerException | SQLException exception) {
                        System.out.println("Unavailable Song Name\n");
                    }
                }

                case 17 -> {
                    System.out.println("\nEnter Song Name to be removed: ");
                    Scanner songIn = new Scanner(System.in);
                    String songName = songIn.nextLine();
                    try {
                        Song song = this.controllerAdmin.getSongList().findByID(songName);
                        this.controllerAdmin.deleteSong(song);
                    }
                    catch (NullPointerException | SQLException e) {
                        System.out.println("Unavailable Song Name\n");
                    }
                }

                case 18 -> {
                    System.out.println("\nEnter Song Name to be modified: ");
                    Scanner songIn = new Scanner(System.in);
                    String songName = songIn.nextLine();
                    try {
                        Song song = this.controllerAdmin.getSongList().findByID(songName);
                        this.controllerAdmin.modifySong(song);
                    }
                    catch (NullPointerException | SQLException e) {
                        System.out.println("Unavailable Song Name\n");
                    }
                }

                case 19 -> {
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("\nEnter Username: ");
                    String userName = scanner.nextLine();
                    System.out.println("\nEnter Password: ");
                    String password = scanner.nextLine();
                    try {
                        User user = this.controllerAdmin.getUserList().findByID(userName);
                        this.controllerAdmin.addUser(new User(userName, password));
                    }
                    catch (NullPointerException | SQLException exception) {
                        System.out.println("Unavailable username\n");
                    }
                }

                case 20 -> {
                    System.out.println("\nEnter Username to be removed: ");
                    Scanner userIn = new Scanner(System.in);
                    String userName = userIn.nextLine();
                    try {
                        User user = this.controllerAdmin.getUserList().findByID(userName);
                        this.controllerAdmin.deleteUser(user);
                    }
                    catch (NullPointerException | SQLException exception) {
                        System.out.println("Unavailable Username\n");
                    }
                }

                case 21 -> {
                    System.out.println("\nEnter Username to be modified: ");
                    Scanner userIn = new Scanner(System.in);
                    String userName = userIn.nextLine();
                    try {
                        User user = this.controllerAdmin.getUserList().findByID(userName);
                        this.controllerAdmin.modifyUser(user);
                    }
                    catch (NullPointerException | SQLException exception) {
                        System.out.println("Unavailable Username\n");
                    }
                }

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
        //todo implementation
    }
}
