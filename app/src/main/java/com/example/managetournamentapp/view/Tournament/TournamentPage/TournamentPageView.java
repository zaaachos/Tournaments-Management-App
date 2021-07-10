package com.example.managetournamentapp.view.Tournament.TournamentPage;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public interface TournamentPageView {

    /**
     * when the "info" button is pressed
     * the tournament info activity starts
     */
    void startTournamentInfo();

    /**
     * when the "teams" button is pressed
     * the participating teams activity starts
     */
    void startTeamsParticipating();

    /**
     * when the "games" button is pressed
     * the tournament rounds activity starts
     */
    void startTournamentGames();


    /**
     * what happens when the homepage button is pressed
     * @param noLogin boolean parameter, if true the user has not logged in
     * @param isPlayer boolean parameter,if true the user is a player
     * @param name name of the player or title of the organizer
     */
    void backToHomePage(boolean noLogin, boolean isPlayer, String name);

}
