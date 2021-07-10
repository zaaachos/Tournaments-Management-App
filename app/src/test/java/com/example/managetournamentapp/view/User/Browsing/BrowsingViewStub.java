package com.example.managetournamentapp.view.User.Browsing;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class BrowsingViewStub implements BrowsingView {
    boolean onTournament = false;

    @Override
    public void startTournamentPage(String title) {
        onTournament = true;
    }
}
