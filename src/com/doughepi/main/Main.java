package com.doughepi.main;

/**
 * Created by dough on 12/20/2015.
 * <p>
 * The main class, starts the program.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * The main class, the entry point for this application.
 */
public class Main extends Application
{
	/**
	 * The main method is only called as a last resort in
	 * JavaFX applications.
	 *
	 * @param args Java program arguments.
	 */
	public static void main(String[] args)
	{
		launch(args);
	}

	/**
	 * The start method is inherited from Application and is
	 * called on startup in JavaFX applications.
	 *
	 * @param primaryStage The first stage to be displayed.
	 */
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
