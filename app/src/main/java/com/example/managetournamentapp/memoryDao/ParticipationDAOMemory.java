package com.example.managetournamentapp.memoryDao;

import com.example.managetournamentapp.dao.ParticipationDAO;
import com.example.managetournamentapp.domain.Participation;
import java.util.ArrayList;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class ParticipationDAOMemory implements ParticipationDAO {
    protected static ArrayList<Participation> entities = new ArrayList<>();

    /**
     * Saves a specific participation.
     * @param entity Participation to be saved.
     */
    @Override
    public void save(Participation entity) {
        entities.add(entity);
    }

    /**
     * Deletes a specific participation.
     * @param entity Participation to be deleted.
     */
    @Override
    public void delete(Participation entity) {
        entities.remove(entity);
    }

    /**
     * Deletes all participations.
     */
    @Override
    public void deleteAll() {
        entities = new ArrayList<>();
    }

    /**
     * Finds a specific participation.
     * @param teamName Name of team
     * @param tournamentName Name of tournament
     * @return Participation with the specific tournament and team
     */
    @Override
    public Participation find(String teamName, String tournamentName) {
        return null;
    }

    /**
     * Finds all participations.
     * @return All participations.
     */
    @Override
    public ArrayList<Participation> findAll() {
        ArrayList<Participation> result = new ArrayList<>();
        result.addAll(entities);
        return result;
    }
}
