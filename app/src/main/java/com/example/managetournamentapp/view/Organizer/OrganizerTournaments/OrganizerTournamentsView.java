package com.example.managetournamentapp.view.Organizer.OrganizerTournaments;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public interface OrganizerTournamentsView {

    /**
     * When the user presses the "+" button to create a new tournament
     * goes to the create tournament page
     */
    void startCreateTournament();

    /**
     * what happens when the homepage button is pressed
     * goes back to the organizer profile
     * @param title is the title of the organizer that is passed as an extra
     */
    void backToHomePage(String title);
}
