package com.example.managetournamentapp.view.Player.PlayerInfo;

import androidx.appcompat.app.AlertDialog;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public interface PlayerInfoView {

    /**
     * show the possible actions popup
     * @param layoutId the layout of the popup
     * @param msg the message of the popup
     * @param btn1 the first button
     * @param btn2 the second button
     */
    AlertDialog showPopUp(int layoutId, String msg, int btn1, int btn2);

    /**
     * set the contents in the username text field
     * @param username the new username
     */
    void setUsername(String username);

    /**
     * set the contents in the password text field
     * @param password the new password
     */
    void setPassword(String password);

    /**
     * set the contents in the name text field
     * @param name the new name
     */
    void setName(String name);

    /**
     * set the contents in the surname text field
     * @param surname the new surname
     */
    void setSurname(String surname);

    /**
     * set the contents in the phone number text field
     * @param phone the new phone number
     */
    void setPhone(String phone);

    /**
     * set the contents in the email text field
     * @param email the new email
     */
    void setEmail(String email);

    /**
     * set the contents in the location text field
     * @param location the new location
     */
    void setLocation(String location);

    /**
     * set the contents in the birth date text field
     * @param date the new birth date
     */
    void setBirthDate(String date);

    /**
     * when the player decides to edit his account
     * the register player activity is started
     */
    void startEditPlayer();

    /**
     * when the player decides to delete his account
     */
    void startDeletePlayer();

    /**
     * show a toast when the player can't delete the account
     */
    void showCantDelete();

    /**
     * the account can be edited or deleted only by the player
     * who owns it
     */
    void changesOfAccess();

    /**
     * show the deletion popup
     * @param layout the layout of the popup
     * @param msg the message of the popup
     * @param btn1 the first button
     * @param btn2 the second button
     */
    void displayPopUp(int layout, String msg, int btn1, int btn2);

    /**
     * close the deletion popup
     */
    void dismissPopUp();

}
