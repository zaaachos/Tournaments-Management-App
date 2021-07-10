package com.example.managetournamentapp.view.Organizer.OrganizerInfo;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public interface OrganizerInfoView {

    /**
     * Method used
     * to set the username field of activity's layout
     * screen to the username of the organizer that is logged in
     * @param username username of organizer
     */
    void setUsername(String username);

    /**
     * Method used
     * to set the password field of activity's layout
     * to the password of the organizer that is logged in
     * @param password password of organizer to be set
     */
    void setPassword(String password);

    /**
     * Method used
     * to set the name field of activity's layout
     * to the name of the organizer that is logged in
     * @param name name of organizer to be set
     */
    void setName(String name);

    /**
     * Method used
     * to set the surname field of activity's layout
     * to the surname of the organizer that is logged in
     * @param surname surname of organizer to be set
     */
    void setSurname(String surname);

    /**
     * Method used
     * to set the phone field of activity's layout
     * to the phone number of the organizer that is logged in
     * @param phone phone of organizer to be set
     */
    void setPhone(String phone);

    /**
     * Method used
     * to set the email field of activity's layout
     * to the email of the organizer that is logged in
     * @param email email of organizer to be set
     */
    void setEmail(String email);

    /**
     * Method used
     * to set the birthdate field of activity's layout
     * to the birthdate of the organizer that is logged in
     * @param date birthdate of organizer to be set
     */
    void setBirthDate(String date);

    /**
     * Starts the register organizer activity so that the user can modify account
     * @param organizerUsername title of organizer to be passed as an extra
     */
    void startEditOrganizer(String organizerUsername);

    /**
     * Deletes the organizer's account
     * and goes back to the home page screen
     */
    void startDeleteOrganizer();

    /**
     * Method used
     * to set the title field of activity's layout
     * to the title of the organizer that is logged in
     * @param title title of organizer to be set
     */
    void setTitle(String title);

    /**
     * Toast message shown in case user can't delete account
     */
    void showCantDelete();
}
