package com.doughepi.main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.URL;

/**
 * Created by dough on 12/20/2015.
 * Manages the movement between the login view and the main view.
 */
public class Manager
{
	private Scene scene;

	public Manager(Scene scene)
	{
		this.scene = scene;
	}

	public void doLogin(String sessionID)
	{
		setScene_Main(sessionID);
	}

	//Called when user requests to log out.
	public void doLogout()
	{
		setScene_Login();
	}

	//Called when doLogout is called, sets the scene's root to the login view.
	public void setScene_Login()
	{
		URL url = getClass().getResource("login.fxml");
		FXMLLoader fxmlLoader = new FXMLLoader(url);

		try
		{
			Parent root = fxmlLoader.load();
			scene.setRoot(root);
			LoginController loginController = fxmlLoader.<LoginController>getController();
			loginController.initializeManager(this);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	//Called when user successfully logs in, sets the scene's root to the main view.
	public void setScene_Main(String sessionID)
	{
		URL url = getClass().getResource("main.fxml");
		FXMLLoader fxmlLoader = new FXMLLoader(url);

		try
		{
			Parent root = fxmlLoader.load();
			scene.setRoot(root);
			MainController mainController = fxmlLoader.<MainController>getController();
			mainController.initializeSessionID(this, sessionID);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
