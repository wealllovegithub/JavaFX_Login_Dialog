package com.doughepi.exceptions;

/**
 * Created by dough on 12/22/2015.
 */
public class UsernameTakenException extends Exception
{
	/**
	 * Custom exception used when a username is already present in a database.
	 *
	 * @param message The message to be presented when exception is thrown.
	 */
	public UsernameTakenException(String message)
	{
		super(message);
	}
}
