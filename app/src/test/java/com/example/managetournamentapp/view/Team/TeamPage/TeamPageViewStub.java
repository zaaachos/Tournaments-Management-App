package com.example.managetournamentapp.view.Team.TeamPage;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class TeamPageViewStub implements TeamPageView {
    public boolean changed = false, onHome = false;

    @Override
    public void startTeamInfo() {

    }

    @Override
    public void startTeamPlayers() {

    }

    @Override
    public void startTeamParticipations() {

    }

    @Override
    public void changesOfAccess() {
        changed = true;
    }

    @Override
    public void backToHomePage(boolean noLogin, boolean isPlayer, String name) {
        onHome = true;

    }


}
