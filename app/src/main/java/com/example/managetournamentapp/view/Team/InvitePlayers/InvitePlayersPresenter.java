package com.example.managetournamentapp.view.Team.InvitePlayers;

import com.example.managetournamentapp.dao.PlayerDAO;
import com.example.managetournamentapp.dao.TeamDAO;
import com.example.managetournamentapp.domain.Invitation;
import com.example.managetournamentapp.domain.Player;
import com.example.managetournamentapp.domain.Team;
import com.example.managetournamentapp.domain.User;
import com.example.managetournamentapp.memoryDao.MemoryLoggedInUser;

import java.util.ArrayList;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class InvitePlayersPresenter {
    private InvitePlayersView view;
    private PlayerDAO playerDAO;
    private TeamDAO teamDAO;
    private String teamName;
    private ArrayList<Player> results = new ArrayList<>();

    /**
     * default constructor
     */
    public InvitePlayersPresenter() {
    }

    /**
     * find the players that the team can invite
     * @param teamName the name of the team
     */
    public void findPlayers(String teamName) {
        this.teamName = teamName;
        results.clear();
        ArrayList<Player> allPlayers = playerDAO.findAll();
        Team team = teamDAO.find(teamName);
        for (Player player : allPlayers) {
            boolean alreadyInvitedForTheTeam = player.getInvitesReceived().contains(new Invitation(team));
            if (player.canJoin(team) && !alreadyInvitedForTheTeam)
                results.add(player);
        }
    }

    /**
     * get the players that the team can invite
     * @return the ArrayList of players
     */
    public ArrayList<Player> getResults() {
        return results;
    }

    /**
     * show the player's page
     * @param p the player
     */
    public void onPlayerAccountSelected(Player p) {
        view.startPlayerPage(p);
    }

    /**
     * invite a new player
     * @param teamName the name of the team
     * @param p the player
     */
    public void inviteNewPlayer(String teamName, Player p) {
        Team team = teamDAO.find(teamName);
        playerDAO.find(p.getCredentials().getUsername()).addInvite(new Invitation(team));
    }

    /**
     * set a new view
     * @param view the new view
     */
    public void setView(InvitePlayersView view) {
        this.view = view;
    }

    /**
     * clear the view
     */
    public void clearView() {
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
     * @param layout the layout of the popup
     * @param msg the message of the popup
     * @param btn1 the first button
     * @param btn2 the second button
     * @param invited true if the second version of the popup will be shown
     */
    public void displayPopAction(int layout, String msg, int btn1, int btn2, boolean invited) {
        view.displayPopUpAction(layout, msg, btn1, btn2, invited);
    }

    /**
     * close the popup
     */
    public void closePopAction() {
        view.dismissPopUpAction();
    }

    /**
     * refresh the current activity
     */
    public void restartActivity() {
        view.restartActivity();
    }

    /**
     * what happens when the homepage button is pressed
     */
    public void onHomePage(){
        User user = new MemoryLoggedInUser().getUser();
        view.backToHomePage(user.getCredentials().getUsername());

    }
}
