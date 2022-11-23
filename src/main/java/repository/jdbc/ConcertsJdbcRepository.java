package repository.jdbc;

import interfaces.ICrudRepository;
import model.album.Album;
import model.album.Artist;
import model.album.Band;
import model.concert.Concert;
import model.song.Song;
import model.users.User;
import repository.inmemory.AlbumsInMemoryRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class ConcertsJdbcRepository implements ICrudRepository<String, Concert> {
    private JDBCConnection connection;

    public ConcertsJdbcRepository(JDBCConnection connection)
    {
        this.connection = connection;
    }

    public void populateUsers()
    {
        Connection con = JDBCConnection.getInstance();
        try {
            PreparedStatement statement = con.prepareStatement("insert into Concerts(name, artistList, location, date, capacity, ticketPrice, ticketsSold, rentCosts) values (?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, "ion");
            statement.setString(2, "1234");
            statement.setString(3, "1234");
            statement.setString(4, "1234");
            statement.setString(5, "1234");
            statement.setString(6, "1234");
            statement.setString(7, "1234");
            statement.setString(8, "1234");
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean add(Concert entity) {
        return false;
    }

    @Override
    public boolean remove(Concert entity) {
        return false;
    }

    @Override
    public Concert update(String s, Concert entity) {
        return null;
    }

    @Override
    public Concert findByID(String s) {
        return null;
    }

    @Override
    public List<Concert> findAll() {
        return null;
    }
}
