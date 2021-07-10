package com.example.managetournamentapp.memoryDao;

import com.example.managetournamentapp.dao.GameDAO;
import com.example.managetournamentapp.domain.Game;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class GameDAOMemory implements GameDAO {
    protected static ArrayList<Game> entities = new ArrayList<>();

    /**
     * Saves a specific game.
     * @param entity Game to be saved.
     */
    @Override
    public void save(Game entity) {
        entities.add(entity);
    }


    /**
     * Deletes a specific game.
     * @param entity Game to be deleted.
     */
    @Override
    public void delete(Game entity) {
        entities.remove(entity);
    }

    /**
     * Deletes all games.
     */
    @Override
    public void deleteAll() {
        entities = new ArrayList<>();
    }

    /**
     * Find a specific game.
     * @param nameTeamA Name of first team
     * @param nameTeamB Name of second team
     * @param matchDate  Match Date
     * @return Game that meets these criterias.
     */
    @Override
    public Game find(String nameTeamA, String nameTeamB, LocalDate matchDate) {
        return null;
    }

    /**
     * Finds all games.
     * @return All games.
     */
    @Override
    public ArrayList<Game> findAll() {
        ArrayList<Game> result = new ArrayList<>();
        result.addAll(entities);
        return result;
    }
}
