package com.example.managetournamentapp.view.Tournament.RoundGames;

import com.example.managetournamentapp.domain.Game;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public interface RoundGamesView {

    /**
     * show a toast on the screen
     * @param text the message of the toast
     */
    void showToast(String text);

    /**
     * when the organizer presses on a game
     * they can set the score of the game
     * @param game the game that was pressed on
     */
    void showPopup(Game game);

    /**
     * refresh the current activity
     */
    void recreateView();

    /**
     * what happens when the homepage button is pressed
     * @param noLogin boolean parameter, if true the user has not logged in
     * @param isPlayer boolean parameter,if true the user is a player
     * @param name name of the player or title of the organizer
     */
    void backToHomePage(boolean noLogin, boolean isPlayer, String name);
}
