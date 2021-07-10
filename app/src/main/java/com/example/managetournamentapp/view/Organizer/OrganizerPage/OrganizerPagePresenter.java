package com.example.managetournamentapp.view.Organizer.OrganizerPage;

import com.example.managetournamentapp.dao.LoggedInUser;
import com.example.managetournamentapp.dao.OrganizerDAO;
import com.example.managetournamentapp.domain.Organizer;
import com.example.managetournamentapp.domain.User;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class OrganizerPagePresenter {
    private OrganizerPageView view;
    private LoggedInUser loggedInUser;
    private Organizer organizer;
    private OrganizerDAO organizerDAO;

    /**
     * Default constructor
     */
    public OrganizerPagePresenter() {

    }
    /**
     * find the info of the organizer
     * @param organizerTitle the title of the organizer
     */
    public void findOrganizerInfo(String organizerTitle) {
        if (organizerTitle == null) {
            return;
        }
        organizer = organizerDAO.findByTitle(organizerTitle);
    }

    /**
     *  get the organzer's title
     * @return the title of the organizer
     */
    public String getOrganizerTitle() {
        if (organizer == null) {
            return "";
        }
        return organizer.getTitle();
    }

    /**
     * Sets the organizer to the user that is currently logged in
     * @param user to be set as an organizer
     */
    public void setOrganizer(User user) {
        if (user == null)
            return;
        if (!(user instanceof Organizer))
            return;
        organizer = (Organizer) user;

    }

    /**
     * set the organizerDAO
     * @param organizerDAO the new OrganizerDAO
     */
    public void setOrganizerDAO(OrganizerDAO organizerDAO) {
        this.organizerDAO = organizerDAO;
    }

    /**
     * set the logged in user
     * @param loggedInUser the logged in user
     */
    public void setLoggedInUser(LoggedInUser loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    /**
     * when the "account" button is pressed
     * the organizer info activity starts
     */
    public void onOrganizerAccount() {
        view.toOrganizerAccount();
    }

    /**
     * when the "tournaments" button is pressed
     * the organizer tournaments activity starts
     */
    public void onOrganizerTournaments() {
        view.toOrganizerTournaments(organizer.getTitle());
    }

    /**
     * when the user presses the log out button
     * and a confirmation pop up shows
     */
    public void onLogOut() {
        view.logOutConfirmation();
    }

    /**
     * if the player doesn't want to log out
     */
    public void onNoLogOut() {
        view.noLogOut();
    }

    /**
     * log out the player
     */
    public void onYesLogOut() {
        loggedInUser.clear();
        view.logOut();
    }

    /**
     * set a new view
     * @param view the new view
     */
    public void setView(OrganizerPageView view) {
        this.view = view;
    }

    /**
     * clear the view
     */
    public void clearView() {
        this.view = null;
    }
}
