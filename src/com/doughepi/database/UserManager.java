package com.doughepi.database;

import java.sql.*;

/**
 * @author Piper Dougherty
 * @version 1.0
 *          <p>
 *          Manages the addition of elements to the database.
 */
public class UserManager
{
	/**
	 * Database constants.
	 */
	private static final String DB_USERNAME = "intellij";
	private static final String DB_PASSWORD = "password";
	private static final String DB_URL = "jdbc:mysql://localhost:3306";
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

	/**
	 * Checks the database for instances of the same username.
	 *
	 * @param username The username to be searched for.
	 *
	 * @return Returns true if the username exists, false if not.
	 */
	public boolean checkUserExists(String username)
	{
		boolean flag = false;
		try
		{
			Class.forName(JDBC_DRIVER);

			Connection databaseConnection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

			String sql = "SELECT * FROM user_login.user_data WHERE username = ?";

			PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql);
			preparedStatement.setString(1, username);

			ResultSet resultSet = preparedStatement.executeQuery();

			int i = 0;
			while (resultSet.next())
			{
				i++;
				if (i == 1)
				{
					flag = true;
					break;
				}
			}
		}
		catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * Retrieves the hashed password for the respective user.
	 *
	 * @param username The desired username.
	 *
	 * @return The desired username's hashed password.
	 */
	public byte[] getUserPassword(String username)
	{
		byte[] password = null;
		try
		{
			Class.forName(JDBC_DRIVER);

			Connection databaseConnection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

			String sql = "SELECT * FROM user_login.user_data WHERE username = ?";
			PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql);

			preparedStatement.setString(1, username);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next())
			{
				password = resultSet.getBytes("password");
			}
		}
		catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
		return password;
	}

	/**
	 * Retrieves the salt for the respective user.
	 *
	 * @param username The desired username.
	 *
	 * @return The salt used during encryption.
	 */
	public byte[] getUserSalt(String username)
	{
		byte[] salt = null;
		try
		{
			Class.forName(JDBC_DRIVER);

			Connection databaseConnection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

			String sql = "SELECT * FROM user_login.user_data WHERE username = ?";
			PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql);

			preparedStatement.setString(1, username);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next())
			{
				salt = resultSet.getBytes("salt");
			}
		}
		catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
		return salt;
	}

	/**
	 * Adds a new user to the table for later verification on login.
	 *
	 * @param username          The username entered by the user.
	 * @param encryptedPassword The encrypted password represented by a byte array.
	 * @param salt              The salt used to generate the password.
	 */
	public void add(String username, byte[] encryptedPassword, byte[] salt)
	{
		try
		{
			Class.forName(JDBC_DRIVER);

			Connection databaseConnection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

			String sql = "INSERT INTO user_login.user_data (username, password, salt) VALUES (?, ?, ?)";
			PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql);

			preparedStatement.setString(1, username);
			preparedStatement.setBytes(2, encryptedPassword);
			preparedStatement.setBytes(3, salt);

			preparedStatement.executeUpdate();
		}
		catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
	}
}
