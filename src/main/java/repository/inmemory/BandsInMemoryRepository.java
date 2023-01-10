package repository.inmemory;

import interfaces.ICrudRepository;
import model.album.Artist;
import model.album.Band;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BandsInMemoryRepository implements ICrudRepository<String, Band>
{
	private List<Band> bandList;
	
	public BandsInMemoryRepository() throws ParseException
	{
		this.bandList = populateBands();
	}
	
	private List<Band> populateBands() throws ParseException
	{
		ArtistsInMemoryRepository repo = new ArtistsInMemoryRepository();
		
		Band b1 = new Band("The Rolling Stones");
		b1.setOrigin("London, UK");
		Artist artist1 = repo.findByID("Mick Jagger");
		Artist artist2 = repo.findByID("Keith Richards");
		Artist artist3 = repo.findByID("Ron Wood");
		b1.setArtistList(Arrays.asList(artist1, artist2, artist3));
		b1.setFormationDate(new SimpleDateFormat("dd.MM.yyyy").parse("01.01.1962"));
		
		Band b2 = new Band("Led Zeppelin");
		b2.setOrigin("UK");
		artist1 = repo.findByID("Robert Plant");
		artist2 = repo.findByID("Jimmy Page");
		artist3 = repo.findByID("John Paul Jones");
		Artist artist4 = repo.findByID("John Bonham");
		b2.setArtistList(Arrays.asList(artist1, artist2, artist3, artist4));
		b2.setFormationDate(new SimpleDateFormat("dd.MM.yyyy").parse("01.01.1968"));
		
		Band b3 = new Band("The Beatles");
		b3.setOrigin("Liverpool, UK");
		artist1 = repo.findByID("John Lennon");
		artist2 = repo.findByID("Ringo Starr");
		artist3 = repo.findByID("Paul McCartney");
		artist4 = repo.findByID("George Harrison");
		Artist artist5 = repo.findByID("Pete Best");
		Artist artist6 = repo.findByID("Stuart Sutcliffe");
		Artist artist7 = repo.findByID("Chas Newby");
		Artist artist8 = repo.findByID("Tommy Moore");
		b3.setArtistList(Arrays.asList(artist1, artist2, artist3, artist4, artist5, artist6, artist7, artist8));
		b3.setFormationDate(new SimpleDateFormat("dd.MM.yyyy").parse("01.01.1968"));
		
		Band b4 = new Band("Black Sabbath");
		b4.setOrigin("Birmingham, UK");
		artist1 = repo.findByID("Tony Iommi");
		artist2 = repo.findByID("Bill Ward");
		artist3 = repo.findByID("Geezer Butler");
		artist4 = repo.findByID("Ozzy Osbourne");
		b4.setArtistList(Arrays.asList(artist1, artist2, artist3, artist4));
		b4.setFormationDate(new SimpleDateFormat("dd.MM.yyyy").parse("01.01.1968"));
		
		return new ArrayList<>(Arrays.asList(b1, b2, b3, b4));
	}
	
	@Override
	public boolean add(Band entity)
	{
		if (findByID(entity.getName()) == null) {
			this.bandList.add(entity);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean remove(Band entity)
	{
		if (findByID(entity.getName()) != null) {
			this.bandList.remove(entity);
			return true;
		}
		return false;
	}
	
	@Override
	public Band update(String name, Band entity)
	{
		Band band = findByID(name);
		if (band != null) {
			this.bandList.set(this.bandList.indexOf(band), entity);
			return band;
		}
		return null;
	}
	
	@Override
	public Band findByID(String name)
	{
		for (Band band : this.bandList)
			if (band.getName().equals(name)) return band;
		return null;
	}
	
	@Override
	public List<Band> findAll()
	{
		return this.bandList;
	}
	
	@Override
	public String toString()
	{
		if (this.bandList == null) return "";
		StringBuilder endString = new StringBuilder();
		for (Band band : this.bandList)
			endString.append(band.toString()).append("\n");
		return endString.toString();
	}
}
