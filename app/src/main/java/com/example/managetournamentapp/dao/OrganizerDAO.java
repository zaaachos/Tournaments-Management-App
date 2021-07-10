package com.example.managetournamentapp.dao;

import com.example.managetournamentapp.domain.Credentials;
import com.example.managetournamentapp.domain.Organizer;
import com.example.managetournamentapp.domain.Player;
import com.example.managetournamentapp.domain.User;

import java.util.ArrayList;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public interface OrganizerDAO {
    /**
     * Verifies user, checks if credentials are ok.
     *
     * @param credentials Credentials of User.
     * @return true if credentials are verified.
     */
    boolean verify(Credentials credentials);

    /**
     * Saves a specific organizer.
     *
     * @param entity Organizer to be saved.
     */
    void save(Organizer entity);

    /**
     * Finds a specific organizer.
     *
     * @param title Title of the organizer.
     * @return Organizer with the title specified.
     */
    Organizer findByTitle(String title);

    /**
     * Finds a specific organizer.
     *
     * @param credentials credentials of the organizer.
     * @return Organizer with the credentials specified.
     */
    Organizer findByCredentials(Credentials credentials);

    /**
     * Finds all organizers.
     *
     * @return All organizers.
     */
    ArrayList<Organizer> findAll();

    /**
     * Deletes a specific organizer.
     *
     * @param entity Organizer to be deleted.
     */
    void delete(Organizer entity);

    /**
     * Deletes all organizers.
     */
    void deleteAll();

    /**
     * Check if the user who tries to login exists in DAO.
     *
     * @param credentials User input credentials
     * @return true if User exists in DAO
     */
    boolean exist(Credentials credentials);

    /**
     * Checks if credentials is used by another user in registration
     * @param credentials User input credentials
     * @param user if user is current
     * @return true if credentials used by another user
     */
    boolean isUsedByAnother(Credentials credentials, User user);

    /**
     * Checks if title is used by another user in registration
     * @param title title input
     * @param user if user is current
     * @return true if credentials used by another user
     */
    boolean TitleUsedByAnother(String title, User user);


}
