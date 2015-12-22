package com.doughepi.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by dough on 12/21/2015.
 * <p>
 * Manages the addition of elements to the database.
 */
public class UserManager
{
	/**
	 * Database constants.
	 */
	private static final String DB_USERNAME = "*******";
	private static final String DB_PASSWORD = "*******";
	private static final String DB_URL = "jdbc:mysql://localhost:3306";
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

	/**
	 * Adds a new user to the table for later verification on login.
	 *
	 * @param username          The username entered by the user.
	 * @param encryptedPassword The encrypted password represented by a byte array.
	 * @param salt              The salt used to generate the password.
	 */
	public void addUser(String username, byte[] encryptedPassword, byte[] salt)
	{
		try
		{
			Class.forName(JDBC_DRIVER);

			System.out.println("Opening connection...");
			Connection databaseConnection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

			System.out.println("Preparing statement...");
			String sql = "INSERT INTO user_login.user_data (username, password, salt) VALUES (?, ?, ?)";
			PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql);

			preparedStatement.setString(1, username);
			preparedStatement.setBytes(2, encryptedPassword);
			preparedStatement.setBytes(3, salt);

			System.out.println("Sending query...");
			preparedStatement.executeUpdate();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}
