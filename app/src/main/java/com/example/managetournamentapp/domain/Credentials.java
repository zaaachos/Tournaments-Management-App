package com.example.managetournamentapp.domain;


/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class Credentials {

    private String username, password;

    /**
     * Default constructor with empty parameters
     */
    public Credentials() {
        this.username = "";
        this.password = "";
    }

    /**
     * The constructor that we mainly use
     * @param username
     * @param password
     */
    public Credentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * get the username of the credentials
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * set the username of the credentials
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * get the password of the credentials
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * set the password of the credentials
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * the string representation of the credentials
     * @return the basic fields to string
     */
    @Override
    public String toString() {
        return "Credentials{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


    /**
     * @param other
     * @return true if this equals the other according to the basic fields
     */
    public boolean equals(Object other) {
        if (other == this)
            return true;
        if (other == null || getClass() != other.getClass())
            return false;

        boolean equal = false;
        if (other instanceof Credentials) {
            Credentials otherCred = (Credentials) other;
            if ((username.equals(otherCred.username) && otherCred.username != null)
                    && (password.equals(otherCred.password) && otherCred.password != null))
                equal = true;
        }
        return equal;
    }
}
