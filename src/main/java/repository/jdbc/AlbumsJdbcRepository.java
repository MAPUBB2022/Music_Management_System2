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

public class AlbumsJdbcRepository implements ICrudRepository<String, Album> {
    private JDBCConnection connection;

    public AlbumsJdbcRepository(JDBCConnection connection)
    {
        this.connection = connection;
    }

    public void populateUsers()
    {
        Connection con = JDBCConnection.getInstance();
        try {
            PreparedStatement statement = con.prepareStatement("insert into Albums(title, artist, band, songList, language, productionCost, releaseDate, copiesSold, discPrice) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, "1234");
            statement.setString(2, "1234");
            statement.setString(3, "1234");
            statement.setString(4, "1234");
            statement.setString(5, "1234");
            statement.setString(6, "1234");
            statement.setString(7, "1234");
            statement.setString(8, "1234");
            statement.setString(9, "1234");
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean add(Album entity) {
        return false;
    }

    @Override
    public boolean remove(Album entity) {
        return false;
    }

    @Override
    public Album update(String s, Album entity) {
        return null;
    }

    @Override
    public Album findByID(String s) {
        return null;
    }

    @Override
    public List<Album> findAll() {
        return null;
    }

}
