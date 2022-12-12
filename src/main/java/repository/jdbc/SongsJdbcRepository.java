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

import java.sql.*;
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
            PreparedStatement statement = con.prepareStatement("insert into Songs(name, rating, releaseDate, singer) values (?, ?, ?, ?)");
            statement.setString(1, "ion");
            statement.setString(2, "1234");
            statement.setString(3, "1234");
            statement.setString(4, "1234");
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean add(Song entity) throws SQLException {
        if(findByID(entity.getName()) == null) {
            Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost\\SQLEXPRESS;database=MAP",
                    "MAP_project", "1234");

            Statement insert = connection.createStatement();

            String insert_string_fancy = ("insert into Songs(name, rating, releaseDate, singer) values (?, ?, ?, ?)");

            PreparedStatement insert_fancy = connection.prepareStatement(insert_string_fancy);
            insert_fancy.setString(1, entity.getName());
            insert_fancy.setFloat(2, entity.getRating());
            insert_fancy.setObject(3, entity.getReleaseDate());
            insert_fancy.setObject(4, entity.getSinger());
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Song entity) throws SQLException {
        if(findByID(entity.getName()) != null){
            Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost\\SQLEXPRESS;database=MAP",
                    "MAP_project", "1234");

            Statement delete = connection.createStatement();

            String delete_string_fancy = "delete * from Songs where Songs.name = "+entity.getName();

            PreparedStatement delete_fancy = connection.prepareStatement(delete_string_fancy);

            return true;
        }
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
