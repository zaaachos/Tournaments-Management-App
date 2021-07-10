package com.example.managetournamentapp.view.Organizer.OrganizerPage;

import androidx.appcompat.app.AlertDialog;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class OrganizerPageViewStub implements OrganizerPageView {
    String title;
    boolean onOrganizer = false, onTournaments = false, onLogout = false, onNoLogout = false, onLogConfirmation = false;

    @Override
    public void toOrganizerAccount() {
        onOrganizer = true;

    }

    @Override
    public void toOrganizerTournaments(String title) {
        onTournaments = true;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void logOut() {
        onLogout = true;
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
        onLogConfirmation = true;
    }

    @Override
    public void noLogOut() {
        onNoLogout = true;
    }
}
