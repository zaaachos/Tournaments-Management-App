package com.example.managetournamentapp.dao;

import com.example.managetournamentapp.domain.User;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */
public interface LoggedInUser {

    /**
     * Sets the current user to the logged in user.
     * @param user User to be set
     */
    void setUser(User user);

    /**
     * User to e returned.
     * @return The user.
     */
    User getUser();

    /**
     * Sets user to null.
     */
    void clear();

}
