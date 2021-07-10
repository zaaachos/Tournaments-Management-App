package com.example.managetournamentapp.memoryDao;

import com.example.managetournamentapp.dao.GameDAO;
import com.example.managetournamentapp.dao.GroupDAO;
import com.example.managetournamentapp.dao.Initializer;
import com.example.managetournamentapp.dao.InvitationDAO;
import com.example.managetournamentapp.dao.OrganizerDAO;
import com.example.managetournamentapp.dao.ParticipationDAO;
import com.example.managetournamentapp.dao.PlayerDAO;
import com.example.managetournamentapp.dao.RoundDAO;
import com.example.managetournamentapp.dao.TeamDAO;
import com.example.managetournamentapp.dao.TournamentDAO;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class MemoryInitializer extends Initializer {

    /**
     *erase all the DAO objects
     */
    @Override
    protected void eraseData() {
        getGroupDAO().deleteAll();
        getGameDAO().deleteAll();
        getInvitationDAO().deleteAll();
        getOrganizerDAO().deleteAll();
        getParticipationDAO().deleteAll();
        getPlayerDAO().deleteAll();
        getRoundDAO().deleteAll();
        getTeamDAO().deleteAll();
        getTournamentDAO().deleteAll();
    }

    /**
     * get the game DAO
     * @return game DAO
     */
    @Override
    public GameDAO getGameDAO() {
        return new GameDAOMemory();
    }

    /**
     * get the group DAO
     * @return group DAO
     */
    @Override
    public GroupDAO getGroupDAO() {
        return new GroupDAOMemory();
    }

    /**
     * get the invitation DAO
     * @return invitation DAO
     */
    @Override
    public InvitationDAO getInvitationDAO() {
        return new InvitationDAOMemory();
    }

    /**
     * get the organizer DAO
     * @return organizer DAO
     */
    @Override
    public OrganizerDAO getOrganizerDAO() {
        return new OrganizerDAOMemory();
    }

    /**
     * get the participation DAO
     * @return participation DAO
     */
    @Override
    public ParticipationDAO getParticipationDAO() {
        return new ParticipationDAOMemory();
    }

    /**
     * get the player DAO
     * @return player DAO
     */
    @Override
    public PlayerDAO getPlayerDAO() {
        return new PlayerDAOMemory();
    }

    /**
     * get the round DAO
     * @return round DAO
     */
    @Override
    public RoundDAO getRoundDAO() {
        return new RoundDAOMemory();
    }

    /**
     * get the team DAO
     * @return team DAO
     */
    @Override
    public TeamDAO getTeamDAO() {  return new TeamDAOMemory(); }

    /**
     * get the tournament DAO
     * @return the TournamentDAO
     */
    @Override
    public TournamentDAO getTournamentDAO() {
        return new TournamentDAOMemory();
    }
}
