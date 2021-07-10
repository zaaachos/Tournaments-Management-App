package com.example.managetournamentapp.view.User.RegisterOrganizer;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public interface RegisterOrganizerView {

    /**
     * get the contents of the edit text
     * @return the given username
     */
    String getUsername();

    /**
     * get the contents of the edit text
     * @return the given password
     */
    String getPassword();

    /**
     * get the contents of the edit text
     * @return the given name
     */
    String getName();

    /**
     * get the contents of the edit text
     * @return the given surname
     */
    String getSurname();

    /**
     * get the contents of the edit text
     * @return the name given
     */
    String getPhoneNumber();

    /**
     * get the contents of the edit text
     * @return the given email
     */
    String getEmail();

    /**
     * get the contents of the edit text
     * @return the given birth date
     */
    String getBirthDate();

    /**
     * get the contents of the edit text
     * @return the given title
     */
    String getOrganizerTitle();

    /**
     * set the contents in the username edit text
     * @param username the new username
     */
    void setUsername(String username);

    /**
     * set the contents in the password edit text
     * @param password the new password
     */
    void setPassword(String password);

    /**
     * set the contents in the name edit text
     * @param name the new name
     */
    void setName(String name);

    /**
     * set the contents in the surname edit text
     * @param surname the new surname
     */
    void setSurname(String surname);

    /**
     * set the contents in the phone number edit text
     * @param phoneNumber the new phone number
     */
    void setPhoneNumber(String phoneNumber);

    /**
     * set the contents in the email edit text
     * @param email the new email
     */
    void setEmail(String email);

    /**
     * set the contents in the birth date edit text
     * @param birthdate the new birth date
     */
    void setBirthdate(String birthdate);

    /**
     * set the contents in the title edit text
     * @param title the new title
     */
    void setTitle(String title);

    /**
     *show a popup on the screen
     * @param view the view of the popup
     * @param msg the message that will be shown
     */
    void showPopUp(RegisterOrganizerView view, String msg);

    /**
     * start the organizer page activity
     * @param title the title of the player
     */
    void startOrganizerPage(String title);

    /**
     * some fields can't be changed
     */
    void lockFields();
}
