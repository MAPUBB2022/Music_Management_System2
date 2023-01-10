package repository.inmemory;

import interfaces.ICrudRepository;
import interfaces.IUserFieldIdentifiers;
import model.concert.Concert;
import model.concert.Ticket;
import model.users.User;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TicketsInMemoryRepository implements IUserFieldIdentifiers<Ticket>
{
	
	private final List<Ticket> tickets;
	private final User user;
	
	public TicketsInMemoryRepository(User user) throws ParseException
	{
		this.tickets = populateTickets();
		this.user = user;
	}
	
	private List<Ticket> populateTickets() throws ParseException
	{
		UserInMemoryRepository userRepo = new UserInMemoryRepository();
		ConcertsInMemoryRepository concertRepo = new ConcertsInMemoryRepository();
		
		User user = userRepo.findByID("admin");
		User user1 = userRepo.findByID("jk");
		User user2 = userRepo.findByID("bob");
		User user3 = userRepo.findByID("john");
		User user4 = userRepo.findByID("mary");
		
		Concert concert = concertRepo.findByID("Heaven 17");
		Concert concert1 = concertRepo.findByID("Baba Dochia");
		
		Ticket t1 = new Ticket(user, concert.getName(), concert.getLocation(), concert.getDate(), new Date(), 15f);
		Ticket t2 = new Ticket(user, concert1.getName(), concert1.getLocation(), concert1.getDate(), new Date(), 15f);
		Ticket t3 = new Ticket(user1, concert.getName(), concert.getLocation(), concert.getDate(), new Date(), 10f);
		Ticket t4 = new Ticket(user2, concert1.getName(), concert1.getLocation(), concert1.getDate(), new Date(), 6f);
		Ticket t5 = new Ticket(user3, concert.getName(), concert.getLocation(), concert.getDate(), new Date(), 20f);
		Ticket t6 = new Ticket(user2, concert1.getName(), concert1.getLocation(), concert1.getDate(), new Date(), 16f);
		Ticket t7 = new Ticket(user1, concert.getName(), concert.getLocation(), concert.getDate(), new Date(), 30f);
		Ticket t8 = new Ticket(user4, concert1.getName(), concert1.getLocation(), concert1.getDate(), new Date(), 26f);
		Ticket t9 = new Ticket(user4, concert.getName(), concert.getLocation(), concert.getDate(), new Date(), 18f);
		Ticket t10 = new Ticket(user4, concert1.getName(), concert1.getLocation(), concert1.getDate(), new Date(), 24f);
		Ticket t11 = new Ticket(user2, concert.getName(), concert.getLocation(), concert.getDate(), new Date(), 35f);
		
		t1.setTicketCount(1);
		t2.setTicketCount(0);
		t3.setTicketCount(0);
		t4.setTicketCount(1);
		t5.setTicketCount(2);
		t6.setTicketCount(1);
		t7.setTicketCount(2);
		t8.setTicketCount(1);
		t9.setTicketCount(2);
		t10.setTicketCount(1);
		t11.setTicketCount(1);
		
		return new ArrayList<>(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11));
	}
	
	@Override
	public boolean add(Ticket entity)
	{
		return this.tickets.add(entity);
	}
	
	@Override
	public boolean remove(Ticket entity)
	{
		return this.tickets.remove(entity);
	}
	
	@Override
	public Ticket update(User user, Ticket entity)
	{
		//Unnecessary
		Ticket ticket = findByID(user);
		if (ticket != null) {
			this.tickets.set(this.tickets.indexOf(ticket), entity);
			return ticket;
		}
		return null;
	}
	
	@Override
	public Ticket findByID(User user)
	{
		//Unnecessary
		for (Ticket ticket : this.tickets)
			if (ticket.getUser().equals(user)) return ticket;
		return null;
	}
	
	@Override
	public List<Ticket> findAll()
	{
		return this.tickets;
	}
	
	@Override
	public List<Ticket> findAllForUser()
	{
		return this.tickets.stream().filter(ticket -> ticket.getUser().equals(user)).toList();
	}
	
	@Override
	public String toString()
	{
		if (this.tickets == null) return "";
		StringBuilder endString = new StringBuilder();
		for (Ticket ticket : this.findAllForUser())
			endString.append(ticket.toString()).append("\n");
		return endString.toString();
	}
}
