package com.example.managetournamentapp.view.Tournament.ParticipatingTeams;

import com.example.managetournamentapp.memoryDao.MemoryInitializer;
import com.example.managetournamentapp.memoryDao.MemoryLoggedInUser;
import com.example.managetournamentapp.memoryDao.OrganizerDAOMemory;
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

public class ParticipatingTeamsPresenterTest {

    private ParticipatingTeamsPresenter presenter;
    private ParticipatingTeamsView view;

    /**
     * setUp the view and presenter for testing Presenter Methods
     * @throws Exception if setup fail
     */
    @Before
    public void setUp() throws Exception {
        new MemoryInitializer().prepareData();
        view = new ParticipatingTeamsViewStub();
        presenter = new ParticipatingTeamsPresenter();
        presenter.setTournamentDAO(new TournamentDAOMemory());
        presenter.setTeamDAO(new TeamDAOMemory());
        presenter.setView(view);

    }

    /**
     * Test if presenter finds participating teams of a tournament properly from DAO
     */
    @Test
    public void findParticipatingTeams() {
        presenter.findParticipatingTeams("TOURNOUA1");
        Assert.assertEquals(8, presenter.getResults().size());
        Assert.assertTrue(presenter.getResults().contains(new TeamDAOMemory().find("Celtic0")));

    }

    /**
     * Test user's actions
     */
    @Test
    public void changePage() {
        presenter.onTeamSelected(new TeamDAOMemory().find("Celtic0"));
        Assert.assertTrue(((ParticipatingTeamsViewStub) view).onTeam);

        new MemoryLoggedInUser().setUser(new OrganizerDAOMemory().findByTitle("ESKA"));
        presenter.onHomePage();
        Assert.assertTrue(((ParticipatingTeamsViewStub) view).onHome);

    }
}