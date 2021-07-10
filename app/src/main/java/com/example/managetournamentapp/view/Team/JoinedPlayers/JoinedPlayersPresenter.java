package com.example.managetournamentapp.view.Team.JoinedPlayers;

import com.example.managetournamentapp.dao.LoggedInUser;
import com.example.managetournamentapp.dao.PlayerDAO;
import com.example.managetournamentapp.dao.TeamDAO;
import com.example.managetournamentapp.domain.Organizer;
import com.example.managetournamentapp.domain.Participation;
import com.example.managetournamentapp.domain.Player;
import com.example.managetournamentapp.domain.Team;
import com.example.managetournamentapp.domain.User;

import java.util.ArrayList;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class JoinedPlayersPresenter {

    private JoinedPlayersView view;
    private PlayerDAO playerDAO;
    private TeamDAO teamDAO;
    private String teamName;
    private ArrayList<Player> results = new ArrayList<>();
    private LoggedInUser loggedInUser;

    /**
     * default constructor
     */
    public JoinedPlayersPresenter() { }

    /**
     * find the players that have joined the team
     * @param teamName the name of the team
     */
    public void findPlayers(String teamName) {
        this.teamName = teamName;
        results.clear();
        results = teamDAO.find(teamName).getPlayers();
    }

    /**
     * remove a player of the team, if there are no arranged participations
     * @param teamName the name of the team
     * @param player the player
     */
    public void removePlayer(String teamName, Player player) {
        Team team = teamDAO.find(teamName);

        for (Participation part : team.getParticipations()){
            if (!part.isPast()){
                view.showToast("CAN'T DELETE : THIS TEAM HAS ARRANGED PARTICIPATIONS");
                return;
            }
        }
        results.clear();
        team.removePlayer(player);
        results = teamDAO.find(teamName).getPlayers();
    }

    /**
     * show the invite and delete buttons only to the captain
     */
    public void findAccess() {
        Team team = teamDAO.find(teamName);
        boolean captain = false;
        boolean player = false;
        if (loggedInUser.getUser() != null) {
            if (loggedInUser.getUser() instanceof Player) {
                player = true;
                if ((loggedInUser.getUser()).equals(team.getCaptain())) {
                    captain = true;
                }

            }
        }
        view.changesOfAccess(captain, player);
    }


    /**
     * get the players that have joined the team
     * @return the ArrayList of players
     */
    public ArrayList<Player> getResults() {
        return results;
    }

    /**
     * set the logged in user
     * @param loggedInUser the logged in user
     */
    public void setLoggedInUser(LoggedInUser loggedInUser) {
        this.loggedInUser = loggedInUser;
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
     * set a new view
     * @param view the new view
     */
    public void setView(JoinedPlayersView view) {
        this.view = view;
    }

    /**
     * clear the view
     */
    public void clearView() {
        this.view = null;
    }

    /**
     * what happens when the homepage button is pressed
     */
    public void onHomePage(){
        User user = loggedInUser.getUser();
        if (user == null){
            view.backToHomePage(true, false,"");
        }
        else if (user instanceof Player){
            view.backToHomePage(false,true,user.getCredentials().getUsername());
        }
        else{
            view.backToHomePage(false,false,((Organizer)user).getTitle());
        }
    }


}
