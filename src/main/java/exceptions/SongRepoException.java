package exceptions;

public class SongRepoException extends RuntimeException
{
	public SongRepoException(String message)
	{
		super(message);
	}
}
