package com.example.managetournamentapp.view.HomePage;


/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class HomePagePresenter {

    private HomePageView view;

    /**
     * Default constructor
     */
    public HomePagePresenter() {
    }

    /**
     * If the user is on the Home Page
     * and clicks on the connect button
     */
    public void onConnectAction() {
        view.connectAction();
    }

    /**
     * If the user is on the Home Page
     * and clicks on the browse button
     */
    public void onBrowseAction() {
        view.browseAction();
    }

    /**
     * If the user is on the Home Page
     * and clicks on the Log In button
     */
    public void onLogInAction() {
        view.loginAction();
    }
    /**
     * If the user is on the Home Page
     * and clicks on the Register button
     */
    public void onRegisterAction() {
        view.registerAction();
    }

    /**
     * If the user is on the Home Page
     * and clicks on the Register button
     * and then chooses the organizer button
     */
    public void onOrganizerRegisterAction() {
        view.organizerRegisterAction();
    }

    /**
     * If the user is on the Home Page
     * and clicks on the Register button
     * amd then chooses the player button
     */
    public void onPlayerRegisterAction() {
        view.playerRegisterAction();
    }

    /**
     * Sets the view to the view specified
     */
    public void setView(HomePageView view) {
        this.view = view;
    }

    /**
     * Sets the view to null
     */
    public void clearView() {
        this.view = null;
    }

}
