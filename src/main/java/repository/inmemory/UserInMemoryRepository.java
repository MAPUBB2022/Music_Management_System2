package repository.inmemory;


import interfaces.UserRepository;
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
	public void add(User entity)
	{
		if(!this.userList.contains(entity))
			this.userList.add(entity);
	}
	
	@Override
	public void remove(User entity)
	{
		if(findByID(entity.getUsername()) != null)
			this.userList.remove(entity);
	}
	
	@Override
	public void update(String s, User entity)
	{
		int index = 0;
		for (User user : this.userList) {
			if (user.getUsername().equals(s)) break;
			index++;
		}
		this.userList.set(index, entity);
	}
	
	@Override
	public User findByID(String s)
	{
		int index = 0;
		for (User user : this.userList) {
			if (user.getUsername().equals(s)) break;
			index++;
		}
		return this.userList.get(index);
	}
	
	@Override
	public List<User> findAll()
	{
		return this.userList;
	}
	
	@Override
	public User findByUsernameAndPassword(String username, String password)
	{
		User user = null;
		for (User user1 : this.userList)
			if (user1.getUsername().equals(username) && user1.getPassword().equals(password)) {
				user = user1;
				break;
			}
		return user;
	}
}