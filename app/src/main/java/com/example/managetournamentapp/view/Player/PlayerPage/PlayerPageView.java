package com.example.managetournamentapp.view.Player.PlayerPage;

import androidx.appcompat.app.AlertDialog;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public interface PlayerPageView {

    /**
     * when the "info" button is pressed
     * the player info activity starts
     * @param playerUsername the player's username
     */
    void toPlayerInfo(String playerUsername);

    /**
     * when the "teams" button is pressed
     * the joined teams activity starts
     * @param playerUsername the player's username
     */
    void toPlayerTeams(String playerUsername);

    /**
     * when the "invites" button is pressed
     * the invites activity starts
     * @param playerUsername the player's username
     */
    void toPlayerInvites(String playerUsername);

    /**
     * the invites can be seen only by the player
     * who owns the account
     */
    void changesOfAccess();

    /**
     * log out from the current account
     */
    void logOut();

    /**
     * display a popup
     * @param layout the layout of the popup
     * @param msg the message of the popup
     * @param btn1 the first button
     * @param btn2 the second button
     */
    void displayPopUpAction(int layout, String msg, int btn1, int btn2);

    /**
     * create a popup
     * @param layoutId the layout of the popup
     * @param msg the message of the popup
     * @param btn1 the first button
     * @param btn2 the second button
     * @return the AlertDialog that will be shown
     */
    AlertDialog showPopUp(int layoutId, String msg, int btn1, int btn2);

    /**
     * close the popup
     */
    void dismissPopUpAction();

    /**
     * confirm that the player wants to log out
     */
    void logOutConfirmation();

    /**
     * if the player doesn't want to log out
     */
    void noLogOut();

}
