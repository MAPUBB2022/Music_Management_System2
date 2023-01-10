package test;

import model.album.Album;
import model.album.Artist;
import model.album.Band;
import model.concert.Concert;
import model.label.MusicLabel;
import model.song.Rap;
import model.song.Rock;
import model.users.User;
import org.junit.jupiter.api.*;
import repository.inmemory.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import model.song.*;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;


class AdminControllerTest {
    AlbumsInMemoryRepository albums;
    ArtistsInMemoryRepository artists;
    BandsInMemoryRepository bands;
    ConcertsInMemoryRepository concerts;
    MusicLabelsInMemoryRepository musicLabels;
    SongsInMemoryRepository songs;
    UserInMemoryRepository users;

    @BeforeEach
    void setUP() throws ParseException {
        albums = new AlbumsInMemoryRepository();
        artists = new ArtistsInMemoryRepository();
        bands = new BandsInMemoryRepository();
        concerts = new ConcertsInMemoryRepository();
        musicLabels = new MusicLabelsInMemoryRepository();
        songs = new SongsInMemoryRepository();
        users = new UserInMemoryRepository();
    }

    @Test
    void addAlbum() throws ParseException {
        Band band = new Band("Nightwish");
        Album a1 = new Album("Imaginaerum", band);
        a1.addSong(new Rock("Storytime", "", new SimpleDateFormat("dd.MM.yyyy").parse("01.06.2012"), band));
        a1.addSong(new Rock("I Want My Tears Back", "", new SimpleDateFormat("dd.MM.yyyy").parse("02.06.2013"), band));
        a1.addSong(new Rock("Rest Calm", "", new SimpleDateFormat("dd.MM.yyyy").parse("04.06.2015"), band));


        Album a2 = new Album("Endless Forms Most Beautiful", band);
        a2.addSong(new Rock("Élan", "", new SimpleDateFormat("dd.MM.yyyy").parse("01.06.2012"), band));
        a2.addSong(new Rock("My Walden", "", new SimpleDateFormat("dd.MM.yyyy").parse("02.06.2013"), band));
        a2.addSong(new Rock("Endless Forms Most Beautiful", "", new SimpleDateFormat("dd.MM.yyyy").parse("04.06.2015"), band));

        albums.add(a1);
        albums.add(a2);

        // checks whether the added entities are in the list
        Assertions.assertTrue(albums.findAll().contains(a1));
        Assertions.assertTrue(albums.findAll().contains(a2));
    }

    @Test
    void deleteAlbum() throws ParseException {
        Album a = albums.findAll().get(0);
        albums.remove(a);
        Assertions.assertFalse(albums.findAll().contains(a)); // checks if album has been removed from the list
        Assertions.assertNotEquals(albums.findByID(0), a); // checks if the deleted object doesn't exist on its former position
    }

    @Test
    void modifyAlbum() {
        Band band = new Band("Nightwish");
        Album a = new Album("Century Child", band);
        albums.update(0, a);
        Assertions.assertEquals(albums.findByID(0), a); // checks if entity on index 0 has been updated correctly
    }

    @Test
    void addArtist() {
        Artist a1 = new Artist("Floor Jansen");
        Artist a2 = new Artist("Tuomas Holopainen");

        artists.add(a1);
        artists.add(a2);

        // checks whether the added entities are in the list
        Assertions.assertTrue(artists.findAll().contains(a1));
        Assertions.assertTrue(artists.findAll().contains(a2));
    }

    @Test
    void deleteArtist() {
        Artist a = artists.findAll().get(0);
        artists.remove(a);
        Assertions.assertFalse(artists.findAll().contains(a)); // checks if artist has been removed from the list
        Assertions.assertNotEquals(artists.findByID(0), a); // checks if the deleted object doesn't exist on its former position
    }

    @Test
    void modifyArtist() {
        Artist artist = new Artist("Jackson");
        artists.update(0, artist);
        Assertions.assertEquals(artists.findByID(0), artist); // checks if entity on index 0 has been updated correctly
    }

    @Test
    void addBand() {
        Band b1 = new Band("Iron Maiden");
        Band b2 = new Band("Metallica");
        bands.add(b1);
        bands.add(b2);

        // checks whether the added entities are in the list
        Assertions.assertTrue(bands.findAll().contains(b1));
        Assertions.assertTrue(bands.findAll().contains(b2));
    }

    @Test
    void deleteBand() {
        Band b = bands.findAll().get(0);
        bands.remove(b);
        Assertions.assertFalse(bands.findAll().contains(b)); // checks if band has been removed from the list
        Assertions.assertNotEquals(bands.findByID(0), b); // checks if the deleted object doesn't exist on its former position
    }

