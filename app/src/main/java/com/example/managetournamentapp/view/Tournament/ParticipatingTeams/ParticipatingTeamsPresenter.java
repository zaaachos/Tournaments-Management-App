package com.example.managetournamentapp.view.Tournament.ParticipatingTeams;

import com.example.managetournamentapp.dao.TeamDAO;
import com.example.managetournamentapp.dao.TournamentDAO;
import com.example.managetournamentapp.domain.Organizer;
import com.example.managetournamentapp.domain.Participation;
import com.example.managetournamentapp.domain.Player;
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

public class ParticipatingTeamsPresenter {
    private ParticipatingTeamsView view;
    private TeamDAO teamDAO;
    private TournamentDAO tournamentDAO;
    private ArrayList<Team> results = new ArrayList<>();
    private Tournament tournament;

    /**
     * default constructor
     */
    public ParticipatingTeamsPresenter() {
    }

    /**
     * find the teams that participate in this tournament
     * @param tournamentTitle the title of the tournament
     */
    public void findParticipatingTeams(String tournamentTitle) {
        tournament = tournamentDAO.find(tournamentTitle);
        if (tournament == null) {
            return;
        }
        results.clear();
        for (Participation participation : tournament.getParticipations()) {
            results.add(participation.getTeam());

        }
    }

    /**
     * set the tournamentDAO
     * @param tournamentDAO the new TournamentDAO
     */
    public void setTournamentDAO(TournamentDAO tournamentDAO) {
        this.tournamentDAO = tournamentDAO;
    }

    /**
     * get the teams that participate in this tournament
     * @return the ArrayList of teams
     */
    public ArrayList<Team> getResults() {
        return results;
    }

    /**
     * what happens when the user presses on a team
     * @param team the team
     */
    public void onTeamSelected(Team team) {
        view.startTeamPage(team.getName());
    }

    /**
     * set a new view
     * @param view the new view
     */
    public void setView(ParticipatingTeamsView view) {
        this.view = view;
    }

    /**
     * clear the view
     */
    public void clearView() {
        this.view = null;
    }

    /**
     * set the teamDAO
     * @param teamDAO the new TeamDAO
     */
    public void setTeamDAO(TeamDAO teamDAO) {
        this.teamDAO = teamDAO;
    }

    /**
     * direct the logged in user to their home page
     */
    public void onHomePage(){
        User user = new MemoryLoggedInUser().getUser();
        if (user == null){
            view.backToHomePage(true, false,"");
        }
        else if (user instanceof Player){
            view.backToHomePage(false,true,user.getCredentials().getUsername());
        }
        else{
            view.backToHomePage(false,false,((Organizer)user).getTitle());
        }
    }


}
