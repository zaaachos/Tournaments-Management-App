package com.example.managetournamentapp.view.Team.TeamInfo;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public interface TeamInfoView {

    /**
     * set the contents in the name edit text
     * @param name name of the team
     */
    void setTeamName(String name);

    /**
     * set the contents in the colors edit text
     * @param colors colors of the team
     */
    void setColors(String colors);

    /**
     * set the contents in the division edit text
     * @param division division of the team
     */
    void setAgeDivision(String division);

    /**
     * set the contents in the sport edit text
     * @param sport sport of the team
     */
    void setSport(String sport);

    /**
     * when the captain decides to edit this team
     * the create team activity is started
     */
    void startEditTeam();

    /**
     * show a toast on the screen
     * @param txt the message of the toast
     */
    void showToast(String txt);

    /**
     * when the captain decides to delete this team
     * @param playerUsername the player's username
     */
    void startDeleteTeam(String playerUsername);

    /**
     * only the captain can edit and delete the team
     */
    void changesOfAccess();

    /**
     * what happens when the homepage button is pressed
     * @param flag true if the logged in user is a player
     * @param string is the name of a player. or the title of an organizer
     */
    void backToHomePage(boolean flag, String string);

}
