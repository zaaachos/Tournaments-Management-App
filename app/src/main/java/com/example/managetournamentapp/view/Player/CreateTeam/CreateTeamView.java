package com.example.managetournamentapp.view.Player.CreateTeam;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public interface CreateTeamView {

    /**
     * show the player's page activity
     * after the team has been saved
     * @param userName the username of the captain
     */
    void startSaveTeam(String userName);

    /**
     * get the colors of the team
     * @return the name of the team
     */
    String getTeamName();

    /**
     * get the colors of the team
     * @return the colors
     */
    String getTeamColors();

    /**
     * set the contents of the spinner
     */
    int getSportType();

    /**
     * set the contents in the name edit text
     * @param name the new name
     */
    void setTeamName(String name);

    /**
     * set the contents in the colors edit text
     * @param colors the new colors
     */
    void setTeamColors(String colors);

    /**
     * set the contents of the spinner
     * @param position the index of the sport type
     */
    void setSportType(int position);

    /**
     * freeze the sport type spinner
     */
    void lockSportType();

    /**
     *show a popup on the screen
     * @param view the view of the popup
     * @param msg the message that will be shown
     */
    void showPopUp(CreateTeamView view, String msg);

    /**
     * what happens when the homepage button is pressed
     * @param string is the name of a player
     */
    void backToHomePage(String string);
}
