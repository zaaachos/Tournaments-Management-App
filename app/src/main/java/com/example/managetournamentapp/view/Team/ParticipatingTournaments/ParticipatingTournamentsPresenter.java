package com.example.managetournamentapp.view.Team.ParticipatingTournaments;

import com.example.managetournamentapp.dao.LoggedInUser;
import com.example.managetournamentapp.dao.TeamDAO;
import com.example.managetournamentapp.dao.TournamentDAO;
import com.example.managetournamentapp.domain.Participation;
import com.example.managetournamentapp.domain.Player;
import com.example.managetournamentapp.domain.Team;
import com.example.managetournamentapp.domain.Tournament;
import com.example.managetournamentapp.domain.User;

import java.util.ArrayList;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class ParticipatingTournamentsPresenter {
    private ParticipatingTournamentsView view;
    private TournamentDAO tournamentDAO;
    private TeamDAO teamDAO;
    private ArrayList<Tournament> results = new ArrayList<>();
    private Team team;
    private LoggedInUser loggedInUser;

    public ParticipatingTournamentsPresenter(){}

    /**
     *  find the tournaments that the team participates in
     * @param teamName the name of the team
     */
    public void findParticipatingTournaments(String teamName){
        if (teamName==null)
            return;
        team = teamDAO.find(teamName);
        if (team==null)
            return;
        results.clear();
        for (Participation p : team.getParticipations())
            results.add( p.getTournament()  );
    }

    /**
     * change the access if the logged in user isn't the captain
     * of the team
     */
    public void findAccess(){
        if ( loggedInUser.getUser() != null )
            if (loggedInUser.getUser() instanceof Player)
                if ( ((Player)loggedInUser.getUser()).equals( team.getCaptain()) )
                    return;
        view.changesOfAccess();
    }

    /**
     * get the tournaments that the team participates in
     * @return the ArrayList of tournaments
     */
    public ArrayList<Tournament> getResults() {
        return results;
    }

    /**
     * set a new view
     * @param view the new view
     */
    public void setView(ParticipatingTournamentsView view) {
        this.view = view;
    }

    /**
     * clear the view
     */
    public void clearView(){
        this.view = null;
    }

    /**
     * set the logged in user
     * @param loggedInUser the logged in user
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
     * set the teamDAO
     * @param teamDAO the new TeamDAO
     */
    public void setTeamDAO(TeamDAO teamDAO) {
        this.teamDAO = teamDAO;
    }

    /**
     * show the page where participations are created
     */
    public void onAddParticipation(){
        view.startAddParticipation();
    }

    /**
     * what happens when the homepage button is pressed
     */
    public void onHomePage(){
        User user = loggedInUser.getUser();
        view.backToHomePage(user.getCredentials().getUsername());

    }

}
