package com.example.managetournamentapp.memoryDao;

import com.example.managetournamentapp.dao.LoggedInUser;
import com.example.managetournamentapp.domain.User;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */
public class MemoryLoggedInUser implements LoggedInUser {
    private static User user = null;

    /**
     * Default Constructor
     */
    public MemoryLoggedInUser() {
    }

    /**
     * Sets the current user to the logged in user.
     * @param user User to be set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * User to e returned.
     * @return The user.
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user to null.
     */
    public void clear() {
        user = null;
    }

}