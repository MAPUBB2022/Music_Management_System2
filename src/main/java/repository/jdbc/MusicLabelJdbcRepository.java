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

public class MusicLabelJdbcRepository implements ICrudRepository<String, MusicLabel> {
    private JDBCConnection connection;

    public MusicLabelJdbcRepository(JDBCConnection connection)
    {
        this.connection = connection;
    }

    public void populateUsers()
    {
        Connection con = JDBCConnection.getInstance();
        try {
            PreparedStatement statement = con.prepareStatement("insert into MusicLabels(name, address, revenue, artistList, albumList, upcomingEvents) values (?, ?, ?, ?, ?, ?)");
            statement.setString(1, "ion");
            statement.setString(2, "1234");
            statement.setString(3, "1234");
            statement.setString(4, "1234");
            statement.setString(5, "1234");
            statement.setString(6, "1234");
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean add(MusicLabel entity) {
        return false;
    }

    @Override
    public boolean remove(MusicLabel entity) {
        return false;
    }

    @Override
    public MusicLabel update(String s, MusicLabel entity) {
        return null;
    }

    @Override
    public MusicLabel findByID(String s) {
        return null;
    }

    @Override
    public List<MusicLabel> findAll() {
        return null;
    }
}