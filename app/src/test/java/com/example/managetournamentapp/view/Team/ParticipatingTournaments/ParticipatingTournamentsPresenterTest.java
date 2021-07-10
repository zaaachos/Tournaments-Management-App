package com.example.managetournamentapp.view.Team.ParticipatingTournaments;

import com.example.managetournamentapp.domain.Team;
import com.example.managetournamentapp.memoryDao.MemoryInitializer;
import com.example.managetournamentapp.memoryDao.MemoryLoggedInUser;
import com.example.managetournamentapp.memoryDao.PlayerDAOMemory;
import com.example.managetournamentapp.memoryDao.TeamDAOMemory;
import com.example.managetournamentapp.memoryDao.TournamentDAOMemory;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */


public class ParticipatingTournamentsPresenterTest {
    private ParticipatingTournamentsPresenter presenter;
    private ParticipatingTournamentsView view;
    private Team team;

    /**
     * setUp the view and presenter for testing Presenter Methods
     * @throws Exception if setup fail
     */
    @Before
    public void setUp() {
        new MemoryInitializer().prepareData();
        new MemoryLoggedInUser().setUser( new PlayerDAOMemory().find("tommy0")  );
        view = new ParticipatingTournamentsViewStub();

        presenter = new ParticipatingTournamentsPresenter();
        presenter.setTournamentDAO( new TournamentDAOMemory());
        presenter.setLoggedInUser(new MemoryLoggedInUser());
        presenter.setTeamDAO(new TeamDAOMemory());
        presenter.setView(view);
    }

    /**
     * Test if presenter finds participation of a team properly from DAO
     */
    @Test
    public void participatingTournaments(){
        team = new TeamDAOMemory().find("Celtic1");
        presenter.findParticipatingTournaments(team.getName());
        presenter.findAccess();
        int number1 =  team.getParticipations().size();

        Assert.assertEquals(presenter.getResults().get(0) , team.getParticipations().get(0).getTournament());

        presenter.onAddParticipation();
        Assert.assertEquals(number1, team.getParticipations().size());
    }

    /**
     * Test user's actions
     */
    @Test
    public void testAction(){
        new MemoryLoggedInUser().setUser(new PlayerDAOMemory().find("tommy0"));
        presenter.onHomePage();
        Assert.assertTrue(((ParticipatingTournamentsViewStub) view).onHome);
    }

}