package com.example.managetournamentapp.view.Team.JoinedPlayers;

import androidx.appcompat.app.AlertDialog;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class JoinedPlayersViewStub implements JoinedPlayersView{
    boolean onHome = false;
    @Override
    public void changesOfAccess(boolean flag, boolean flag2) {

    }

    @Override
    public AlertDialog showPopUp(int layoutId, String msg, int btn1, int btn2) {
        return null;
    }

    @Override
    public void startPlayerInfo() {

    }

    @Override
    public void startInvitePlayerPage() {

    }

    @Override
    public void displayPopUpAction(int layout, String msg, int btn1, int btn2) {

    }

    @Override
    public void displayPopUpDeletion(int layout, String msg, int btn1, int btn2) {

    }

    @Override
    public void dismissPopUpAction() {

    }

    @Override
    public void dismissPopUpDeletion() {

    }

    @Override
    public void resetPopUps() {

    }

    @Override
    public void restartActivity() {

    }

    @Override
    public void backToHomePage(boolean noLogin, boolean isPlayer, String name) {
onHome = true;
    }

    @Override
    public void showToast(String txt) {

    }
}
