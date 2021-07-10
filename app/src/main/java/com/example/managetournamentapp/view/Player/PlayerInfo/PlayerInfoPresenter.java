package com.example.managetournamentapp.view.Player.PlayerInfo;

import com.example.managetournamentapp.R;
import com.example.managetournamentapp.dao.LoggedInUser;
import com.example.managetournamentapp.dao.PlayerDAO;
import com.example.managetournamentapp.domain.Player;
import com.example.managetournamentapp.memoryDao.MemoryLoggedInUser;

import java.time.format.DateTimeFormatter;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class PlayerInfoPresenter {
    private PlayerInfoView view;
    private Player player;
    private PlayerDAO playerDAO;
    private LoggedInUser loggedInUser;

    /**
     * default constructor
     */
    public PlayerInfoPresenter() { }

    /**
     * find the info of the player
     * @param playerUsername the username of the player
     */
    public void findPlayerInfo(String playerUsername) {
        if (playerUsername == null)
            return;
        player = playerDAO.find(playerUsername);
        if (player == null)
            return;

        view.setUsername(player.getCredentials().getUsername());
        view.setPassword(player.getCredentials().getPassword());
        view.setName(player.getName());
        view.setSurname(player.getSurname());
        view.setPhone(player.getPhoneNumber());
        view.setEmail(player.getEmail());
        view.setLocation(player.getLocation());
        view.setBirthDate(player.getBirthDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")).replace("-", "/"));
    }

    /**
     * the account can be edited or deleted only by the player
     * how owns it
     */
    public void findAccess() {
        if (loggedInUser.getUser() != null)
            if (loggedInUser.getUser() instanceof Player)
                if (((Player) loggedInUser.getUser()).equals(player))
                    return;
        view.changesOfAccess();
    }

    /**
     * when the player decides to edit his account
     * the register player activity is started
     */
    public void onEditPlayer() {
        view.startEditPlayer();
    }


    /**
     * when the player decides to delete his account
     */
    public void onDeletePlayer() {
        if (player.getTeamsJoined().size() > 0) {
            view.showCantDelete();
            return;
        }
        displayPopUp(R.layout.player_delete_popup, "Do you really want to delete your account?", R.id.no_delete, R.id.yes_delete);

    }

    /**
     * set the playerDAO
     * @param playerDAO the new PlayerDAO
     */
    public void setPlayerDAO(PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }

    /**
     * set the logged in user
     * @param loggedInUser the logged in user
     */
    public void setLoggedInUser(LoggedInUser loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    /**
     * set a new view
     * @param view the new view
     */
    public void setView(PlayerInfoView view) {
        this.view = view;
    }

    /**
     * clear the view
     */
    public void clearView() {
        this.view = null;
    }

    /**
     * when the player doesn't verify the deletion
     */
    public void onNoDeletePlayer() {
        view.dismissPopUp();
    }

    /**
     * when the player verifies the deletion
     */
    public void onYesDeletePlayer() {
        playerDAO.delete(player);
        (new MemoryLoggedInUser()).clear();
        view.dismissPopUp();
        view.startDeletePlayer();
    }

    /**
     * close the deletion popup
     */
    public void displayPopUp(int layout, String msg, int btn1, int btn2) {
        view.displayPopUp(layout, msg, btn1, btn2);
    }

}
