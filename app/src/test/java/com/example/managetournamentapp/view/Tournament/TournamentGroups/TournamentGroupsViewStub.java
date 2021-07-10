package com.example.managetournamentapp.view.Tournament.TournamentGroups;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class TournamentGroupsViewStub implements TournamentGroupsView {
    boolean onHome = false, onChange = false, onGroup = false, onRankings = false, onShow = false;

    @Override
    public void changesOfAccess(int groupsNumber) {
        onChange = true;
    }

    @Override
    public void showGroupGames(String tournamentGame, int roundTeamsNumber, int index) {
        onGroup = true;
    }

    @Override
    public void showGroupRankings(String tournamentGame, int roundTeamsNumber, int index) {
        onRankings = true;
    }

    @Override
    public void showPopup(int index) {
        onShow = true;
    }

    @Override
    public void backToHomePage(boolean noLogin, boolean isPlayer, String name) {
        onHome = true;
    }
}
