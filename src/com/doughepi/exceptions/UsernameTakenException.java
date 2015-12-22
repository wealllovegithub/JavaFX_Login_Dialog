package com.doughepi.exceptions;

/**
 * Created by dough on 12/22/2015.
 */
public class UsernameTakenException extends Exception
{
	public UsernameTakenException(String message)
	{
		super(message);
	}
}
