package repository.jdbc;

import interfaces.ICrudRepository;
import model.album.Album;
import model.album.Artist;
import model.album.Band;
import model.concert.Concert;
import model.label.MusicLabel;
import model.song.Song;
import model.users.User;
import repository.inmemory.AlbumsInMemoryRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class SongsJdbcRepository implements ICrudRepository<String, Song> {
    private JDBCConnection connection;

    public SongsJdbcRepository(JDBCConnection connection)
    {
        this.connection = connection;
    }

    public void populateUsers()
    {
        Connection con = JDBCConnection.getInstance();
        try {
            PreparedStatement statement = con.prepareStatement("insert into Songs(name, rating, releaseDate, singer, band_singers) values (?, ?, ?, ?, ?, ?)");
            statement.setString(1, "ion");
            statement.setString(2, "1234");
            statement.setString(2, "1234");
            statement.setString(2, "1234");
            statement.setString(2, "1234");
            statement.setString(2, "1234");
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean add(Song entity) {
        return false;
    }

    @Override
    public boolean remove(Song entity) {
        return false;
    }

    @Override
    public Song update(String s, Song entity) {
        return null;
    }

    @Override
    public Song findByID(String s) {
        return null;
    }

    @Override
    public List<Song> findAll() {
        return null;
    }
}
