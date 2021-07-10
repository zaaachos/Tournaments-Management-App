package com.example.managetournamentapp.view.Organizer.CreateTournament;

import java.util.ArrayList;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public interface CreateTournamentView {

    /**
     * Goes back to organizer profile
     * if the user edits account and
     * presses the save button
     * @param organizerTitle organizer title that is passed as an extra to the next activity
     */
    void startSaveTournament(String organizerTitle);

    /**
     * If user saves the initial info of the new tournament
     * @param basicInfo arraylist containing the basic info of the newly created tournament, passed as an extra
     */
    void startSetDates(ArrayList<String> basicInfo);

    /**
     * Returns the organizer title of the related field in the current layout
     * @return the organizer title found in the related field
     */
    String getTournamentTitle();

    /**
     * Returns the tournament location of the related field in the current layout
     * @return the organizer title found in the related field
     */
    String getLocation();

    /**
     * Returns the tournament start date of the related field in the current layout
     * @return the tournament start date found in the related field
     */
    String getStartDate();

    /**
     * Returns the tournament finish date of the related field in the current layout
     * @return the tournament finish date found in the related field
     */
    String getFinishDate();

    /**
     * Returns the tournament age division of the related field in the current layout
     * @return the tournament age division found in the related field
     */
    int getAgeDivision();

    /**
     * Returns the tournament teams number of the related field in the current layout
     * @return the tournament teams number found in the related field
     */
    int getTeamsNumber();

    /**
     * Returns the tournament sport type of the related field in the current layout
     * @return the tournament sport type found in the related field
     */
    int getSportType();

    /**
     * Method used
     * to set the tournament title field of activity's layout
     * screen to the tournament title that user has typed in
     * @param title title of tournament to be set
     */
    void setTournamentTitle(String title);

    /**
     * Method used
     * to set the tournament description field of activity's layout
     * screen to the tournament description that user has typed in
     * @param description description of tournament to be set
     */
    void setDescription(String description);

    /**
     * Method used
     * to set the tournament location field of activity's layout
     * screen to the tournament location that user has typed in
     * @param location location of tournament to be set
     */
    void setLocation(String location);

    /**
     * Method used
     * to set the tournament start date field of activity's layout
     * screen to the tournament start date that user has typed in
     * @param date start date of tournament to be set
     */
    void setStartDate(String date);

    /**
     * Method used
     * to set the tournament finish date of activity's layout
     * screen to the tournament finish date that user has typed in
     * @param date  finish date of tournament to be set
     */
    void setFinishDate(String date);

    /**
     * Method used
     * to set the tournament age division of activity's layout
     * screen to the tournament age division that user has typed in
     * @param position position of spinner that must be set
     */
    void setAgeDivision(int position);

    /**
     * Method used
     * to set the tournament teams number of activity's layout
     * screen to the tournament teams number that user has typed in
     * @param position position of spinner that must be set
     */
    void setTeamsNumber(int position);

    /**
     * Method used
     * to set the tournament sport type of activity's layout
     * screen to the tournament sport type that user has typed in
     * @param position position of spinner that must be set
     */
    void setSportType(int position);

    /**
     * Shows a pop up message
     * @param view of the current layout
     * @param msg message that the pop up shows
     */
    void showPopUp(CreateTournamentView view, String msg);

    /**
     * Locks the fields so they can't be changed
     */
    void lockPrevious();

    /**
     * Returns the tournament description of the related field in the current layout
     * @return the tournament description found in the related field
     */
    String getDescription();

    /**
     * Goes back to organizer profile
     * @param name organizer name that is passed as an extra in the organizer page activity
     */
    void backToHomePage(String name);
}
