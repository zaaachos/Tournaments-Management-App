package com.example.managetournamentapp.view.Player.JoinedTeams;

import com.example.managetournamentapp.dao.LoggedInUser;
import com.example.managetournamentapp.dao.PlayerDAO;
import com.example.managetournamentapp.dao.TeamDAO;
import com.example.managetournamentapp.domain.Player;
import com.example.managetournamentapp.domain.Team;

import java.util.ArrayList;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class JoinedTeamsPresenter {
    private JoinedTeamsView view;
    private TeamDAO teamDAO;
    private Player player = null;
    private PlayerDAO playerDAO;
    private ArrayList<Team> results = new ArrayList<>();
    private LoggedInUser loggedInUser;

    /**
     * default constructor
     */
    public JoinedTeamsPresenter(){}

    /**
     * find the teams that the player has joined
     * @param playerUsername the username of the player
     */
    public void findJoinedTeams(String playerUsername){
        if (playerUsername==null)
            return;
        player = playerDAO.find(playerUsername);
        if( player == null )
            return;

        results.clear();
        results.addAll(player.getTeamsJoined());
    }

    /**
     * get the teams that the player has joined
     * @return the ArrayList of teams
     */
    public ArrayList<Team> getResults() {
        return results;
    }

    /**
     * show the page of a team creation
     */
    public void onAddTeam(){
        view.startAddTeam();
    }

    /**
     * set a new view
     * @param view the new view
     */
    public void setView(JoinedTeamsView view) {
        this.view = view;
    }

    /**
     * clear the view
     */
    public void clearView(){
        this.view = null;
    }

    /**
     * set the playerDAO
     * @param playerDAO the new PlayerDAO
     */
    public void setPlayerDAO(PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }

    /**
     * set the teamDAO
     * @param teamDAO the new TeamDAO
     */
    public void setTeamDAO(TeamDAO teamDAO) {
        this.teamDAO = teamDAO;
    }

    /**
     * set the logged in user
     * @param loggedInUser the logged in user
     */
    public void setLoggedInUser(LoggedInUser loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    /**
     * what happens when the homepage button is pressed
     */
    public void onHomePage(){
        view.backToHomePage();
    }


}
