package com.example.managetournamentapp.view.Team.AddParticipation;

import androidx.appcompat.app.AlertDialog;

import com.example.managetournamentapp.domain.Tournament;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class AddParticipationViewStub implements AddParticipationView {
    boolean onHome = false;

    @Override
    public AlertDialog showPopUp(int layoutId, String msg, int btn1, int btn2, boolean flag) {
        return null;
    }

    @Override
    public void startTournamentPage(Tournament tournament) {
    }

    @Override
    public void startPlayerPage(String userName) {
    }

    @Override
    public void backToHomePage(String string) {
        onHome = true;
    }
}
