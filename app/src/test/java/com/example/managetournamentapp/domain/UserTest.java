package com.example.managetournamentapp.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class UserTest {
    private User user;
    private User user2;
    private User user3;
    private LocalDate date;
    private LocalDate date2;
    private Credentials credentials;
    private Credentials credentials2;

    /**
     *setup some initial variables before attempting each test
     */
    @Before
    public void setUp() throws Exception {
        date = LocalDate.parse("1994-05-03");
        date2 = LocalDate.parse("2000-01-04");
        credentials = new Credentials("Nondic", "123456");
        credentials2 = new Credentials("zaaachos", "123456");
        user = new User("Nondas", "Ioannou", "69xxxxxxx", "nondas@gmail.com", date, credentials);
        user2 = new User("Giorgos", "Zachariadis", "69xxxxxxx", "zacchos@gmail.com", date2, credentials2);
        user3 = new User();
    }

    /**
     * test the equality between to instances
     */
    @Test
    public void testEquals() {

        User userTest = new User("Nondas", "Ioannou", "69xxxxxxx", "nondas@gmail.com", date, credentials);
        Assert.assertEquals(userTest, user);
        Assert.assertEquals(user, user);

    }

    /**
     * test the inequality between to instances
     */
    @Test
    public void testNotEquals() {

        Assert.assertNotEquals(user2, user);
        Assert.assertNotEquals(user3, user);
        Assert.assertNotEquals(user2, user3);

    }

    /**
     * test the equality between to instances
     */
    @Test
    public void testPrinting() {

        User userTest = new User("Nondas", "Ioannou", "69xxxxxxx", "nondas@gmail.com", date, credentials);
        Assert.assertEquals(userTest.toString(), user.toString());

    }

    /**
     * test the name getter
     */
    @Test
    public void getNameTest() {
        Assert.assertEquals("Nondas", user.getName());
    }

    /**
     * test the surname getter
     */
    @Test
    public void getSurnameTest() {
        Assert.assertEquals("Zachariadis", user2.getSurname());
    }

    /**
     * test the phone number
     */
    @Test
    public void getPhoneNumber() {
        String phone = "69xxxxxxx";
        Assert.assertEquals(phone, user.getPhoneNumber());
    }

    /**
     * test the birth date getter
     */
    @Test
    public void getBirthdayTest() {
        LocalDate dateTest = LocalDate.parse("2000-01-04");
        Assert.assertEquals(dateTest, user2.getBirthDate());
    }

    /**
     * test the email getter
     */
    @Test
    public void getEmailTest() {
        String emailTest = "nondas@gmail.com";
        Assert.assertEquals(emailTest, user.getEmail());
    }

    /**
     * test the credentials getter
     */
    @Test
    public void getCredentialsTest() {
        Credentials credentialsTest = new Credentials("zaaachos", "123456");
        Assert.assertEquals(credentialsTest, user2.getCredentials());
    }

    /**
     * test name getters and setters
     */
    @Test
    public void testSetterGetterName() {

        String newName = "Christophoros";
        user.setName(newName);
        Assert.assertEquals(newName, user.getName());
    }

    /**
     * test the surname getters and setters
     */
    @Test
    public void testSetterGetterSurname() {

        String newSurname = "Trakas";
        user2.setSurname(newSurname);
        Assert.assertEquals(newSurname, user2.getSurname());

    }

    /**
     * test the phone number getters and setters
     */
    @Test
    public void testSetterGetterPhoneNumber() {

        String phoneNumber = "697xxxxxxx";
        user2.setPhoneNumber(phoneNumber);
        Assert.assertEquals(phoneNumber, user2.getPhoneNumber());

    }

    /**
     * test the email getters and setters
     */
    @Test
    public void testSetterGetterEmail() {

        String email = "christo@yahoo.gr";
        user3.setEmail(email);
        Assert.assertEquals(email, user3.getEmail());

    }

    /**
     * test the birth date getters and setters
     */
    @Test
    public void testSetterGetterBirthdate() {

        LocalDate birthDate = LocalDate.parse("1999-03-04");
        user.setBirthDate(birthDate);
        Assert.assertEquals(birthDate, user.getBirthDate());

    }

    /**
     * test the credentials getters and setters
     */
    @Test
    public void testSetterGetterCredentials() {

        Credentials cred = new Credentials("nikos!", "123789");
        user2.setCredentials(cred);
        Assert.assertEquals(cred, user2.getCredentials());

    }
}