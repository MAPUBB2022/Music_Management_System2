package repository.inmemory;

import interfaces.ICrudRepository;
import model.album.Album;
import model.album.Artist;
import model.album.Band;
import model.concert.Ticket;
import model.song.Rap;
import model.song.Rock;
import model.users.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TicketsInMemoryRepository implements ICrudRepository<User, Ticket>
{
    private List<Ticket> inMemoryTickets;

    public TicketsInMemoryRepository() throws ParseException
    {
        this.inMemoryTickets = populateTickets();
    }

    private List<Ticket> populateTickets() throws ParseException
    {
        User u1 = new User("my_username", "12345");
        Ticket t1 = new Ticket("concert",
                "concertLocation", new SimpleDateFormat("dd.MM.yyyy").parse("01.06.2012"),
                new SimpleDateFormat("dd.MM.yyyy").parse("01.06.2012"), 50.2f, u1);

        User u2 = new User("my_username2", "012345");
        Ticket t2 = new Ticket("concert2",
                "concertLocation", new SimpleDateFormat("dd.MM.yyyy").parse("01.03.2012"),
                new SimpleDateFormat("dd.MM.yyyy").parse("01.03.2012"), 50.2f, u2);

        return new ArrayList<>(Arrays.asList(t1, t2));
    }

    @Override
    public boolean add(Ticket entity)
    {
        if(findByID(entity.getUser()) == null) {
            this.inMemoryTickets.add(entity);
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Ticket entity) throws Error
    {
        if(findByID(entity.getUser()) != null){
            this.inMemoryTickets.remove(entity);
            return true;
        }
        return false;
    }

    @Override
    public Ticket update(User user, Ticket entity)
    {
        Ticket ticket = findByID(user);
        if (ticket != null) {
            this.inMemoryTickets.set(this.inMemoryTickets.indexOf(ticket), entity);
            return ticket;
        }
        return null;
    }

    @Override
    public Ticket findByID(User user) throws Error
    {
        for (Ticket ticket : inMemoryTickets) {
            if (ticket.getUser().equals(user))
                return ticket;
        }
        return null;
    }

    @Override
    public List<Ticket> findAll() throws Error
    {
        if (!inMemoryTickets.isEmpty()){
            return this.inMemoryTickets;
        }
        return null;
    }
}
