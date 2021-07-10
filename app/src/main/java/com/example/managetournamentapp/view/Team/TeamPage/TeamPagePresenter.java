package com.example.managetournamentapp.view.Team.TeamPage;


import com.example.managetournamentapp.dao.LoggedInUser;
import com.example.managetournamentapp.dao.TeamDAO;
import com.example.managetournamentapp.domain.Organizer;
import com.example.managetournamentapp.domain.Player;
import com.example.managetournamentapp.domain.Team;
import com.example.managetournamentapp.domain.User;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class TeamPagePresenter {
    private TeamPageView view;
    private Team team;
    private TeamDAO teamDAO;
    private LoggedInUser loggedInUser;

    /**
     * default constructor
     */
    public TeamPagePresenter(){}

    /**
     * find the team
     * @param teamName the name of the team
     */
    public void findTeamInfo(String teamName){
        if (teamName==null)
            return;
        team = teamDAO.find(teamName);
        if( team == null )
            return;
    }

    /**
     *only the players of this team can see
     * the participations of this team
     */
    public void findAccess(){
        if ( loggedInUser.getUser() != null )
            if (loggedInUser.getUser() instanceof Player)
                if ( team.getPlayers().contains((Player)loggedInUser.getUser())  )
                    return;
        view.changesOfAccess();
    }

    /**
     * set the teamDAO
     * @param teamDAO the new TeamDAO
     */
    public void setTeamDAO(TeamDAO teamDAO) {
        this.teamDAO = teamDAO;
    }

    /**
     * set the logged in user
     * @param loggedInUser the logged in user
     */
    public void setLoggedInUser(LoggedInUser loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    /**
     * set a new view
     * @param view the new view
     */
    public void setView(TeamPageView view) {
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
        User user = loggedInUser.getUser();
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
