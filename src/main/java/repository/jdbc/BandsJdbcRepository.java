package repository.jdbc;

import interfaces.ICrudRepository;
import model.album.Album;
import model.album.Artist;
import model.album.Band;
import model.song.Song;
import model.users.User;
import repository.inmemory.AlbumsInMemoryRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class BandsJdbcRepository implements ICrudRepository<String, Band> {
    private JDBCConnection connection;

    public BandsJdbcRepository(JDBCConnection connection)
    {
        this.connection = connection;
    }

    public void populateUsers()
    {
        Connection con = JDBCConnection.getInstance();
        try {
            PreparedStatement statement = con.prepareStatement("insert into Bands(name, formationDate, origin, artistList) values (?, ?, ?, ?)");
            statement.setString(1, "ion");
            statement.setString(2, "1234");
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public boolean add(Band entity) {
        return false;
    }

    @Override
    public boolean remove(Band entity) {
        return false;
    }

    @Override
    public Band update(String s, Band entity) {
        return null;
    }

    @Override
    public Band findByID(String s) {
        return null;
    }

    @Override
    public List<Band> findAll() {
        return null;
    }
}
