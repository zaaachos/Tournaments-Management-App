package com.example.managetournamentapp.view.Player.CreateTeam;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class CreateTeamViewStub implements CreateTeamView {
    String teamname, teamcolor;
    int sportType;
    boolean onSave = false, onHome = false, onLock = false;

    @Override
    public void startSaveTeam(String userName) {
        onSave = true;
    }

    @Override
    public String getTeamName() {
        return this.teamname;
    }

    @Override
    public String getTeamColors() {
        return this.teamcolor;
    }

    @Override
    public int getSportType() {
        return this.sportType;
    }

    @Override
    public void setTeamName(String name) {
        this.teamname = name;
    }

    @Override
    public void setTeamColors(String colors) {
        this.teamcolor = colors;
    }

    @Override
    public void setSportType(int position) {
        this.sportType = position;
    }

    @Override
    public void lockSportType() {
        onLock = true;
    }

    @Override
    public void showPopUp(CreateTeamView view, String msg) {

    }

    @Override
    public void backToHomePage(String string) {
        onHome = true;
    }
}