    @Test
    void modifyBand() {
        Band band = new Band("The Rolling Stones");
        bands.update(0, band);
        Assertions.assertEquals(bands.findByID(0), band); // checks if entity on index 0 has been updated correctly
    }

    @Test
    void addConcert() throws ParseException {
        Artist c1a1 = new Artist("Floor Jansen");
        Artist c1a2 = new Artist("Tuomas Holopainen");
        Artist c1a3 = new Artist("Emppu Vuorinen");
        Artist c1a4 = new Artist("Troy Donockley");
        Artist c1a5 = new Artist("Kai Hahto");
        Artist c1a6 = new Artist("Jukka Koskinen");
        List<Artist> c1Artists = new ArrayList<>(Arrays.asList(c1a1, c1a2, c1a3));
        Concert c1 = new Concert("Human :||: Nature", c1Artists, "Hungary, Budapest", new SimpleDateFormat("dd.MM.yyyy").parse("20.12.2022"), 70000);
        concerts.add(c1);

        Assertions.assertTrue(concerts.findAll().contains(c1)); // checks whether the added entity is in the list
    }

    @Test
    void deleteConcert() {
        Concert c = concerts.findAll().get(0);
        concerts.remove(c);

        Assertions.assertFalse(concerts.findAll().contains(c)); // checks if concert has been removed from the list
        Assertions.assertNotEquals(concerts.findByID(0), c); // checks if the deleted object doesn't exist on its former position
    }

    @Test
    void modifyConcert() throws ParseException {
        Artist artist = new Artist("Robbie Williams");
        List<Artist> concertArtists = new ArrayList<>(List.of(artist));
        Concert concert = new Concert("Better man",concertArtists,"London, England",new SimpleDateFormat("dd.MM.yyyy").parse("20.12.2022"), 6000);
        concerts.update(0, concert);

        Assertions.assertEquals(concerts.findByID(0), concert); // checks if entity on index 0 has been updated correctly
    }

    @Test
    void addMusicLabel() {
        MusicLabel m = new MusicLabel("Record Accord", "Los Angeles, California");
        musicLabels.add(m);

        Assertions.assertTrue(musicLabels.findAll().contains(m)); // checks whether the added entity is in the list
    }

    @Test
    void deleteMusicLabel() {
        MusicLabel m = musicLabels.findAll().get(0);
        musicLabels.remove(m);

        Assertions.assertFalse(musicLabels.findAll().contains(m)); // checks if label has been removed from the list
        Assertions.assertNotEquals(musicLabels.findByID(0), m); // checks if the deleted object doesn't exist on its former position
    }

    @Test
    void modifyMusicLabel() {
        MusicLabel m = new MusicLabel("In The Lab", "Manchester, England");
        musicLabels.update(0, m);

        Assertions.assertEquals(musicLabels.findByID(0), m); // checks if entity on index 0 has been updated correctly
    }

    @Test
    void addSong() throws ParseException {
        Rock r = new Rock("Bless the Child", "Excellent", new SimpleDateFormat("dd.MM.yyyy").parse("01.02.1972"), new Band("Nightwish"));
        songs.add(r);

        Assertions.assertTrue(songs.findAll().contains(r)); // checks whether the added entity is in the list

    }

    @Test
    void deleteSong() {
        Song s = songs.findAll().get(0);
        songs.remove(s);

        Assertions.assertFalse(songs.findAll().contains(s)); // checks if song has been removed from the list
        Assertions.assertNotEquals(songs.findByID(0), s); // checks if the deleted object doesn't exist on its former position
    }

    @Test
    void modifySong() throws ParseException {
        Pop p = new Pop("Lose Yourself", "Good", new SimpleDateFormat("dd.MM.yyyy").parse("01.02.2002"), new Band("Eminem"));
        songs.update(0, p);

        Assertions.assertEquals(songs.findByID(0), p); // checks if entity on index 0 has been updated correctly
    }

    @Test
    void addUser() {
        User u = new User("whiteking", "my password");
        users.add(u);

        Assertions.assertTrue(users.findAll().contains(u)); // checks whether the added entity is in the list
    }

    @Test
    void deleteUser() {
        User u = users.findAll().get(0);
        users.remove(u);

        Assertions.assertFalse(users.findAll().contains(u)); // checks if user has been removed from the list
        Assertions.assertNotEquals(users.findByUsernameAndPassword(u.getUsername(), u.getPassword()), u); // checks if the deleted object doesn't exist on its former position
    }

    @Test
    void modifyUser() {
        User u = new User("white king01", "some password");
        users.update(users.findAll().get(0).getUsername(), u);

        Assertions.assertEquals(users.findByID(u.getUsername()), u); // checks if entity on index 0 has been updated correctly
    }
}