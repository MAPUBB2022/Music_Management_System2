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
            statement.setString(3, "1234");
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean add(Artist entity) throws SQLException {
        if(findByID(entity.getName()) == null) {
            Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost\\SQLEXPRESS;database=MAP",
                    "MAP_project", "1234");

            Statement insert = connection.createStatement();

            String insert_string_fancy = ("insert into Artists(name, stage_name, salary) values (?, ?, ?)");

            PreparedStatement insert_fancy = connection.prepareStatement(insert_string_fancy);
            insert_fancy.setString(1, entity.getName());
            insert_fancy.setString(2, entity.getStage_name());
            insert_fancy.setFloat(3, entity.getSalary());
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Artist entity) throws SQLException {
        if(findByID(entity.getName()) != null){
            Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost\\SQLEXPRESS;database=MAP",
                    "MAP_project", "1234");

            Statement delete = connection.createStatement();

            String delete_string_fancy = "delete * from Artists where Artists.name = "+entity.getName();

            PreparedStatement delete_fancy = connection.prepareStatement(delete_string_fancy);

            return true;
        }
        return false;
    }

    @Override
    public Artist update(String name, Artist entity) throws SQLException {
        Artist artist = findByID(name);
        if (artist != null) {
            Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost\\SQLEXPRESS;database=MAP",
                    "MAP_project", "1234");

            Statement update = connection.createStatement();

            String update_string_fancy = "update Artists set name = " + entity.getName() +
                    ", stage_name = " + entity.getStage_name() +
                    ", salary = " + entity.getSalary() +
                    " where Artists.name = " + entity.getName();
            PreparedStatement update_fancy = connection.prepareStatement(update_string_fancy);

            return artist;
        }
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
