package com.example.managetournamentapp.view.Organizer.OrganizerInfo;

import com.example.managetournamentapp.dao.OrganizerDAO;
import com.example.managetournamentapp.domain.Organizer;
import com.example.managetournamentapp.domain.Round;
import com.example.managetournamentapp.domain.Tournament;
import com.example.managetournamentapp.domain.User;
import com.example.managetournamentapp.memoryDao.MemoryLoggedInUser;

import java.time.format.DateTimeFormatter;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class OrganizerInfoPresenter {
    private OrganizerInfoView view;
    private Organizer organizer;
    private OrganizerDAO organizerDAO;

    /**
     * Default Constructor
     */
    public OrganizerInfoPresenter(){}

    /**
     * Finds the organizer info to
     * set all text fields in the current layout
     */
    public void findOrganizerInfo() {
        view.setUsername(organizer.getCredentials().getUsername());
        view.setPassword(organizer.getCredentials().getPassword());
        view.setName(organizer.getName());
        view.setSurname(organizer.getSurname());
        view.setPhone(organizer.getPhoneNumber());
        view.setEmail(organizer.getEmail());
        view.setBirthDate(organizer.getBirthDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")).replace("-","/") );
        view.setTitle(organizer.getTitle());
    }

    /**
     * Starts the Register Organizer Actitity
     * so that the user can modify account
     */
    public void onEditOrganizer() {

        view.startEditOrganizer(organizer.getTitle());
    }

    /**
     * Deletes the organizer's account
     * and goes back to the home page screen
     */
    public void onDeleteOrganizer() {
        boolean canDelete = true;

        for (Tournament tournament : organizer.getTournaments())
            for ( Round round : tournament.getRounds())
                if ( !round.allGamesFinished() )
                    canDelete = false;

        if ( !canDelete ){
            view.showCantDelete();
            return;
        }
        organizerDAO.delete(organizer);
        (new MemoryLoggedInUser()).clear();
        view.startDeleteOrganizer();
    }

    /**
     * Sets the organizer to the user that is currently logged in
     * @param user user to be set as an organizer
     */
    public void setOrganizer(User user){
        if (user == null)
            return;
        if (!(user instanceof Organizer))
            return;
        organizer = (Organizer) user;

    }

    /**
     * Sets the organizerDAO
     * @param organizerDAO organizerDAO to be set
     */
    public void setOrganizerDAO(OrganizerDAO organizerDAO){
        this.organizerDAO = organizerDAO;
    }

    /**
     * Sets the view of the OrganizerInfoPresenter
     * @param view OrganizerInfoView to be set
     */
    public void setView(OrganizerInfoView view) {
        this.view = view;
    }

    /**
     * Sets the view of the presenter to null
     */
    public void clearView() {
        this.view = null;
    }
}
