package com.example.managetournamentapp.view.Tournament.TournamentGroups;

import com.example.managetournamentapp.memoryDao.MemoryInitializer;
import com.example.managetournamentapp.memoryDao.MemoryLoggedInUser;
import com.example.managetournamentapp.memoryDao.OrganizerDAOMemory;
import com.example.managetournamentapp.memoryDao.TournamentDAOMemory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class TournamentGroupsPresenterTest {

    private TournamentGroupsPresenter presenter;
    private TournamentGroupsView view;

    /**
     * setUp the view and presenter for testing Presenter Methods
     * @throws Exception if setup fail
     */
    @Before
    public void setUp() throws Exception {
        new MemoryInitializer().prepareData();
        view = new TournamentGroupsViewStub();
        presenter = new TournamentGroupsPresenter();
        presenter.setTournamentDAO(new TournamentDAOMemory());

        presenter.setView(view);
    }


    /**
     * Test presenter onGroup
     */
    @Test
    public void onGroup() {
        presenter.findTournamentInfo("TOURNOUA1");
        presenter.onGroup(0);

        Assert.assertTrue(((TournamentGroupsViewStub) view).onShow);
    }

    /**
     * Test presenter onGames
     */
    @Test
    public void onGames() {
        presenter.findTournamentInfo("TOURNOUA1");
        presenter.onGames(0);
        Assert.assertTrue(((TournamentGroupsViewStub) view).onGroup);
    }

    /**
     * Test presenter onRankings
     */
    @Test
    public void onRankings() {
        presenter.findTournamentInfo("TOURNOUA1");
        presenter.onRankings(0);
        Assert.assertTrue(((TournamentGroupsViewStub) view).onRankings);
    }

    /**
     * Test if presenter gives the access properly
     */
    @Test
    public void findAccess() {
        presenter.findTournamentInfo("TOURNOUA1");
        presenter.findAccess();
        Assert.assertTrue(((TournamentGroupsViewStub) view).onChange);
    }

    /**
     * Test user's actions
     */
    @Test
    public void changePage() {
        new MemoryLoggedInUser().setUser(new OrganizerDAOMemory().findByTitle("ESKA"));
        presenter.onHomePage();
        Assert.assertTrue(((TournamentGroupsViewStub) view).onHome);

    }
}