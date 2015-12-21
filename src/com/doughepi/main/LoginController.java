package com.doughepi.main;

/**
 * Created by dough on 12/20/2015.
 */
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController
{
	//JavaFX injected fields.
	@FXML
	private TextField user;
	@FXML
	private TextField password;
	@FXML
	private Button loginButton;

	//A simple session id counter.
	private static int sessionID = 0;

	//On call of initializeManager, set event handlers.
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

	//Increment the session id and return a string.
	private String generateSessionID()
	{
		sessionID++;
		return "Session " + sessionID;
	}
}