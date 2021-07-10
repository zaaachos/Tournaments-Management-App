package com.example.managetournamentapp.view.Tournament.ParticipatingTeams;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class ParticipatingTeamsViewStub implements ParticipatingTeamsView {
    boolean onHome = false, onTeam = false;

    @Override
    public void startTeamPage(String teamname) {
        onTeam = true;
    }

    @Override
    public void backToHomePage(boolean noLogin, boolean isPlayer, String name) {
        onHome = true;
    }
}
