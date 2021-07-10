package com.example.managetournamentapp.view.User.Login;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class LoginViewStub implements LoginView {
    String username = "tommy0";
    String password = "12345";
    boolean onPlayer = false, onOrganizer = false;

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void showPopUp(LoginView view, String msg) {

    }

    @Override
    public void startPlayerPage(String username) {

    }

    @Override
    public void startOrganizerPage(String title) {

    }

}
