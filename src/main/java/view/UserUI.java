package view;

import controller.AdminController;
import controller.UserController;
import model.album.Album;
import model.album.Artist;
import model.album.Band;
import model.concert.Concert;
import model.label.MusicLabel;
import model.song.*;
import model.users.User;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

// TODO - redo functions
public class UserUI {
    UserController controller;
    AdminController controllerAdmin;
    boolean isAdmin;

    public UserUI() {
    };

    public UserUI(Integer saveType, boolean isAdmin) throws ParseException, RuntimeException {
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

    public void switchMenu() throws SQLException, ParseException {
        if (isAdmin)
            adminMenu();
        else
            userMenu();
    }

    private void adminMenu() throws SQLException, ParseException {
        // create instances
        Artist artist = new Artist();
        Album album = new Album();
        Band band = new Band();
        Concert concert = new Concert();
        User user = new User();
        MusicLabel label = new MusicLabel();
        Classical classical = new Classical();
        Pop pop = new Pop();
        Rap rap = new Rap();
        Rock rock = new Rock();

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
                        if (choice2 == 1 || choice2 == 2)
                            valid = true;
                        else System.out.println("Invalid input! Choose 1 or 2!");

                    } while (!valid);

                    if (choice2 == 1) {
                        String albumTitle;
                        System.out.println("Enter album information:");

                        //name
                        System.out.println("name: ");
                        albumTitle = inp.nextLine();
                        Album album1 = this.controllerAdmin.getAlbumList().findByID(albumTitle);
                        if (album1 != null) // album already exists
                            controllerAdmin.addAlbum(album1);
                        else album.setTitle(albumTitle);


                        System.out.println("Enter artist information:");

                        //stage name
                        System.out.println("stage name: ");
                        String stageName = inp.nextLine();
                        Artist artist1 = this.controllerAdmin.getArtistList().findByID(stageName);

                        if (artist1 != null)  //set artist to album if it already exists in the artist list
                            album.setArtist(artist1);
                        else { // input for new artist
                            //stage name
                            artist.setStage_name(stageName);

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
                            valid = false;
                            do {
                                //type of song
                                System.out.println("Enter song type (Classical, Pop, Rap, Rock): ");
                                type = inp.nextLine();
                                if (type.equals("Classical") || type.equals("Pop") || type.equals("Rap") || type.equals("Rock"))
                                    valid = true;
                                else System.out.println("Invalid input! Please try again!");
                            } while (!valid);

                            //name
                            System.out.println("name: ");
                            songName = inp.nextLine();

                            // set song
                            switch (type) {

                                case "Classical" -> {
                                    Song song = this.controllerAdmin.getSongList().findByID(songName);
                                    if (song != null)  //set song to album if it already exists in the song list
                                        album.addSong(song); // add input song to album
                                    else {
                                        classical.setName(songName);

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

                                        classical.setRating(rating);
                                        classical.setReleaseDate(release);
                                        classical.setSinger(artist);

                                        album.addSong(classical); // add input song to album
                                    }
                                }
                                case "Pop" -> {
                                    Song song = this.controllerAdmin.getSongList().findByID(songName);
                                    if (song != null)  //set song to album if it already exists in the artist list
                                        album.addSong(song); // add input song to album
                                    else {
                                        pop.setName(songName);

                                        //rating
                                        System.out.println("rating: ");
                                        float rating = inp.nextFloat();

                                        //release date
                                        System.out.println("release date: ");
                                        String date = inp.nextLine();
                                        Date release = new SimpleDateFormat("dd/MM/yyyy").parse(date);


                                        //language of album
                                        System.out.println("\nlanguage of the album: ");
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

                                        pop.setRating(rating);
                                        pop.setReleaseDate(release);
                                        pop.setSinger(artist);

                                        album.addSong(pop); // add input song to album

                                    }
                                }
                                case "Rap" -> {
                                    Song song = this.controllerAdmin.getSongList().findByID(songName);
                                    if (song != null)  //set song to album if it already exists in the song list
                                        album.addSong(song); // add input song to album
                                    else {
                                        rap.setName(songName);

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

                                        rap.setRating(rating);
                                        rap.setReleaseDate(release);
                                        rap.setSinger(artist);

                                        album.addSong(rap); // add input song to album
                                    }
                                }
                                case "Rock" -> {
                                    Song song = this.controllerAdmin.getSongList().findByID(songName);
                                    if (song != null)  //set song to album if it already exists in the song list
                                        album.addSong(song); // add input song to album
                                    else {
                                        rock.setName(songName);

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

                                        rock.setRating(rating);
                                        rock.setReleaseDate(release);
                                        rock.setSinger(artist);

                                        album.addSong(rock); // add input song to album
                                    }
                                    nr--;
                                }
                            }
                        } while (nr > 0);

                        // add new album to the list of albums (at this point, album must be valid)
                        controllerAdmin.addAlbum(album);

                    } else {   // choice2 = 2
                        String albumTitle;
                        System.out.println("Enter album information:");

                        //name
                        System.out.println("name: ");
                        albumTitle = inp.nextLine();
                        Album album1 = this.controllerAdmin.getAlbumList().findByID(albumTitle);
                        if (album1 != null) // album already exists
                            controllerAdmin.addAlbum(album1);
                        else album.setTitle(albumTitle);


                        System.out.println("Enter band information:");

                        //name
                        System.out.println("name: ");
                        String name = inp.nextLine();
                        Band band1 = this.controllerAdmin.getBandList().findByID(name);

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

                                Artist artist2 = this.controllerAdmin.getArtistList().findByID(stageName);

                                if (artist2 != null)  //set artist to album if it already exists in the artist list
                                    album.setArtist(artist2);
                                else { // input for new artist
                                    //name
                                    artist.setStage_name(stageName);

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
                            valid = false;
                            do {
                                //type of song
                                System.out.println("Enter song type (Classical, Pop, Rap, Rock): ");
                                type = inp.nextLine();
                                if (type.equals("Classical") || type.equals("Pop") || type.equals("Rap") || type.equals("Rock"))
                                    valid = true;
                                else System.out.println("Invalid input! Please try again!");
                            } while (!valid);

                            //name
                            System.out.println("name: ");
                            songName = inp.nextLine();

                            // set song
                            switch (type) {

                                case "Classical" -> {
                                    Song song = this.controllerAdmin.getSongList().findByID(songName);
                                    if (song != null)  //set artist to album if it already exists in the artist list
                                        album.addSong(song); // add input song to album
                                    else {
                                        classical.setName(songName);

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

                                        classical.setRating(rating);
                                        classical.setReleaseDate(release);
                                        classical.setSinger(artist);

                                        album.addSong(classical); // add input song to album
                                    }
                                }
                                case "Pop" -> {
                                    Song song = this.controllerAdmin.getSongList().findByID(songName);
                                    if (song != null)  //set song to album if it already exists in the artist list
                                        album.addSong(song); // add input song to album
                                    else {
                                        pop.setName(songName);

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

                                        pop.setRating(rating);
                                        pop.setReleaseDate(release);
                                        pop.setSinger(artist);

                                        album.addSong(pop); // add input song to album

                                    }
                                }
                                case "Rap" -> {
                                    Song song = this.controllerAdmin.getSongList().findByID(songName);
                                    if (song != null)  //set artist to album if it already exists in the artist list
                                        album.addSong(song); // add input song to album
                                    else {
                                        rap.setName(songName);

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

                                        rap.setRating(rating);
                                        rap.setReleaseDate(release);
                                        rap.setSinger(artist);

                                        album.addSong(rap); // add input song to album
                                    }
                                }
                                case "Rock" -> {
                                    Song song = this.controllerAdmin.getSongList().findByID(songName);
                                    if (song != null)  //set artist to album if it already exists in the artist list
                                        album.addSong(song); // add input song to album
                                    else {
                                        rock.setName(songName);

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

                                        rock.setRating(rating);
                                        rock.setReleaseDate(release);
                                        rock.setSinger(artist);

                                        album.addSong(rock); // add input song to album
                                    }
                                    nr--;
                                }
                            }
                        } while (nr > 0);

                        // add new album to the list of albums (at this point, album must be valid)
                        controllerAdmin.addAlbum(album);
                    }
                }

