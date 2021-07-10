package com.example.managetournamentapp.view.Player.ReceivedInvites;

import com.example.managetournamentapp.dao.LoggedInUser;
import com.example.managetournamentapp.dao.PlayerDAO;
import com.example.managetournamentapp.dao.TeamDAO;
import com.example.managetournamentapp.domain.Invitation;
import com.example.managetournamentapp.domain.Player;
import com.example.managetournamentapp.domain.Team;

import java.util.ArrayList;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class ReceivedInvitesPresenter {
    private ReceivedInvitesView view;
    private LoggedInUser loggedInUser;
    private Player player;
    private PlayerDAO playerDAO;
    private TeamDAO teamDAO;
    private ArrayList<Invitation> invites = new ArrayList<>();
    private ArrayList<Team> teamsInvites = new ArrayList<>();

    /**
     * default constructor
     */
    public ReceivedInvitesPresenter() {}

    /**
     * find the invites that the player has received
     * @param playerUsername the username of the player
     */
    public void findInvites(String playerUsername) {
        if (playerUsername == null)
            return;
        player = playerDAO.find(playerUsername);
        invites.clear();
        teamsInvites.clear();
        ArrayList<Invitation> allInvites = player.getInvitesReceived();
        for (Invitation invitation : allInvites) {
            System.out.println("Invitation: " + invitation.getPending());
            if (invitation.getPending())
                invites.add(invitation);
        }
        for (Invitation invitation : invites)
            teamsInvites.add(invitation.getTeam());
    }

    /**
     * set the logged in user
     * @param loggedInUser the logged in user
     */
    public void setLoggedInUser(LoggedInUser loggedInUser) {
        this.loggedInUser = loggedInUser;
    }


    public String getPlayerName() {
        if (player == null)
            return "";

        return player.getName();
    }

    /**
     * set a new view
     * @param view the new view
     */
    public void setView(ReceivedInvitesView view) {
        this.view = view;
    }

    /**
     * clear the view
     */
    public void clearView() {
        this.view = null;
    }

    /**
     * set the teamDAO
     * @param teamDAO the new TeamDAO
     */
    public void setTeamDAO(TeamDAO teamDAO) {
        this.teamDAO = teamDAO;
    }

    /**
     * set the playerDAO
     * @param playerDAO the new PlayerDAO
     */
    public void setPlayerDAO(PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }

    /**
     * get the invites that the player has received
     * @return the ArrayList of invites
     */
    public ArrayList<Invitation> getInvites() {
        return invites;
    }

    /**
     * decline an ivitation
     * @param invitation the invitation
     */
    public void declineInvitation(Invitation invitation) {
        player.replyToInvitation(invitation, false);
        player.removeInvite(invitation);
    }

    /**
     * accept an ivitation
     * @param invitation the invitation
     */
    public void acceptInvitation(Invitation invitation) {
        player.replyToInvitation(invitation, true);
        player.removeInvite(invitation);
    }

    /**
     * when the player wants to see the info of the team
     * that invited him
     */
    public void onTeamPageClick() {
        view.startTeamPage();
    }

    /**
     * what happens when the homepage button is pressed
     */
    public void onHomePage() {
        view.backToHomePage();
    }
}
