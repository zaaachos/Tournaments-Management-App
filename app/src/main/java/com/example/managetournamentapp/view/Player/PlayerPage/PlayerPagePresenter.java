package com.example.managetournamentapp.view.Player.PlayerPage;

import com.example.managetournamentapp.dao.LoggedInUser;
import com.example.managetournamentapp.dao.PlayerDAO;
import com.example.managetournamentapp.domain.Player;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class PlayerPagePresenter {
    private PlayerPageView view;
    private LoggedInUser loggedInUser;
    private Player player;
    private PlayerDAO playerDAO;

    /**
     * default constructor
     */
    public PlayerPagePresenter() { }

    /**
     * find the info of the player
     * @param playerUsername the username of the player
     */
    public void findPlayerInfo(String playerUsername) {
        if (playerUsername == null)
            return;
        player = playerDAO.find(playerUsername);
    }

    /**
     * the invites can be seen only by the player
     * who owns the account
     */
    public void findAccess(String playerUsername) {
        if (loggedInUser.getUser() != null)
            if (loggedInUser.getUser() instanceof Player)
                if ((loggedInUser.getUser()).equals(player))
                    return;
        view.changesOfAccess();
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
     *  get the player's name
     * @return the name of the player
     */
    public String getPlayerName() {
        if (player == null)
            return "";
        return player.getName();
    }

    /**
     * when the "account" button is pressed
     * the player info activity starts
     */
    public void onPlayerAccount() {
        view.toPlayerInfo(player.getCredentials().getUsername());
    }

    /**
     * when the "teams" button is pressed
     * the joined teams activity starts
     */
    public void onPlayerTeams() {
        view.toPlayerTeams(player.getCredentials().getUsername());
    }

    /**
     * when the "invites" button is pressed
     * the invites activity starts
     */
    public void onPlayerInvites() {
        view.toPlayerInvites(player.getCredentials().getUsername());
    }

    /**
     * log out from the current account
     */
    public void onLogOut() {
        view.logOutConfirmation();
    }

    /**
     * if the player doesn't want to log out
     */
    public void onNoLogOut() {
        view.noLogOut();
    }

    /**
     * log out the player
     */
    public void onYesLogOut() {
        loggedInUser.clear();
        view.logOut();
    }

    /**
     * set a new view
     * @param view the new view
     */
    public void setView(PlayerPageView view) {
        this.view = view;
    }

    /**
     * clear the view
     */
    public void clearView() {
        this.view = null;
    }


}