                case 2 -> {
                    System.out.println("\nEnter Album Name to be deleted: ");
                    Scanner albumIn = new Scanner(System.in);
                    String albumName = albumIn.nextLine();
                    album = this.controllerAdmin.getAlbumList().findByID(albumName);
                    if (album != null) {
                        this.controllerAdmin.deleteAlbum(album);
                        System.out.println("Album deleted successfully!");
                    } else
                        System.out.println("Unavailable Album Name\n");
                }

                case 3 -> {
                    System.out.println("\nEnter Album Name to be modified: ");
                    Scanner albumIn = new Scanner(System.in);
                    String albumName = albumIn.nextLine();

                    if (this.controllerAdmin.getAlbumList().findByID(albumName) != null) {
                        album.setTitle(albumName);

                        System.out.println("The number of attributes you want to modify: ");
                        Scanner nrModif = new Scanner(System.in);
                        int counter = nrModif.nextInt();

                        do {
                            System.out.println("Enter what you want to modify (1.production cost," +
                                    " 2.copies sold, 3.disc price ): ");
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

                        this.controllerAdmin.modifyAlbum(album);
                        System.out.println("Album modified successfully!");
                    } else System.out.println("Album Name not available!");

                }

                case 4 -> {
                    System.out.println("\nEnter Artist Name to be added: ");
                    Scanner artistIn = new Scanner(System.in);
                    String artistName = artistIn.nextLine();

                    if (this.controllerAdmin.getArtistList().findByID(artistName) == null) {

                        artist.setName(artistName);

                        System.out.println("Enter Artist information:\nstage name: ");
                        Scanner input = new Scanner(System.in);
                        String stageName = input.nextLine();
                        artist.setStage_name(stageName);

                        System.out.println("salary: ");
                        Scanner input3 = new Scanner(System.in);
                        float salary = input3.nextFloat();
                        artist.setSalary(salary);

                        this.controllerAdmin.addArtist(artist);
                        System.out.println("Artist added successfully!");
                    } else
                        System.out.println("Artist Name already exists!\n");

                }

                case 5 -> {
                    System.out.println("\nEnter Artist Name to be deleted: ");
                    Scanner artistIn = new Scanner(System.in);
                    String artistName = artistIn.nextLine();
                    artist = this.controllerAdmin.getArtistList().findByID(artistName);
                    if (artist != null) {
                        this.controllerAdmin.deleteArtist(artist);
                        System.out.println("Artist deleted successfully");
                    } else
                        System.out.println("Unavailable Artist Name\n");
                }

                case 6 -> {
                    System.out.println("\nEnter Artist Name to be modified: ");
                    Scanner artistIn = new Scanner(System.in);
                    String artistStageName = artistIn.nextLine();

                    if (this.controllerAdmin.getArtistList().findByID(artistStageName) != null) {
                        artist.setStage_name(artistStageName);

                        System.out.println("The number of attributes you want to modify: ");
                        Scanner nrModif = new Scanner(System.in);
                        int counter = nrModif.nextInt();

                        do {
                            System.out.println("Enter what you want to modify (1.name," +
                                    " 2.salary): ");
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

                        this.controllerAdmin.modifyArtist(artist);
                        System.out.println("Artist modified successfully!");
                    } else System.out.println("Artist Name not available!");

                }

                case 7 -> {
                    //name
                    System.out.println("Enter band name to be added:");
                    String name = inp.nextLine();
                    band = this.controllerAdmin.getBandList().findByID(name);

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

                            if (this.controllerAdmin.getArtistList().findByID(stageName) == null) {
                                //stage name
                                artist.setStage_name(stageName);

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
                            } else
                                System.out.println("Artist already exists!");

                            nr--;
                        } while (nr > 0);

                        band.setArtistList(artists); // set artist list
                        this.controllerAdmin.addBand(band); // add band
                    } else
                        System.out.println("Band already exists!");
                }

