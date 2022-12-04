package model.concert;

import model.album.Artist;
import interfaces.Profitable;

import java.util.Date;
import java.util.List;

public class Concert implements Profitable
{
	private String name; //Unique
	private List<Artist> artistList;
	private String location;
	private Date date;
	private Integer capacity;
	private Float ticketPrice;
	private Integer ticketsSold;
	private Float rentCosts;
	
	public Concert(String name, List<Artist> artistList, String location, Date date, Integer capacity)
	{
		this.name = name;
		this.artistList = artistList;
		this.location = location;
		this.date = date;
		this.capacity = capacity;
		this.ticketPrice = 0f;
		this.ticketsSold = 0;
		this.rentCosts = 0f;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public List<Artist> getArtistList()
	{
		return artistList;
	}
	
	public void setArtistList(List<Artist> artistList)
	{
		this.artistList = artistList;
	}
	
	public String getLocation()
	{
		return location;
	}
	
	public void setLocation(String location)
	{
		this.location = location;
	}
	
	public Date getDate()
	{
		return date;
	}
	
	public void setDate(Date date)
	{
		this.date = date;
	}
	
	public Integer getCapacity()
	{
		return capacity;
	}
	
	public void setCapacity(Integer capacity)
	{
		this.capacity = capacity;
	}
	
	public Float getTicketPrice()
	{
		return ticketPrice;
	}
	
	public void setTicketPrice(Float ticketPrice)
	{
		this.ticketPrice = ticketPrice;
	}
	
	public Integer getTicketsSold()
	{
		return ticketsSold;
	}
	
	public void setTicketsSold(Integer ticketsSold)
	{
		this.ticketsSold = ticketsSold;
	}
	
	public Float getRentCosts()
	{
		return rentCosts;
	}
	
	public void setRentCosts(Float rentCosts)
	{
		this.rentCosts = rentCosts;
	}
	
	@Override
	public float calculateProfit()
	{
		return ticketPrice * ticketsSold - rentCosts;
	}
	
	@SuppressWarnings("StringConcatenationInLoop")
	@Override
	public String toString()
	{
		String artists = "";
		for (Artist artist : this.artistList)
			artists += artist.getName() + " ";
		return "Concert: " + name + "\nArtists: " + artists + "\nLocation: " + location + "\nDate: " + date + "\nCapacity: " + capacity + "\nTicket Price: " + ticketPrice + "\nTickets Sold: " + ticketsSold + "\nRent Costs: " + rentCosts + "\n";
	}
}
