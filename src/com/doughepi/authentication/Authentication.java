package com.doughepi.authentication;

import com.doughepi.database.UserManager;
import com.doughepi.exceptions.UsernameTakenException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

/**
 * @author Piper Dougherty
 * @version 1.1
 */
public class Authentication
{
    /**
     * Constants for algorithmic encryption.
     */
    private static final String SHA1_PRNG = "SHA1PRNG";

    /**
     * Constants for algorithmic encryption.
     */
    private static final String PBKDF2_WITH_HMAC_SHA1 = "PBKDF2WithHmacSHA1";

    /**
     * Encrypt and add password to the database only if password is not already taken.
     *
     * @param username The preferred username.
     * @param password The plaintext password to be encoded.
     */
    public void addUser(String username, String password) throws UsernameTakenException
    {
        UserManager userManager = new UserManager();

        if (!userManager.checkUserExists(username))
        {
            byte[] salt;
            byte[] encryptedPassword;

            salt = generateSalt();
            encryptedPassword = getEncryptedPassword(password, salt);
            userManager.add(username, encryptedPassword, salt);
        }
        else
        {
            throw new UsernameTakenException();
        }
    }

    /**
     * Used for authenticating a user, compares the users entered plaintext password by hashing with the same salt used
     * when the original password was generated. If they match, true is returned.
     *
     * @param attemptedPassword The password entered by the user to be checked.
     * @param encryptedPassword The actual hashed password corresponding to the username entered.
     * @param salt              The salt used when the password was hashed. Salts are unique to each user.
     * @return True or false depending on if the user entered password matches the correct hashed password on record.
     */
    public boolean authenticate(String attemptedPassword, byte[] encryptedPassword, byte[] salt)
    {
        byte[] encryptedAttemtpedPassword;
        encryptedAttemtpedPassword = getEncryptedPassword(attemptedPassword, salt);
        return Arrays.equals(encryptedPassword, encryptedAttemtpedPassword);
    }

    /**
     * Generates a hashed password using a salt and a plaintext password entered by the user.
     *
     * @param password The password entered by the user to be encrypted.
     * @param salt     The salt to be used. Use of generateSalt() is recommended.
     * @return The hashed password.
     */
    private byte[] getEncryptedPassword(String password, byte[] salt)
    {
        byte[] encryptedPassword = null;

        int derivedKeyLength = 160;
        int iterations = 20000;

        KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt, iterations, derivedKeyLength);
        SecretKeyFactory secretKeyFactory;

        try
        {
            secretKeyFactory = SecretKeyFactory.getInstance(PBKDF2_WITH_HMAC_SHA1);
            encryptedPassword = secretKeyFactory.generateSecret(keySpec).getEncoded();
        }
        catch (NoSuchAlgorithmException | InvalidKeySpecException e)
        {
            e.printStackTrace();
        }
        return encryptedPassword;
    }


    /**
     * Generates a random byte array of size 8, to be used when hashing a user's password. Helpful in preventing
     * rainbow
     * table attacks.
     *
     * @return A randomly generated salt.
     */
    private byte[] generateSalt()
    {
        SecureRandom secureRandom = new SecureRandom();

        try
        {
            secureRandom = SecureRandom.getInstance(SHA1_PRNG);
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }

        byte[] salt = new byte[8];

        secureRandom.nextBytes(salt);

        return salt;
    }
}