                case 8 -> {
                    System.out.println("\nEnter Band Name to be deleted: ");
                    Scanner bandIn = new Scanner(System.in);
                    String bandName = bandIn.nextLine();

                    band = this.controllerAdmin.getBandList().findByID(bandName);
                    if (band != null) {
                        this.controllerAdmin.deleteBand(band);
                        System.out.println("Band deleted successfully");
                    } else
                        System.out.println("Unavailable Band Name\n");
                }

                case 9 -> {
                    System.out.println("\nEnter Band Name to be modified: ");
                    Scanner bandIn = new Scanner(System.in);
                    String bandName = bandIn.nextLine();

                    band = this.controllerAdmin.getBandList().findByID(bandName);
                    if (band != null) {

                        System.out.println("The number of artists you want to modify: ");
                        Scanner nrModif = new Scanner(System.in);
                        int counter = nrModif.nextInt();
                        do {
                            System.out.println("\nEnter Artist Name to be modified: ");
                            Scanner artistIn = new Scanner(System.in);
                            String artistStageName = artistIn.nextLine();

                            if (this.controllerAdmin.getArtistList().findByID(artistStageName) != null) {
                                artist.setStage_name(artistStageName);

                                System.out.println("The number of attributes you want to modify: ");
                                Scanner nrModifA = new Scanner(System.in);
                                int counter1 = nrModifA.nextInt();

                                do {
                                    System.out.println("Enter what you want to modify (1.name," +
                                            " 2.salary): ");
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

                                this.controllerAdmin.modifyArtist(artist);
                                System.out.println("Artist modified successfully!");
                            } else {
                                System.out.println("Artist Name not available! You still have " + counter + " modifications to do!");
                                counter++;
                            }
                            counter--;
                        } while (counter > 0);

                        this.controllerAdmin.modifyBand(band);
                        System.out.println("Band modified successfully!");
                    } else System.out.println("Band Name not available!");
                }

                case 10 -> {
                    System.out.println("\nEnter Concert Name to be added: ");
                    Scanner concertIn = new Scanner(System.in);
                    String concertName = concertIn.nextLine();

                    if (this.controllerAdmin.getConcertList().findByID(concertName) == null) {
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

                            if (this.controllerAdmin.getArtistList().findByID(stageName) == null) {
                                //stage name
                                artist.setStage_name(stageName);

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
                            } else
                                System.out.println("Artist already exists!");

                            nr--;
                        } while (nr > 0);

                        concert.setArtistList(artists); // set artist list
                        this.controllerAdmin.addConcert(concert); // add concert

                    } else
                        System.out.println("Unavailable Concert Name\n");
                }

