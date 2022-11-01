package model.users;


import java.util.Objects;

public class User
{
	private String username;
	private String password;
	
	public User(String username, String password)
	{
		//constructor
		this.username = username;
		this.password = password;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public String getPassword()
	{
		return password;
	}
}
