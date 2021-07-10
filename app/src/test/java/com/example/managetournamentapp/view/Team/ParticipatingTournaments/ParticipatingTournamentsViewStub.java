package com.example.managetournamentapp.view.Team.ParticipatingTournaments;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class ParticipatingTournamentsViewStub implements ParticipatingTournamentsView {
    boolean onHome = false;

    @Override
    public void startAddParticipation() {

    }

    @Override
    public void changesOfAccess() {

    }

    @Override
    public void backToHomePage(String string) {
        onHome = true;
    }
}
