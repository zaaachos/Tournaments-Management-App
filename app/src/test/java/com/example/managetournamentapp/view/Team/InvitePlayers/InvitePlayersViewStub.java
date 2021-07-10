package com.example.managetournamentapp.view.Team.InvitePlayers;

import androidx.appcompat.app.AlertDialog;

import com.example.managetournamentapp.domain.Player;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class InvitePlayersViewStub implements InvitePlayersView {
    boolean onHome = false;

    @Override
    public AlertDialog showPopUp(int layoutId, String msg, int btn1, int btn2, boolean flag) {
        return null;
    }

    @Override
    public void startPlayerPage(Player player) {

    }

    @Override
    public void displayPopUpAction(int layout, String msg, int btn1, int btn2, boolean invited) {

    }

    @Override
    public void dismissPopUpAction() {

    }

    @Override
    public void resetPopUps() {

    }

    @Override
    public void restartActivity() {

    }

    @Override
    public void backToHomePage(String string) {
        onHome = true;
    }
}
