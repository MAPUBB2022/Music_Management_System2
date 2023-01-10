package controller;

import interfaces.IAdminController;
import interfaces.ICrudRepository;
import model.album.Album;
import model.album.Artist;
import model.album.Band;
import model.concert.Concert;
import model.label.MusicLabel;
import model.song.Song;
import model.users.User;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.Date;

public class AdminController implements IAdminController {
    private ICrudRepository<String, Album> albumList;
    private ICrudRepository<String, Artist> artistList;
    private ICrudRepository<String, Concert> concertList;
    private ICrudRepository<String, Song> songList;
    private ICrudRepository<String, Band> bandList;
    private ICrudRepository<String, MusicLabel> labelList;
    private ICrudRepository<String, User> userList;

    public AdminController(ICrudRepository<String, Album> albumList, ICrudRepository<String, Artist> artistList,
                           ICrudRepository<String, Concert> concertList, ICrudRepository<String, Song> songList,
                           ICrudRepository<String, Band> bandList, ICrudRepository<String, MusicLabel> labelList,
                           ICrudRepository<String, User> userList) {
        this.albumList = albumList;
        this.artistList = artistList;
        this.concertList = concertList;
        this.songList = songList;
        this.bandList = bandList;
        this.labelList = labelList;
        this.userList = userList;
    }

    public AdminController() {};


    public ICrudRepository<String, Album> getAlbumList() {
        return albumList;
    }

    public ICrudRepository<String, Artist> getArtistList() {
        return artistList;
    }

    public ICrudRepository<String, Concert> getConcertList() {
        return concertList;
    }

    public ICrudRepository<String, Song> getSongList() {
        return songList;
    }

    public ICrudRepository<String, Band> getBandList() {
        return bandList;
    }

    public ICrudRepository<String, MusicLabel> getLabelList() {
        return labelList;
    }

    public ICrudRepository<String, User> getUserList() {
        return userList;
    }


    /**
     * @param album is an object of type Album
     * @return the function returns false if the received album is already present in the list of albums named albumList
     * or adds the album to the albumList and returns true otherwise
     * @throws SQLException that provides information on a database access error or other errors.
     */
    @Override
    public boolean addAlbum(Album album) throws SQLException {
        if (this.albumList.findAll().contains(album))
            return false;
        this.albumList.add(album);
        return true;
    }

    /**
     * @param album is an object of type Album
     * @return the function returns false if the received album is not present in the list of albums named albumList
     * or removes the album from the albumList and returns true otherwise
     * @throws SQLException that provides information on a database access error or other errors.
     */
    @Override
    public boolean deleteAlbum(Album album) throws SQLException {
        if (!this.albumList.findAll().contains(album))
            return false;
        this.albumList.remove(album);
        return true;
    }

    /**
     * @param album is an object of type Album
     * @return the function returns false if the received album is already present in the list of albums named albumList
     * or modifies the album from the albumList and returns true otherwise
     * @throws SQLException that provides information on a database access error or other errors.
     */
    @Override
    public boolean modifyAlbum(Album album) throws SQLException {
        for (Album albums : albumList.findAll()) {
            if (albums.equals(album)) {
                albumList.update(String.valueOf(albumList.findAll().indexOf(albums)), album);
                return true;
            }
        }
        return false;
    }

    /**
     * @param artist is an object of type Artist
     * @return the function returns false if the received artist is already present in the list of artists named artistList
     * or adds the artist to the artistList and returns true otherwise
     * @throws SQLException that provides information on a database access error or other errors.
     */
    @Override
    public boolean addArtist(Artist artist) throws SQLException {
        if (this.artistList.findAll().contains(artist))
            return false;
        this.artistList.add(artist);
        return true;
    }

    /**
     * @param artist is an object of type Artist
     * @return the function returns false if the received artist is not present in the list of artists named artistList
     * or removes the artist to the artistList and returns true otherwise
     * @throws SQLException that provides information on a database access error or other errors.
     */
    @Override
    public boolean deleteArtist(Artist artist) throws SQLException {
        if (!this.artistList.findAll().contains(artist))
            return false;
        this.artistList.remove(artist);
        return true;
    }

