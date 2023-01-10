package model.users;


public class User
{
	private String username; //Unique
	private String password; //Unique
	
	private boolean isAdmin;
	
	public User() {}
	
	public User(String username, String password)
	{
		this.username = username;
		this.password = password;
		this.isAdmin = false;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public boolean isAdmin()
	{
		return isAdmin;
	}
	
	public void setAdminStatus(boolean admin)
	{
		isAdmin = admin;
	}
	
	@Override
	public String toString()
	{
		return "User\n" + "Username: " + username + "\nPassword: " + password + "\nAdmin Status: " + isAdmin + "\n";
	}
}
