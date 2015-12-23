package com.doughepi.main;

import com.doughepi.authentication.Authentication;
import com.doughepi.exceptions.UsernameTakenException;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class RegisterController
{
	@FXML
	private TextField user;

	@FXML
	private TextField pass;

	@FXML
	private TextField secondPass;

	@FXML
	private Button cancelButton;

	@FXML
	private Button registerButton;

	@FXML
	private Label errorLabel;

	public void initializeManager(final Manager manager)
	{
		cancelButton.setOnAction(event -> manager.setScene_Login());
		registerButton.setOnAction(event ->
		{
			if (!user.getText().isEmpty())
			{
				registerUser();
			}
			else
			{
				setLabel("Please enter username...");
			}
		});
	}

	private void registerUser()
	{
		String username = user.getText();
		String password = pass.getText();
		String secondPassword = secondPass.getText();

		Authentication authentication = new Authentication();

		try
		{
			if (password.equals(secondPassword))
			{
				authentication.addUser(username, password);
			}
			else
			{
				setLabel("Passwords do not match...");
			}
		}
		catch (UsernameTakenException e)
		{
			setLabel(e.getMessage());
		}
	}

	private void setLabel(String text)
	{
		errorLabel.setText(text);
		FadeTransition fadeTransition = new FadeTransition(Duration.seconds(5), errorLabel);
		fadeTransition.setFromValue(1.0);
		fadeTransition.setToValue(0.0);
		fadeTransition.play();
	}
}