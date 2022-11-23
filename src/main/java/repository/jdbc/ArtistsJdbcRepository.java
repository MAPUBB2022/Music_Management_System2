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

public class ArtistsJdbcRepository implements ICrudRepository<String, Artist> {
    private JDBCConnection connection;

    public ArtistsJdbcRepository(JDBCConnection connection)
    {
        this.connection = connection;
    }

    public void populateUsers()
    {
        Connection con = JDBCConnection.getInstance();
        try {
            PreparedStatement statement = con.prepareStatement("insert into Artists(name, stage_name, salary) values (?, ?, ?)");
            statement.setString(1, "ion");
            statement.setString(2, "1234");
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean add(Artist entity) {
        return false;
    }

    @Override
    public boolean remove(Artist entity) {
        return false;
    }

    @Override
    public Artist update(String s, Artist entity) {
        return null;
    }

    @Override
    public Artist findByID(String s) {
        return null;
    }

    @Override
    public List<Artist> findAll() {
        return null;
    }

}
