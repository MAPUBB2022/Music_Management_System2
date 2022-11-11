package test;

import model.album.Album;
import model.album.Artist;
import model.album.Band;
import model.concert.Concert;
import model.song.Rap;
import model.song.Rock;
import org.junit.jupiter.api.*;
import repository.inmemory.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

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
        Assertions.assertFalse(artists.findAll().contains(a)); // checks if album has been removed from the list
        Assertions.assertNotEquals(artists.findByID(0), a); // checks if the deleted object doesn't exist on its former position
    }

    @Test
    void modifyArtist() {
        Artist artist = new Artist("Jackson");
        artists.update(0, artist);
        Assertions.assertEquals(artists.findByID(0), artist); // checks if entity on index 0 has been updated correctly
    }

    @Test
    void addConcert() throws ParseException {

    }

    @Test
    void deleteConcert() {
    }

    @Test
    void modifyConcert() {
    }

    @Test
    void addMusicLabel() {
    }

    @Test
    void deleteMusicLabel() {
    }

    @Test
    void modifyMusicLabel() {
    }

    @Test
    void addSong() {
    }

    @Test
    void deleteSong() {
    }

    @Test
    void modifySong() {
    }

    @Test
    void addUser() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void modifyUser() {
    }

    @Test
    void sortAlbumsByRevenue() {
    }

    @Test
    void sortSongsByRating() {
    }

    @Test
    void sortSongsByReleaseDate() {
    }

    @Test
    void sortArtistsByName() {
    }

    @Test
    void sortAlbumsByReleaseDate() {
    }

    @Test
    void sortAlbumsByProductionCost() {
    }

    @Test
    void showConcerts() {
    }

    @Test
    void showArtists() {
    }

    @Test
    void showAlbums() {
    }

    @Test
    void showUpcomingConcerts() {
    }
}