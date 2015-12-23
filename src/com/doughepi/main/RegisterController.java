package com.doughepi.main;

import com.doughepi.authentication.Authentication;
import com.doughepi.exceptions.UsernameTakenException;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;

/**
 * @author Piper Dougherty
 * @version 1.0
 *          <p>
 *          Several private fields are injected by FXML and represent the ids set for each node in the register.fxml
 *          file.
 */
public class RegisterController
{
	/**
	 * JavaFX injected fields.
	 */
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

	/**
	 * Initializes event handlers.
	 *
	 * @param manager The manager class that controls movement between views.
	 */
	public void initializeManager(final Manager manager)
	{
		cancelButton.setOnAction(event -> manager.setScene_Login());
		registerButton.setOnAction(event ->
		{
			if (!user.getText().isEmpty()) //TODO username requirement check.
			{
				registerUser();
			}
			else
			{
				setLabel("Please enter username...");
			}
		});
	}

	/**
	 * Invokes the addition of a new user to the database.
	 */
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

	/**
	 * Sets the text displayed by the Label errorLabel with and additional fade out effect.
	 *
	 * @param text The text to be displayed by the label.
	 */
	private void setLabel(String text)
	{
		errorLabel.setText(text);
		FadeTransition fadeTransition = new FadeTransition(Duration.seconds(5), errorLabel);
		fadeTransition.setFromValue(1.0);
		fadeTransition.setToValue(0.0);
		fadeTransition.play();
	}
}