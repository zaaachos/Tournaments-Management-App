package com.example.managetournamentapp.view.Team.TeamPage;

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

public class TeamPagePresenterTest {
    private TeamPagePresenter presenter;
    private TeamPageView view;
    private Team team;

    /**
     * setUp the view and presenter for testing Presenter Methods
     * @throws Exception if setup fail
     */
    @Before
    public void setUp() {
        new MemoryInitializer().prepareData();
        new MemoryLoggedInUser().setUser( new PlayerDAOMemory().find("tommy0")  );
        view = new TeamPageViewStub();

        presenter = new TeamPagePresenter();
        presenter.setLoggedInUser( new MemoryLoggedInUser() );
        presenter.setTeamDAO(new TeamDAOMemory());
        presenter.setView(view);
    }

    /**
     * Test onTeamPage
     */
    @Test
    public void teamPage(){
        team = new TeamDAOMemory().find("Celtic1");
        presenter.findTeamInfo(team.getName());
        presenter.findAccess();
        Assert.assertEquals( ((TeamPageViewStub)view ).changed , true );

    }

    /**
     * Test user's actions.
     */
    @Test
    public void testAction(){
        new MemoryLoggedInUser().setUser(new PlayerDAOMemory().find("tommy0"));
        presenter.onHomePage();
        Assert.assertTrue(((TeamPageViewStub) view).onHome);
    }




}