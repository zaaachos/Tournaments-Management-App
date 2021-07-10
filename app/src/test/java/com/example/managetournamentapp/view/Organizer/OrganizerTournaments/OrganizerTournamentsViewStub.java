package com.example.managetournamentapp.view.Organizer.OrganizerTournaments;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class OrganizerTournamentsViewStub implements OrganizerTournamentsView {
    boolean onCreate = false, onHome = false;

    @Override
    public void startCreateTournament() {
        onCreate = true;
    }

    @Override
    public void backToHomePage(String title) {
        onHome = true;
    }
}
