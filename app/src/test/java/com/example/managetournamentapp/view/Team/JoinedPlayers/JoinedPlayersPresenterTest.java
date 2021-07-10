package com.example.managetournamentapp.view.Team.JoinedPlayers;

import com.example.managetournamentapp.domain.Player;
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

public class JoinedPlayersPresenterTest {

    JoinedPlayersPresenter presenter;
    JoinedPlayersView view;

    /**
     * setUp the view and presenter for testing Presenter Methods
     * @throws Exception if setup fail
     */
    @Before
    public void setUp() {
        new MemoryInitializer().prepareData();
        new MemoryLoggedInUser().setUser( new PlayerDAOMemory().find("tommy0")  );

        view = new JoinedPlayersViewStub();
        presenter = new JoinedPlayersPresenter();
        presenter.setPlayerDAO(new PlayerDAOMemory());
        presenter.setTeamDAO(new TeamDAOMemory());
        presenter.setLoggedInUser(new MemoryLoggedInUser());
        presenter.setView(view);

    }

    /**
     * Test if presenter finds properly the teams' players from DAO
     */
    @Test
    public void joinedPlayers(){
        Team team = new TeamDAOMemory().find("Celtic8");

        presenter.findPlayers(team.getName());
        presenter.findAccess();
        Player player = presenter.getResults().get(0);

        Assert.assertEquals( presenter.getResults() , team.getPlayers() );
        int number1 = presenter.getResults().size();
        presenter.removePlayer(team.getName(), player);
        Assert.assertEquals(number1-1 , team.getPlayers().size() );
    }

    /**
     * Test user's actions
     */
    @Test
    public void testAction(){
        new MemoryLoggedInUser().setUser(new PlayerDAOMemory().find("tommy0"));
        presenter.onHomePage();
        Assert.assertTrue(((JoinedPlayersViewStub) view).onHome);
    }



}