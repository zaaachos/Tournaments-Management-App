package com.example.managetournamentapp.dao;

import com.example.managetournamentapp.domain.Game;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public interface GameDAO {

    /**
     * Saves a specific game.
     * @param entity Game to be saved.
     */
    void save(Game entity);

    /**
     * Find a specific game.
     * @param nameTeamA Name of first team
     * @param nameTeamB Name of second team
     * @param matchDate  Match Date
     * @return Game that meets these criterias.
     */
    Game find(String nameTeamA, String nameTeamB, LocalDate matchDate);

    /**
     * Finds all games.
     * @return All games.
     */
    ArrayList<Game> findAll();

    /**
     * Deletes a specific game.
     * @param entity Game to be deleted.
     */
    void delete(Game entity);

    /**
     * Deletes all games.
     */
    void deleteAll();

}
