package com.example.managetournamentapp.view.Tournament.TournamentRounds;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public interface TournamentRoundsView {

    /**
     * hide the button of a round
     * if this round doesn't exist in the tournament
     * @param teamsNumber the number of teams in the tournament
     */
    void changesOfAccess(int teamsNumber);

    /**
     * when the "groups" button is pressed,
     * the user wants to explore the "group" stage (1st round)
     * the tournament groups activity starts
     */
    void showGroups(String tournamentTitle, int roundTeamsNumber);

    /**
     * when a player presses on the button of a round
     * apart from the group stage round
     * @param tournamentTitle the title of the tournament
     * @param roundTeamsNumber the number of teams in the round
     */
    void showRoundGames(String tournamentTitle, int roundTeamsNumber);

    /**
     * what happens when the homepage button is pressed
     * @param noLogin boolean parameter, if true the user has not logged in
     * @param isPlayer boolean parameter,if true the user is a player
     * @param name name of the player or title of the organizer
     */
    void backToHomePage(boolean noLogin, boolean isPlayer, String name);


}
