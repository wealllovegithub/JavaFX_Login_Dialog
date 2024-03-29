package com.doughepi.main;

import com.doughepi.authentication.Authentication;
import com.doughepi.database.UserManager;
import com.sun.istack.internal.Nullable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * @author Piper Dougherty
 * @version 1.0
 */
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
    private TextField pass;
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;

    /**
     * When called, a sessionID generation attempt is made. If successful, a switch to the main view is made.
     *
     * @param manager The manager object.
     */
    void initializeManager(final Manager manager)
    {
        loginButton.setOnAction(event ->
                                {
                                    String sessionID = checkCredentials();

                                    if (sessionID != null)
                                    {
                                        manager.doLogin(sessionID);
                                    }
                                });

        registerButton.setOnAction(event -> manager.setScene_Register());
    }

    /**
     * When called, checks the entered credentials.
     *
     * @return A session id is generated if login credentials pass. If not, null is returned.
     */
    @Nullable
    private String checkCredentials()
    {
        String sessionID = null;

        String username = user.getText();
        String password = pass.getText();

        UserManager userManager = new UserManager();
        byte[] userPassword = userManager.getUserPassword(username);
        byte[] userSalt = userManager.getUserSalt(username);

        Authentication authentication = new Authentication();
        if (userManager.checkUserExists(username) && authentication.authenticate(password, userPassword, userSalt))
        {
            sessionID = generateSessionID();
        }
        return sessionID;
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