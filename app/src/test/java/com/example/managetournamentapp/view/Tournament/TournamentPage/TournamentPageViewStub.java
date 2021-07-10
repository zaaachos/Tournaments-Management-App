package com.example.managetournamentapp.view.Tournament.TournamentPage;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class TournamentPageViewStub implements TournamentPageView {
    boolean onInfo = false, onTeams = false, onGames = false, onHome = false;

    @Override
    public void startTournamentInfo() {
        onInfo = true;
    }

    @Override
    public void startTeamsParticipating() {
        onTeams = true;
    }

    @Override
    public void startTournamentGames() {
        onGames = true;
    }

    @Override
    public void backToHomePage(boolean noLogin, boolean isPlayer, String name) {
        onHome = true;
    }
}
