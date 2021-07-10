package com.example.managetournamentapp.view.Team.TeamInfo;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class TeamInfoViewStub implements TeamInfoView {
    String name = "Celtic9", colors = "green", division = "K100", sport = "Basketball3v3";
    boolean onHome = false;

    @Override
    public void setTeamName(String name) {
        this.name = name;
    }

    @Override
    public void setColors(String colors) {
        this.colors = colors;
    }

    @Override
    public void setAgeDivision(String division) {
        this.division = division;
    }

    @Override
    public void setSport(String sport) {
        this.sport = sport;
    }

    @Override
    public void startEditTeam() {

    }

    @Override
    public void showToast(String txt) {

    }

    @Override
    public void startDeleteTeam(String playerUsername) {

    }

    @Override
    public void changesOfAccess() {

    }

    @Override
    public void backToHomePage(boolean flag, String string) {
        onHome = true;
    }
}
