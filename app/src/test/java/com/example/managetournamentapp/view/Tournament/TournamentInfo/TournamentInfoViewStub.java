package com.example.managetournamentapp.view.Tournament.TournamentInfo;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class TournamentInfoViewStub implements TournamentInfoView {
    String teamsnumber, location, startdate, finishdate, sportType, title, ageDivision, description;
    boolean onEdit = false, onChange = false, onConfirm = false, onNoDelete = false, onYesDelete = false;

    @Override
    public void setTeamsNumber(String teamsNumber) {
        this.teamsnumber = teamsNumber;
    }

    @Override
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public void setStartDate(String startDate) {
        this.startdate = startDate;
    }

    @Override
    public void setFinishDate(String finishDate) {
        this.finishdate = finishDate;
    }

    @Override
    public void setsportType(String sportType) {
        this.sportType = sportType;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void setAgeDivision(String ageDivision) {
        this.ageDivision = ageDivision;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void startEditTournament() {
        onEdit = true;
    }

    @Override
    public void changesOfAccess() {
        onChange = true;
    }

    @Override
    public void deleteConfirmation() {
        onConfirm = true;
    }

    @Override
    public void noDeleteTournament() {
        onNoDelete = true;
    }

    @Override
    public void yesDeleteTournament(String title) {
        onYesDelete = true;
    }

    @Override
    public void showToast(String txt) {

    }
}
