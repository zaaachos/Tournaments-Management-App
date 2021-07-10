package com.example.managetournamentapp.view.User.Browsing;

import com.example.managetournamentapp.memoryDao.MemoryInitializer;
import com.example.managetournamentapp.memoryDao.TournamentDAOMemory;



import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Developed for the purposes of the Course "Software Engineering" at AUEB
 * Athens University of Economics and Business
 * 2020-2021
 */

public class BrowsingPresenterTest {

    private BrowsingPresenter presenter;
    private BrowsingView view;

    /**
     * setUp the view and presenter for testing Presenter Methods
     * @throws Exception if setup fail
     */
    @Before
    public void setUp() throws Exception {
        new MemoryInitializer().prepareData();
        view = new BrowsingViewStub();
        presenter = new BrowsingPresenter();
        presenter.setTournamentDAO(new TournamentDAOMemory());

        presenter.setView(view);
    }

    /**
     * Test if presenter find tournaments properly from DAO
     */
    @Test
    public void findAllTournaments() {
        presenter.findAllTournaments();
        Assert.assertEquals(3, presenter.getResults().size());

    }

    /**
     * Test on tournament click
     */
    @Test
    public void startPage() {
        presenter.findAllTournaments();
        presenter.startTournamentPage(presenter.getResults().get(0));
        Assert.assertTrue(((BrowsingViewStub) view).onTournament);

    }
}