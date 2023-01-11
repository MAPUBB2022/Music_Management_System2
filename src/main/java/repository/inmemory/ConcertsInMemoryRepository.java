package repository.inmemory;

import interfaces.ICrudRepository;
import model.album.Artist;
import model.concert.Concert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConcertsInMemoryRepository implements ICrudRepository<String, Concert>
{
	private final List<Concert> concertList;
	
	public ConcertsInMemoryRepository() throws ParseException
	{
		this.concertList = populateConcerts();
	}
	
	private List<Concert> populateConcerts() throws ParseException
	{
		ArtistsInMemoryRepository repo = new ArtistsInMemoryRepository();
		Artist artist1 = repo.findByID("Martyn Ware");
		Artist artist2 = repo.findByID("Ian Craig Marsh");
		Artist artist3 = repo.findByID("Glenn Gregory");
		
		Artist artist4 = repo.findByID("Baba Dochia");
		Artist artist5 = repo.findByID("Smiley");
		
		Concert c1 = new Concert("Heaven 17", Arrays.asList(artist1, artist2, artist3), "Liverpool, Great Britain", new SimpleDateFormat("dd.MM.yyyy").parse("26.11.2012"), 2500);
		c1.setTicketsSold(30);
		c1.setTicketPrice(37.5f);
		c1.setRentCosts(50000f);
		Concert c2 = new Concert("Baba Dochia", Arrays.asList(artist4, artist5), "Cluj-Napoca, Cluj", new SimpleDateFormat("dd.MM.yyyy").parse("05.11.2012"), 800);
		c2.setTicketsSold(20);
		c2.setTicketPrice(50f);
		c2.setRentCosts(150000f);
		return new ArrayList<>(Arrays.asList(c1, c2));
	}
	
	@Override
	public boolean add(Concert entity)
	{
		if (findByID(entity.getName()) == null) {
			this.concertList.add(entity);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean remove(Concert entity)
	{
		if (findByID(entity.getName()) != null) {
			this.concertList.remove(entity);
			return true;
		}
		return false;
	}
	
	@Override
	public Concert update(String name, Concert entity)
	{
		Concert concert = findByID(name);
		if (concert != null) {
			this.concertList.set(this.concertList.indexOf(concert), entity);
			return concert;
		}
		return null;
	}
	
	@Override
	public Concert findByID(String name)
	{
		for (Concert concert : this.concertList)
			if (concert.getName().equals(name)) return concert;
		return null;
	}
	
	@Override
	public List<Concert> findAll()
	{
		return this.concertList;
	}
	
	@Override
	public String toString()
	{
		if (this.concertList == null) return "";
		StringBuilder endString = new StringBuilder();
		for (Concert concert : this.concertList)
			endString.append(concert.toString()).append("\n");
		return endString.toString();
	}
}
