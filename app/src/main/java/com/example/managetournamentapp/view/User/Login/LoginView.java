package com.example.managetournamentapp.view.User.Login;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public interface LoginView {

    /**
     * get the contents of the edit text
     * @return the username given
     */
    String getUsername();


    /**
     * get the contents of the edit text
     * @return the password given
     */
    String getPassword();

    /**
     *show a popup on the screen
     * @param view the view of the popup
     * @param msg the message that will be shown
     */
    void showPopUp(LoginView view, String msg);

    /**
     * start the player page activity
     * @param username the username of the player
     */
    void startPlayerPage(String username);

    /**
     * start the organizer page activity
     * @param title the title of the player
     */
    void startOrganizerPage(String title);

}
