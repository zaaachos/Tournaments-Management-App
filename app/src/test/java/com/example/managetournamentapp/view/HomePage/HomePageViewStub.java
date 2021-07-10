package com.example.managetournamentapp.view.HomePage;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class HomePageViewStub implements HomePageView {
    boolean onConnect = false, onBrowse = false, onLogin = false, onRegister = false, onOrganizerRegister = false, onPlayerRegister = false;

    @Override
    public void connectAction() {
        onConnect = true;

    }

    @Override
    public void browseAction() {
        onBrowse = true;

    }

    @Override
    public void loginAction() {
        onLogin = true;

    }

    @Override
    public void registerAction() {
        onRegister = true;

    }

    @Override
    public void organizerRegisterAction() {
        onOrganizerRegister = true;

    }

    @Override
    public void playerRegisterAction() {
        onPlayerRegister = true;

    }
}
