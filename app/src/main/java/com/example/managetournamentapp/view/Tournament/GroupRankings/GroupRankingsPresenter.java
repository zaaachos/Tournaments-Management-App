package com.example.managetournamentapp.view.Tournament.GroupRankings;


import com.example.managetournamentapp.dao.TournamentDAO;
import com.example.managetournamentapp.domain.Group;
import com.example.managetournamentapp.domain.Organizer;
import com.example.managetournamentapp.domain.Player;
import com.example.managetournamentapp.domain.Team;
import com.example.managetournamentapp.domain.Tournament;
import com.example.managetournamentapp.domain.User;
import com.example.managetournamentapp.memoryDao.MemoryLoggedInUser;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class GroupRankingsPresenter {
    private GroupRankingsView view;
    private TournamentDAO tournamentDAO;
    private ArrayList<Team> results = new ArrayList<>();
    private Tournament tournament;

    /**
     * find the list of the teams in the group
     * @param tournamentName the name of the tournament
     * @param specificGroup the index of the group
     */
    public void findTeams(String tournamentName, int specificGroup){
        if (tournamentName == null)
            return;
        tournament = tournamentDAO.find(tournamentName);
        if (tournament== null){
            return;
        }
        results.clear();

        Group group = tournament.getRounds().get(0).getGroups().get(specificGroup);
        group.refreshRankings();
        ArrayList<Team> reversed = new ArrayList<>( group.getRankings().keySet());
        results = new ArrayList<>(Arrays.asList( reversed.get(3),reversed.get(2),reversed.get(1),reversed.get(0) ) );
    }

    /**
     * set the tournamentDAO
     * @param tournamentDAO the new TournamentDAO
     */
    public void setTournamentDAO(TournamentDAO tournamentDAO) {
        this.tournamentDAO = tournamentDAO;
    }


    /**
     * get the teams of the group
     * @return the ArrayList of teams
     */
    public ArrayList<Team> getResults() {
        return results;
    }

    /**
     * set a new view
     * @param view the new view
     */
    public void setView(GroupRankingsView view) {
        this.view = view;
    }

    /**
     * clear the view
     */
    public void clearView(){
        this.view = null;
    }

    /**
     * what happens when the homepage button is pressed
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
