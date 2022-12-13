package repository.jdbc;

import interfaces.ICrudRepository;
import model.album.Album;
import model.album.Artist;
import model.album.Band;
import model.concert.Ticket;
import model.song.Song;
import model.users.User;
import repository.inmemory.AlbumsInMemoryRepository;

import java.sql.*;
import java.util.Date;
import java.util.List;

public class TicketsJdbcRepository implements ICrudRepository<User, Ticket> {
    private JDBCConnection connection;

    public TicketsJdbcRepository(JDBCConnection connection)
    {
        this.connection = connection;
    }

    public void populateUsers()
    {
        Connection con = JDBCConnection.getInstance();
        try {
            PreparedStatement statement = con.prepareStatement("insert into Ticket(concertLocation, concertDate," +
                    " purchaseDate, ticketPrice, ticketCount, username, concertName) values (?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, "ion");
            statement.setString(2, "1234");
            statement.setString(3, "1234");
            statement.setString(4, "1234");
            statement.setString(5, "1234");
            statement.setString(6, "1234");
            statement.setString(7, "1234");
            statement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean add(Ticket entity) throws SQLException {
        if(findByID(entity.getUser()) == null) {
            Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost\\SQLEXPRESS;database=MAP",
                    "MAP_project", "1234");

            Statement insert = connection.createStatement();

            String insert_string_fancy = ("insert into Ticket(concertLocation, concertDate," +
                    " purchaseDate, ticketPrice, ticketCount, username, concertName) values (?, ?, ?, ?, ?, ?, ?)");

            PreparedStatement insert_fancy = connection.prepareStatement(insert_string_fancy);
            insert_fancy.setString(1, entity.getConcertLocation());
            insert_fancy.setObject(2, entity.getConcertDate());
            insert_fancy.setObject(3, entity.getPurchaseDate());
            insert_fancy.setFloat(4, entity.getTicketPrice());
            insert_fancy.setInt(5, entity.getTicketCount());
            insert_fancy.setString(6, entity.getUser().getUsername());
            insert_fancy.setString(7, entity.getConcertName());
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Ticket entity) throws SQLException {
        if(findByID(entity.getUser()) != null){
            Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost\\SQLEXPRESS;database=MAP",
                    "MAP_project", "1234");

            Statement delete = connection.createStatement();

            String delete_string_fancy = "delete * from Ticket where Ticket.username = "+entity.getUser().getUsername();

            PreparedStatement delete_fancy = connection.prepareStatement(delete_string_fancy);

            return true;
        }
        return false;
    }

    @Override
    public Ticket update(User user, Ticket entity) throws SQLException {
        Ticket ticket = findByID(user);
        if (ticket != null) {
            Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost\\SQLEXPRESS;database=MAP",
                    "MAP_project", "1234");

            String update_string_fancy = "update Ticket set username = " + entity.getUser().getUsername() +
                    ", concertName = " + entity.getConcertName() +
                    ", concertLocation = " + entity.getConcertLocation() +
                    ", concertDate = " + entity.getConcertDate() +
                    ", purchaseDate = " + entity.getPurchaseDate() +
                    ", ticketPrice = " + entity.getTicketPrice() +
                    ", ticketCount = " + entity.getTicketCount() +
                    ", username = " + entity.getUser().getUsername() +
                    " where Ticket.username = " + entity.getUser().getUsername();
            PreparedStatement update_fancy = connection.prepareStatement(update_string_fancy);

            return ticket;
        }
        return null;
    }

    @Override
    public Ticket findByID(User user) throws SQLException {
        return null;
    }


    @Override
    public List<Ticket> findAll() {
        return null;
    }

}
