package repository.inmemory;

import interfaces.ICrudRepository;
import model.album.Artist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArtistsInMemoryRepository implements ICrudRepository<String, Artist>
{
	private List<Artist> artistList;
	
	public ArtistsInMemoryRepository()
	{
		this.artistList = populateArtists();
	}
	
	private List<Artist> populateArtists()
	{
		Artist a1 = new Artist("Eminem");
		a1.setName("Marshall Bruce Mathers III");
		a1.setSalary(5495250f);
		Artist a2 = new Artist("50 Cent");
		a2.setName("Curtis James Jackson III");
		a2.setSalary(8833076f);
		Artist a3 = new Artist("Kanye West");
		a3.setName(a3.getStageName());
		a3.setSalary(5696991f);
		Artist a4 = new Artist("Travis Scott");
		a4.setName("Jacques Bermon Webster II");
		a4.setSalary(2083101f);
		Artist a5 = new Artist("Pitbull");
		a5.setName("Armando Christian Pérez");
		a5.setSalary(3897263f);
		Artist a6 = new Artist("Macklemore");
		a6.setName("Benjamin Hammond Haggerty");
		a6.setSalary(6760462f);
		Artist a7 = new Artist("Kid Cudi");
		a7.setName("Scott Ramon Seguro Mescudi");
		a7.setSalary(4662192f);
		Artist a8 = new Artist("Madcon");
		a8.setName("Tshawe Baqwa");
		a8.setSalary(5416972f);
		Artist a9 = new Artist("Lil Wayne");
		a9.setName("Dwayne Michael Carter, Jr.");
		a9.setSalary(1080758f);
		Artist a10 = new Artist("Maroon 5");
		a10.setName("Adam Levine");
		a10.setSalary(9710489f);
		Artist a11 = new Artist("Daft Punk");
		a11.setName("Thomas Bangalter");
		a11.setSalary(4074502f);
		Artist a12 = new Artist("Sean Paul");
		a12.setName(a12.getStageName());
		a12.setSalary(30411f);
		Artist a13 = new Artist("Ludwig van Beethoven");
		a13.setName(a13.getStageName());
		a13.setSalary(6897937f);
		Artist a14 = new Artist("Wolfgang Amadeus Mozart");
		a14.setName(a14.getStageName());
		a14.setSalary(7415520f);
		Artist a15 = new Artist("Mick Jagger");
		a15.setName(a15.getStageName());
		a15.setSalary(250000f);
		Artist a16 = new Artist("Keith Richards");
		a16.setName(a16.getStageName());
		a16.setSalary(250000f);
		Artist a17 = new Artist("Ron Wood");
		a17.setName(a17.getStageName());
		a17.setSalary(250000f);
		Artist a18 = new Artist("Robert Plant");
		a18.setName(a18.getStageName());
		a18.setSalary(200000f);
		Artist a19 = new Artist("John Bonham");
		a19.setName(a19.getStageName());
		a19.setSalary(200000f);
		Artist a20 = new Artist("Jimmy Page");
		a20.setName(a20.getStageName());
		a20.setSalary(200000f);
		Artist a21 = new Artist("John Paul Jones");
		a21.setName(a21.getStageName());
		a21.setSalary(200000f);
		Artist a22 = new Artist("John Lennon");
		a22.setName(a22.getStageName());
		a22.setSalary(180000f);
		Artist a23 = new Artist("Ringo Starr");
		a23.setName(a23.getStageName());
		a23.setSalary(180000f);
		Artist a24 = new Artist("Paul McCartney");
		a24.setName(a24.getStageName());
		a24.setSalary(180000f);
		Artist a25 = new Artist("George Harrison");
		a25.setName(a25.getStageName());
		a25.setSalary(180000f);
		Artist a26 = new Artist("Pete Best");
		a26.setName(a26.getStageName());
		a26.setSalary(180000f);
		Artist a27 = new Artist("Stuart Sutcliffe");
		a27.setName(a27.getStageName());
		a27.setSalary(180000f);
		Artist a28 = new Artist("Chas Newby");
		a28.setName(a28.getStageName());
		a28.setSalary(180000f);
		Artist a29 = new Artist("Tommy Moore");
		a29.setName(a29.getStageName());
		a29.setSalary(180000f);
		Artist a30 = new Artist("Tony Iommi");
		a30.setName(a30.getStageName());
		a30.setSalary(150000f);
		Artist a31 = new Artist("Bill Ward");
		a31.setName(a31.getStageName());
		a31.setSalary(150000f);
		Artist a32 = new Artist("Geezer Butler");
		a32.setName(a32.getStageName());
		a32.setSalary(150000f);
		Artist a33 = new Artist("Ozzy Osbourne");
		a33.setName(a33.getStageName());
		a33.setSalary(150000f);
		Artist a34 = new Artist("Martyn Ware");
		a34.setName(a34.getStageName());
		a34.setSalary(1500f);
		Artist a35 = new Artist("Ian Craig Marsh");
		a35.setName(a35.getStageName());
		a35.setSalary(2000f);
		Artist a36 = new Artist("Glenn Gregory");
		a36.setName(a36.getStageName());
		a36.setSalary(2100f);
		Artist a37 = new Artist("Baba Dochia");
		a37.setName(a37.getStageName());
		a37.setSalary(1300f);
		Artist a38 = new Artist("Smiley");
		a38.setName("Andrei Tiberiu Maria");
		a38.setSalary(25000f);
		
		return new ArrayList<>(Arrays.asList(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a20, a21, a22, a23, a24, a25, a26, a27, a28, a29, a30, a31, a32, a33, a34, a35, a36, a37, a38));
	}
	
	
	@Override
	public boolean add(Artist entity)
	{
		if (findByID(entity.getStageName()) == null) {
			this.artistList.add(entity);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean remove(Artist entity)
	{
		if (findByID(entity.getStageName()) != null) {
			this.artistList.remove(entity);
			return true;
		}
		return false;
	}
	
	@Override
	public Artist update(String name, Artist entity)
	{
		Artist artist = findByID(name);
		if (artist != null) {
			this.artistList.set(this.artistList.indexOf(artist), entity);
			return artist;
		}
		return null;
	}
	
	@Override
	public Artist findByID(String name)
	{
		for (Artist artist : this.artistList)
			if (artist.getStageName().equals(name)) return artist;
		return null;
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
