package com.example.managetournamentapp.dao;

import com.example.managetournamentapp.domain.Round;


/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public interface RoundDAO {

    /**
     * Saves a specific round.
     * @param entity Round to be saved.
     */
    void save(Round entity);

    /**
     * Deletes a specific round.
     * @param entity
     */
    void delete(Round entity);

    /**
     * Deletes all rounds.
     */
    void deleteAll();
}
