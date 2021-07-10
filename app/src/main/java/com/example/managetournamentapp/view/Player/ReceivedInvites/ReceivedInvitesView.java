package com.example.managetournamentapp.view.Player.ReceivedInvites;

import androidx.appcompat.app.AlertDialog;

import com.example.managetournamentapp.domain.Invitation;

import java.util.ArrayList;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public interface ReceivedInvitesView {

    /** show the popup when the player selects an invitation
     * @param layoutId the layout of the popup
     * @param msg the message of the popup
     * @param btn1 the first button
     * @param btn2 the second button
     * @param btn3 the third button
     * @param flag is true when the second version of the popup is shown
     * @return
     */
    AlertDialog showPopUp(int layoutId, String msg, int btn1, int btn2, int btn3,boolean flag);

    /**
     * when the player wants to see the info of the team
     * that invited him
     */
    void startTeamPage();

    /**
     * get the invites that the player has received
     * @return the ArrayList of invites
     */
    ArrayList<Invitation> getInvitationsList();

    /**
     * what happens when the homepage button is pressed
     */
    void backToHomePage();
}
