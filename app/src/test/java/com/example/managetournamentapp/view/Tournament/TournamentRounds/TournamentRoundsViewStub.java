package com.example.managetournamentapp.view.Tournament.TournamentRounds;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class TournamentRoundsViewStub implements TournamentRoundsView {
    boolean onChange = false, onRound = false, onGroups = false, onHome = false;

    @Override
    public void changesOfAccess(int teamsNumber) {
        onChange = true;
    }

    @Override
    public void showRoundGames(String tournamentTitle, int roundTeamsNumber) {
        onRound = true;
    }

    @Override
    public void showGroups(String tournamentTitle, int roundTeamsNumber) {
        onGroups = true;
    }

    @Override
    public void backToHomePage(boolean noLogin, boolean isPlayer, String name) {
        onHome = true;
    }
}
