package com.doughepi.main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


/**
 * Created by dough on 12/20/2015.
 */
public class MainController
{
	//JavaFX injected fields.
	@FXML
	private Button logoutButton;
	@FXML
	private Label sessionLabel;

	//Called on switch to the main view.
	public void initializeSessionID(final Manager manager, String sessionID)
	{
		//When switched to main view, set text to display the session id.
		sessionLabel.setText(sessionID);

		//Create event handler; on click of log out, set to login view.
		logoutButton.setOnAction(event -> manager.doLogout());
	}
}