    /**
     * @param artist is an object of type Artist
     * @return the function returns false if the received artist is already present in the list of artists named artistList
     * or modifies the artist from the artistList and returns true otherwise
     * @throws SQLException that provides information on a database access error or other errors.
     */
    @Override
    public boolean modifyArtist(Artist artist) throws SQLException {
        for (Artist artists : artistList.findAll()) {
            if (artist.equals(artists)) {
                artistList.update(String.valueOf(artistList.findAll().indexOf(artists)), artist);
                return true;
            }
        }
        return false;
    }

    /**
     * @param band is an object of type Band
     * @return the function returns false if the received band is already present in the list of bands named bandList
     * or adds the band to the bandList and returns true otherwise
     * @throws SQLException that provides information on a database access error or other errors.
     */
    @Override
    public boolean addBand(Band band) throws SQLException {
        if (this.bandList.findAll().contains(band))
            return false;
        this.bandList.add(band);
        return true;
    }

    /**
     * @param band is an object of type Band
     * @return the function returns false if the received band is not present in the list of bands named bandList
     * or removes the band from the bandList and returns true otherwise
     * @throws SQLException that provides information on a database access error or other errors.
     */
    @Override
    public boolean deleteBand(Band band) throws SQLException {
        if (!this.bandList.findAll().contains(band))
            return false;
        this.bandList.remove(band);
        return true;
    }

    /**
     * @param band is an object of type Band
     * @return the function returns false if the received band is already present in the list of bands named bandList
     * or modifies the band from the bandList and returns true otherwise
     * @throws SQLException that provides information on a database access error or other errors.
     */
    @Override
    public boolean modifyBand(Band band) throws SQLException {
        for (Band bands : bandList.findAll()) {
            if (bands.equals(band)) {
                bandList.update(String.valueOf(bandList.findAll().indexOf(bands)), band);
            }
        }
        return false;
    }

    /**
     * @param concert is an object of type Concert
     * @return the function returns false if the received album is already present in the list of concerts named concertList
     * or adds the concert to the concertList and returns true otherwise
     * @throws SQLException that provides information on a database access error or other errors.
     */
    @Override
    public boolean addConcert(Concert concert) throws SQLException {
        if (this.concertList.findAll().contains(concert))
            return false;
        this.concertList.add(concert);
        return true;
    }

    /**
     * @param concert is an object of type Concert
     * @return the function returns false if the received album is not present in the list of concerts named concertList
     * or removes the concert from the concertList and returns true otherwise
     * @throws SQLException that provides information on a database access error or other errors.
     */
    @Override
    public boolean deleteConcert(Concert concert) throws SQLException {
        if (!this.concertList.findAll().contains(concert))
            return false;
        this.concertList.remove(concert);
        return true;
    }

    /**
     * @param concert is an object of type Concert
     * @return the function returns false if the received album is already present in the list of concerts named concertList
     * or modifies the concert from the concertList and returns true otherwise
     * @throws SQLException that provides information on a database access error or other errors.
     */
    @Override
    public boolean modifyConcert(Concert concert) throws SQLException {
        for (Concert concerts : concertList.findAll()) {
            if (concerts.equals(concert)) {
                concertList.update(String.valueOf(concertList.findAll().indexOf(concerts)), concert);
                return true;
            }
        }
        return false;
    }

    /**
     * @param musicLabel is an object of type MusicLabel
     * @return the function returns false if the received label is already present in the list of labels named labelList
     * or adds the musicLabel to the labelList and returns true otherwise
     * @throws SQLException that provides information on a database access error or other errors.
     */
    @Override
    public boolean addMusicLabel(MusicLabel musicLabel) throws SQLException {
        if (this.labelList.findAll().contains(musicLabel))
            return false;
        this.labelList.add(musicLabel);
        return true;
    }

    /**
     * @param musicLabel is an object of type MusicLabel
     * @return the function returns false if the received label is not present in the list of labels named labelList
     * or removes the musicLabel from the labelList and returns true otherwise
     * @throws SQLException that provides information on a database access error or other errors.
     */
    @Override
    public boolean deleteMusicLabel(MusicLabel musicLabel) throws SQLException {
        if (!this.labelList.findAll().contains(musicLabel))
            return false;
        this.labelList.remove(musicLabel);
        return true;
    }

