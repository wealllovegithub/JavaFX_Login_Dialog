package com.doughepi.main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * @author Piper Dougherty
 * @version 1.0
 *          <p>
 *          Manages the movement between the login view and the main view.
 */
class Manager
{
    private final Stage stage;

    /**
     * @param stage The scene created on start used in all JavaFX applications.
     */
    Manager(Stage stage)
    {
        this.stage = stage;
    }

    /**
     * When called invokes a change to the main view.
     *
     * @param sessionID The session id generated by the login controller.
     */
    void doLogin(String sessionID)
    {
        setScene_Main(sessionID);
    }

    /**
     * When called invokes a change to the login view.
     */
    void doLogout()
    {
        setScene_Login();
    }

    /**
     * Handles the change to the login view.
     */
    void setScene_Login()
    {
        URL url = getClass().getResource("login.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Image loginicon = new Image("img/loginicon.png");
        try
        {
            Parent root = fxmlLoader.load();
            root.getStylesheets().add("css/global.css");
            stage.getIcons().setAll(loginicon);
            stage.setTitle("Login");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            LoginController loginController = fxmlLoader.<LoginController>getController();
            loginController.initializeManager(this);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Handles the change to the main view.
     *
     * @param sessionID The session id generated by the login controller.
     */
    private void setScene_Main(String sessionID)
    {
        URL url = getClass().getResource("main.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);

        try
        {
            Parent root = fxmlLoader.load();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            MainController mainController = fxmlLoader.<MainController>getController();
            mainController.initializeSessionID(this, sessionID);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    void setScene_Register()
    {
        URL url = getClass().getResource("register.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Image registericon = new Image("img/registericon.png");
        try
        {
            Parent root = fxmlLoader.load();
            root.getStylesheets().add("css/global.css");
            root.getStylesheets().add("css/register.css");
            stage.setTitle("Register");
            stage.getIcons().setAll(registericon);
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            RegisterController registerController = fxmlLoader.<RegisterController>getController();
            registerController.initializeManager(this);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
