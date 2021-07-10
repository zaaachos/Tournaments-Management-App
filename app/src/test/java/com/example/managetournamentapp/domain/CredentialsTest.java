package com.example.managetournamentapp.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class CredentialsTest {
    private Credentials credentials;
    private Credentials credentials2;
    private Credentials credentials3;
    private Credentials credentials4;
    private Credentials credentials5;

    /**
     *setup some initial variables before attempting each test
     */
    @Before
    public void setUp() {
        credentials = new Credentials("Nondas", "123456");
        credentials2 = new Credentials("Giannis", "123456");
        credentials3 = new Credentials("Giannis", "GioGio");
        credentials4 = new Credentials("Nondas", "123456");
        credentials5 = new Credentials();
    }

    /**
     * test the equality between to instances
     */
    @Test
    public void testEquals() {

        Credentials credentials4 = new Credentials("Nondas", "123456");
        Assert.assertEquals(credentials4, credentials);
        Assert.assertEquals(credentials, credentials);
    }

    /**
     * test the inequality between to instances
     */
    @Test
    public void testNotEquals() {

        Assert.assertNotEquals(credentials2, credentials);
        Assert.assertNotEquals(credentials3, credentials);
        Assert.assertNotEquals(credentials5, credentials);
        Credentials credTest = null;
        Assert.assertNotEquals(credTest, credentials);

    }

    /**
     * test the toString method
     */
    @Test
    public void testPrinting() {
        Assert.assertEquals(credentials4.toString(), credentials.toString());
    }

    /**
     * test the username getter
     */
    @Test
    public void getUsernameTest() {
        Assert.assertEquals("Nondas", credentials.getUsername());
    }

    /**
     * test the password getter
     */
    @Test
    public void getPasswordTest() {
        Assert.assertEquals("123456", credentials.getPassword());
    }


    /**
     * test the username setter
     */
    @Test
    public void testSetterGetterUsername() {

        String newName = "Giorgos";
        credentials.setUsername(newName);
        Assert.assertEquals(newName, credentials.getUsername());

    }


    /**
     * test the password setter
     */
    @Test
    public void testSetterGetterPassword() {

        String newPassword = "456789";
        credentials.setPassword(newPassword);
        Assert.assertEquals(newPassword, credentials.getPassword());

    }
}