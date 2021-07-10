package com.example.managetournamentapp.view.Team.JoinedPlayers;

import androidx.appcompat.app.AlertDialog;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public interface JoinedPlayersView {

    /**
     * show the invite button only to the captain
     * @param flag true if the logged in user is the captain of the team
     * @param flag2 true if the player has joined the team
     */
    void changesOfAccess(boolean flag, boolean flag2);

    /**
     * @param layoutId the layout of the popup
     * @param msg the message of the popup
     * @param btn1 the first button
     * @param btn2 the second button
     * @return the AlertDialog that will be shown
     */
    AlertDialog showPopUp(int layoutId, String msg, int btn1, int btn2);

    /**
     * show the info page of the player
     */
    void startPlayerInfo();

    /**
     * show the page where invites are sent
     */
    void startInvitePlayerPage();

    /**
     * show the possible actions popup
     * @param layout the layout of the popup
     * @param msg the message of the popup
     * @param btn1 the first button
     * @param btn2 the second button
     */
    void displayPopUpAction(int layout, String msg, int btn1, int btn2);

    /**
     * show the deletion popup
     * @param layout the layout of the popup
     * @param msg the message of the popup
     * @param btn1 the first button
     * @param btn2 the second button
     */
    void displayPopUpDeletion(int layout, String msg, int btn1, int btn2);

    /**
     * close the possible actions popup
     */
    void dismissPopUpAction();

    /**
     * close the deletion popup
     */
    void dismissPopUpDeletion();

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
     * @param noLogin boolean parameter, if true the user has not logged in
     * @param isPlayer boolean parameter,if true the user is a player
     * @param name name of the player or title of the organizer
     */
    void backToHomePage(boolean noLogin, boolean isPlayer, String name);

    /**
     * show a toast on the screen
     * @param txt the message of the toast
     */
    void showToast(String txt);
}
