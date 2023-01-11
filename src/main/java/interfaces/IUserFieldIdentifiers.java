package interfaces;

import model.users.User;

import java.util.List;

public interface IUserFieldIdentifiers<T> extends ICrudRepository<User, T>
{
	List<T> findAllForUser();
}
