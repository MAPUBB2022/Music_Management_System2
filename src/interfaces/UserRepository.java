package interfaces;

import model.users.User;

public interface UserRepository extends ICrudRepository<String, User>
{
	User findByUsernameAndPassword(String username, String password);
}
