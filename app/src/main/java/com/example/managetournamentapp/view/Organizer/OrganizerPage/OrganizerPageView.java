package com.example.managetournamentapp.view.Organizer.OrganizerPage;

import androidx.appcompat.app.AlertDialog;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public interface OrganizerPageView {
    /**
     * Starts Organizer's Account Activity
     * when "Account" button is pressed
     */
    void toOrganizerAccount();

    /**
     * Starts organizer's tournaments activity
     * when "My tournaments" button is pressed
     * @param title organizer's title is passed as an extra to the activity
     */
    void toOrganizerTournaments(String title);

    /**
     * Sets organizer title in the layout to the title of the organizer we specify
     * @param title organizer title that will be showed
     */
    void setTitle(String title);

    /**
     * When the user logs out
     *  Goes Back to log in screen
     */
    void logOut();

    /**
     * Displays the desired pop up
     * @param layout the layout of the popup
     * @param msg the message of the popup
     * @param btn1 the first button
     * @param btn2 the second button
     */
    void displayPopUpAction(int layout, String msg, int btn1, int btn2);

    /**
     * The pop up dismisses
     */
    void dismissPopUpAction();

    /**
     * Shows a popup
     * @param layoutId the layout of the popup
     * @param msg the message of the popup
     * @param btn1 the first button
     * @param btn2 the second button
     * @return the AlertDialog that will be shown
     */
    AlertDialog showPopUp(int layoutId, String msg, int btn1, int btn2);

    /**
     * When the user presses log out
     * a pop up shows asking for confirmation
     */
    void logOutConfirmation();

    /**
     * When the user presses no to the log out pop up
     * the pop up message dismisses
     */
    void noLogOut();


}
