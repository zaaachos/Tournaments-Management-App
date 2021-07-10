package com.example.managetournamentapp.view.Team.TeamPage;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public interface TeamPageView {

    /**
     * when the "info" button is pressed
     * the team info activity starts
     */
    void startTeamInfo();

    /**
     * when the "players" button is pressed
     * the participating players activity starts
     */
    void startTeamPlayers();

    /**
     * when the "participations" button is pressed
     * the participations activity starts
     */
    void startTeamParticipations();

    /**
     *only the players of this team can see
     * the participations of this team
     */
    void changesOfAccess();

    /**
     * what happens when the homepage button is pressed
     * @param noLogin boolean parameter, if true the user has not logged in
     * @param isPlayer boolean parameter,if true the user is a player
     * @param name name of the player or title of the organizer
     */
    void backToHomePage(boolean noLogin, boolean isPlayer, String name);
}