                case 11 -> {
                    System.out.println("\nEnter Concert Name to be deleted: ");
                    Scanner concertIn = new Scanner(System.in);
                    String concertName = concertIn.nextLine();

                    concert = this.controllerAdmin.getConcertList().findByID(concertName);
                    if (concert != null) {
                        this.controllerAdmin.deleteConcert(concert);
                        System.out.println("Concert deleted successfully");
                    } else
                        System.out.println("Unavailable Concert Name\n");
                }

                case 12 -> {
                    System.out.println("\nEnter Concert Name to be modified: ");
                    Scanner concertIn = new Scanner(System.in);
                    String concertName = concertIn.nextLine();

                    concert = this.controllerAdmin.getConcertList().findByID(concertName);
                    if (concert != null) {

                        System.out.println("The number of attributes you want to modify: ");
                        Scanner nrModif = new Scanner(System.in);
                        int counter = nrModif.nextInt();

                        do {
                            System.out.println("Enter what you want to modify (1.artist list (delete or add artist," +
                                    " 2.location, 3.date, 4.capacity, 5.ticketPrice, 6.ticketsSold, 7.rentCosts: ");
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
                                            artist = this.controllerAdmin.getArtistList().findByID(artistName);

                                            if (concert.getArtistList().contains(artist)) {
                                                this.controllerAdmin.deleteArtist(artist); // delete artist if exists in the list
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
                                            artist = this.controllerAdmin.getArtistList().findByID(artistName);

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

                        this.controllerAdmin.modifyConcert(concert);
                        System.out.println("Concert modified successfully!");
                    } else System.out.println("Concert Name not available!");


                }
                case 13 -> {
                    System.out.println("\nEnter Label Name to be added: ");
                    Scanner labelIn = new Scanner(System.in);
                    String labelName = labelIn.nextLine();

                    if (this.controllerAdmin.getLabelList().findByID(labelName) == null) {
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

                            if (this.controllerAdmin.getArtistList().findByID(stageName) == null) {
                                //stage name
                                artist.setStage_name(stageName);

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
                            } else
                                System.out.println("Artist already exists!");

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

                            album = this.controllerAdmin.getAlbumList().findByID(title);

                            if (album != null) { // add existing album (can't create new album here)
                                albums.add(album);
                            } else
                                System.out.println("This album is not available!");

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

                            concert = this.controllerAdmin.getConcertList().findByID(name);

                            if (concert != null) { // add existing concert (can't create new concert here)
                                concerts.add(concert);
                            } else
                                System.out.println("This concert is not available!");

                            nrC--;
                        } while (nrC > 0);
                        label.setUpcomingEvents(concerts); // set upcoming concerts

                        this.controllerAdmin.addMusicLabel(label);
                        System.out.println("Label added successfully!");
                    } else
                        System.out.println("Unavailable Label Name\n");
                }

                case 14 -> {
                    System.out.println("\nEnter Label Name to be deleted: ");
                    Scanner labelIn = new Scanner(System.in);
                    String labelName = labelIn.nextLine();
                    label = this.controllerAdmin.getLabelList().findByID(labelName);

                    if (label != null) {
                        this.controllerAdmin.deleteMusicLabel(label);
                        System.out.println("Music Label deleted successfully!");
                    } else
                        System.out.println("Unavailable Label Name\n");
                }

                case 15 -> {
                    System.out.println("\nEnter Label Name to be modified: ");
                    Scanner labelIn = new Scanner(System.in);
                    String labelName = labelIn.nextLine();

                    label = this.controllerAdmin.getLabelList().findByID(labelName);
                    if (label != null) {

                        System.out.println("The number of attributes you want to modify: ");
                        Scanner nrModifA = new Scanner(System.in);
                        int counter1 = nrModifA.nextInt();

                        do {
                            System.out.println("Enter what you want to modify (1.address," +
                                    " 2.revenue): ");
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

                        this.controllerAdmin.modifyMusicLabel(label);
                        System.out.println("Artist modified successfully!");
                    } else
                        System.out.println("Unavailable Label Name");
                }

                case 16 -> {
                    System.out.println("Enter song information:\n");
                    String songName = "";
                    String type;
                    boolean valid = false;

                    do {
                        //type of song
                        System.out.println("Enter song type (Classical, Pop, Rap, Rock): ");
                        type = inp.nextLine();
                        if (type.equals("Classical") || type.equals("Pop") || type.equals("Rap") || type.equals("Rock"))
                            valid = true;
                        else System.out.println("Invalid input! Please try again!");
                    } while (!valid);

                    //name
                    System.out.println("Enter song name to be added: ");
                    songName = inp.nextLine();

                    // set song
                    switch (type) {
                        case "Classical" -> {
                            Song song = this.controllerAdmin.getSongList().findByID(songName);
                            if (song != null)  //set song to album if it already exists in the song list
                                album.addSong(song); // add input song to album
                            else {
                                classical.setName(songName);

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

                                classical.setRating(rating);
                                classical.setReleaseDate(release);
                                classical.setSinger(artist);

                                this.controllerAdmin.addSong(classical); // add input song to album
                            }
                        }
                        case "Pop" -> {
                            Song song = this.controllerAdmin.getSongList().findByID(songName);
                            if (song != null)  //set song to album if it already exists in the artist list
                                album.addSong(song); // add input song to album
                            else {
                                pop.setName(songName);

                                //rating
                                System.out.println("rating: ");
                                float rating = inp.nextFloat();

                                //release date
                                System.out.println("release date: ");
                                String date = inp.nextLine();
                                Date release = new SimpleDateFormat("dd/MM/yyyy").parse(date);


                                //language of album
                                System.out.println("\nlanguage of the album: ");
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

                                pop.setRating(rating);
                                pop.setReleaseDate(release);
                                pop.setSinger(artist);

                                this.controllerAdmin.addSong(pop); // add input song to album

                            }
                        }
                        case "Rap" -> {
                            Song song = this.controllerAdmin.getSongList().findByID(songName);
                            if (song != null)  //set artist to album if it already exists in the artist list
                                album.addSong(song); // add input song to album
                            else {
                                rap.setName(songName);

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

                                rap.setRating(rating);
                                rap.setReleaseDate(release);
                                rap.setSinger(artist);

                                this.controllerAdmin.addSong(rap); // add input song to album
                            }
                        }
                        case "Rock" -> {
                            Song song = this.controllerAdmin.getSongList().findByID(songName);
                            if (song != null)  //set artist to album if it already exists in the artist list
                                album.addSong(song); // add input song to album
                            else {
                                rock.setName(songName);

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

                                rock.setRating(rating);
                                rock.setReleaseDate(release);
                                rock.setSinger(artist);

                                this.controllerAdmin.addSong(rock); // add input song to album
                            }
                        }
                    }
                }

                case 17 -> {
                    System.out.println("\nEnter Song Name to be removed: ");
                    Scanner songIn = new Scanner(System.in);
                    String songName = songIn.nextLine();
                    Song song = this.controllerAdmin.getSongList().findByID(songName);

                    if (song != null) {
                        this.controllerAdmin.deleteSong(song);
                        System.out.println("Song deleted successfully!");
                    } else
                        System.out.println("Unavailable Song Name\n");
                }

                case 18 -> {
                    System.out.println("Enter song information:\n");
                    String songName = "";
                    String type;
                    boolean valid = false;

                    do {
                        //type of song
                        System.out.println("Enter song type (Classical, Pop, Rap, Rock): ");
                        type = inp.nextLine();
                        if (type.equals("Classical") || type.equals("Pop") || type.equals("Rap") || type.equals("Rock"))
                            valid = true;
                        else System.out.println("Invalid input! Please try again!");
                    } while (!valid);

                    //name
                    System.out.println("Enter song name to be modified: ");
                    songName = inp.nextLine();

                    Song song = this.controllerAdmin.getSongList().findByID(songName);
                    if (song == null)  //modify only existing song
                        System.out.println("This song doesn't exist!");
                    else {
                        // set modified song
                        switch (type) {
                            case "Classical" -> {
                                classical.setName(songName);
                                classical.setSinger(song.getSinger());

                                System.out.println("The number of attributes you want to modify: ");
                                Scanner nrModifA = new Scanner(System.in);
                                int counter1 = nrModifA.nextInt();

                                do {
                                    System.out.println("Enter what you want to modify (1.rating," +
                                            " 2.stream count): ");
                                    Scanner choice3 = new Scanner(System.in);
                                    int inp1 = choice3.nextInt();

                                    if (inp1 == 1) {
                                        System.out.println("Enter the new rating: ");
                                        Scanner rating = new Scanner(System.in);
                                        float newRating = rating.nextFloat();
                                        classical.setRating(newRating);
                                    } else if (inp1 == 2) {
                                        System.out.println("Enter the new stream count: ");
                                        Scanner streamCount = new Scanner(System.in);
                                        int newStreamCount = streamCount.nextInt();
                                        classical.setStreamCount(newStreamCount);
                                    } else {
                                        System.out.println("Invalid input! Choose between 1-2. You still have " + counter1 + " modifications to do");
                                        counter1++;
                                    }

                                    counter1--;
                                } while (counter1 > 0);

                                this.controllerAdmin.modifySong(classical);
                                System.out.println("Song modified successfully!");
                            }
                            case "Pop" -> {
                                pop.setName(songName);
                                pop.setSinger(song.getSinger());

                                System.out.println("The number of attributes you want to modify: ");
                                Scanner nrModifA = new Scanner(System.in);
                                int counter1 = nrModifA.nextInt();

                                do {
                                    System.out.println("Enter what you want to modify (1.rating," +
                                            " 2.stream count): ");
                                    Scanner choice3 = new Scanner(System.in);
                                    int inp1 = choice3.nextInt();

                                    if (inp1 == 1) {
                                        System.out.println("Enter the new rating: ");
                                        Scanner rating = new Scanner(System.in);
                                        float newRating = rating.nextFloat();
                                        pop.setRating(newRating);
                                    } else if (inp1 == 2) {
                                        System.out.println("Enter the new stream count: ");
                                        Scanner streamCount = new Scanner(System.in);
                                        int newStreamCount = streamCount.nextInt();
                                        pop.setStreamCount(newStreamCount);
                                    } else {
                                        System.out.println("Invalid input! Choose between 1-2. You still have " + counter1 + " modifications to do");
                                        counter1++;
                                    }

                                    counter1--;
                                } while (counter1 > 0);

                                this.controllerAdmin.modifySong(pop);
                                System.out.println("Song modified successfully!");
                            }
                            case "Rap" -> {
                                rap.setName(songName);
                                rap.setSinger(song.getSinger());

                                System.out.println("The number of attributes you want to modify: ");
                                Scanner nrModifA = new Scanner(System.in);
                                int counter1 = nrModifA.nextInt();

                                do {
                                    System.out.println("Enter what you want to modify (1.rating," +
                                            " 2.stream count): ");
                                    Scanner choice3 = new Scanner(System.in);
                                    int inp1 = choice3.nextInt();

                                    if (inp1 == 1) {
                                        System.out.println("Enter the new rating: ");
                                        Scanner rating = new Scanner(System.in);
                                        float newRating = rating.nextFloat();
                                        rap.setRating(newRating);
                                    } else if (inp1 == 2) {
                                        System.out.println("Enter the new stream count: ");
                                        Scanner streamCount = new Scanner(System.in);
                                        int newStreamCount = streamCount.nextInt();
                                        rap.setStreamCount(newStreamCount);
                                    } else {
                                        System.out.println("Invalid input! Choose between 1-2. You still have " + counter1 + " modifications to do");
                                        counter1++;
                                    }

                                    counter1--;
                                } while (counter1 > 0);

                                this.controllerAdmin.modifySong(rap);
                                System.out.println("Song modified successfully!");
                            }
                            case "Rock" -> {
                                rock.setName(songName);
                                rock.setSinger(song.getSinger());

                                System.out.println("The number of attributes you want to modify: ");
                                Scanner nrModifA = new Scanner(System.in);
                                int counter1 = nrModifA.nextInt();

                                do {
                                    System.out.println("Enter what you want to modify (1.rating," +
                                            " 2.stream count): ");
                                    Scanner choice3 = new Scanner(System.in);
                                    int inp1 = choice3.nextInt();

                                    if (inp1 == 1) {
                                        System.out.println("Enter the new rating: ");
                                        Scanner rating = new Scanner(System.in);
                                        float newRating = rating.nextFloat();
                                        rock.setRating(newRating);
                                    } else if (inp1 == 2) {
                                        System.out.println("Enter the new stream count: ");
                                        Scanner streamCount = new Scanner(System.in);
                                        int newStreamCount = streamCount.nextInt();
                                        rock.setStreamCount(newStreamCount);
                                    } else {
                                        System.out.println("Invalid input! Choose between 1-2. You still have " + counter1 + " modifications to do");
                                        counter1++;
                                    }

                                    counter1--;
                                } while (counter1 > 0);

                                this.controllerAdmin.modifySong(rock);
                                System.out.println("Song modified successfully!");
                            }

                        }

                    }
                }

                case 19 -> {
                    System.out.println("\nEnter information about the new user: ");
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("\nEnter Username to be added: ");
                    String userName = scanner.nextLine();
                    if (this.controllerAdmin.getUserList().findByID(userName) == null) {
                        System.out.println("\nEnter Password: ");
                        String password = scanner.nextLine();
                        this.controllerAdmin.addUser(new User(userName, password));
                        System.out.println("\nUser added successfully!");
                    } else
                        System.out.println("This username already exists!\n");
                }

                case 20 -> {
                    System.out.println("\nEnter Username to be deleted: ");
                    Scanner userIn = new Scanner(System.in);
                    String userName = userIn.nextLine();
                    user = this.controllerAdmin.getUserList().findByID(userName);
                    if (user != null) {
                        this.controllerAdmin.deleteUser(user);
                        System.out.println("User deleted successfully!");
                    } else
                        System.out.println("Unavailable Username\n");
                }

                case 21 -> {
                    //only password can be modified
                    System.out.println("\nEnter Username to be modified: ");
                    Scanner userIn = new Scanner(System.in);
                    String userName = userIn.nextLine();

                    if (this.controllerAdmin.getUserList().findByID(userName) != null) {
                        user.setUsername(userName);

                        System.out.println("\nEnter new password: ");
                        Scanner userInNew = new Scanner(System.in);
                        String userPasswdNew = userInNew.nextLine();
                        user.setUsername(userPasswdNew);

                        this.controllerAdmin.modifyUser(user);
                        System.out.println("User modified successfully!");
                    } else
                        System.out.println("This username doesn't exist!\n");
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

    private void userMenu() {

    }
}
