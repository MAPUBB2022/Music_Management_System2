package repository.jdbc;

import interfaces.ICrudRepository;
import model.album.Album;
import model.album.Artist;
import model.album.Band;
import model.song.Song;
import model.users.User;
import repository.inmemory.AlbumsInMemoryRepository;

import java.sql.*;
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
            PreparedStatement statement = con.prepareStatement("insert into Albums(title, artist, band, songList," +
                    " language, productionCost, releaseDate, copiesSold, discPrice) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
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
    public boolean add(Album entity) throws SQLException {
        if(findByID(entity.getTitle()) == null) {
            Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost\\SQLEXPRESS;database=MAP",
                    "MAP_project", "1234");

            String insert_string_fancy = "insert into Albums(title, artist, band, songList," +
                    " language, productionCost, releaseDate, copiesSold, discPrice) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement insert_fancy = connection.prepareStatement(insert_string_fancy);
            insert_fancy.setString(1, entity.getTitle());
            insert_fancy.setObject(2, entity.getArtist());
            insert_fancy.setObject(3, entity.getBand());
            insert_fancy.setObject(4, entity.getSongList());
            insert_fancy.setString(5, entity.getLanguage());
            insert_fancy.setFloat(6, entity.getProductionCost());
            insert_fancy.setObject(7, entity.getReleaseDate());
            insert_fancy.setInt(8, entity.getCopiesSold());
            insert_fancy.setFloat(9, entity.getDiscPrice());
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Album entity) throws SQLException {
        if(findByID(entity.getTitle()) != null){
            Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost\\SQLEXPRESS;database=MAP",
                    "MAP_project", "1234");

            String delete_string_fancy = "delete * from Albums where Albums.title = "+entity.getTitle();

            PreparedStatement delete_fancy = connection.prepareStatement(delete_string_fancy);

            return true;
        }
        return false;
    }

    @Override
    public Album update(String name, Album entity) throws SQLException {
        Album album = findByID(name);
        if (album != null) {
            Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost\\SQLEXPRESS;database=MAP",
                    "MAP_project", "1234");

            String update_string_fancy = "update Albums set title = " + entity.getTitle() +
                    ", artist = " + entity.getArtist() +
                    ", band = " + entity.getBand() +
                    ", songList = " + entity.getSongList() +
                    ", language = " + entity.getLanguage() +
                    ", productionCost = " + entity.getProductionCost() +
                    ", releaseDate = " + entity.getReleaseDate() +
                    ", copiesSold = " + entity.getCopiesSold() +
                    ", discPrice = " + entity.getDiscPrice() +
                    " where Albums.title = " + entity.getTitle();
            PreparedStatement update_fancy = connection.prepareStatement(update_string_fancy);

            return album;
        }
        return null;
    }

    @Override
    public Album findByID(String name) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost\\SQLEXPRESS;database=MAP",
                "MAP_project", "1234");

        String find_string_fancy = "select * from Albums where Albums.title = " + name;

       //todo -> return album if found

        PreparedStatement find_fancy = connection.prepareStatement(find_string_fancy);
        return null;
    }

    @Override
    public List<Album> findAll() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost\\SQLEXPRESS;database=MAP",
                "MAP_project", "1234");

        String find_string_fancy = "select * from Albums";

        //todo -> return all if not empty

        PreparedStatement find_fancy = connection.prepareStatement(find_string_fancy);
        return null;
    }

}
