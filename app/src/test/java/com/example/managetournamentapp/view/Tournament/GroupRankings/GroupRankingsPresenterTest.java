package com.example.managetournamentapp.view.Tournament.GroupRankings;


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
 */;

public class GroupRankingsPresenterTest {

    private GroupRankingsPresenter presenter;
    private GroupRankingsView view;

    /**
     * setUp the view and presenter for testing Presenter Methods
     * @throws Exception if setup fail
     */
    @Before
    public void setUp() throws Exception {
        new MemoryInitializer().prepareData();
        view = new GroupRankingsViewStub();
        presenter = new GroupRankingsPresenter();
        presenter.setTournamentDAO(new TournamentDAOMemory());
        presenter.setView(view);

    }

    /**
     * Test if presenter finds teams properly from DAO
     */
    @Test
    public void testFindTeams() {
        presenter.findTeams("TOURNOUA1", 0);

        Assert.assertTrue(presenter.getResults().contains(new TeamDAOMemory().find("Celtic0")));

    }

    /**
     * Test user's actions
     */
    @Test
    public void testAction(){
        new MemoryLoggedInUser().setUser(new OrganizerDAOMemory().findByTitle("ESKA"));
        presenter.onHomePage();
        Assert.assertTrue(((GroupRankingsViewStub) view).onHome);
    }

}