package com.doughepi.main;

/**
 * @author Piper Dougherty
 * @version 1.0
 * <p>
 * The main class, starts the program.
 */

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The main class, the entry point for this application.
 */
public class Main extends Application {
    /**
     * The main method is only called as a last resort in
     * JavaFX applications.
     *
     * @param args Java program arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * The start method is inherited from Application and is
     * called on startup in JavaFX applications.
     *
     * @param primaryStage The first stage to be displayed.
     */
    @Override
    public void start(Stage primaryStage) {
        Manager manager = new Manager(primaryStage);
        manager.setScene_Login();
        primaryStage.show();
    }
}
