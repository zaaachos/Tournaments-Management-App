package com.example.managetournamentapp.domain;

import java.time.LocalDate;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class User {

    private String name, surname, phoneNumber, email;
    private LocalDate birthDate;
    private Credentials credentials;

    /**
     * the default constructor
     */
    public User() {
        this.name = "";
        this.surname = "";
        this.phoneNumber = "";
        this.email = "";
        this.birthDate = null;
        this.credentials = null;
    }

    /**
     * the full constructor
     * @param name of the player
     * @param surname of the player
     * @param phoneNumber of the player
     * @param email of the player
     * @param birthDate of the player
     * @param credentials of the player
     */
    public User(String name, String surname, String phoneNumber, String email, LocalDate birthDate, Credentials credentials) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.birthDate = birthDate;
        this.credentials = credentials;
    }

    /**
     * get the name of the user
     * @return the user's name
     */
    public String getName() {
        return name;
    }

    /**
     * set a new name for the player
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get the surname of the user
     * @return the user's surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * set a new surname for the player
     * @param surname the new surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * get the phone number of the user
     * @return the user's phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * set a new phone number for the player
     * @param phoneNumber the new phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * get the email of the user
     * @return the user's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * set a new email for the player
     * @param email the new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * get the birth date of the user
     * @return the user's birth date as a LocalDate object
     */
    public LocalDate getBirthDate() {
        return birthDate;
    }

    /**
     * set a new birth date for the player
     * @param birthDate the new birth date
     */
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * get the credentials of the user
     * @return the user's credentials
     */
    public Credentials getCredentials() {
        return credentials;
    }

    /**
     * set new credentials for the player
     * @param credentials the new credentials
     */
    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }


    /**
     * the string representation of the User
     * @return the basic info of the User to string
     */
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                ", credentials=" + credentials +
                '}';
    }

    /**
     *check if two users are equal
     * @param other is the other user
     * @return if this user is equal to the other user
     */
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null || getClass() != other.getClass())
            return false;
        boolean equal = false;
        if (other instanceof User) {
            User otherUser = (User) other;

            if ((name.equals(otherUser.name) && otherUser.name != null) && (surname.equals(otherUser.surname) && otherUser.surname != null)
                    && (phoneNumber.equals(otherUser.phoneNumber) && otherUser.phoneNumber != null)
                    && (birthDate.equals(otherUser.birthDate) && otherUser.birthDate != null)
                    && (credentials.equals(otherUser.credentials) && otherUser.credentials != null))
                equal = true;
        }
        return equal;
    }


}