    /**
     * @param musicLabel is an object of type MusicLabel
     * @return the function returns false if the received label is already present in the list of labels named labelList
     * or modifies the musicLabel from the labelList and returns true otherwise
     * @throws SQLException that provides information on a database access error or other errors.
     */
    @Override
    public boolean modifyMusicLabel(MusicLabel musicLabel) throws SQLException {
        for (MusicLabel labels : labelList.findAll()) {
            if (labels.equals(musicLabel)) {
                labelList.update(String.valueOf(labelList.findAll().indexOf(labels)), musicLabel);
                return true;
            }
        }
        return false;
    }

    /**
     * @param song is an object of type Song
     * @return the function returns false if the received song is already present in the list of songs named songList
     * or adds the song to the songList and returns true otherwise
     * @throws SQLException that provides information on a database access error or other errors.
     */
    @Override
    public boolean addSong(Song song) throws SQLException {
        //TODO implementation -> related songs - empty
        if (this.songList.findAll().contains(song))
            return false;
        this.songList.add(song);
        return true;
    }

    /**
     * @param song is an object of type Song
     * @return the function returns false if the received song is not present in the list of songs named songList
     * or removes the song from the songList and returns true otherwise
     * @throws SQLException that provides information on a database access error or other errors.
     */
    @Override
    public boolean deleteSong(Song song) throws SQLException {
        if (!this.songList.findAll().contains(song))
            return false;
        this.songList.remove(song);
        return true;
    }

    /**
     * @param song is an object of type Song
     * @return the function returns false if the received song is already present in the list of songs named songList
     * or modifies the song in the songList and returns true otherwise
     * @throws SQLException that provides information on a database access error or other errors.
     */
    @Override
    public boolean modifySong(Song song) throws SQLException {
        for (Song songs : songList.findAll()) {
            if (song.equals(songs)) {
                songList.update(String.valueOf(songList.findAll().indexOf(songs)), song);
                return true;
            }
        }
        return false;
    }

    /**
     * @param user is an object of type User
     * @return the function returns false if the received user is already present in the list of users named userList
     * or adds the user to the userList and returns true otherwise
     * @throws SQLException that provides information on a database access error or other errors.
     */
    @Override
    public boolean addUser(User user) throws SQLException {
        if (this.userList.findAll().contains(user))
            return false;
        this.userList.add(user);
        return true;
    }

    /**
     * @param user is an object of type User
     * @return the function returns false if the received user is not present in the list of users named userList
     * or removes the user from the userList and returns true otherwise
     * @throws SQLException that provides information on a database access error or other errors.
     */
    @Override
    public boolean deleteUser(User user) throws SQLException {
        if (!this.userList.findAll().contains(user))
            return false;
        this.userList.remove(user);
        return true;
    }

    /**
     * @param user is an object of type User
     * @return the function returns false if the received user is already present in the list of users named userList
     * or modifies the user in the userList and returns true otherwise
     * @throws SQLException that provides information on a database access error or other errors.
     */
    @Override
    public boolean modifyUser(User user) throws SQLException {
        for (User users : userList.findAll()) {
            if (users.equals(user)) {
                userList.update(String.valueOf(userList.findAll().indexOf(users)), user);
                return true;
            }
        }
        return false;
    }

    /**
     * Sorts albums by revenue if albumList is not empty or throws exception otherwise
     *
     * @throws NullPointerException thrown when an application attempts to use null in a case where an object is required
     * @throws SQLException         an exception that provides information on a database access error or other errors.
     */
    @Override
    public void sortAlbumsByRevenue() throws NullPointerException, SQLException {
        if (this.albumList.findAll() == null) {
            throw new NullPointerException();
        }
        this.albumList.findAll().sort((album1, album2) -> (int) (album1.calculateProfit() - album2.calculateProfit()));
    }

    /**
     * Sorts songs by rating if songList is not empty or throws exception otherwise
     *
     * @throws NullPointerException thrown when an application attempts to use null in a case where an object is required
     * @throws SQLException         an exception that provides information on a database access error or other errors.
     */
    @Override
    public void sortSongsByRating() throws NullPointerException, SQLException {
        if (this.songList.findAll() == null) {
            throw new NullPointerException();
        }
        this.songList.findAll().sort(Comparator.comparing(Song::getRating));
    }

    /**
     * Sorts songs by release date if albumList is not empty or throws exception otherwise
     *
     * @throws NullPointerException thrown when an application attempts to use null in a case where an object is required
     * @throws SQLException         an exception that provides information on a database access error or other errors.
     */
    @Override
    public void sortSongsByReleaseDate() throws NullPointerException, SQLException {
        if (this.songList.findAll() == null) {
            throw new NullPointerException();
        }
        this.songList.findAll().sort(Comparator.comparing(Song::getReleaseDate));
    }

