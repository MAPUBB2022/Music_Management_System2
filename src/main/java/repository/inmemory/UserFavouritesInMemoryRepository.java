package repository.inmemory;

import interfaces.IUserFieldIdentifiers;
import model.song.Song;
import model.users.User;

import java.util.ArrayList;
import java.util.List;

public class UserFavouritesInMemoryRepository implements IUserFieldIdentifiers<Song>
{
	private final List<Song> songs;
	private final User user;
	
	public UserFavouritesInMemoryRepository(User user)
	{
		this.user = user;
		this.songs = new ArrayList<>();
	}
	
	@Override
	public boolean add(Song entity)
	{
		if (!this.songs.contains(entity)) {
			this.songs.add(entity);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean remove(Song entity)
	{
		if (this.songs.contains(entity)) {
			this.songs.remove(entity);
			return true;
		}
		return false;
	}
	
	@Override
	public Song update(User user, Song entity)
	{
		//Unnecessary
		return null;
	}
	
	@Override
	public Song findByID(User user)
	{
		//Unnecessary
		return null;
	}
	
	@Override
	public List<Song> findAll()
	{
		return this.songs;
	}
	
	@Override
	public List<Song> findAllForUser()
	{
		return this.songs;
	}
	
	@Override
	public String toString()
	{
		if (this.songs.isEmpty()) return "";
		StringBuilder endString = new StringBuilder("User: " + user.getUsername() + "\n");
		for (Song song : this.songs)
			endString.append(song.getName()).append("\n");
		return endString.toString();
	}
}
