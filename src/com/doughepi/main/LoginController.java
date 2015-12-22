package com.doughepi.main;

/**
 * Created by dough on 12/20/2015.
 */

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.jetbrains.annotations.Nullable;

public class LoginController
{
	/**
	 * A simple session id counter to be incremented on successful login.
	 */
	private static int sessionID = 0;
	/**
	 * JavaFX injected fields.
	 */
	@FXML
	private TextField user;
	@FXML
	private TextField password;
	@FXML
	private Button loginButton;

	/**
	 * When called, a sessionID generation attempt is made.
	 * If successful, a switch to the main view is made.
	 *
	 * @param manager The manager object.
	 */
	public void initializeManager(final Manager manager)
	{
		loginButton.setOnAction(event ->
		{
			//On click, generate a session id if credentials pass.
			String sessionID = checkCredentials();

			//If credentials fail authentication, null is returned.
			if (sessionID != null)
			{
				//If credentials pass, the scene is set to the main view and a session id is passed.
				manager.doLogin(sessionID);
			}
		});
	}

	/**
	 * When called, checks the entered credentials.
	 *
	 * @return A generated session id if login credentials pass. If not, null is returned.
	 */
	@Nullable
	private String checkCredentials()
	{
		if (user.getText().equals("open") && password.getText().equals("sesame"))
		{
			return generateSessionID();
		}
		else
		{
			return null;
		}
	}

	/**
	 * Generates a simple session id by incrementing the session id field.
	 *
	 * @return The session id.
	 */
	private String generateSessionID()
	{
		sessionID++;
		return "Session " + sessionID;
	}
}