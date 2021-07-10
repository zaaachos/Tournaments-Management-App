package com.example.managetournamentapp.view.Tournament.TournamentInfo;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public interface TournamentInfoView {

    /**
     * set the contents in the teams number edit text
     * @param teamsNumber the number of teams
     */
    void setTeamsNumber(String teamsNumber);

    /**
     * set the contents in the location edit text
     * @param location the new location
     */
    void setLocation(String location);

    /**
     * get the contents of the location edit text
     * @return the given finish date
     */
    void setStartDate(String startDate);

    /**
     * get the contents of the finish date edit text
     * @return the given finish date
     */
    void setFinishDate(String finishDate);

    /**
     * set the contents in the sport edit text
     * @param sportType the new sport
     */
    void setsportType(String sportType);

    /**
     * set the contents in the title edit text
     * @param title the new title
     */
    void setTitle(String title);

    /**
     * set the contents in the age division edit text
     * @param ageDivision the new age division
     */
    void setAgeDivision(String ageDivision);

    /**
     * set the contents in the description edit text
     * @param description the new description
     */
    void setDescription(String description);

    /**
     * when the organizer chooses to edit this tournament
     * the create tournament activity is started
     */
    void startEditTournament();

    /**
     * hide the edit and delete buttons
     * if the user viewing the page is not
     * the organizer of this tournament
     */
    void changesOfAccess();

    /**
     * ask for a confirmation before
     * deleting a tournament
     */
    void deleteConfirmation();

    /**
     * when the organizer presses
     * "no" on the confirmation
     */
    void noDeleteTournament();

    /**
     * when the organizer presses
     * "yes" on the confirmation
     */
    void yesDeleteTournament(String title);

    /**
     * show a toast on the screen
     * @param txt the message of the toast
     */
    void showToast(String txt);

}
