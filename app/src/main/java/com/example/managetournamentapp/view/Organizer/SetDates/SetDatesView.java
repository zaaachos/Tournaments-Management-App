package com.example.managetournamentapp.view.Organizer.SetDates;

import java.util.ArrayList;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public interface SetDatesView {

    /**
     * when the tournament is saved
     * @param organizerTitle the tournament name
     */
    void startSaveTournament(String organizerTitle);

    /**
     * get the date of every tournament game
     * @return the ArrayList of dates
     */
    ArrayList<String> getDates();

    /**
     * Shows a popup
     * @param view
     * @param msg
     */

    void showPopUp(SetDatesView view, String msg);

    /**
     * what happens when the homepage button is pressed
     * @param isPlayer is true if the logged in user is a player
     * @param name is the name of a player. or the title of an organizer
     */
    void backToHomePage(boolean isPlayer, String name);
}
