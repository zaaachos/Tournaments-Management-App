package com.example.managetournamentapp.view.Team.TeamInfo;

import com.example.managetournamentapp.dao.LoggedInUser;
import com.example.managetournamentapp.dao.TeamDAO;
import com.example.managetournamentapp.domain.Organizer;
import com.example.managetournamentapp.domain.Participation;
import com.example.managetournamentapp.domain.Player;
import com.example.managetournamentapp.domain.Team;
import com.example.managetournamentapp.domain.User;
import com.example.managetournamentapp.memoryDao.MemoryLoggedInUser;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class TeamInfoPresenter {
    private TeamInfoView view;
    private Team team;
    private TeamDAO teamDAO;
    private LoggedInUser loggedInUser;

    /**
     * default constructor
     */
    public TeamInfoPresenter(){}

    /**
     * show the info of the team
     * @param teamName the name of the team
     */
    public void findTeamInfo(String teamName) {
        if (teamName==null)
            return;
        team = teamDAO.find(teamName);
        if( team == null )
            return;

        view.setTeamName(team.getName());
        view.setColors(team.getColors() );
        view.setSport(team.getSportType().getName());
        view.setAgeDivision(team.getAgeDivision().toString());
    }

    /**
     * hide the edit and delete buttons
     * if the user viewing the page is not
     * the captain of the team
     */
    public void findAccess(){
        if ( loggedInUser.getUser() != null )
            if (loggedInUser.getUser() instanceof  Player)
                if ( ((Player)loggedInUser.getUser()).equals( team.getCaptain()) )
                    return;
        view.changesOfAccess();
    }

    /**
     * when the captain chooses to edit this team
     * the create team activity is started
     */
    public void onEditTeam(){
        for (Participation part : team.getParticipations()){
            if (!part.isPast()){
                view.showToast("CAN'T EDIT : THIS TEAM HAS ARRANGED PARTICIPATIONS");
                return;
            }
        }
        view.startEditTeam();
    }

    /**
     * when the captain tries to delete the team
     * we check if this team can be deleted
     */
    public void onDeleteTeam(){
        for (Participation part : team.getParticipations()){
            if (!part.isPast()){
                view.showToast("CAN'T DELETE : THIS TEAM HAS ARRANGED PARTICIPATIONS");
                return;
            }
        }
        if (team.getPlayers().size()>1){
            view.showToast("CAN'T DELETE : YOU HAVE TO REMOVE ALL THE OTHER PLAYERS");
            return;
        }
        team.removePlayer(team.getCaptain());
        teamDAO.delete(team);
        view.startDeleteTeam(((Player)loggedInUser.getUser()).getCredentials().getUsername() );
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
    public void setView(TeamInfoView view) {
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
        loggedInUser = new MemoryLoggedInUser();
        User user = loggedInUser.getUser();
        if (loggedInUser.getUser() instanceof Player){
            view.backToHomePage(true,user.getCredentials().getUsername());
        }
        else{
            view.backToHomePage(false,((Organizer)user).getTitle());
        }
    }

}
