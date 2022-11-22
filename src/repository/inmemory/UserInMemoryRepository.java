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
		u1.setAdminStatus(true);
		User u2 = new User("bob", "dob");
		User u3 = new User("john", "sn0w");
		User u4 = new User("mary", "queenofscots1542");
		User u5 = new User("admin", "password");
		u5.setAdminStatus(true);
		return new ArrayList<>(Arrays.asList(u1, u2, u3, u4, u5));
	}
	
	@Override
	public void add(User entity)
	{
		if (findByUsernameAndPassword(entity.getUsername(), entity.getPassword()) == null) this.userList.add(entity);
	}
	
	@Override
	public void remove(User entity)
	{
		if (findByUsernameAndPassword(entity.getUsername(), entity.getPassword()) != null) this.userList.remove(entity);
	}
	
	@Override
	public void update(String username, User entity)
	{
		User user = findByID(username);
		if (user != null) this.userList.set(this.userList.indexOf(user), entity);
	}
	
	@Override
	public User findByID(String username)
	{
		for (User user : this.userList) {
			if (user.getUsername().equals(username)) return user;
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
		for (User user : this.userList)
			if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
				return user;
			}
		return null;
	}
}