package com.example.managetournamentapp.dao;


import com.example.managetournamentapp.domain.Credentials;
import com.example.managetournamentapp.domain.Player;
import com.example.managetournamentapp.domain.User;

import java.util.ArrayList;


/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */
public interface PlayerDAO {

    /**
     * Verifies user, checks if credentials are ok.
     *
     * @param credentials Credentials of User.
     * @return true if credentials are verified.
     */
    boolean verify(Credentials credentials);

    /**
     * Saves a specific player.
     *
     * @param entity Player to be saved.
     */
    void save(Player entity);

    /**
     * Finds a specific player.
     *
     * @param userName Player username.
     * @return Player with the specific username.
     */
    Player find(String userName);

    /**
     * Finds all players.
     *
     * @return All players.
     */
    ArrayList<Player> findAll();

    /**
     * Deletes a specific player.
     *
     * @param entity Player to be deleted.
     */
    void delete(Player entity);

    /**
     * Deletes all players.
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


}
