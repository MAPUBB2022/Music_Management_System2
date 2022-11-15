package repository.inmemory;

import interfaces.ICrudRepository;
import model.album.Artist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArtistsInMemoryRepository implements ICrudRepository<Integer, Artist>
{
	private List<Artist> artistList;
	
	public ArtistsInMemoryRepository()
	{
		this.artistList = populateArtists();
	}
	
	private List<Artist> populateArtists()
	{
		Artist a1 = new Artist("Eminem");
		Artist a2 = new Artist("50 Cent");
		Artist a3 = new Artist("Kanye West");
		Artist a4 = new Artist("Travis Scott");
		Artist a5 = new Artist("Pitbull");
		Artist a6 = new Artist("Macklemore");
		Artist a7 = new Artist("Kid Cudi");
		Artist a8 = new Artist("Madcon");
		Artist a9 = new Artist("Lil Wayne");
		Artist a10 = new Artist("Maroon 5");
		Artist a11 = new Artist("Daft Punk");
		Artist a12 = new Artist("Sean Paul");
		Artist a13 = new Artist("Ludwig van Beethoven");
		Artist a14 = new Artist("Wolfgang Amadeus Mozart");
		return new ArrayList<>(Arrays.asList(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14));
	}
	
	
	@Override
	public void add(Artist entity)
	{
		this.artistList.add(entity);
	}
	
	@Override
	public void remove(Artist entity)
	{
		this.artistList.remove(entity);
	}
	
	@Override
	public void update(Integer index, Artist entity)
	{
		this.artistList.set(index, entity);
	}
	
	@Override
	public Artist findByID(Integer index)
	{
		return this.artistList.get(index);
	}
	
	@Override
	public List<Artist> findAll()
	{
		return artistList;
	}
	
	@Override
	public String toString()
	{
		StringBuilder endString = new StringBuilder();
		for (Artist artist : this.artistList)
			endString.append(artist.toString()).append("\n");
		return endString.toString();
	}
}
