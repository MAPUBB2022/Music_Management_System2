package repository.inmemory;


import interfaces.UserRepository;
import model.label.MusicLabel;
import model.users.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserInMemoryRepository implements UserRepository
{
	private List<User> userList;
	
	public UserInMemoryRepository()
	{
		this.userList = populateUsers();
	}
	
	private List<User> populateUsers()
	{
		User u1 = new User("jk", "secret");
		User u2 = new User("bob", "dob");
		User u3 = new User("john", "sn0w");
		User u4 = new User("mary", "queenofscots1542");
		User u5 = new User("admin", "password");
		return new ArrayList<>(Arrays.asList(u1, u2, u3, u4, u5));
	}
	
	@Override
	public boolean add(User entity)
	{
		if(!this.userList.contains(entity)) {
			this.userList.add(entity);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean remove(User entity)
	{
		if(findByID(entity.getUsername()) != null) {
			this.userList.remove(entity);
			return true;
		}
		return false;
	}
	
	@Override
	public User update(String s, User entity)
	{
		User user = findByID(s);
		if(user != null) {
			this.userList.set(this.userList.indexOf(user), entity);
			return user;
		}
		return null;
	}
	
	@Override
	public User findByID(String username)
	{
		for(User user: userList){
			if(user.getUsername().equals(username))
				return user;
		}
		return null;
	}
	
	@Override
	public List<User> findAll()
	{
		return this.userList;
	}
	
	@Override
	public User findByUsernameAndPassword(String username, String password)
	{
		for (User user1 : userList)
			if (user1.getUsername().equals(username) && user1.getPassword().equals(password)) {
				return user1;
			}
		return null;
	}
}