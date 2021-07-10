package com.example.managetournamentapp.memoryDao;

import com.example.managetournamentapp.dao.TeamDAO;
import com.example.managetournamentapp.domain.Participation;
import com.example.managetournamentapp.domain.Team;
import com.example.managetournamentapp.domain.Tournament;

import java.util.ArrayList;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class TeamDAOMemory implements TeamDAO {
    protected static ArrayList<Team> entities = new ArrayList<>();

    /**
     * Saves a specific team.
     *
     * @param entity Team to be saved.
     */
    @Override
    public void save(Team entity) {
        entities.add(entity);
    }

    /**
     * Deletes a specific team.
     *
     * @param entity Team to be deleted.
     */
    @Override
    public void delete(Team entity) {
        entities.remove(entity);
    }

    /**
     * Deletes all teams
     */
    @Override
    public void deleteAll() {
        entities = new ArrayList<>();
    }

    @Override
    public Team find(String teamName) {
        for (Team t : entities) {
            if (t.getName().equals(teamName))
                return t;
        }
        return null;
    }

    /**
     * Finds all teams.
     *
     * @return All teams.
     */
    @Override
    public ArrayList<Team> findAll() {
        ArrayList<Team> result = new ArrayList<>();
        result.addAll(entities);
        return result;
    }

    /**
     * Find teams that are participating in the specified tournament.
     *
     * @param tournament Tournament in which we are searching for teams.
     * @return Teams that are participating.
     */
    @Override
    public ArrayList<Team> findByTournament(Tournament tournament) {
        ArrayList<Team> results = new ArrayList<>();
        for (Team t : entities) {
            for (Participation p : t.getParticipations()) {
                if (p.getTournament().getTitle().equals(tournament))
                    results.add(t);
            }
        }
        return results;
    }

    /**
     * Checks if title is used by another team
     *
     * @param title title input
     * @param team current team
     * @return true if title used by another team
     */
    public boolean TitleIsUsed(String title, Team team) {
        Team existingTeam = find(title);
        return !(existingTeam == null || existingTeam.equals(team));
    }

}
