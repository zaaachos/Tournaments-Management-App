package com.example.managetournamentapp.view.Team.TeamInfo;

import com.example.managetournamentapp.domain.Team;
import com.example.managetournamentapp.memoryDao.MemoryInitializer;
import com.example.managetournamentapp.memoryDao.MemoryLoggedInUser;
import com.example.managetournamentapp.memoryDao.PlayerDAOMemory;
import com.example.managetournamentapp.memoryDao.TeamDAOMemory;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class TeamInfoPresenterTest {
    private TeamInfoPresenter presenter;
    private TeamInfoView view;
    private Team team;

    /**
     * setUp the view and presenter for testing Presenter Methods
     * @throws Exception if setup fail
     */
    @Before
    public void setUp() {
        new MemoryInitializer().prepareData();
        new MemoryLoggedInUser().setUser(new PlayerDAOMemory().find("tommy24"));

        view = new TeamInfoViewStub();
        presenter = new TeamInfoPresenter();
        presenter.setLoggedInUser(new MemoryLoggedInUser());
        presenter.setTeamDAO(new TeamDAOMemory());
        presenter.setView(view);

        team = new TeamDAOMemory().find("Celtic8");
    }

    /**
     * Test user's action with different cases
     * and captain removing players from a team
     */
    @Test
    public void teamInfoEditable(){

        presenter.findTeamInfo(team.getName());
        presenter.findAccess();

        presenter.onEditTeam();
        Assert.assertEquals( team.getColors(),"green");

        int number1 = new TeamDAOMemory().findAll().size();
        presenter.onDeleteTeam();
        Assert.assertEquals( number1, new TeamDAOMemory().findAll().size());

        team.removePlayer(team.getPlayers().get(1));
        team.removePlayer(team.getPlayers().get(1));
        presenter.onDeleteTeam();
        Assert.assertEquals( number1-1, new TeamDAOMemory().findAll().size());
    }


    /**
     * Test if user has access ( so he could edit team info )
     */
    @Test
    public void teamInfoUneditable(){
        new MemoryInitializer().prepareData();
        new MemoryLoggedInUser().setUser(new PlayerDAOMemory().find("tommy0"));

        view = new TeamInfoViewStub();
        presenter = new TeamInfoPresenter();
        presenter.setLoggedInUser(new MemoryLoggedInUser());
        presenter.setTeamDAO(new TeamDAOMemory());
        presenter.setView(view);

        team = new TeamDAOMemory().find("Celtic0");
        presenter.findTeamInfo(team.getName());
        presenter.findAccess();

        presenter.onEditTeam();
        Assert.assertEquals( team.getColors(),"green");

        int number1 = new TeamDAOMemory().findAll().size();
        presenter.onDeleteTeam();
        Assert.assertEquals( number1, new TeamDAOMemory().findAll().size());
    }

    /**
     * Test user's actions
     */
    @Test
    public void testAction(){
        new MemoryLoggedInUser().setUser(new PlayerDAOMemory().find("tommy0"));
        presenter.onHomePage();
        Assert.assertTrue(((TeamInfoViewStub) view).onHome);
    }

}