    /**
     * Sorts artists by name if artistList is not empty or throws exception otherwise
     *
     * @throws NullPointerException thrown when an application attempts to use null in a case where an object is required
     * @throws SQLException         an exception that provides information on a database access error or other errors.
     */
    @Override
    public void sortArtistsByName() throws NullPointerException, SQLException {
        if (this.artistList.findAll() == null) {
            throw new NullPointerException();
        }
        this.artistList.findAll().sort(Comparator.comparing(Artist::getStage_name));
    }

    /**
     * Sorts albums by release date if albumList is not empty or throws exception otherwise
     *
     * @throws NullPointerException thrown when an application attempts to use null in a case where an object is required
     * @throws SQLException         an exception that provides information on a database access error or other errors.
     */
    @Override
    public void sortAlbumsByReleaseDate() throws NullPointerException, SQLException {
        if (this.albumList.findAll() == null) {
            throw new NullPointerException();
        }
        this.albumList.findAll().sort(Comparator.comparing(Album::getReleaseDate));
    }

    /**
     * Sorts albums by production cost if albumList is not empty or throws exception otherwise
     *
     * @throws NullPointerException thrown when an application attempts to use null in a case where an object is required
     * @throws SQLException         an exception that provides information on a database access error or other errors.
     */
    @Override
    public void sortAlbumsByProductionCost() throws NullPointerException, SQLException {
        if (this.albumList.findAll() == null) {
            throw new NullPointerException();
        }
        this.albumList.findAll().sort(Comparator.comparing(Album::getProductionCost));
    }

    /**
     * @return returns the list of concerts if the list is not empty or warning message otherwise
     * @throws NullPointerException thrown when an application attempts to use null in a case where an object is required
     * @throws SQLException         an exception that provides information on a database access error or other errors.
     */
    @Override
    public String showConcerts() throws NullPointerException, SQLException {
        if (this.concertList == null) throw new NullPointerException();
        StringBuilder endString = new StringBuilder();
        for (Concert concert : this.concertList.findAll())
            endString.append(concert.toString());
        return endString.toString().equals("") ? "[WARNING] Concert List is Empty\n" : endString.toString();
    }

    /**
     * @return returns the list of artists if the list is not empty or warning message otherwise
     * @throws NullPointerException thrown when an application attempts to use null in a case where an object is required
     * @throws SQLException         an exception that provides information on a database access error or other errors.
     */
    @Override
    public String showArtists() throws NullPointerException, SQLException {
        if (this.artistList == null) throw new NullPointerException();
        StringBuilder endString = new StringBuilder();
        for (Artist artist : this.artistList.findAll())
            endString.append(artist.toString());
        return endString.toString().equals("") ? "[WARNING] Artist List is Empty\n" : endString.toString();
    }

    /**
     * @return returns the list of albums if the list is not empty or warning message otherwise
     * @throws NullPointerException thrown when an application attempts to use null in a case where an object is required
     * @throws SQLException         an exception that provides information on a database access error or other errors.
     */
    @Override
    public String showAlbums() throws NullPointerException, SQLException {
        if (this.albumList == null) throw new NullPointerException();
        StringBuilder endString = new StringBuilder();
        for (Album album : this.albumList.findAll())
            endString.append(album.toString());
        return endString.toString().equals("") ? "[WARNING] Album List is Empty\n" : endString.toString();
    }

    /**
     * @return returns the list of upcoming concerts if the list is not empty or warning message otherwise
     * @throws NullPointerException thrown when an application attempts to use null in a case where an object is required
     * @throws SQLException         an exception that provides information on a database access error or other errors.
     */
    @Override
    public String showUpcomingConcerts() throws NullPointerException, SQLException {
        if (this.concertList.findAll() == null) {
            throw new NullPointerException();
        }
        StringBuilder endString = new StringBuilder();
        Date today = new Date();
        for (Concert concert : this.concertList.findAll()) {
            if (concert.getDate().after(today)) endString.append(concert.toString());
        }
        return endString.toString().equals("") ? "[WARNING] No Upcoming Concert exist\n" : endString.toString();
    }

}
