package com.example.managetournamentapp.dao;

import com.example.managetournamentapp.domain.Team;
import com.example.managetournamentapp.domain.Tournament;

import java.util.ArrayList;


/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public interface TeamDAO {

    /**
     * Saves a specific team.
     *
     * @param entity Team to be saved.
     */
    void save(Team entity);

    /**
     * Finds a specific team
     *
     * @param teamName Team name.
     * @return Team with the specific name.
     */
    Team find(String teamName);

    /**
     * Finds all teams.
     *
     * @return All teams.
     */
    ArrayList<Team> findAll();

    /**
     * Deletes a specific team.
     *
     * @param entity Team to be deleted.
     */
    void delete(Team entity);

    /**
     * Deletes all teams
     */
    void deleteAll();

    /**
     * Find teams that are participating in the specified tournament.
     *
     * @param tournament Tournament in which we are searching for teams.
     * @return Teams that are participating.
     */
    public ArrayList<Team> findByTournament(Tournament tournament);

    /**
     * Checks if title is used by another team
     *
     * @param title title input
     * @param team  current team
     * @return true if title used by another team
     */
    boolean TitleIsUsed(String title, Team team);

}
