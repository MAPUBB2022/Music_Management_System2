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
            PreparedStatement statement = con.prepareStatement("insert into Bands(name, formationDate, origin) values (?, ?, ?)");
            statement.setString(1, "ion");
            statement.setString(2, "1234");
            statement.setString(3, "1234");
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public boolean add(Band entity) throws SQLException {
        if(findByID(entity.getName()) == null) {
            Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost\\SQLEXPRESS;database=MAP",
                    "MAP_project", "1234");

            Statement insert = connection.createStatement();

            String insert_string_fancy = "insert into Bands(name, formationDate, origin) values (?, ?, ?)";

            PreparedStatement insert_fancy = connection.prepareStatement(insert_string_fancy);
            insert_fancy.setString(1, entity.getName());
            insert_fancy.setObject(2, entity.getFormationDate());
            insert_fancy.setString(3, entity.getOrigin());
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Band entity) throws SQLException {
        if(findByID(entity.getName()) != null){
            Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost\\SQLEXPRESS;database=MAP",
                    "MAP_project", "1234");

            Statement delete = connection.createStatement();

            String delete_string_fancy = "delete * from Bands where Bands.name = "+entity.getName();

            PreparedStatement delete_fancy = connection.prepareStatement(delete_string_fancy);

            return true;
        }
        return false;
    }

    @Override
    public Band update(String name, Band entity) throws SQLException {
        Band band = findByID(name);
        if (band != null) {
            Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost\\SQLEXPRESS;database=MAP",
                    "MAP_project", "1234");

            Statement update = connection.createStatement();

            String update_string_fancy = "update Bands set name = " + entity.getName() +
                    ", formationDate = " + entity.getFormationDate() +
                    ", origin = " + entity.getOrigin() +
                    " where Bands.name = " + entity.getName();
            PreparedStatement update_fancy = connection.prepareStatement(update_string_fancy);

            return band;
        }
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
