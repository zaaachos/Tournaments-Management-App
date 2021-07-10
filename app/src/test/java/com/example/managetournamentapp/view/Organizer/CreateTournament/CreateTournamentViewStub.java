package com.example.managetournamentapp.view.Organizer.CreateTournament;

import java.util.ArrayList;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class CreateTournamentViewStub implements CreateTournamentView {
    String location, description, title, startDate, finishDate;
    int ageDivision, teamsNumber, sportType;
    boolean onSave = false, onHome = false, onSet = false, onLock = false;

    @Override
    public void startSaveTournament(String tournamentTitle) {
        onSave = true;
        this.title = tournamentTitle;
    }

    @Override
    public void startSetDates(ArrayList<String> basicInfo) {
        onSet = true;
    }

    @Override
    public String getTournamentTitle() {
        return title;
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public String getStartDate() {
        return startDate;
    }

    @Override
    public String getFinishDate() {
        return finishDate;
    }

    @Override
    public int getAgeDivision() {
        return ageDivision;
    }

    @Override
    public int getTeamsNumber() {
        return teamsNumber;
    }

    @Override
    public int getSportType() {
        return sportType;
    }

    @Override
    public void setTournamentTitle(String title) {
        this.title = title;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void setLocation(String location) {
        this.location = location;

    }

    @Override
    public void setStartDate(String date) {
        this.startDate = date;

    }

    @Override
    public void setFinishDate(String date) {
        this.finishDate = date;

    }

    @Override
    public void setAgeDivision(int position) {
        this.ageDivision = position;
    }

    @Override
    public void setTeamsNumber(int position) {
        this.teamsNumber = position;
    }

    @Override
    public void setSportType(int position) {
        this.sportType = position;
    }

    @Override
    public void showPopUp(CreateTournamentView view, String msg) {

    }

    @Override
    public void lockPrevious() {
        onLock = true;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void backToHomePage(String name) {
        onHome = true;
    }
}
