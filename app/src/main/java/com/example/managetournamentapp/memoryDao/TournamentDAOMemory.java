package com.example.managetournamentapp.memoryDao;

import com.example.managetournamentapp.dao.TournamentDAO;
import com.example.managetournamentapp.domain.Organizer;
import com.example.managetournamentapp.domain.Tournament;

import java.util.ArrayList;


/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class TournamentDAOMemory implements TournamentDAO {
    protected static ArrayList<Tournament> entities = new ArrayList<>();

    /**
     * Saves a specific tournament.
     *
     * @param entity Tournament to be saved.
     */
    @Override
    public void save(Tournament entity) {
        entities.add(entity);
    }

    /**
     * Deletes a specific tournament.
     *
     * @param entity
     */
    @Override
    public void delete(Tournament entity) {
        entities.remove(entity);
    }

    /**
     * Deletes all tournaments.
     */
    @Override
    public void deleteAll() {
        entities = new ArrayList<>();
    }

    /**
     * Finds a specific tournament.
     *
     * @param tournamentName Tournament name.
     * @return Tournament with the specific name.
     */
    @Override
    public Tournament find(String tournamentName) {
        for (Tournament t : entities) {
            if (t.getTitle().equals(tournamentName))
                return t;
        }
        return null;
    }

    /**
     * Finds all tournaments.
     *
     * @return All tournaments.
     */
    @Override
    public ArrayList<Tournament> findAll() {
        ArrayList<Tournament> result = new ArrayList<>();
        result.addAll(entities);
        return result;
    }

    /**
     * @param organizer Organizer that owns the specific tournament
     * @return Tournaments of the specified organizer
     */
    @Override
    public ArrayList<Tournament> findByOrganizer(Organizer organizer) {
        if (organizer == null)
            return new ArrayList<Tournament>();

        return organizer.getTournaments();
    }

    /**
     * Checks if title is used by another tournament
     *
     * @param title title input
     * @param tournament current tournament
     * @return true if title used by another tournament
     */
    public boolean TitleIsUsed(String title, Tournament tournament) {
        Tournament existingTournament = find(title);
        return !(existingTournament == null || existingTournament.equals(tournament));
    }

}
