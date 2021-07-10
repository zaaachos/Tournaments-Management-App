package com.example.managetournamentapp.view.Team.InvitePlayers;

import androidx.appcompat.app.AlertDialog;

import com.example.managetournamentapp.domain.Player;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public interface InvitePlayersView {

    /**
     * @param layoutId the layout of the popup
     * @param msg the message of the popup
     * @param btn1 the first button
     * @param btn2 the second button
     * @param flag true if the second version of the popup will be shown
     * @return the AlertDialog that will be shown
     */
    AlertDialog showPopUp(int layoutId, String msg, int btn1, int btn2, boolean flag);

    /**
     * show the page of the player
     * @param player the player
     */
    void startPlayerPage(Player player);

    /**
     * @param layout the layout of the popup
     * @param msg the message of the popup
     * @param btn1 the first button
     * @param btn2 the second button
     * @param invited true if the second version of the popup will be shown
     * @return the AlertDialog that will be shown
     */
    void displayPopUpAction(int layout, String msg, int btn1, int btn2, boolean invited);

    /**
     * close the popup
     */
    void dismissPopUpAction();

    /**
     * reset the popups of this activity
     */
    void resetPopUps();

    /**
     * refresh the current activity
     */
    void restartActivity();

    /**
     * what happens when the homepage button is pressed
     * @param string is the name of a player. or the title of an organizer
     */
    void backToHomePage(String string);
}
