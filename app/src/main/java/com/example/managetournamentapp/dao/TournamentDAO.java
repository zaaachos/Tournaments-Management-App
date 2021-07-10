package com.example.managetournamentapp.dao;

import com.example.managetournamentapp.domain.Organizer;
import com.example.managetournamentapp.domain.Tournament;

import java.util.ArrayList;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public interface TournamentDAO {

    /**
     * Saves a specific tournament.
     *
     * @param entity Tournament to be saved.
     */
    void save(Tournament entity);

    /**
     * Finds a specific tournament.
     *
     * @param tournamentName Tournament name.
     * @return Tournament with the specific name.
     */
    Tournament find(String tournamentName);

    /**
     * Finds all tournaments.
     *
     * @return All tournaments.
     */
    ArrayList<Tournament> findAll();

    /**
     * @param organizer Organizer that owns the specific tournament
     * @return Tournaments of the specified organizer
     */
    ArrayList<Tournament> findByOrganizer(Organizer organizer);

    /**
     * Deletes a specific tournament.
     *
     * @param entity
     */
    void delete(Tournament entity);

    /**
     * Deletes all tournaments.
     */
    void deleteAll();

    /**
     * Checks if title is used by another tournament
     *
     * @param title title input
     * @param tournament current tournament
     * @return true if title used by another tournament
     */
    boolean TitleIsUsed(String title, Tournament tournament);


}
