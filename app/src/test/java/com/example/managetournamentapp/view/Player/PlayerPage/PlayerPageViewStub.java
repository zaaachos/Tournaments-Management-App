package com.example.managetournamentapp.view.Player.PlayerPage;

import androidx.appcompat.app.AlertDialog;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class PlayerPageViewStub implements PlayerPageView {

    boolean onTeams = false, onInvites = false, onInfo = false, onLogout = false, onNoLogout = false;

    @Override
    public void toPlayerInfo(String playerUsername) {
        onInfo = true;

    }

    @Override
    public void toPlayerTeams(String playerUsername) {
        onTeams = true;
    }

    @Override
    public void toPlayerInvites(String playerUsername) {
        onInvites = true;
    }

    @Override
    public void changesOfAccess() {

    }

    @Override
    public void logOut() {

    }

    @Override
    public void displayPopUpAction(int layout, String msg, int btn1, int btn2) {

    }

    @Override
    public void dismissPopUpAction() {

    }

    @Override
    public AlertDialog showPopUp(int layoutId, String msg, int btn1, int btn2) {
        return null;
    }

    @Override
    public void logOutConfirmation() {
        onLogout = true;
    }

    @Override
    public void noLogOut() {
        onNoLogout = true;
    }
}
