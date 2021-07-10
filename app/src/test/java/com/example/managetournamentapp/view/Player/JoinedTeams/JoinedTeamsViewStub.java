package com.example.managetournamentapp.view.Player.JoinedTeams;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class JoinedTeamsViewStub implements JoinedTeamsView {
    boolean onAdd = false, onHome = false, onChange = false;

    @Override
    public void startAddTeam() {
        onAdd = true;
    }

    @Override
    public void backToHomePage() {
        onHome = true;
    }
}
