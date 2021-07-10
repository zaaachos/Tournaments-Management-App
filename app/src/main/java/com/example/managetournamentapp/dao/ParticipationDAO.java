package com.example.managetournamentapp.dao;

import com.example.managetournamentapp.domain.Participation;
import java.util.ArrayList;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public interface ParticipationDAO {

    /**
     * Saves a specific participation.
     * @param entity Participation to be saved.
     */
    void save(Participation entity);

    /**
     * Finds a specific participation.
     * @param teamName Name of team
     * @param tournamentName Name of tournament
     * @return Participation with the specific tournament and team
     */
    Participation find(String teamName, String tournamentName);

    /**
     * Finds all participations.
     * @return All participations.
     */
    ArrayList<Participation> findAll();

    /**
     * Deletes a specific participation.
     * @param entity Participation to be deleted.
     */
    void delete(Participation entity);

    /**
     * Deletes all participations.
     */
    void deleteAll();



}
