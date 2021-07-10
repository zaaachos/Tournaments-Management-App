package com.example.managetournamentapp.view.Tournament.TournamentInfo;

import com.example.managetournamentapp.dao.LoggedInUser;
import com.example.managetournamentapp.dao.TournamentDAO;
import com.example.managetournamentapp.domain.Organizer;
import com.example.managetournamentapp.domain.Tournament;


import java.time.format.DateTimeFormatter;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class TournamentInfoPresenter {
    private TournamentInfoView view;
    private Tournament tournament;
    private TournamentDAO tournamentDAO;
    private LoggedInUser loggedInUser;

    /**
     * default constructor
     */
    public TournamentInfoPresenter(){}

    /**
     * show the info of the tournament
     * @param tournamentTitle the title of the tournament
     */
    public void findTournamentInfo(String tournamentTitle){
        if(tournamentTitle ==null){
            return;
        }
        tournament = tournamentDAO.find(tournamentTitle);
        if( tournament == null )
            return;
        view.setTitle(tournament.getTitle());
        view.setLocation(tournament.getLocation());
        view.setStartDate(tournament.getStartDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")).replace("-","/"));
        view.setFinishDate(tournament.getFinishDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")).replace("-","/"));
        view.setLocation(tournament.getLocation());
        view.setsportType(tournament.getSportType().getName());
        view.setTeamsNumber(String.valueOf(tournament.getMAX_TEAMS_NUMBER()));
        view.setAgeDivision(tournament.getAgeDivision().toString());
        view.setDescription(tournament.getDescription());

    }

    /**
     * hide the edit and delete buttons
     * if the user viewing the page is not
     * the organizer of this tournament
     */
    public void findAccess(){
        if ( loggedInUser.getUser() != null )
            if (loggedInUser.getUser() instanceof Organizer)
                if ( ((Organizer)loggedInUser.getUser()).getTournaments().contains(tournament) )
                    return;
        view.changesOfAccess();
    }

    /**
     * when the organizer chooses to edit this tournament
     * the create tournament activity is started
     */
    public void onEditTournament(){
        if (!tournament.getParticipations().isEmpty()){
            view.showToast("CAN'T EDIT: THERE ARE ACTIVE PARTICIPATIONS");
            return;
        }
        view.startEditTournament();
    }

    /**
     * when the organizer tries to delete a tournament
     * we check if this tournament can be deleted
     * and then we ask for a confirmation
     */
    public void onDeleteTournament(){
        if (!tournament.getParticipations().isEmpty()){
            view.showToast("CAN'T DELETE: THERE ARE ACTIVE PARTICIPATIONS");
            return;
        }
        ((Organizer)loggedInUser.getUser()).deleteTournament(tournament);
        tournamentDAO.delete(tournament);
        view.deleteConfirmation();
    }

    /**
     * when the organizer presses
     * "yes" on the confirmation
     */
    public void onYesDeleteTournament(){
        Organizer organizer = (Organizer) loggedInUser.getUser();
        view.yesDeleteTournament(organizer.getTitle());
    }

    /**
     * when the organizer presses
     * "no" on the confirmation
     */
    public void onNoDeleteTournament(){
        view.noDeleteTournament();
    }

    /**
     * set the loggedInUser
     * @param loggedInUser the new LoggedInUser
     */
    public void setLoggedInUser(LoggedInUser loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    /**
     * set the tournamentDAO
     * @param tournamentDAO the new TournamentDAO
     */
    public void setTournamentDAO(TournamentDAO tournamentDAO) {
        this.tournamentDAO = tournamentDAO;
    }

    /**
     * set a new view
     * @param view the new view
     */
    public void setView(TournamentInfoView view) {
        this.view = view;
    }

    /**
     * clear the view
     */
    public void clearView(){
        this.view = null;
    }
}
