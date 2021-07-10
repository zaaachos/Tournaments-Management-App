package com.example.managetournamentapp.view.Organizer.OrganizerTournaments;

import com.example.managetournamentapp.dao.OrganizerDAO;
import com.example.managetournamentapp.dao.TournamentDAO;
import com.example.managetournamentapp.domain.Organizer;
import com.example.managetournamentapp.domain.Tournament;

import java.util.ArrayList;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class OrganizerTournamentsPresenter {
    private OrganizerTournamentsView view;
    private TournamentDAO tournamentDAO;
    private String orgTitle;
    private OrganizerDAO organizerDAO;
    private ArrayList<Tournament> results = new ArrayList<>();

    /**
     * Default constructor
     */
    public OrganizerTournamentsPresenter(){}


    public void findCreatedTournaments(String organizerTitle){
        Organizer organizer = organizerDAO.findByTitle(organizerTitle);
        if (organizer==null)
            return;
        orgTitle = organizer.getTitle();
        results.clear();
        results.addAll( organizer.getTournaments()  );
    }

    /**
     * get the tournaments of the organizer
     * @return the ArrayList of the tournaments
     */
    public ArrayList<Tournament> getResults() {
        return results;
    }

    /**
     * When the user presses the "+" button to create a new tournament
     * goes to the create tournament page
     */
    public void onAddTournament(){
        view.startCreateTournament();
    }

    /**
     * set a new view
     * @param view the new view
     */
    public void setView(OrganizerTournamentsView view) {
        this.view = view;
    }

    /**
     * clear the view
     */
    public void clearView(){
        this.view = null;
    }

    /**
     * set the tournamentDAO
     * @param tournamentDAO the new TeamDAO
     */
    public void setTournamentDAO(TournamentDAO tournamentDAO) {
        this.tournamentDAO = tournamentDAO;
    }

    /**
     * set the organizerDAO
     * @param organizerDAO the new TeamDAO
     */
    public void setOrganizerDAO(OrganizerDAO organizerDAO) {
        this.organizerDAO = organizerDAO;
    }

    /**
     * what happens when the homepage button is pressed
     * goes back to the organizer profile
     */
    public void onHomePage(){
        view.backToHomePage(orgTitle);
    }

}
