package com.doughepi.main;/**
 * Created by dough on 12/20/2015.
 * The main class, starts the program.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application
{
	//Main method not used in JavaFX applications, only as a last resort.
	public static void main(String[] args)
	{
		launch(args);
	}

	//Start method called on startup of JavaFX application.
	@Override
	public void start(Stage primaryStage)
	{
		//Create a new scene, with an anonymous StackPane.
		Scene scene = new Scene(new StackPane());

		//On startup, set the scene to show the login dialog.
		Manager manager = new Manager(scene);
		manager.setScene_Login();

		//Finish startup, show the stage.
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
