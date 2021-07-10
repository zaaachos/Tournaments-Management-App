package com.example.managetournamentapp.view.Tournament.TournamentRounds;

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

public class TournamentRoundsPresenter {
    private TournamentRoundsView view;
    private Tournament tournament;
    private TournamentDAO tournamentDAO;

    /**
     * default constructor
     */
    public TournamentRoundsPresenter(){}

    /**
     * find the
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
     * hide the button of a round
     * if this round doesn't exist in the tournament
     */
    public void findAccess(){
        view.changesOfAccess(tournament.getMAX_TEAMS_NUMBER());
    }

    /**
     * the user wants to explore the "group" stage (1st round)
     * the tournament groups activity starts
     */
    public void onGroups(){
        view.showGroups(tournament.getTitle(),tournament.getMAX_TEAMS_NUMBER());
    }

    /**
     * the user wants to explore the round of 16
     * the round games activity starts
     */
    public void on16(){
        view.showRoundGames(tournament.getTitle(),16 );
    }

    /**
     * the user wants to explore the round of 8
     * the round games activity starts
     */
    public void on8(){
        view.showRoundGames(tournament.getTitle(),8);
    }

    /**
     * the user wants to explore the semifinals round
     * the round games activity starts
     */
    public void onSemifinals(){
        view.showRoundGames(tournament.getTitle(),4);
    }

    /**
     * the user wants to explore the final round
     * the round games activity starts
     */
    public void onFinal(){
        view.showRoundGames(tournament.getTitle(),2);
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
    public void setView(TournamentRoundsView view) {
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
