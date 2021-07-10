package com.example.managetournamentapp.view.Organizer.SetDates;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class SetDatesViewStub implements SetDatesView {
    ArrayList<String> dates = new ArrayList<String>(Arrays.asList("15/05/2030", "16/05/2030", "17/05/2030", "18/05/2030", "19/05/2030", "20/05/2030"));
    boolean onSave = false, onHome = false;

    @Override
    public void startSaveTournament(String tournamentName) {
        onSave = true;
    }

    @Override
    public ArrayList<String> getDates() {
        return this.dates;
    }

    @Override
    public void showPopUp(SetDatesView view, String msg) {

    }

    @Override
    public void backToHomePage(boolean isPlayer, String name) {
        onHome = true;
    }
}
