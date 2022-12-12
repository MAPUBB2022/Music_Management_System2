package repository.jdbc;

import interfaces.ICrudRepository;
import model.album.Album;
import model.album.Artist;
import model.album.Band;
import model.concert.Concert;
import model.song.Song;
import model.users.User;
import repository.inmemory.AlbumsInMemoryRepository;

import java.sql.*;
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
    public boolean add(Concert entity) throws SQLException {
        if(findByID(entity.getName()) == null) {
            Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost\\SQLEXPRESS;database=MAP",
                    "MAP_project", "1234");

            String insert_string_fancy = ("insert into Concerts(name, artistList, location, date, capacity, ticketPrice," +
                    " ticketsSold, rentCosts) values (?, ?, ?, ?, ?, ?, ?, ?)");

            PreparedStatement insert_fancy = connection.prepareStatement(insert_string_fancy);
            insert_fancy.setString(1, entity.getName());
            insert_fancy.setObject(2, entity.getArtistList());
            insert_fancy.setString(3, entity.getLocation());
            insert_fancy.setObject(4, entity.getDate());
            insert_fancy.setInt(5, entity.getCapacity());
            insert_fancy.setFloat(6, entity.getTicketPrice());
            insert_fancy.setInt(7, entity.getTicketsSold());
            insert_fancy.setFloat(8, entity.getRentCosts());
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Concert entity) throws SQLException {
        if(findByID(entity.getName()) != null){
            Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost\\SQLEXPRESS;database=MAP",
                    "MAP_project", "1234");

            String delete_string_fancy = "delete * from Concerts where Concerts.name = "+entity.getName();

            PreparedStatement delete_fancy = connection.prepareStatement(delete_string_fancy);

            return true;
        }
        return false;
    }

    @Override
    public Concert update(String name, Concert entity) throws SQLException {
        Concert concert = findByID(name);
        if (concert != null) {
            Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost\\SQLEXPRESS;database=MAP",
                    "MAP_project", "1234");

            String update_string_fancy = "update Concerts set name = " + entity.getName() +
                    ", artistList = " + entity.getArtistList() +
                    ", location = " + entity.getLocation() +
                    ", date = " + entity.getDate() +
                    ", capacity = " + entity.getCapacity() +
                    ", ticketPrice = " + entity.getTicketPrice() +
                    ", ticketsSold = " + entity.getTicketsSold() +
                    ", rentCosts = " + entity.getRentCosts() +
                    " where Concerts.name = " + entity.getName();
            PreparedStatement update_fancy = connection.prepareStatement(update_string_fancy);

            return concert;
        }
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
