package com.example.managetournamentapp.view.Tournament.TournamentGroups;

import com.example.managetournamentapp.dao.TournamentDAO;
import com.example.managetournamentapp.domain.Organizer;
import com.example.managetournamentapp.domain.Player;
import com.example.managetournamentapp.domain.Tournament;
import com.example.managetournamentapp.domain.User;
import com.example.managetournamentapp.memoryDao.MemoryLoggedInUser;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class TournamentGroupsPresenter {
    private TournamentGroupsView view;
    private Tournament tournament;
    private TournamentDAO tournamentDAO;

    /**
     * default constructor
     */
    public TournamentGroupsPresenter(){ }

    /**
     * find the Tournament object
     * @param tournamentTitle the title of the tournament
     */
    public void findTournamentInfo(String tournamentTitle){
        if(tournamentTitle ==null){
            return;
        }
        tournament = tournamentDAO.find(tournamentTitle);
        if( tournament == null )
            return;
    }

    /**
     * when the user presses on a group
     * @param index the index of the group
     */
    public void onGroup(int index){
        view.showPopup(index);
    }

    /**
     * show the games of a group
     * @param index the index of the group
     */
    public void onGames(int index){
        view.showGroupGames( tournament.getTitle(),tournament.getRounds().get(0).getTeamsNumber(), index  );
    }

    /**
     * show the rankings of a group
     * @param index the index of the group
     */
    public void onRankings(int index){
        view.showGroupRankings( tournament.getTitle(),tournament.getRounds().get(0).getTeamsNumber(), index  );
    }

    /**
     * hide the button of a group
     * if this group doesn't exist in the tournament
     */
    public void findAccess(){
        view.changesOfAccess(tournament.getRounds().get(0).getGroups().size() );
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
    public void setView(TournamentGroupsView view) {
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
