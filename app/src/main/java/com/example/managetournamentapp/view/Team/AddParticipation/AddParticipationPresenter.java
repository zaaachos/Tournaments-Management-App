package com.example.managetournamentapp.view.Team.AddParticipation;

import com.example.managetournamentapp.dao.TeamDAO;
import com.example.managetournamentapp.dao.TournamentDAO;
import com.example.managetournamentapp.domain.Participation;
import com.example.managetournamentapp.domain.Team;
import com.example.managetournamentapp.domain.Tournament;
import com.example.managetournamentapp.domain.User;
import com.example.managetournamentapp.memoryDao.MemoryLoggedInUser;

import java.util.ArrayList;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class AddParticipationPresenter {
    private AddParticipationView view;
    private TournamentDAO tournamentDAO;
    private TeamDAO teamDAO;
    private ArrayList<Tournament> results = new ArrayList<>();
    private Team team;

    /**
     * default constructor
     */
    public AddParticipationPresenter(){}

    /**
     * find the tournaments that the team can participate in
     * @param teamName the name of the team
     */
    public void findTournaments(String teamName){
        team = teamDAO.find(teamName);
        if (team==null)
            return;

        results.clear();
        for (Tournament tournament : tournamentDAO.findAll()){
            Participation part = new Participation(tournament, team);
            if ( team.canParticipate(part)  )
                results.add(tournament);
        }
    }

    /**
     *  get the tournaments that the team can participate in
     * @return the ArrayList of tournaments
     */
    public ArrayList<Tournament> getResults() {
        return results;
    }

    /**
     * set a new view
     * @param view the new view
     */
    public void setView(AddParticipationView view) {
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
     * participate in a new tournament
     * @param tournamentTitle the name of the tournament
     */
    public void onAddParticipation(String tournamentTitle){
        Tournament tournament = tournamentDAO.find(tournamentTitle);
        Participation part = new Participation(tournament, team);
        team.addParticipation(part);
    }

    /**
     * show the page of the tournament
     * @param tournamentTitle the name of the tournament
     */
    public void onTournamentPage(String tournamentTitle){
        Tournament tournament = tournamentDAO.find(tournamentTitle);
        view.startTournamentPage(tournament);
    }

    /**
     * is called after the participation is added
     * and starts the player page activity
     */
    public void onStartPartTournaments(){
        String teamName = team.getName();
        view.startPlayerPage(team.getCaptain().getCredentials().getUsername());

    }

    /**
     * what happens when the homepage button is pressed
     */
    public void onHomePage(){
        User user = new MemoryLoggedInUser().getUser();
        view.backToHomePage(user.getCredentials().getUsername());
    }

}
