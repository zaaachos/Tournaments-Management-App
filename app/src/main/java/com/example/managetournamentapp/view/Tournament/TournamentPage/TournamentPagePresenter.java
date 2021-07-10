package com.example.managetournamentapp.view.Tournament.TournamentPage;


import com.example.managetournamentapp.domain.Organizer;
import com.example.managetournamentapp.domain.Player;
import com.example.managetournamentapp.domain.User;
import com.example.managetournamentapp.memoryDao.MemoryLoggedInUser;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class TournamentPagePresenter {
    private TournamentPageView view;


    /**
     * the default constructor
     */
    public TournamentPagePresenter(){ }

    /**
     * direct the logged in user to the tournament info activity starts
     */
    public void onTournamentInfo(){
        view.startTournamentInfo();
    }

    /**
     * direct the logged in user to the participating teams activity
     */
    public void onTournamentTeams(){
        view.startTeamsParticipating();
    }

    /**
     * direct the logged in user to the tournament rounds activity
     */
    public void onTournamentGames(){
        view.startTournamentGames();
    }

    /**
     * set a new view
     * @param view the new view
     */
    public void setView(TournamentPageView view) {
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
