package com.example.managetournamentapp.memoryDao;

import com.example.managetournamentapp.dao.RoundDAO;
import com.example.managetournamentapp.domain.Round;

import java.util.ArrayList;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class RoundDAOMemory implements RoundDAO {
    protected static ArrayList<Round> entities = new ArrayList<>();

    /**
     * Saves a specific round.
     * @param entity Round to be saved.
     */
    @Override
    public void save(Round entity) {
        entities.add(entity);
    }

    /**
     * Deletes a specific round.
     * @param entity
     */
    @Override
    public void delete(Round entity) {
        entities.remove(entity);
    }

    /**
     * Deletes all rounds.
     */
    @Override
    public void deleteAll() {
        entities = new ArrayList<>();
    }
}
